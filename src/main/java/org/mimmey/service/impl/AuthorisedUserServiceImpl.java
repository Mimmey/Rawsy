package org.mimmey.service.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.dto.TrackDto;
import org.mimmey.dto.UserInfoDto;
import org.mimmey.entity.Track;
import org.mimmey.repository.UserRepository;
import org.mimmey.service.AuthorizedUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
@RequiredArgsConstructor
public class AuthorisedUserServiceImpl implements AuthorizedUserService {

    private final UserRepository userRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserInfoDto> getSubscriptionList(long page, long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserInfoDto> getSubscriberList(long page, long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void subscribe(long subscriptionUserId) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unsubscribe(long subscriptionUserId) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TrackDto> getPublishedTrackList(long page, long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TrackDto> getPurchasedTrackList(long page, long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TrackDto> getFavouriteTrackList(long page, long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TrackDto> getBasketTrackList(long page, long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void publishTrack(Track track) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void purchaseTrack(long trackId) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTrackToFavorites(long trackId) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTrackToBasket(long trackId) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deletePublishedTrack(long trackId) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeTrackFromFavourites(long trackId) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeTrackFromBasket(long trackId) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearBasket() {

    }
}
