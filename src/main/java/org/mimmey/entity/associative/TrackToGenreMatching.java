package org.mimmey.entity.associative;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mimmey.entity.embedded_keys.TrackToGenreMatchingPK;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "genre_list")
public class TrackToGenreMatching {

    @EmbeddedId
    private TrackToGenreMatchingPK pk;
}
