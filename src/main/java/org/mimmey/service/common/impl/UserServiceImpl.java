package org.mimmey.service.common.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mimmey.config.exception.FileException;
import org.mimmey.entity.MediaLink;
import org.mimmey.entity.Track;
import org.mimmey.entity.User;
import org.mimmey.entity.associative.Subscription;
import org.mimmey.repository.MediaLinkRepository;
import org.mimmey.repository.SubscriptionRepository;
import org.mimmey.repository.TrackRepository;
import org.mimmey.repository.UserRepository;
import org.mimmey.service.common.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service("common-user")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    protected final UserRepository userRepository;

    protected final SubscriptionRepository subscriptionRepository;

    protected final TrackRepository trackRepository;

    protected final MediaLinkRepository mediaLinkRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createUser(User user) {
        List<MediaLink> mediaLinks = user.getMediaLinks();
        user.setMediaLinks(mediaLinks);
        userRepository.save(user);

        User createdUser = userRepository.findByNickname(user.getNickname()).orElseThrow(RuntimeException::new);
        mediaLinks.forEach(link -> link.setOwner(createdUser));
        mediaLinkRepository.saveAll(mediaLinks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser(long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getAvatar(long id) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        try {
            return Files.readAllBytes(Path.of(user.getAvatarPath()));
        } catch (IOException e) {
            throw new FileException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<User> getSubscriptions(long userId, int page, int unitsOnPage) {
        Pageable pageable = PageRequest.of(page, unitsOnPage);
        Page<Subscription> subscriptions = subscriptionRepository.findAllBySubscriberId(userId, pageable);

        List<User> userSubscriptions = subscriptions.stream()
                .map(sub -> userRepository.findById(sub.getPk().getSubject().getId())
                        .orElseThrow(EntityNotFoundException::new))
                .toList();

        return new PageImpl<>(userSubscriptions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<User> getSubscribers(long userId, int page, int unitsOnPage) {
        Pageable pageable = PageRequest.of(page, unitsOnPage);
        Page<Subscription> subscriptions = subscriptionRepository.findAllBySubjectId(userId, pageable);

        List<User> userSubscribers = subscriptions.stream()
                .map(sub -> userRepository.findById(sub.getPk().getSubscriber().getId())
                        .orElseThrow(EntityNotFoundException::new))
                .toList();

        return new PageImpl<>(userSubscribers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Track> getPublishedTracks(long userId, int page, int unitsOnPage) {
        Pageable pageable = PageRequest.of(page, unitsOnPage);
        return trackRepository.findAllByAuthorId(userId, pageable);
    }
}
