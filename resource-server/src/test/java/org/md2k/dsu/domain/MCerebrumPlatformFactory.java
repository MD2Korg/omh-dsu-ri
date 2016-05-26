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

/**
 * @author Emerson Farrugia
 */
public class MCerebrumPlatformFactory {

    public static MCerebrumPlatform newPlatform() {

        MCerebrumPlatform platform = new MCerebrumPlatform();

        platform.setId(1L);
        platform.setIdentifier("RIGHT_WRIST");
        platform.setCreationTimestamp(LocalDateTime.of(2016, 4, 14, 9, 45, 20, 0));
        platform.setModificationTimestamp(LocalDateTime.of(2016, 4, 14, 9, 45, 20, 0));
        platform.setType("MICROSOFT_BAND");
        platform.setMetadata("{\n" +
                "    \"NAME\": \"MicrosoftBand (Right Wrist)\",\n" +
                "    \"DEVICE_ID\": \"58:82:A8:CD:65:75\",\n" +
                "    \"VERSION_FIRMWARE\": \"2.0.3923.0\",\n" +
                "    \"VERSION_HARDWARE\": \"26\"\n" +
                "}");

        return platform;
    }
}
