package org.mimmey.service.common.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.dto.response.common.TrackCommonDto;
import org.mimmey.dto.response.common.UserInfoCommonDto;
import org.mimmey.entity.User;
import org.mimmey.repository.UserRepository;
import org.mimmey.service.common.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createUser(User user) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserInfoCommonDto getUserInfo(long userId) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserInfoCommonDto> getSubscriptionList(long userId, long page, long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserInfoCommonDto> getSubscriberList(long userId, long page, long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TrackCommonDto> getPublishedTrackList(long userId, long page, long unitsOnPage) {
        return null;
    }
}
