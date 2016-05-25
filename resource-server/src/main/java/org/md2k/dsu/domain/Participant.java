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
import java.util.UUID;


/**
 * A participant in a study.
 *
 * @author Emerson Farrugia
 */
@Entity
@Table(name = "participants")
public class Participant implements Serializable {

    private static final long serialVersionUID = -1461772592948907477L;

    private UUID id;
    private String identifier;
    private LocalDateTime creationTimestamp;
    private LocalDateTime modificationTimestamp;


    @Id
    @Type(type = "pg-uuid")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * @return the study-specific identifier of this participant
     */
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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
