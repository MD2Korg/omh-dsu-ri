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

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


/**
 * @author Emerson Farrugia
 */
public class DataPointSearchConfigurationFactory {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static DataPointSearchConfiguration newDataPointSearchConfiguration() {

        try {
            return objectMapper.readValue("{\n" +
                    "  \"searchCriteria\": {\n" +
                    "    \"schemaNamespace\": \"omh\",\n" +
                    "    \"schemaName\": \"heart-rate\"\n" +
                    "  },\n" +
                    "  \"databaseQueryFilters\": {\n" + // TODO add some query filters
                    "  },\n" +
                    "  \"mapperSettings\": {\n" +
                    "    \"mapper\": \"default\",\n" +
                    "    \"schemaNamespace\": \"omh\",\n" +
                    "    \"schemaName\": \"heart-rate\",\n" +
                    "    \"schemaVersion\": \"1.0\",\n" +
                    "    \"propertyMappings\": [\n" +
                    "      {\n" +
                    "        \"outputPath\": \"body.heart_rate.unit\",\n" +
                    "        \"constantValue\": \"beats/min\"\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"outputPath\": \"body.heart_rate.value\",\n" +
                    "        \"inputField\": \"sample\",\n" +
                    "        \"inputJsonPath\": \"$[0]\"\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  }\n" +
                    "}", DataPointSearchConfiguration.class);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
