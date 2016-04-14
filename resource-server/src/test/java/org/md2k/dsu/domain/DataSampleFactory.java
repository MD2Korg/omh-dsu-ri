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

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.md2k.dsu.domain.DataStreamFactory.newDataStream;


/**
 * @author Emerson Farrugia
 */
public class DataSampleFactory {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static DataSample newDataSample() {
        return newDataSample(newDataStream());
    }

    public static DataSample newDataSample(DataStream dataStream) {

        DataSample dataSample = new DataSample();

        dataSample.setId(1L);
        dataSample.setDataStream(dataStream);
        dataSample.setCreationTimestamp(LocalDateTime.of(2016, 4, 14, 9, 45, 20, 0));
        dataSample.setModificationTimestamp(dataSample.getCreationTimestamp());

        dataSample.setEffectiveTimestamp(dataSample.getCreationTimestamp().minusMinutes(15));
        dataSample.setOffsetInMinutes(60 * 5);

        try {
            dataSample.setValue(objectMapper.readTree("[60, 0]"));
        }
        catch (IOException e) {
            throw new RuntimeException("A data sample can't be generated.", e);
        }

        return dataSample;
    }
}
