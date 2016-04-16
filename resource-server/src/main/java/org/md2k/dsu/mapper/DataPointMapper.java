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

import org.md2k.dsu.configuration.DataPointMapperSettings;
import org.openmhealth.schema.domain.omh.DataPoint;

import java.util.List;


/**
 * A mapper that creates data points from one or more inputs.
 *
 * @param <I> the input type
 * @author Emerson Farrugia
 */
public interface DataPointMapper<I> {

    /**
     * @return the unique identifier of this mapper
     */
    String getIdentifier();

    /**
     * Maps one or more inputs into one or more data points. The method accepts multiple inputs to allow a mapper to
     * assemble a data point from disparate inputs, e.g. disconnected model entities, and should not be used in lieu of
     * multiple mapper calls.
     *
     * @param inputs the list of inputs
     * @param mapperSettings a configuration for this mapper
     * @return the list of data points mapped from the inputs
     */
    List<DataPoint<?>> asDataPoints(List<I> inputs, DataPointMapperSettings mapperSettings);
}