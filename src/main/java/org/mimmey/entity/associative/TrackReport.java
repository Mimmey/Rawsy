package org.mimmey.entity.associative;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mimmey.entity.embdded_keys.TrackReportPK;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "track_report")
public class TrackReport {

    @EmbeddedId
    private TrackReportPK pk;
}
