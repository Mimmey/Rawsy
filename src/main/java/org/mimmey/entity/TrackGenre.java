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
import org.mimmey.entity.associative.TrackToGenreMatching;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "track_genre")
public class TrackGenre implements Serializable {

    @Id
    private long id;

    @Column(name = "_name")
    private String name;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL)
    private List<TrackToGenreMatching> trackToGenreMatchings;
}
