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

import com.google.common.base.Joiner;
import com.jayway.jsonpath.JsonPath;
import org.md2k.dsu.configuration.DataPointMapperSettings;
import org.md2k.dsu.configuration.PropertyMapping;
import org.md2k.dsu.domain.DataSample;
import org.openmhealth.schema.domain.omh.*;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Collections.singletonList;


/**
 * A data point mapper that maps a data sample to a single data point.
 *
 * @author Emerson Farrugia
 */
@Component
public class DefaultDataSampleDataPointMapper implements DataPointMapper<DataSample> {

    public static final String MAPPER_ID = "defaultDataSampleMapper";

    @Override
    public String getIdentifier() {
        return MAPPER_ID;
    }


    @Override
    public List<DataPoint<?>> asDataPoints(List<DataSample> samples, DataPointMapperSettings mapperSettings) {

        checkNotNull(samples);
        checkArgument(samples.size() == 1, "A single sample is allowed per call.");
        checkNotNull(mapperSettings);
        checkArgument(mapperSettings.getMapperIdentifier().equals(MAPPER_ID)); // replace with stronger typing?

        DataSample sample = samples.get(0);
        SchemaId bodySchemaId = mapperSettings.getBodySchemaId();

        DataPointHeader header = newDataPointHeader(sample, bodySchemaId);

        ZoneOffset effectiveZoneOffset = ZoneOffset.ofTotalSeconds(sample.getOffsetInMinutes() * 60);

        FreeFormMeasure measure = new FreeFormMeasure
                // TODO add a convenience constructor that takes a SchemaId
                .Builder(bodySchemaId.getNamespace(), bodySchemaId.getName(), bodySchemaId.getVersion().toString())
                // TODO handle different forms of effective time
                .setEffectiveTimeFrame(sample.getEffectiveTimestamp().atOffset(effectiveZoneOffset))
                .build();

        DataPoint<?> dataPoint = new DataPoint<>(header, measure);

        for (PropertyMapping propertyMapping : mapperSettings.getPropertyMappings()) {

            Object propertyValue = getMappedValue(sample, propertyMapping);

            dataPoint.setAdditionalProperty(propertyMapping.getOutputPath(), propertyValue);
        }

        return singletonList(dataPoint);
    }

    /**
     * @param sample a sample
     * @param bodySchemaId the body schema to map to
     * @return a new data point header corresponding to the data sample
     */
    DataPointHeader newDataPointHeader(DataSample sample, SchemaId bodySchemaId) {

        String sourceName = Joiner.on(":").useForNull("").join(
                sample.getDataStream().getDataSource().getIdentifier(),
                sample.getDataStream().getDataSource().getType(),
                sample.getDataStream().getDataSource().getApplication().getIdentifier(),
                sample.getDataStream().getDataSource().getApplication().getType(),
                sample.getDataStream().getDataSource().getPlatform().getIdentifier(),
                sample.getDataStream().getDataSource().getPlatform().getType(),
                sample.getDataStream().getDataSource().getPlatformApp().getIdentifier(),
                sample.getDataStream().getDataSource().getPlatformApp().getType());

        // TODO add modality
        DataPointAcquisitionProvenance acquisitionProvenance = new DataPointAcquisitionProvenance.Builder(sourceName)
                .build();

        acquisitionProvenance.setAdditionalProperty("external_id", sample.getId().toString());

        OffsetDateTime creationDateTime = sample.getCreationTimestamp().atOffset(ZoneOffset.UTC);

        return new DataPointHeader.Builder(sample.getId().toString(), bodySchemaId, creationDateTime)
                .setAcquisitionProvenance(acquisitionProvenance)
                .setUserId(sample.getDataStream().getParticipant().getIdentifier())
                .build();
    }

    /**
     * @param sample a sample
     * @param propertyMapping a property mapping
     * @return the value obtained by applying the property mapping to the sample
     */
    Object getMappedValue(DataSample sample, PropertyMapping propertyMapping) {

        if (propertyMapping.hasConstantValue()) {
            return propertyMapping.getConstantValue();
        }

        // TODO support other input fields, possibly dynamically using JXPath
        if (!propertyMapping.getInputField().equals("sample")) {
            throw new UnsupportedOperationException("This mapper only supports extracting sample values.");
        }

        return JsonPath.read(sample.getValue(), propertyMapping.getInputJsonPath());
    }
}
