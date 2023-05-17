package org.mimmey.service.special;

import org.mimmey.entity.User;
import org.mimmey.utils.Audio;
import org.mimmey.utils.Image;

/**
 * @author Olga Motyleva
 */
public interface ProfileSettingsService extends AuthorizedUserService {

    /**
     * The function that deletes the currently authorized user from database
     */
    void deleteUser();

    /**
     * The function that updates the information about the currently authorized user
     *
     * @param updatedUser the updated information
     */
    void updateUser(User updatedUser);

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
