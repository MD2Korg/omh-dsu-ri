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

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.md2k.dsu.configuration.StringJsonUserType;
import org.md2k.dsu.repository.LocalDateTimeAttributeConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * A stream of data samples from a {@link DataSource} belonging to a single {@link Participant}.
 *
 * @author Emerson Farrugia
 */
@Entity
@Table(name = "datastreams")
@TypeDefs({@TypeDef(name = "StringJsonObject", typeClass = StringJsonUserType.class)})
public class DataStream implements Serializable {

    private static final long serialVersionUID = -7943728245175325579L;

    private Long id;
    private DataSource dataSource;
    private Participant participant;
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
     * @return the data source that generates the samples in this stream
     */
    @ManyToOne
    @JoinColumn(name = "datasource_id")
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * @return the participant this stream belongs to
     */
    @ManyToOne
    @JoinColumn(name = "participant_id")
    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
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
