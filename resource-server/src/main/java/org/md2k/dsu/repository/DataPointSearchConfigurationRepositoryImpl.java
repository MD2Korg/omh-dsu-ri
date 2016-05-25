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

import com.querydsl.mongodb.AbstractMongodbQuery;
import org.md2k.dsu.configuration.DataPointSearchConfiguration;
import org.md2k.dsu.configuration.QDataPointSearchConfiguration;
import org.openmhealth.dsu.domain.DataPointSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * @author Emerson Farrugia
 */
public class DataPointSearchConfigurationRepositoryImpl extends QuerydslRepositorySupport implements DataPointSearchConfigurationRepositoryCustom {


    /**
     * Creates a new {@link QuerydslRepositorySupport} for the given {@link MongoOperations}.
     *
     * @param operations must not be {@literal null}.
     */
    @Autowired
    public DataPointSearchConfigurationRepositoryImpl(MongoOperations operations) {
        super(operations);

    }


    @Override
    public List<DataPointSearchConfiguration> findBySearchCriteria(DataPointSearchCriteria searchCriteria) {

        checkNotNull(searchCriteria);

        QDataPointSearchConfiguration qDataPointSearchConfiguration = QDataPointSearchConfiguration.dataPointSearchConfiguration;
        return ((AbstractMongodbQuery<DataPointSearchConfiguration, ?>) from(qDataPointSearchConfiguration)
                .where(qDataPointSearchConfiguration.searchCriteria.schemaName.eq(searchCriteria.getSchemaName())
                        .and(qDataPointSearchConfiguration.searchCriteria.schemaNamespace.eq(searchCriteria.getSchemaNamespace())))
        ).fetch();
    }


}