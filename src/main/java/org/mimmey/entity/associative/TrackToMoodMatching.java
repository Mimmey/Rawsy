package org.mimmey.entity.associative;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mimmey.entity.embdded_keys.TrackToMoodMatchingPK;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "mood_list")
public class TrackToMoodMatching {

    @EmbeddedId
    private TrackToMoodMatchingPK pk;
}
