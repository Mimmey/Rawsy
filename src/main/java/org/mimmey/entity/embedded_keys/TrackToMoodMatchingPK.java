package org.mimmey.entity.embedded_keys;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.mimmey.entity.Track;
import org.mimmey.entity.TrackMood;

import java.io.Serializable;

@Data
public class TrackToMoodMatchingPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "track_id")
    private Track track;

    @ManyToOne
    @JoinColumn(name = "mood_id")
    private TrackMood mood;
}
