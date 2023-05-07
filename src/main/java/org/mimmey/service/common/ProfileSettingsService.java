package org.mimmey.service.common;

import org.mimmey.dto.request.update.UserUpdateDto;
import org.mimmey.dto.response.UserInfoAuthorizedDto;
import org.mimmey.utils.Audio;
import org.mimmey.utils.Image;

/**
 * @author Olga Motyleva
 */
public interface ProfileSettingsService {

    /**
     * The function that returns the information about the currently authorized user
     *
     * @return the information about currently authorized user
     */
    UserInfoAuthorizedDto getUserInfo();

    /**
     * The function that deletes the currently authorized user from database
     */
    void deleteUser();

    /**
     * The function that updates the information about the currently authorized user
     *
     * @param userUpdateDto the updated information
     */
    void updateUserInfo(UserUpdateDto userUpdateDto);

    /**
     * The function that sets the jingle to the currently authorized user's profile
     *
     * @param jingle the new jingle
     */
    void setJingle(Audio jingle);

    /**
     * The function that sets the avatar to the currently authorized user's profile
     *
     * @param avatar the new avatar
     */
    void setAvatar(Image avatar);
}
