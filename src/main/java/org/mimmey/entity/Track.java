package org.mimmey.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mimmey.entity.associative.FavouriteAddition;
import org.mimmey.entity.associative.Purchase;
import org.mimmey.entity.associative.TrackReport;
import org.mimmey.entity.associative.TrackToGenreMatching;
import org.mimmey.entity.associative.TrackToMoodMatching;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "track")
public class Track implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime publishingTimestamp = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TrackType type;

    private Double rating = 0.0;

    private String about;

    private String invoice;

    private Boolean hasVocal;

    private Boolean isCycled;

    private Integer bpm;

    private Integer duration;

    private Long cost = 0L;

    private String audioPreviewPath;

    private String trackArchivePath;

    @OneToMany(mappedBy = "pk.track", cascade = CascadeType.ALL)
    private List<TrackToGenreMatching> genres;

    @OneToMany(mappedBy = "pk.track", cascade = CascadeType.ALL)
    private List<TrackToMoodMatching> moods;

    @OneToMany(mappedBy = "pk.track", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "pk.track", cascade = CascadeType.ALL)
    private List<FavouriteAddition> favouriteAdditions;

    @OneToMany(mappedBy = "pk.track", cascade = CascadeType.ALL)
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "pk.trackSubject", cascade = CascadeType.ALL)
    private List<TrackReport> trackReports;
}
