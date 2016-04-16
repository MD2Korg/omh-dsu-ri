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

package org.md2k.dsu.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.md2k.dsu.configuration.DataPointMapperSettings;
import org.openmhealth.dsu.configuration.TransientIntegrationTestConfiguration;
import org.openmhealth.schema.domain.omh.DataPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


/**
 * @author Emerson Farrugia
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {
        TransientIntegrationTestConfiguration.class,
        DataPointMapperResolverIntegrationTests.class
})
@Configuration
@ComponentScan(basePackageClasses = DataPointMapper.class)
public class DataPointMapperResolverIntegrationTests {

    @Autowired
    private DataPointMapperResolver mapperResolver;


    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldFailOnIdentifierConflicts() {

        DataPointMapper mapper = new DataPointMapper() {
            @Override
            public String getIdentifier() {
                return "mapper";
            }

            @Override
            public List<DataPoint<?>> asDataPoints(List inputs, DataPointMapperSettings mapperSettings) {
                return null;
            }
        };

        new DataPointMapperResolver(Collections.nCopies(2, mapper));
    }

    @Test
    public void getMapperShouldReturnEmptyOnUnknownMapperId() {

        assertThat(mapperResolver.getMapper("nonExistent").isPresent(), equalTo(false));
    }

    @Test
    public void getMapperShouldReturnMapperOnValidMapperId() {

        Optional<DataPointMapper> mapper = mapperResolver.getMapper(DefaultDataSampleDataPointMapper.MAPPER_ID);

        assertThat(mapper.isPresent(), equalTo(true));
        assertThat(mapper.get().getIdentifier(), equalTo(DefaultDataSampleDataPointMapper.MAPPER_ID));
    }
}