package org.mimmey.service.common.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.dto.request.update.UserUpdateDto;
import org.mimmey.dto.response.UserInfoAuthorizedDto;
import org.mimmey.utils.Audio;
import org.mimmey.utils.Image;
import org.mimmey.repository.ProfileSettingsRepository;
import org.mimmey.service.common.ProfileSettingsService;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
@RequiredArgsConstructor
public class ProfileSettingsServiceImpl implements ProfileSettingsService {

    private final ProfileSettingsRepository profileSettingsRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserInfoAuthorizedDto getUserInfo() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteUser() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUserInfo(UserUpdateDto userUpdateDto) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setJingle(Audio jingle) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAvatar(Image avatar) {

    }
}
