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

import org.openmhealth.dsu.domain.DataPointSearchCriteria;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;


/**
 * A configuration for handling requests for data points.
 *
 * @author Emerson Farrugia
 */
@Document
public class DataPointSearchConfiguration {

    private String id;
    private DataPointSearchCriteria searchCriteria;
    private String databaseQueryFilters;
    private DataPointMapperSettings mapperSettings;


    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the search criteria that will trigger the use of this configuration when handling a data point request
     */
    @NotNull
    // TODO @Valid is intentionally omitted until configuration loading is sorted out
    public DataPointSearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(DataPointSearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    /**
     * @return the filters that will be applied to database queries issued to service the request
     */
    @NotNull
    public String getDatabaseQueryFilters() {
        return databaseQueryFilters;
    }

    public void setDatabaseQueryFilters(String databaseQueryFilters) {
        this.databaseQueryFilters = databaseQueryFilters;
    }

    /**
     * @return the settings to use to map database query results into data points matching the request
     */
    @NotNull
    public DataPointMapperSettings getMapperSettings() {
        return mapperSettings;
    }

    public void setMapperSettings(DataPointMapperSettings mapperSettings) {
        this.mapperSettings = mapperSettings;
    }
}
