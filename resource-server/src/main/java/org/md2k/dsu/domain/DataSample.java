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

import com.fasterxml.jackson.databind.JsonNode;
import com.querydsl.core.annotations.QueryInit;
import org.hibernate.annotations.Type;
import org.md2k.dsu.repository.LocalDateTimeAttributeConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * A data point in a {@link DataStream}.
 *
 * @author Emerson Farrugia
 * @see {@link DataPoint}
 */
@Entity
@Table(name = "datapoints")
public class DataSample implements DataPoint, Serializable {

    private static final long serialVersionUID = -249433556588372891L;

    private Long id;
    private DataStream dataStream;
    private LocalDateTime effectiveTimestamp;
    private Integer offsetInMinutes;
    private LocalDateTime creationTimestamp;
    private LocalDateTime modificationTimestamp;
    private JsonNode value;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the stream this sample belongs to
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "datastream_id")
    @QueryInit({"dataSource.*", "participant"})
    public DataStream getDataStream() {
        return dataStream;
    }

    public void setDataStream(DataStream dataStream) {
        this.dataStream = dataStream;
    }

    /**
     * @return the effective time of this sample in UTC, assuming a {@link DataSample#getOffsetInMinutes()} time zone
     * offset
     */
    @Column(name = "timestamp")
    @Convert(converter = LocalDateTimeAttributeConverter.class)

    public LocalDateTime getEffectiveTimestamp() {
        return effectiveTimestamp;
    }

    public void setEffectiveTimestamp(LocalDateTime effectiveTimestamp) {
        this.effectiveTimestamp = effectiveTimestamp;
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

    /**
     * @return an array of sample values as a JSON document
     *
     * @see {@link DataSource#getDataDescriptor()}
     */
    @Column(name = "sample")
    @Type(type = "StringJsonObject")
//    @Convert(converter = JsonNodeAttributeConverter.class)
    public JsonNode getValue() {
        return value;
    }

    public void setValue(JsonNode value) {
        this.value = value;
    }


    /**
     * @return the time zone offset of the effective time of this sample in minutes
     */
    @Column(name = "\"offset\"")
    public Integer getOffsetInMinutes() {
        return offsetInMinutes;
    }

    public void setOffsetInMinutes(Integer offset) {
        this.offsetInMinutes = offset;
    }

}
