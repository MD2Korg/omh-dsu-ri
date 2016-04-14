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

package org.md2k.dsu.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;

import static java.lang.String.format;


/**
 * @author Emerson Farrugia
 */
@Converter
public class JsonNodeAttributeConverter implements AttributeConverter<JsonNode, String> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(JsonNode attribute) {

        return attribute == null ? null : attribute.toString();
    }

    @Override
    public JsonNode convertToEntityAttribute(String dbData) {

        try {
            return dbData == null ? null : objectMapper.readTree(dbData);
        }
        catch (IOException e) {
            throw new IllegalArgumentException(format("The value '%s' isn't valid JSON data.", dbData), e);
        }
    }
}
