package org.mimmey.service.common;

import org.mimmey.dto.response.common.TrackCommonDto;
import org.mimmey.dto.response.common.UserInfoCommonDto;
import org.mimmey.entity.User;

import java.util.List;

/**
 * @author Olga Motyleva
 */
public interface UserService {

    /**
     * The function that saves the user in the database
     *
     * @param user user to be saved
     */
    void createUser(User user);

    /**
     * The function that returns the information about the given user
     *
     * @param userId ID of the user to get information about
     * @return the information about the given user
     */
    UserInfoCommonDto getUserInfo(long userId);

    /**
     * The function that returns the page of the list of the given user's subscriptions
     *
     * @param userId      ID of the user to get subscriptions of
     * @param page        index of subscription list's page
     * @param unitsOnPage number of subscriptions per one page
     * @return the page of the list of the given user's subscriptions
     */
    List<UserInfoCommonDto> getSubscriptionList(long userId, long page, long unitsOnPage);

    /**
     * The function that returns the page of the list of the given user's subscribers
     *
     * @param userId      ID of the user to get subscribers of
     * @param page        index of subscriber list's page
     * @param unitsOnPage number of subscribers per one page
     * @return the page of the list of the given user's subscribers
     */
    List<UserInfoCommonDto> getSubscriberList(long userId, long page, long unitsOnPage);


    /**
     * The function that returns the page of the list of tracks published by the given user
     *
     * @param userId      ID of the user to get published tracks of
     * @param page        index of published track list's page
     * @param unitsOnPage number of tracks per one page
     * @return the page of the list of tracks published by the given user
     */
    List<TrackCommonDto> getPublishedTrackList(long userId, long page, long unitsOnPage);
}
