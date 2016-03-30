package org.md2k.dsu.domain;


import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
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
