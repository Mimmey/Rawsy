package org.mimmey.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "track")
public class Track implements Serializable {
    //TODO: check if string fields matches varchar array capacity

    @Id
    private long id;

    private String name;

    private LocalDateTime publishingTimestamp;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TrackType type;

    private double rating;

    private String about;

    private String invoice;

    private boolean hasVocal;

    private boolean isCycled;

    private int bpm;  //TODO: > 0

    private int duration;  //TODO: > 0

    private long cost; //TODO: > 0

    private String audioPreviewPath; //TODO: add new name to schemas

    private String trackArchivePath;

    private long commentsCount;

    private long purchasesCount;

    private long inFavouritesCount;

    @OneToMany(mappedBy = "track", cascade = CascadeType.ALL)
    private List<TrackToGenreMatching> genres;

    @OneToMany(mappedBy = "track", cascade = CascadeType.ALL)
    private List<TrackToMoodMatching> moods;

    @OneToMany(mappedBy = "track", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "track", cascade = CascadeType.ALL)
    private List<FavouriteAddition> favouriteAdditions;

    @OneToMany(mappedBy = "track", cascade = CascadeType.ALL)
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "trackSubject", cascade = CascadeType.ALL)
    private List<TrackReport> trackReports;
}
