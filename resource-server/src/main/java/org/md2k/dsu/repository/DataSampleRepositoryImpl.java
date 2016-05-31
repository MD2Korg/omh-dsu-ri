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

package org.md2k.dsu.repository;

import com.github.vineey.rql.filter.parser.DefaultFilterParser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import org.md2k.dsu.configuration.DataPointSearchConfiguration;
import org.md2k.dsu.domain.DataSample;
import org.md2k.dsu.domain.QDataSample;
import org.openmhealth.dsu.domain.DataPointSearchCriteria;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static com.github.vineey.rql.querydsl.filter.QueryDslFilterContext.withMapping;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.time.ZoneOffset.UTC;
import static org.md2k.dsu.configuration.DatabaseQueryFilterAliasConfiguration.predicateCache;
import static org.md2k.dsu.configuration.DatabaseQueryFilterAliasConfiguration.searchMappings;


/**
 * @author Emerson Farrugia
 */
public class DataSampleRepositoryImpl extends QueryDslRepositorySupport implements DataSampleRepositoryCustom {

    /**
     * Creates a new {@link QueryDslRepositorySupport} instance for the given domain type.
     */
    public DataSampleRepositoryImpl() {
        super(DataSample.class);
    }


    @Override
    public List<DataSample> findBySearchConfigurationAndSearchCriteria(DataPointSearchConfiguration searchConfiguration,
                                                                       DataPointSearchCriteria searchCriteria, long offset, long limit) {


        checkNotNull(searchConfiguration);
        checkNotNull(searchConfiguration.getSearchCriteria());

        Optional<OffsetDateTime> from = searchCriteria.getCreatedOnOrAfter();
        Optional<OffsetDateTime> to = searchCriteria.getCreatedBefore();

        QDataSample qDataSample = QDataSample.dataSample;

        BooleanBuilder builder = new BooleanBuilder();
        if (from.isPresent() || to.isPresent()) {
            builder = builder.and(qDataSample.creationTimestamp.between(atUtc(from.orElse(null)), atUtc(to.orElse(null))));
        }

        from = searchConfiguration.getSearchCriteria().getEffectiveOnOrAfter();
        to = searchConfiguration.getSearchCriteria().getEffectiveBefore();
        if (from.isPresent() || to.isPresent()) {
            builder = builder.and(qDataSample.effectiveTimestamp.between(atUtc(from.orElse(null)), atUtc(to.orElse(null))));
        }

        DefaultFilterParser filterParser = new DefaultFilterParser();

        String dbFilter = searchConfiguration.getDatabaseQueryFilters();

        if (dbFilter != null && !"".equals(dbFilter.trim())) {
            builder = builder.and(predicateCache.computeIfAbsent(dbFilter, s -> filterParser.parse(s, withMapping(searchMappings))));
        }

        return from(qDataSample)
                .where(builder)
                .orderBy(new OrderSpecifier(Order.DESC, QDataSample.dataSample.effectiveTimestamp))
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    private LocalDateTime atUtc(OffsetDateTime offsetDateTime) {
        return offsetDateTime.atZoneSameInstant(UTC).toLocalDateTime();
    }

}
