package org.mimmey.repository;

import org.mimmey.dto.response.UserInfoAuthorizedDto;
import org.mimmey.utils.Audio;
import org.mimmey.utils.Image;

public interface ProfileSettingsRepository {

    UserInfoAuthorizedDto getUserInfo(long userId);
    void updateUserInfo(long userId, UserInfoAuthorizedDto updatedUserInfo);

    void setJingle(long userId, Audio jingle);

    void setAvatar(long userId, Image avatar);

    void addMediaLink(long userId, String link);

    void removeMediaLink(long userId, int linkIndex);
}
