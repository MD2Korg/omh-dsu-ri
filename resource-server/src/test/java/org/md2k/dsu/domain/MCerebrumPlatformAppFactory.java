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
public class MCerebrumPlatformAppFactory {

    // TODO update this with meaningful data when provided
    public static MCerebrumPlatformApp newPlatformApp() {

        MCerebrumPlatformApp platformApp = new MCerebrumPlatformApp();

        platformApp.setId(1L);
        platformApp.setIdentifier("");
        platformApp.setType("");
        platformApp.setMetadata("{}");
        platformApp.setCreationTimestamp(LocalDateTime.now());
        platformApp.setModificationTimestamp(LocalDateTime.now());
        return platformApp;
    }
}
