package org.md2k.dsu.domain;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * An mCerebrum platform.
 *
 * @author Emerson Farrugia
 */
@Entity
@Table(name = "m_cerebrum_platforms")
public class MCerebrumPlatform {

    private Long id;
    private String identifier;
    private String platformType;
    private String metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * TODO talk to Mani about the significance of these fields
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
     * @return a secondary platform identifier, for example {@code LEFT_WRIST}
     */
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
