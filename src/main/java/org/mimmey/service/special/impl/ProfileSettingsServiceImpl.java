package org.mimmey.service.special.impl;

import org.mimmey.config.exception.FileException;
import org.mimmey.config.security.utils.AuthorizedUserGetter;
import org.mimmey.dto.request.update.mapper.UserUpdateMapper;
import org.mimmey.entity.MediaLink;
import org.mimmey.entity.User;
import org.mimmey.repository.BasketRepository;
import org.mimmey.repository.FavouriteRepository;
import org.mimmey.repository.MediaLinkRepository;
import org.mimmey.repository.PurchaseRepository;
import org.mimmey.repository.SubscriptionRepository;
import org.mimmey.repository.TrackRepository;
import org.mimmey.repository.UserRepository;
import org.mimmey.service.special.ProfileSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service("settings-user")
public final class ProfileSettingsServiceImpl extends AuthorizedUserServiceImpl implements ProfileSettingsService {

    private final UserUpdateMapper userUpdateMapper;

    public ProfileSettingsServiceImpl(@Autowired UserRepository userRepository,
                                      @Autowired SubscriptionRepository subscriptionRepository,
                                      @Autowired TrackRepository trackRepository,
                                      @Autowired MediaLinkRepository mediaLinkRepository,
                                      @Autowired AuthorizedUserGetter authorizedUserGetter,
                                      @Autowired PurchaseRepository purchaseRepository,
                                      @Autowired FavouriteRepository favouriteRepository,
                                      @Autowired BasketRepository basketRepository,
                                      @Autowired UserUpdateMapper userUpdateMapper) {
        super(userRepository, subscriptionRepository, trackRepository, mediaLinkRepository, authorizedUserGetter, purchaseRepository, favouriteRepository, basketRepository);

        this.userUpdateMapper = userUpdateMapper;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteUser() {
        userRepository.deleteById(authorizedUserGetter.getAuthorizedUser().getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUser(User updatedUser) {
        User currentUser = authorizedUserGetter.getAuthorizedUser();
        List<MediaLink> newMediaLinks = updatedUser.getMediaLinks();

        if (newMediaLinks != null) {
            mediaLinkRepository.deleteAllByOwnerId(currentUser.getId());
            newMediaLinks.forEach(link -> link.setOwner(currentUser));
            updatedUser.setMediaLinks(newMediaLinks);
        }

        userUpdateMapper.updateUser(updatedUser, currentUser);
        userRepository.save(currentUser);

        if (newMediaLinks != null) {
            mediaLinkRepository.saveAll(newMediaLinks);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setJingle(byte[] jingle) {
        User currentUser = authorizedUserGetter.getAuthorizedUser();

        try (FileOutputStream fos = new FileOutputStream(currentUser.getJinglePath())) {
            fos.write(jingle);
        } catch (IOException e) {
            throw new FileException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAvatar(byte[] avatar) {
        User currentUser = authorizedUserGetter.getAuthorizedUser();

        try (FileOutputStream fos = new FileOutputStream(currentUser.getAvatarPath())) {
            fos.write(avatar);
        } catch (IOException e) {
            throw new FileException();
        }
    }
}
