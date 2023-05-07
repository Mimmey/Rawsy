package org.mimmey.service.common.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.dto.request.creation.TrackCreationDto;
import org.mimmey.dto.response.TrackAuthorDto;
import org.mimmey.dto.response.common.TrackCommonDto;
import org.mimmey.dto.response.common.UserInfoCommonDto;
import org.mimmey.repository.UserRepository;
import org.mimmey.service.common.AuthorizedUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
@RequiredArgsConstructor
public class AuthorizedUserServiceImpl implements AuthorizedUserService {

    private final UserRepository userRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserInfoCommonDto> getSubscriptionList(long page, long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserInfoCommonDto> getSubscriberList(long page, long unitsOnPage) {
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
    public List<TrackAuthorDto> getPublishedTrackList(long page, long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TrackCommonDto> getPurchasedTrackList(long page, long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TrackCommonDto> getFavouriteTrackList(long page, long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TrackCommonDto> getBasketTrackList(long page, long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void publishTrack(TrackCreationDto trackAuthorDto) {

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
