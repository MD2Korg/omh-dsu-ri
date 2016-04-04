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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * An mCerebrum application.
 *
 * @author Emerson Farrugia
 */
@Entity
@Table(name = "m_cerebrum_applications")
public class MCerebrumApplication {

    private Long id;
    private String identifier;
    private String metadata;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * TODO talk to Mani about the significance of these fields
     *
     * @return an application identifier, for example {@code org.md2k.streamprocessor}
     */
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * @return metadata about this application as a JSON document
     */
    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
}
