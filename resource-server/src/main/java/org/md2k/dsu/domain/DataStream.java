package org.md2k.dsu.domain;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * A stream of data samples from a {@link DataSource} belonging to a single {@link Participant}.
 *
 * @author Emerson Farrugia
 */
@Entity
@Table(name = "datastreams")
public class DataStream {

    private Long id;
    private DataSource dataSource;
    private Participant participant;

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
}
