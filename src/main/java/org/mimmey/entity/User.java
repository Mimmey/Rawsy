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
import org.mimmey.entity.associative.Subscription;
import org.mimmey.entity.associative.TrackInBasket;
import org.mimmey.entity.associative.UserReport;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "_user")
public class User implements Serializable {

    @Id
    private long id;

    private String nickname;

    private String email;

    private String passwordHash;

    private boolean isBanned;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    private String about;

    private String jinglePath;

    private String avatarPath;

    private long subscribersCount;

    private long subscriptionsCount;

    private long tracksInOtherUsersFavouritesCount;

    private long publishedTracksCount;

    private long tracksPurchasedByOtherUsersCount;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<MediaLink> mediaLinks;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Track> tracks;

    @OneToMany(mappedBy = "commentAuthor", cascade = CascadeType.ALL)
    private List<Comment> commentsAuthoredBy;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Report> reportsAuthoredBy;

    @OneToMany(mappedBy = "userSubject", cascade = CascadeType.ALL)
    private List<UserReport> reportsMadeAbout;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Subscription> subscribers;

    @OneToMany(mappedBy = "subscriber", cascade = CascadeType.ALL)
    private List<Subscription> subscriptions;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<FavouriteAddition> favouritesAdditions;

    @OneToMany(mappedBy = "purchaser", cascade = CascadeType.ALL)
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<TrackInBasket> tracksInBasket;
}
