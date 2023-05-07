package org.mimmey.entity.associative;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mimmey.entity.embedded_keys.TrackToGenreMatchingPK;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "genre_list")
public class TrackToGenreMatching {

    @EmbeddedId
    private TrackToGenreMatchingPK pk;
}
