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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.String.format;


/**
 * A service that returns data point mappers by identifier.
 *
 * @author Emerson Farrugia
 */
@Service
public class DataPointMapperResolver {

    private Map<String, DataPointMapper> mappers = new HashMap<>();


    @Autowired
    public DataPointMapperResolver(List<DataPointMapper> mappers) {

        for (DataPointMapper mapper : mappers) {
            if (this.mappers.put(mapper.getIdentifier(), mapper) != null) {

                throw new IllegalArgumentException(
                        format("At least two mappers have been given identifier '%s'.", mapper.getIdentifier()));
            }
        }
    }

    /**
     * @param mapperId the identifier of the mapper
     * @return the corresponding mapper, if any
     */
    public Optional<DataPointMapper> getMapper(String mapperId) {

        return Optional.ofNullable(mappers.get(mapperId));
    }
}
