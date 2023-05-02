package org.mimmey.service;

import org.mimmey.dto.ExtendedUserInfo;
import org.mimmey.entity.Audio;
import org.mimmey.entity.Image;

/**
 * @author Olga Motyleva
 * */
public interface ProfileSettingsService {

    /**
     * The function that returns the information about the currently authorized user
     * @return the information about currently authorized user
     */
    ExtendedUserInfo getUserInfo();

    /**
     * The function that deletes the currently authorized user from database
     */
    void deleteUser();

    /**
     * The function that updates the information about the currently authorized user
     * @param updatedUserInfo the updated information
     */
    void updateUserInfo(ExtendedUserInfo updatedUserInfo);

    /**
     * The function that sets the jingle to the currently authorized user's profile
     * @param jingle the new jingle
     */
    void setJingle(Audio jingle);

    /**
     * The function that sets the avatar to the currently authorized user's profile
     * @param avatar the new avatar
     */
    void setAvatar(Image avatar);

    /**
     * The function that adds the new media link to the currently authorized user's profile
     * @param link the new media link
     */
    void addMediaLink(String link);

    /**
     * The function that removes the new media link from the currently authorized user's profile
     * @param linkIndex the index of the link in the link list
     */
    void removeMediaLink(int linkIndex);
}
