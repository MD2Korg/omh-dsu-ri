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

import org.md2k.dsu.configuration.DataPointSearchConfiguration;
import org.md2k.dsu.domain.DataPointSearchResult;
import org.md2k.dsu.domain.DataSample;
import org.md2k.dsu.mapper.DataPointMapper;
import org.md2k.dsu.mapper.DataPointMapperResolver;
import org.md2k.dsu.repository.DataPointSearchConfigurationRepository;
import org.md2k.dsu.repository.DataSampleRepository;
import org.openmhealth.dsu.domain.DataPointSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;


/**
 * @author Emerson Farrugia
 */
@Service
public class DataPointSearchServiceImpl implements DataPointSearchService {

    @Autowired
    private DataPointSearchConfigurationRepository configurationRepository;

    @Autowired
    private DataSampleRepository sampleRepository;

    @Autowired
    private DataPointMapperResolver<DataSample> mapperResolver;


    // TODO discuss what pagination strategy we'd like to use
    @Override
    @Transactional(readOnly = true)
    public DataPointSearchResult findBySearchCriteria(DataPointSearchCriteria searchCriteria, @Nullable Integer offset,
            @Nullable Integer limit) {

        checkNotNull(searchCriteria);
        checkArgument(offset == null || offset >= 0);
        checkArgument(limit == null || limit >= 0);

        DataPointSearchResult searchResult = new DataPointSearchResult();

        for (DataPointSearchConfiguration searchConfiguration :
                configurationRepository.findBySearchCriteria(searchCriteria)) {

            // TODO add configuration ID to result

            DataPointMapper<DataSample> mapper =
                    mapperResolver.getMapper(searchConfiguration.getMapperSettings().getMapperIdentifier())
                            .orElseThrow(RuntimeException::new); // TODO create an exception hierarchy

            List<DataSample> dataSamples =
                    sampleRepository.findBySearchConfigurationAndSearchCriteria(searchConfiguration, searchCriteria);

            // TODO add data sample count to result

            for (DataSample dataSample : dataSamples) {
                searchResult.addDataPoints(mapper.asDataPoints(dataSample, searchConfiguration.getMapperSettings()));
            }

            // TODO review all relevant statistics and add them to the result
        }

        return searchResult;
    }
}
