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

import org.hibernate.annotations.Type;
import org.md2k.dsu.repository.LocalDateTimeAttributeConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * An mCerebrum platform app.
 *
 * @author Emerson Farrugia
 */
@Entity
@Table(name = "m_cerebrum_platform_apps")
public class MCerebrumPlatformApp implements Serializable {

    private static final long serialVersionUID = -9165080265134119599L;

    private Long id;
    private String identifier;
    private String type;
    private String metadata;
    private LocalDateTime creationTimestamp;
    private LocalDateTime modificationTimestamp;


    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * TODO find a better description of this property
     *
     * @return a platform app identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * TODO find a better description of this property
     *
     * @return a platform app type
     */
    @Column(name = "platformapptype")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return metadata about this platform app as a JSON document
     */
    @Type(type = "StringJsonObject")
    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * @return the creation time of this sample in UTC
     */
    @Column(name = "created_at")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(LocalDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    /**
     * @return the modification time of this sample in UTC
     */
    @Column(name = "updated_at")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    public LocalDateTime getModificationTimestamp() {
        return modificationTimestamp;
    }

    public void setModificationTimestamp(LocalDateTime modificationTimestamp) {
        this.modificationTimestamp = modificationTimestamp;
    }


}
