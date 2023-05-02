package org.mimmey.repository;

import org.mimmey.entity.Track;
import org.mimmey.entity.User;

import java.util.List;

public interface UserRepository {

    void save(User user);
    User getById(long userId);
    void deleteById(long userId);
    void banUser(long userId);

    List<User> getSubscriptionList(long userId, long page, long unitsOnPage);
    List<User> getSubscriberList(long userId, long page, long unitsOnPage);
    void subscribe(long userId, long subscriptionUserId);
    void unsubscribe(long userId, long subscriptionUserId);

    List<Track> getPublishedTrackList(long userId, long page, long unitsOnPage);
    List<Track> getPurchasedTrackList(long userId, long page, long unitsOnPage);
    List<Track> getFavouriteTrackList(long userId, long page, long unitsOnPage);
    List<Track> getBasketTrackList(long userId, long page, long unitsOnPage);

    void publishTrack(long userId, Track track);
    void purchaseTrack(long userId, long trackId);
    void addTrackToFavorites(long userId, long trackId);
    void addTrackToBasket(long userId, long trackId);

    void removePublishedTrack(long userId, long trackId);
    void removeTrackFromFavourites(long userId, long trackId);
    void removeTrackFromBasket(long userId, long trackId);

    void clearBasket(long userId);
}


