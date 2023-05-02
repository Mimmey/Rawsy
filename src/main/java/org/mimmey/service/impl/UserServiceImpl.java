package org.mimmey.service.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.dto.TrackDto;
import org.mimmey.dto.UserInfoDto;
import org.mimmey.entity.User;
import org.mimmey.repository.UserRepository;
import org.mimmey.service.UserService;
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
    public UserInfoDto getUserInfo(long userId) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void banUser(long userId) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserInfoDto> getSubscriptionList(long userId, long page, long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserInfoDto> getSubscriberList(long userId, long page, long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TrackDto> getPublishedTrackList(long userId, long page, long unitsOnPage) {
        return null;
    }
}
