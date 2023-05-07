package org.mimmey.service.admin;

import org.mimmey.dto.response.admin.TrackAdminDto;
import org.mimmey.dto.response.admin.UserInfoAdminDto;

import java.util.List;

public interface AdminUserService {

    /**
     * The function that bans the given user
     *
     * @param userId ID of the user to be banned
     */
    void banUser(long userId);

    /**
     * The function that returns the admin information about the given user
     *
     * @param userId ID of the user to get information about
     * @return the information about the given user
     */
    UserInfoAdminDto getUserInfo(long userId);

    /**
     * The function that returns the page of the list of the given user's subscriptions (with admin info)
     *
     * @param userId      ID of the user to get subscriptions of
     * @param page        index of subscription list's page
     * @param unitsOnPage number of subscriptions per one page
     * @return the page of the list of the given user's subscriptions
     */
    List<UserInfoAdminDto> getSubscriptionList(long userId, long page, long unitsOnPage);

    /**
     * The function that returns the page of the list of the given user's subscribers (with admin info)
     *
     * @param userId      ID of the user to get subscribers of
     * @param page        index of subscriber list's page
     * @param unitsOnPage number of subscribers per one page
     * @return the page of the list of the given user's subscribers
     */
    List<UserInfoAdminDto> getSubscriberList(long userId, long page, long unitsOnPage);


    /**
     * The function that returns the page of the list of tracks published by the given user (with admin info)
     *
     * @param userId      ID of the user to get published tracks of
     * @param page        index of published track list's page
     * @param unitsOnPage number of tracks per one page
     * @return the page of the list of tracks published by the given user
     */
    List<TrackAdminDto> getPublishedTrackList(long userId, long page, long unitsOnPage);
}
