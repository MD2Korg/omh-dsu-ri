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

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * A validator that checks the structure of a property mapping. This is necessitated by not using polymorphic mappings,
 * which are more trouble than they're worth in Jackson.
 *
 * @author Emerson Farrugia
 */
public class PropertyMappingValidator
        implements ConstraintValidator<ValidPropertyMapping, PropertyMapping> {

    @Override
    public void initialize(ValidPropertyMapping validPropertyMapping) {
    }

    @Override
    public boolean isValid(PropertyMapping mapping, ConstraintValidatorContext context) {

        if (!mapping.hasConstantValue() && !mapping.hasInputField()) {
            context.buildConstraintViolationWithTemplate("A constant value or input field must be specified.")
                    .addConstraintViolation();
            return false;
        }

        if (mapping.hasConstantValue() && mapping.hasInputField()) {
            context.buildConstraintViolationWithTemplate("A constant value and input field can't both be specified.")
                    .addConstraintViolation();
            return false;
        }

        if (!mapping.hasInputField() && mapping.hasInputJsonPath()) {
            context.buildConstraintViolationWithTemplate("A JSON path can't be specified without an input field.")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}

