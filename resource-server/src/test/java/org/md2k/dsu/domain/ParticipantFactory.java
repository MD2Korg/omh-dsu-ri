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

import java.util.UUID;


/**
 * @author Emerson Farrugia
 */
public class ParticipantFactory {

    public static Participant newParticipant() {

        Participant participant = new Participant();

        participant.setId(UUID.randomUUID().toString());
        participant.setIdentifier("joe");

        return participant;
    }
}
