/*
 * Copyright 2016 Open mHealth
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openmhealth.dsu.service;

import com.github.vineey.rql.filter.parser.DefaultFilterParser;
import cz.jirutka.rsql.parser.RSQLParserException;
import org.md2k.dsu.configuration.DataPointSearchConfiguration;
import org.md2k.dsu.domain.DataPointSearchResult;
import org.md2k.dsu.domain.DataSample;
import org.md2k.dsu.mapper.DataPointMapper;
import org.md2k.dsu.mapper.DataPointMapperResolver;
import org.md2k.dsu.repository.DataPointSearchConfigurationRepository;
import org.md2k.dsu.repository.DataSampleRepository;
import org.openmhealth.dsu.domain.DataPointSearchCriteria;
import org.openmhealth.schema.domain.omh.DataPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import java.util.List;

import static com.github.vineey.rql.querydsl.filter.QueryDslFilterContext.withMapping;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.md2k.dsu.configuration.DatabaseQueryFilterAliasConfiguration.predicateCache;
import static org.md2k.dsu.configuration.DatabaseQueryFilterAliasConfiguration.searchMappings;


/**
 * @author Emerson Farrugia
 */
@Service
public class DataPointSearchServiceImpl implements DataPointSearchService, InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(DataPointSearchServiceImpl.class);

    @Autowired
    private DataPointSearchConfigurationRepository configurationRepository;

    @Autowired
    private DataSampleRepository sampleRepository;

    @Autowired
    private DataPointMapperResolver<DataSample> mapperResolver;


    // TODO discuss what pagination strategy we'd like to use
    @Override
    @Transactional(readOnly = true)
    public DataPointSearchResult findBySearchCriteria(DataPointSearchCriteria searchCriteria, @Nullable Long offset,
                                                      @Nullable Long limit) {

        checkNotNull(searchCriteria);
        checkArgument(offset == null || offset >= 0);
        checkArgument(limit == null || limit >= 0);

        DataPointSearchResult searchResult = new DataPointSearchResult();

        for (DataPointSearchConfiguration searchConfiguration : configurationRepository.findBySearchCriteria(searchCriteria)) {


            DataPointMapper<DataSample> mapper =
                    mapperResolver.getMapper(searchConfiguration.getMapperSettings().getMapperIdentifier())
                            .orElseThrow(RuntimeException::new); // TODO create an exception hierarchy

            List<DataSample> dataSamples =
                    sampleRepository.findBySearchConfigurationAndSearchCriteria(searchConfiguration, searchCriteria, offset, limit);


            int dataSamplesSize = dataSamples.size();
            for (DataSample dataSample : dataSamples) {
                List<DataPoint<?>> dataPoints = mapper.asDataPoints(dataSample, searchConfiguration.getMapperSettings());
                searchResult.addDataPoints(dataPoints);
                searchResult.addSearchStatistics(searchConfiguration.getId(), dataPoints.size(), dataSamplesSize);
            }

        }

        return searchResult;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // warm up cache (and trigger errors if there are bad queries in the DB

        Pageable pageRequest = new PageRequest(0, 100);
        Page<DataPointSearchConfiguration> page;
        DefaultFilterParser filterParser = new DefaultFilterParser();

        do {
            page = configurationRepository.findAll(pageRequest);
            for (DataPointSearchConfiguration dpsc : page) {
                try {
                    if (!dpsc.getDatabaseQueryFilters().trim().isEmpty()) {
                        predicateCache.put(dpsc.getDatabaseQueryFilters(), filterParser.parse(dpsc.getDatabaseQueryFilters(), withMapping(searchMappings)));
                    }

                } catch (RSQLParserException e) {
                    log.error("Unable to parse db query filter in record #{}: Found: {}", dpsc.getId(), dpsc.getDatabaseQueryFilters(), e);
                    throw e;
                }

                }
            pageRequest = page.nextPageable();

        } while (page.hasNext());
    }
}
