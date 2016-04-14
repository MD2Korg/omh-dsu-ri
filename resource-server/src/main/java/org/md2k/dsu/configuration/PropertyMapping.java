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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * A mapping to an output property from a constant value or from input properties.
 * <p/>
 * This class should be really be split into different classes, but implicit polymorphic serialization gets <a
 * href="http://stackoverflow.com/questions/24263904/deserializing-polymorphic-types-with-jackson">unnecessarily
 * complicated</a> in Jackson.
 *
 * @author Emerson Farrugia
 */
@ValidPropertyMapping
public class PropertyMapping {

    private String outputPath;
    private Object constantValue;
    private String inputField;
    private String inputJsonPath;

    /**
     * @return the path of the output property
     */
    @NotNull
    @Size(min = 1)
    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    /**
     * @return the value to set the output property to, if constant
     */
    public Object getConstantValue() {
        return constantValue;
    }

    public void setConstantValue(Object constantValue) {
        this.constantValue = constantValue;
    }

    /**
     * @return true if this mapping sets a constant value, or false otherwise
     */
    public boolean hasConstantValue() {
        return constantValue != null;
    }

    /**
     * @return the Java field containing the output value, if the value is not a constant
     */
    @Size(min = 1)
    public String getInputField() {
        return inputField;
    }

    public void setInputField(String inputField) {
        this.inputField = inputField;
    }

    /**
     * @return true if this mapping uses an input field, or false otherwise
     */
    public boolean hasInputField() {
        return inputField != null;
    }

    /**
     * @return the JSON Path expression applied to the input field to get the output value, if any
     */
    @Size(min = 1)
    public String getInputJsonPath() {
        return inputJsonPath;
    }

    public void setInputJsonPath(String inputJsonPath) {
        this.inputJsonPath = inputJsonPath;
    }

    /**
     * @return true if this mapping uses a JSON Path expression, or false otherwise
     */
    public boolean hasInputJsonPath() {
        return inputJsonPath != null;
    }
}