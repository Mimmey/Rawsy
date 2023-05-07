package org.mimmey.service.common;

import org.mimmey.dto.request.creation.TrackCreationDto;
import org.mimmey.dto.response.TrackAuthorDto;
import org.mimmey.dto.response.common.TrackCommonDto;
import org.mimmey.dto.response.common.UserInfoCommonDto;

import java.util.List;

/**
 * @author Olga Motyleva
 */
public interface AuthorizedUserService {

    /**
     * The function that returns the page of the list of the currently authorized user's subscriptions
     *
     * @param page        index of subscription list's page
     * @param unitsOnPage number of subscriptions per one page
     * @return the page of the list of user's subscriptions
     */
    List<UserInfoCommonDto> getSubscriptionList(long page, long unitsOnPage);

    /**
     * The function that returns the page of the list of the currently authorized user's subscribers
     *
     * @param page        index of subscriber list's page
     * @param unitsOnPage number of subscribers per one page
     * @return the page of the list of user's subscribers
     */
    List<UserInfoCommonDto> getSubscriberList(long page, long unitsOnPage);

    /**
     * The function that subscribes the currently authorized user to the given one
     *
     * @param subscriptionUserId ID of the user to be subscribed
     */
    void subscribe(long subscriptionUserId);

    /**
     * The function that unsubscribes the currently authorized user from the given one
     *
     * @param subscriptionUserId ID of the user to be unsubscribed
     */
    void unsubscribe(long subscriptionUserId);

    /**
     * The function that returns the page of the list of tracks published by the currently authorized user
     *
     * @param page        index of published track list's page
     * @param unitsOnPage number of tracks per one page
     * @return the page of the list of tracks published by user
     */
    List<TrackAuthorDto> getPublishedTrackList(long page, long unitsOnPage);

    /**
     * The function that returns the page of the list of tracks purchased by the currently authorized user
     *
     * @param page        index of purchased track list's page
     * @param unitsOnPage number of tracks per one page
     * @return the page of the list of tracks purchased by user
     */
    List<TrackCommonDto> getPurchasedTrackList(long page, long unitsOnPage);

    /**
     * The function that returns the page of the list of the currently authorized user's favourites tracks
     *
     * @param page        index of favourite track list's page
     * @param unitsOnPage number of tracks per one page
     * @return the page of the list of user's favourites tracks
     */
    List<TrackCommonDto> getFavouriteTrackList(long page, long unitsOnPage);

    /**
     * The function that returns the page of the list of tracks in the currently authorized user's basket
     *
     * @param page        index of tracks in basket list's page
     * @param unitsOnPage number of tracks per one page
     * @return the page of the list of tracks in user's basket
     */
    List<TrackCommonDto> getBasketTrackList(long page, long unitsOnPage);

    /**
     * The function that publishes the given track if the authorized user is a track author
     *
     * @param trackAuthorDto track to be published
     */
    void publishTrack(TrackCreationDto trackAuthorDto);

    /**
     * The function that makes a purchase
     *
     * @param trackId ID of the track
     */
    void purchaseTrack(long trackId);

    /**
     * The function that adds the given track to favourites of the currently authorized user
     *
     * @param trackId ID of the track
     */
    void addTrackToFavorites(long trackId);

    /**
     * The function that adds the given track to the basket of the currently authorized user
     *
     * @param trackId ID of the track
     */
    void addTrackToBasket(long trackId);

    /**
     * The function that deletes the given track if the currently authorized user is an author
     *
     * @param trackId ID of the track
     */
    void deletePublishedTrack(long trackId);

    /**
     * The function that removes the given track from favourites of the currently authorized user
     *
     * @param trackId ID of the track
     */
    void removeTrackFromFavourites(long trackId);

    /**
     * The function that removes the given track from basket of the currently authorized user
     *
     * @param trackId ID of the track
     */
    void removeTrackFromBasket(long trackId);

    /**
     * The function that clears the basket of the currently authorized user
     */
    void clearBasket();
}
