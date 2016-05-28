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

package org.openmhealth.dsu.validation;

import com.github.vineey.rql.filter.parser.DefaultFilterParser;
import cz.jirutka.rsql.parser.RSQLParserException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.github.vineey.rql.querydsl.filter.QueryDslFilterContext.withMapping;
import static org.md2k.dsu.configuration.DatabaseQueryFilterAliasConfiguration.searchMappings;

/**
 * @author Wallace Wadge
 */
public class DataPointDatabaseQueryValidator
        implements ConstraintValidator<ValidDataPointDatabaseQueryCriteria, String> {

    @Override
    public void initialize(ValidDataPointDatabaseQueryCriteria constraintAnnotation) {
    }

    @Override
    public boolean isValid(String filter, ConstraintValidatorContext constraintValidatorContext) {
        DefaultFilterParser filterParser = new DefaultFilterParser();

        try {
            if (!filter.trim().isEmpty()) {
                filterParser.parse(filter, withMapping(searchMappings));
            }
            return true;
        } catch (RSQLParserException pe) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("Unable to parse database query value").addConstraintViolation();
            return false;
        }

    }


}