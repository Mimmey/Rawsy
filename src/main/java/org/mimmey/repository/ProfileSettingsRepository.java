package org.mimmey.repository;

import org.mimmey.dto.ExtendedUserInfo;
import org.mimmey.entity.Audio;
import org.mimmey.entity.Image;

public interface ProfileSettingsRepository {

    ExtendedUserInfo getUserInfo(long userId);
    void updateUserInfo(long userId, ExtendedUserInfo updatedUserInfo);

    void setJingle(long userId, Audio jingle);

    void setAvatar(long userId, Image avatar);

    void addMediaLink(long userId, String link);

    void removeMediaLink(long userId, int linkIndex);
}
