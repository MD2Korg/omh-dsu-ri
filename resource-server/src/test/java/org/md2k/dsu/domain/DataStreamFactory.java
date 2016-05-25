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


import java.time.LocalDateTime;

import static org.md2k.dsu.domain.DataSourceFactory.newDataSource;
import static org.md2k.dsu.domain.ParticipantFactory.newParticipant;


/**
 * @author Emerson Farrugia
 */
public class DataStreamFactory {

    public static DataStream newDataStream() {
        return newDataStream(newDataSource(), newParticipant());
    }

    public static DataStream newDataStream(DataSource dataSource, Participant participant) {

        DataStream dataStream = new DataStream();

        dataStream.setId(1L);
        dataStream.setDataSource(dataSource);
        dataStream.setParticipant(participant);
        dataStream.setCreationTimestamp(LocalDateTime.now());
        dataStream.setModificationTimestamp(LocalDateTime.now());
        return dataStream;
    }
}
