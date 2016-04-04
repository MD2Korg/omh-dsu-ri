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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * An mCerebrum platform.
 *
 * @author Emerson Farrugia
 */
@Entity
@Table(name = "m_cerebrum_platforms")
public class MCerebrumPlatform implements Serializable {

    private static final long serialVersionUID = 7257561890103895326L;

    private Long id;
    private String identifier;
    private String platformType;
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
     * @return a platform identifier, for example {@code MICROSOFT_BAND}
     */
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * TODO talk to Mani about the significance of these fields
     *
     * @return a secondary platform identifier, for example {@code LEFT_WRIST}
     */
    @Column(name = "platformtype")
    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    /**
     * @return metadata about this platform as a JSON document
     */
    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
}
