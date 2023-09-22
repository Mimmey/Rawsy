package org.mimmey.entity.associative;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mimmey.entity.embedded_keys.TrackToMoodMatchingPK;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mood_list")
public class TrackToMoodMatching {

    @EmbeddedId
    private TrackToMoodMatchingPK pk;
}
