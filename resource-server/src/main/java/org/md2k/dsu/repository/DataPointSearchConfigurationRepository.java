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

import org.md2k.dsu.configuration.DataPointSearchConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;


/**
 * @author Emerson Farrugia
 */
public interface DataPointSearchConfigurationRepository extends MongoRepository<DataPointSearchConfiguration, String>,
        QueryDslPredicateExecutor<DataPointSearchConfiguration>, DataPointSearchConfigurationRepositoryCustom {



}
