package org.mimmey.service.admin.impl;

import jakarta.persistence.EntityNotFoundException;
import org.mimmey.entity.User;
import org.mimmey.repository.MediaLinkRepository;
import org.mimmey.repository.SubscriptionRepository;
import org.mimmey.repository.TrackRepository;
import org.mimmey.repository.UserRepository;
import org.mimmey.service.admin.AdminUserService;
import org.mimmey.service.common.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("admin-user")
public final class AdminUserServiceImpl extends UserServiceImpl implements AdminUserService {


    public AdminUserServiceImpl(@Autowired UserRepository userRepository,
                                @Autowired SubscriptionRepository subscriptionRepository,
                                @Autowired TrackRepository trackRepository,
                                @Autowired MediaLinkRepository mediaLinkRepository) {
        super(userRepository, subscriptionRepository, trackRepository, mediaLinkRepository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void banUser(long id) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (user.getIsBanned()) {
            throw new RuntimeException("Already banned");
        }

        user.setIsBanned(true);
        userRepository.save(user);
    }
}
