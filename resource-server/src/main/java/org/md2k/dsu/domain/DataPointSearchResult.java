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

package org.md2k.dsu.domain;

import org.openmhealth.schema.domain.omh.DataPoint;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * A bean that represents the result of a search for data points.
 *
 * @author Emerson Farrugia
 */
public class DataPointSearchResult {

    /**
     * TODO find a better name?
     */
    class SearchConfigurationResult {

        private String configurationId;
        private int dataSamplesFound;
        private int dataPointsMapped;

        /**
         * TODO change this to more clearly reflect that this search configuration could have been used to find data
         * points, even if it wasn't
         *
         * @return the identifier of a search configuration used to find data points
         */
        public String getConfigurationId() {
            return configurationId;
        }

        public void setConfigurationId(String configurationId) {
            this.configurationId = configurationId;
        }

        // TODO add an indicator of whether the search configuration was actually used, i.e. had queries executed

        /**
         * @return the number of data samples found using the queries defined by the configuration
         */
        public int getDataSamplesFound() {
            return dataSamplesFound;
        }

        public void setDataSamplesFound(int dataSamplesFound) {
            this.dataSamplesFound = dataSamplesFound;
        }

        /**
         * @return the number of data points mapped from the data samples returned by the queries
         */
        public int getDataPointsMapped() {
            return dataPointsMapped;
        }

        public void setDataPointsMapped(int dataPointsMapped) {
            this.dataPointsMapped = dataPointsMapped;
        }
    }


    private List<SearchConfigurationResult> searchStatistics = new ArrayList<>();
    private List<DataPoint<?>> dataPoints = new ArrayList<>();

    /**
     * @return a list of statistics about each search configuration eligible for the search
     */
    public List<SearchConfigurationResult> getSearchStatistics() {
        return searchStatistics;
    }

    public void setSearchStatistics(List<SearchConfigurationResult> searchStatistics) {
        this.searchStatistics = searchStatistics;
    }

    public void addSearchStatistics(String configurationId, int dataPointsMapped, int dataSamplesMapped) {
        SearchConfigurationResult searchConfigurationResult = new SearchConfigurationResult();
        searchConfigurationResult.setConfigurationId(configurationId);
        searchConfigurationResult.setDataPointsMapped(dataPointsMapped);
        searchConfigurationResult.setDataSamplesFound(dataSamplesMapped);
        this.searchStatistics.add(searchConfigurationResult);
    }

    /**
     * @return the list of data points returned by a search
     */
    @NotNull
    public List<DataPoint<?>> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(List<DataPoint<?>> dataPoints) {
        this.dataPoints = dataPoints;
    }

    public void addDataPoints(Collection<DataPoint<?>> dataPoints) {
        this.dataPoints.addAll(dataPoints);
    }
}
