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

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;


/**
 * @author Emerson Farrugia
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class PropertyMappingValidationUnitTests {

    private static Validator validator;


    @BeforeClass
    public static void prepareValidator() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validateFailsOnMissingOutputPath() throws Exception {

        PropertyMapping mapping = new PropertyMapping();
        mapping.setConstantValue(1L);

        assertThat(hasViolations(mapping), equalTo(true));
    }

    private boolean hasViolations(PropertyMapping mapping) {

        return !validator.validate(mapping).isEmpty();
    }

    @Test
    public void validateFailsOnEmptyOutputPath() throws Exception {

        PropertyMapping mapping = new PropertyMapping();
        mapping.setOutputPath("");
        mapping.setConstantValue(1L);

        assertThat(hasViolations(mapping), equalTo(true));
    }

    @Test
    public void validateFailsOnMissingConstantValueAndMissingInputField() throws Exception {

        PropertyMapping mapping = new PropertyMapping();
        mapping.setOutputPath("foo");

        assertThat(hasViolations(mapping), equalTo(true));
    }

    @Test
    public void validateFailsOnConstantValueAndInputField() throws Exception {

        PropertyMapping mapping = new PropertyMapping();
        mapping.setOutputPath("foo");
        mapping.setConstantValue(1L);
        mapping.setInputField("bar");

        assertThat(hasViolations(mapping), equalTo(true));
    }

    @Test
    public void validatePassesOnConstantValue() throws Exception {

        PropertyMapping mapping = new PropertyMapping();
        mapping.setOutputPath("foo");
        mapping.setConstantValue(1L);

        assertThat(hasViolations(mapping), equalTo(false));
    }

    @Test
    public void validateFailsOnEmptyInputField() throws Exception {

        PropertyMapping mapping = new PropertyMapping();
        mapping.setOutputPath("foo");
        mapping.setInputField("");

        assertThat(hasViolations(mapping), equalTo(true));
    }

    @Test
    public void validatePassesOnInputField() throws Exception {

        PropertyMapping mapping = new PropertyMapping();
        mapping.setOutputPath("foo");
        mapping.setInputField("bar");

        assertThat(hasViolations(mapping), equalTo(false));
    }

    @Test
    public void validateFailsOnInputJsonPathAndMissingInputField() throws Exception {

        PropertyMapping mapping = new PropertyMapping();
        mapping.setOutputPath("foo");
        mapping.setInputJsonPath("$.baz");

        assertThat(hasViolations(mapping), equalTo(true));
    }

    @Test
    public void validateFailsOnEmptyInputJsonPath() throws Exception {

        PropertyMapping mapping = new PropertyMapping();
        mapping.setOutputPath("foo");
        mapping.setInputField("bar");
        mapping.setInputJsonPath("");

        assertThat(hasViolations(mapping), equalTo(true));
    }

    @Test
    public void validatePassesOnInputJsonPath() throws Exception {

        PropertyMapping mapping = new PropertyMapping();
        mapping.setOutputPath("foo");
        mapping.setInputField("bar");
        mapping.setInputJsonPath("$.baz");

        assertThat(hasViolations(mapping), equalTo(false));
    }
}