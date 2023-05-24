package org.mimmey.service.common.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mimmey.entity.MediaLink;
import org.mimmey.entity.Track;
import org.mimmey.entity.User;
import org.mimmey.entity.associative.Subscription;
import org.mimmey.repository.MediaLinkRepository;
import org.mimmey.repository.SubscriptionRepository;
import org.mimmey.repository.TrackRepository;
import org.mimmey.repository.UserRepository;
import org.mimmey.service.common.UserService;
import org.mimmey.utils.FileTypes;
import org.mimmey.utils.FileWorker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
        user.setAvatarPath("temp");
        user.setJinglePath("temp");
        user.setMediaLinks(Collections.emptyList());
        userRepository.save(user);

        User createdUser = userRepository.findByNickname(user.getNickname()).orElseThrow(RuntimeException::new);

        String avatarPath = FileWorker.getAvatarPath(createdUser.getId());
        String jinglePath = FileWorker.getJinglePath(createdUser.getId());

        createdUser.setAvatarPath(avatarPath);
        createdUser.setJinglePath(jinglePath);
        mediaLinks.forEach(link -> link.setOwner(createdUser));
        createdUser.setMediaLinks(mediaLinks);
        userRepository.save(createdUser);

        FileWorker.tryCreateDefault(avatarPath, FileTypes.AVATAR);
        FileWorker.tryCreateDefault(jinglePath, FileTypes.JINGLE);
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
        return FileWorker.tryReadFile(user.getAvatarPath());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getJingle(long id) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return FileWorker.tryReadFile(user.getJinglePath());
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
