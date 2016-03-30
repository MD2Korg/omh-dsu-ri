package org.md2k.dsu.domain;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * A participant in a study.
 *
 * @author Emerson Farrugia
 */
@Entity
@Table(name = "participants")
public class Participant {

    private String id;
    private String identifier;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
