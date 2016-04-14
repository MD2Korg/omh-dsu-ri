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

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openmhealth.dsu.validation.ValidSchemaName;
import org.openmhealth.dsu.validation.ValidSchemaNamespace;
import org.openmhealth.dsu.validation.ValidSchemaVersion;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


/**
 * A configuration for a data point mapper.
 *
 * @author Emerson Farrugia
 */
public class DataPointMapperSettings {

    private String mapperIdentifier;
    private String schemaNamespace;
    private String schemaName;
    private String schemaVersion;
    private List<PropertyMapping> propertyMappings;

    /**
     * @return the mapper to use
     */
    @NotNull
    @JsonProperty(value = "mapper")
    public String getMapperIdentifier() {
        return mapperIdentifier;
    }

    public void setMapperIdentifier(String mapperIdentifier) {
        this.mapperIdentifier = mapperIdentifier;
    }

    /**
     * @return the namespace of the body schema of the mapped data point
     */
    @NotNull
    @ValidSchemaNamespace
    public String getSchemaNamespace() {
        return schemaNamespace;
    }

    public void setSchemaNamespace(String schemaNamespace) {
        this.schemaNamespace = schemaNamespace;
    }

    /**
     * @return the name of the body schema of the mapped data point
     */
    @NotNull
    @ValidSchemaName
    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    /**
     * @return the name of the body schema of the mapped data point
     */
    @NotNull
    @ValidSchemaVersion
    public String getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(String schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    /**
     * @return a list of property mappings used to compose the data point
     */
    @NotNull
    @Size(min = 1) // sanity check, remove if there are legitimate reasons to not require at least one property mapping
    @Valid
    public List<PropertyMapping> getPropertyMappings() {
        return propertyMappings;
    }

    public void setPropertyMappings(List<PropertyMapping> propertyMappings) {
        this.propertyMappings = propertyMappings;
    }
}
