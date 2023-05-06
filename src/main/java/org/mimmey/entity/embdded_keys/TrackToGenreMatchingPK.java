package org.mimmey.entity.embdded_keys;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.mimmey.entity.Track;
import org.mimmey.entity.TrackGenre;

import java.io.Serializable;

@Data
public class TrackToGenreMatchingPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "track_id")
    private Track track;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private TrackGenre genre;
}
