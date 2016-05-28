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

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * A constraint annotation used to trigger validation on data point query criteria.
 *
 * @author Wallace Wadge
 */
@Target({TYPE, METHOD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {DataPointDatabaseQueryValidator.class})
public @interface ValidDataPointDatabaseQueryCriteria {

    String message() default "The data point query criteria isn't valid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
