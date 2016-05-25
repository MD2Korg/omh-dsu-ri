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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.md2k.dsu.configuration.DataPointSearchConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.md2k.dsu.configuration.DataPointSearchConfigurationFactory.newDataPointSearchConfiguration;


/**
 * A suite of integration tests for a data point search configuration repository.
 *
 * @author Emerson Farrugia
 */
public abstract class DataPointSearchConfigurationRepositoryIntegrationTests {

    private static final String UNRECOGNIZED_ID = "foo";

    private DataPointSearchConfiguration testSearchConfiguration;
    private List<DataPointSearchConfiguration> testSearchConfigurations;


    protected abstract DataPointSearchConfigurationRepository getRepository();

    @Before
    public void initialiseFixture() {

        testSearchConfiguration = getRepository().save(newDataPointSearchConfiguration());

        testSearchConfigurations = new ArrayList<>();
        testSearchConfigurations.add(testSearchConfiguration);
    }

    @After
    public void deleteFixture() {
        testSearchConfigurations.forEach((configuration) -> getRepository().delete(configuration.getId()));
    }


    @Test
    public void existsShouldReturnFalseOnUnrecognizedId() {

        assertThat(getRepository().exists(UNRECOGNIZED_ID), equalTo(false));
    }

    @Test
    public void existsShouldReturnTrueOnMatchingId() {

        assertThat(getRepository().exists(testSearchConfiguration.getId()), equalTo(true));
    }
}
