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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "_user")
public class User implements Serializable, UserDetails {

    @Id
    private Long id;

    private String nickname;

    private String email;

    private String password;

    private String passwordHash;

    private Boolean isBanned;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    private String about;

    private String jinglePath;

    private String avatarPath;

//    private Long subscribersCount;
//
//    private Long subscriptionsCount;

    private Long tracksInOtherUsersFavouritesCount;

//    private Long publishedTracksCount;

    private Long tracksPurchasedByOtherUsersCount;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<MediaLink> mediaLinks;

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

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Track> publishedTracks;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<FavouriteAddition> favouritesAdditions;

    @OneToMany(mappedBy = "purchaser", cascade = CascadeType.ALL)
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<TrackInBasket> tracksInBasket;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isBanned;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
