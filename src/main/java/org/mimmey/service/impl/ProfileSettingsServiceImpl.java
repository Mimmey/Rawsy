package org.mimmey.service.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.dto.ExtendedUserInfoDto;
import org.mimmey.entity.Audio;
import org.mimmey.entity.Image;
import org.mimmey.repository.ProfileSettingsRepository;
import org.mimmey.service.ProfileSettingsService;
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
    public ExtendedUserInfoDto getUserInfo() {
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
    public void updateUserInfo(ExtendedUserInfoDto updatedUserInfo) {

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void addMediaLink(String link) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeMediaLink(int linkIndex) {

    }
}
