package org.mimmey.entity.embdded_keys;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.mimmey.entity.Report;
import org.mimmey.entity.Track;

import java.io.Serializable;

@Data
public class TrackReportPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;

    @ManyToOne
    @JoinColumn(name = "track_subject_id")
    private Track trackSubject;
}
