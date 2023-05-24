package org.mimmey.service.special.impl;

import jakarta.persistence.EntityNotFoundException;
import org.mimmey.config.security.utils.AuthorizedUserGetter;
import org.mimmey.entity.Track;
import org.mimmey.entity.User;
import org.mimmey.entity.associative.FavouriteAddition;
import org.mimmey.entity.associative.Purchase;
import org.mimmey.entity.associative.Subscription;
import org.mimmey.entity.associative.TrackInBasket;
import org.mimmey.entity.associative.TrackToGenreMatching;
import org.mimmey.entity.associative.TrackToMoodMatching;
import org.mimmey.entity.embedded_keys.FavouriteAdditionPK;
import org.mimmey.entity.embedded_keys.PurchasePK;
import org.mimmey.entity.embedded_keys.SubscriptionPK;
import org.mimmey.entity.embedded_keys.TrackInBasketPK;
import org.mimmey.repository.BasketRepository;
import org.mimmey.repository.FavouriteRepository;
import org.mimmey.repository.MediaLinkRepository;
import org.mimmey.repository.PurchaseRepository;
import org.mimmey.repository.SubscriptionRepository;
import org.mimmey.repository.TrackRepository;
import org.mimmey.repository.UserRepository;
import org.mimmey.service.common.impl.UserServiceImpl;
import org.mimmey.service.special.AuthorizedUserService;
import org.mimmey.utils.FileTypes;
import org.mimmey.utils.FileWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service("authorized-user")
public class AuthorizedUserServiceImpl extends UserServiceImpl implements AuthorizedUserService {

    protected final AuthorizedUserGetter authorizedUserGetter;

    protected final PurchaseRepository purchaseRepository;

    protected final FavouriteRepository favouriteRepository;

    protected final BasketRepository basketRepository;

    public AuthorizedUserServiceImpl(@Autowired UserRepository userRepository,
                                     @Autowired SubscriptionRepository subscriptionRepository,
                                     @Autowired TrackRepository trackRepository,
                                     @Autowired MediaLinkRepository mediaLinkRepository,
                                     @Autowired AuthorizedUserGetter authorizedUserGetter,
                                     @Autowired PurchaseRepository purchaseRepository,
                                     @Autowired FavouriteRepository favouriteRepository,
                                     @Autowired BasketRepository basketRepository) {
        super(userRepository, subscriptionRepository, trackRepository, mediaLinkRepository);

        this.authorizedUserGetter = authorizedUserGetter;
        this.purchaseRepository = purchaseRepository;
        this.favouriteRepository = favouriteRepository;
        this.basketRepository = basketRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser() {
        return getUser(authorizedUserGetter.getAuthorizedUser().getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<User> getSubscriptions(int page, int unitsOnPage) {
        return getSubscriptions(authorizedUserGetter.getAuthorizedUser().getId(), page, unitsOnPage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<User> getSubscribers(int page, int unitsOnPage) {
        return getSubscribers(authorizedUserGetter.getAuthorizedUser().getId(), page, unitsOnPage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Track> getPublishedTracks(int page, int unitsOnPage) {
        return getPublishedTracks(authorizedUserGetter.getAuthorizedUser().getId(), page, unitsOnPage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void subscribe(long subscriptionUserId) {
        User currentUser = authorizedUserGetter.getAuthorizedUser();
        User subscriptionUser = userRepository.findById(subscriptionUserId).orElseThrow(EntityNotFoundException::new);

        checkIllegalSubscription(currentUser, subscriptionUser);

        Subscription subscription = new Subscription(new SubscriptionPK(currentUser, subscriptionUser));
        subscriptionRepository.save(subscription);
    }

    private void checkIllegalSubscription(User subscriber, User subject) {
        if (subscriber.getId().equals(subject.getId())) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unsubscribe(long subscriptionUserId) {
        User currentUser = authorizedUserGetter.getAuthorizedUser();
        User subscriptionUser = userRepository.findById(subscriptionUserId).orElseThrow(EntityNotFoundException::new);

        try {
            subscriptionRepository.deleteBySubscriberIdAndSubjectId(currentUser.getId(), subscriptionUser.getId());
        } catch (JpaSystemException ignored) {
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Track> getPurchasedTracks(int page, int unitsOnPage) {
        Pageable pageable = PageRequest.of(page, unitsOnPage);
        Page<Purchase> purchases = purchaseRepository.findAllByPurchaserId(
                authorizedUserGetter.getAuthorizedUser().getId(), pageable
        );

        List<Track> purchasedTracks = purchases.stream()
                .map(p -> trackRepository.findById(p.getPk().getTrack().getId())
                        .orElseThrow(() -> new EntityNotFoundException("Found purchase but not found track")))
                .toList();

        return new PageImpl<>(purchasedTracks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Track> getFavouriteTracks(int page, int unitsOnPage) {
        Pageable pageable = PageRequest.of(page, unitsOnPage);
        Page<FavouriteAddition> favouriteAdditions = favouriteRepository.findAllByOwnerId(authorizedUserGetter.getAuthorizedUser().getId(), pageable);

        List<Track> favouriteTracks = favouriteAdditions.stream()
                .map(p -> trackRepository.findById(p.getPk().getTrack().getId())
                        .orElseThrow(() -> new EntityNotFoundException("Found favorite addition but not found track")))
                .toList();

        return new PageImpl<>(favouriteTracks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Track> getBasketTracks(int page, int unitsOnPage) {
        Pageable pageable = PageRequest.of(page, unitsOnPage);
        Page<TrackInBasket> tracksInBasket = basketRepository.findAllByOwnerId(authorizedUserGetter.getAuthorizedUser().getId(), pageable);

        List<Track> basketTracks = tracksInBasket.stream()
                .map(p -> trackRepository.findById(p.getPk().getTrack().getId())
                        .orElseThrow(() -> new EntityNotFoundException("Found basket addition but not found track")))
                .toList();

        return new PageImpl<>(basketTracks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void publishTrack(Track track) {
        List<TrackToGenreMatching> trackGenres = track.getGenres();
        List<TrackToMoodMatching> trackMoods = track.getMoods();
        track.setGenres(Collections.emptyList());
        track.setMoods(Collections.emptyList());

        User currentUser = authorizedUserGetter.getAuthorizedUser();
        track.setAuthor(currentUser);
        track.setTrackArchivePath("temp");
        track.setAudioPreviewPath("temp");
        trackRepository.save(track);

        Track createdTrack = trackRepository.findByName(track.getName()).orElseThrow(RuntimeException::new);

        String trackArchivePath = FileWorker.getArchivePath(track.getId());
        String previewPath = FileWorker.getPreviewPath(track.getId());

        createdTrack.setTrackArchivePath(trackArchivePath);
        createdTrack.setAudioPreviewPath(previewPath);
        trackGenres.forEach(genre -> genre.getPk().setTrack(createdTrack));
        trackMoods.forEach(mood -> mood.getPk().setTrack(createdTrack));
        createdTrack.setGenres(trackGenres);
        createdTrack.setMoods(trackMoods);
        trackRepository.save(createdTrack);

        FileWorker.tryCreateDefault(trackArchivePath, FileTypes.ARCHIVE);
        FileWorker.tryCreateDefault(previewPath, FileTypes.PREVIEW);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void purchaseTrack(long trackId) {
        User currentUser = authorizedUserGetter.getAuthorizedUser();
        Track track = trackRepository.findById(trackId).orElseThrow(EntityNotFoundException::new);

        checkPurchase(currentUser, track);
        checkPurchaserIsAuthor(currentUser, track);

        purchaseRepository.save(new Purchase(new PurchasePK(currentUser, track), track.getCost(), LocalDateTime.now()));
    }

    private void checkPurchase(User user, Track track) {
        if (purchaseRepository.findByPurchaserIdAndTrackId(user.getId(), track.getId()).isPresent()) {
            throw new AccessDeniedException("Трек уже приобретен");
        }
    }

    private void checkPurchaserIsAuthor(User purchaser, Track track) {
        if (purchaser.getId().equals(track.getAuthor().getId())) {
            throw new AccessDeniedException("Вы не ожете купить трек, чьим автором являетесь");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTrackToFavorites(long trackId) {
        User currentUser = authorizedUserGetter.getAuthorizedUser();
        Track track = trackRepository.findById(trackId).orElseThrow(EntityNotFoundException::new);
        favouriteRepository.save(new FavouriteAddition(new FavouriteAdditionPK(currentUser, track), LocalDateTime.now()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTrackToBasket(long trackId) {
        User currentUser = authorizedUserGetter.getAuthorizedUser();
        Track track = trackRepository.findById(trackId).orElseThrow(EntityNotFoundException::new);

        checkPurchase(currentUser, track);
        checkPurchaserIsAuthor(currentUser, track);

        basketRepository.save(new TrackInBasket(new TrackInBasketPK(currentUser, track)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deletePublishedTrack(long trackId) {
        User currentUser = authorizedUserGetter.getAuthorizedUser();
        Track track = trackRepository.findById(trackId).orElseThrow(EntityNotFoundException::new);

        if (!currentUser.getId().equals(track.getAuthor().getId())) {
            throw new AccessDeniedException("Access is denied");
        }

        trackRepository.deleteById(trackId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeTrackFromFavourites(long trackId) {
        User currentUser = authorizedUserGetter.getAuthorizedUser();
        Track track = trackRepository.findById(trackId).orElseThrow(EntityNotFoundException::new);

        try {
            favouriteRepository.deleteByOwnerIdAndTrackId(currentUser.getId(), track.getId());
        } catch (JpaSystemException ignored) {
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeTrackFromBasket(long trackId) {
        User currentUser = authorizedUserGetter.getAuthorizedUser();
        Track track = trackRepository.findById(trackId).orElseThrow(EntityNotFoundException::new);
        try {
            basketRepository.deleteByOwnerIdAndTrackId(currentUser.getId(), track.getId());
        } catch (JpaSystemException ignored) {
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearBasket() {
        try {
            basketRepository.deleteByOwnerId(authorizedUserGetter.getAuthorizedUser().getId());
        } catch (JpaSystemException ignored) {
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getBasketCost() {
        return getBasketTracks(0, Integer.MAX_VALUE).stream().mapToLong(Track::getCost).sum();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void payForBasket() {
        User currentUser = authorizedUserGetter.getAuthorizedUser();
        List<TrackInBasket> basketTracks = basketRepository.findAllByOwnerId(
                currentUser.getId(), Pageable.unpaged()
        ).stream().toList();

        List<Track> tracks = basketTracks.stream().map(tr -> tr.getPk().getTrack()).toList();
        tracks.forEach(track -> purchaseRepository.save(
                new Purchase(new PurchasePK(currentUser, track), track.getCost(), LocalDateTime.now())
        ));
    }
}
