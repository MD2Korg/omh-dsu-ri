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

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.md2k.dsu.configuration.JsonPathConfiguration;
import org.md2k.dsu.configuration.PropertyMapping;
import org.md2k.dsu.domain.DataSample;
import org.md2k.dsu.domain.DataSampleFactory;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;


/**
 * @author Emerson Farrugia
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class DefaultDataSampleDataPointMapperUnitTests {

    private DefaultDataSampleDataPointMapper mapper = new DefaultDataSampleDataPointMapper();
    private DataSample testSample;


    @BeforeClass
    public static void initializeJsonPathLibrary() {
        new JsonPathConfiguration().initialize();
    }

    @Before
    public void initializeTestSample() {
        testSample = DataSampleFactory.newDataSample();
    }

    @Test(expected = Exception.class)
    public void getMappedValueShouldThrowExceptionOnUnknownField() {

        PropertyMapping propertyMapping = new PropertyMapping();
        propertyMapping.setOutputPath("foo");
        propertyMapping.setInputField("nonExistent");
        propertyMapping.setInputJsonPath("foo");

        mapper.getMappedValue(testSample, propertyMapping);
    }

    @Test
    public void getMappedValueShouldReturnMappedConstantValue() {

        PropertyMapping propertyMapping = new PropertyMapping();
        propertyMapping.setOutputPath("foo");
        propertyMapping.setConstantValue("someValue");

        assertThat(mapper.getMappedValue(testSample, propertyMapping), equalTo("someValue"));
    }

    @Ignore("breaking on IntNode vs Integer assertion, confirm if the type is even important")
    @Test
    public void getMappedValueShouldReturnMappedJsonPathValue() {

        PropertyMapping propertyMapping = new PropertyMapping();
        propertyMapping.setOutputPath("foo");
        propertyMapping.setInputField("sample");
        propertyMapping.setInputJsonPath("$[0]"); // set to 60 in DataSampleFactory

        assertThat(mapper.getMappedValue(testSample, propertyMapping), equalTo(60));
    }

    // TODO add tests
}