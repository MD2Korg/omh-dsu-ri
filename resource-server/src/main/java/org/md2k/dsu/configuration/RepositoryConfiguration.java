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

package org.md2k.dsu.configuration;

import org.md2k.dsu.domain.DataSample;
import org.md2k.dsu.repository.*;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;


/**
 * A configuration for Spring Data repositories specific to MD2K.
 *
 * @author Emerson Farrugia
 */
@Configuration("md2kRepositoryConfiguration")
@EnableJpaRepositories(basePackages = "org.md2k.dsu.repository", includeFilters = {
        @Filter(type = ASSIGNABLE_TYPE, value = DataSampleRepository.class),
        @Filter(type = ASSIGNABLE_TYPE, value = DataSourceRepository.class),
        @Filter(type = ASSIGNABLE_TYPE, value = DataStreamRepository.class),
        @Filter(type = ASSIGNABLE_TYPE, value = MCerebrumApplicationRepository.class),
        @Filter(type = ASSIGNABLE_TYPE, value = MCerebrumPlatformAppRepository.class),
        @Filter(type = ASSIGNABLE_TYPE, value = MCerebrumPlatformRepository.class),
        @Filter(type = ASSIGNABLE_TYPE, value = ParticipantRepository.class)
})
@EnableMongoRepositories(basePackages = "org.md2k.dsu.repository", includeFilters = {
        @Filter(type = ASSIGNABLE_TYPE, value = DataPointSearchConfigurationRepository.class),
})
@EntityScan(basePackageClasses = DataSample.class)
public class RepositoryConfiguration {

}
