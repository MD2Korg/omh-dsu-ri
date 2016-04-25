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
import org.md2k.dsu.configuration.DataPointMapperSettings;
import org.openmhealth.schema.domain.omh.DataPoint;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.nCopies;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


/**
 * @param <I> the input type of the mappers
 * @author Emerson Farrugia
 */
public abstract class DataPointMapperResolverIntegrationTests<I> {

    /**
     * @return the resolver to test
     */
    protected abstract DataPointMapperResolver<I> getResolver();


    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldFailOnIdentifierConflicts()
            throws Throwable {

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

        try {
            getResolver().getClass().getConstructor(List.class).newInstance(nCopies(2, mapper));
        }
        catch (InvocationTargetException e) {
            throw e.getCause(); // unwrap the invocation target exception to get to the actual cause
        }
    }

    @Test
    public void getMapperShouldReturnEmptyOnUnknownMapperId() {

        assertThat(getResolver().getMapper("nonExistent").isPresent(), equalTo(false));
    }

    @Test
    public void getMapperShouldReturnMapperOnValidMapperId() {

        Optional<DataPointMapper<I>> mapper = getResolver().getMapper(DefaultDataSampleDataPointMapper.MAPPER_ID);

        assertThat(mapper.isPresent(), equalTo(true));
        assertThat(mapper.get().getIdentifier(), equalTo(DefaultDataSampleDataPointMapper.MAPPER_ID));
    }
}