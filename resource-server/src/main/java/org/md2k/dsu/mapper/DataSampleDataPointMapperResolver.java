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

import org.md2k.dsu.domain.DataSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * A resolver for mappers that map data samples to data points.
 *
 * @author Emerson Farrugia
 */
@Service
public class DataSampleDataPointMapperResolver extends DataPointMapperResolver<DataSample> {

    @Autowired
    public DataSampleDataPointMapperResolver(List<DataPointMapper<DataSample>> dataPointMappers) {
        super(dataPointMappers);
    }
}
