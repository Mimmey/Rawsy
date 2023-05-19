package org.mimmey.service.common;

import org.mimmey.entity.Track;
import org.mimmey.entity.User;
import org.springframework.data.domain.Page;

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
     * @param id ID of the user to get information about
     * @return the information about the given user
     */
    User getUser(long id);

    /**
     * The function that returns the avatar of the given user
     *
     * @param id ID of the user to get information about
     * @return the avatar of the given user
     */
    byte[] getAvatar(long id);

    /**
     * The function that returns the page of the list of the given user's subscriptions
     *
     * @param userId      ID of the user to get subscriptions of
     * @param page        index of subscription list's page
     * @param unitsOnPage number of subscriptions per one page
     * @return the page of the list of the given user's subscriptions
     */
    Page<User> getSubscriptions(long userId, int page, int unitsOnPage);

    /**
     * The function that returns the page of the list of the given user's subscribers
     *
     * @param userId      ID of the user to get subscribers of
     * @param page        index of subscriber list's page
     * @param unitsOnPage number of subscribers per one page
     * @return the page of the list of the given user's subscribers
     */
    Page<User> getSubscribers(long userId, int page, int unitsOnPage);


    /**
     * The function that returns the page of the list of tracks published by the given user
     *
     * @param userId      ID of the user to get published tracks of
     * @param page        index of published track list's page
     * @param unitsOnPage number of tracks per one page
     * @return the page of the list of tracks published by the given user
     */
    Page<Track> getPublishedTracks(long userId, int page, int unitsOnPage);
}
