package org.mimmey.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import org.mimmey.config.security.utils.Role;
import org.mimmey.entity.associative.FavouriteAddition;
import org.mimmey.entity.associative.Purchase;
import org.mimmey.entity.associative.Subscription;
import org.mimmey.entity.associative.TrackInBasket;
import org.mimmey.entity.associative.UserReport;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String email;

    private String password;

    private Boolean isBanned = false;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role = Role.USER;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    private String about;

    private String jinglePath;

    private String avatarPath;

    private Long tracksInOtherUsersFavouritesCount = 0L;

    private Long tracksPurchasedByOtherUsersCount = 0L;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<MediaLink> mediaLinks;

    @OneToMany(mappedBy = "pk.author", cascade = CascadeType.ALL)
    private List<Comment> commentsAuthoredBy;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Report> reportsAuthoredBy;

    @OneToMany(mappedBy = "pk.userSubject", cascade = CascadeType.ALL)
    private List<UserReport> reportsMadeAbout;

    @OneToMany(mappedBy = "pk.subject", cascade = CascadeType.ALL)
    private List<Subscription> subscribers;

    @OneToMany(mappedBy = "pk.subscriber", cascade = CascadeType.ALL)
    private List<Subscription> subscriptions;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Track> publishedTracks;

    @OneToMany(mappedBy = "pk.owner", cascade = CascadeType.ALL)
    private List<FavouriteAddition> favouritesAdditions;

    @OneToMany(mappedBy = "pk.purchaser", cascade = CascadeType.ALL)
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "pk.owner", cascade = CascadeType.ALL)
    private List<TrackInBasket> tracksInBasket;
}
