package org.mimmey.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mimmey.entity.associative.TrackToMoodMatching;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "track_mood")
public class TrackMood implements Serializable {

    @Id
    private Long id;

    @Column(name = "_name")
    private String name;

    @OneToMany(mappedBy = "mood", cascade = CascadeType.ALL)
    private List<TrackToMoodMatching> trackToMoodMatchings;
}
