package org.mimmey.service.special.impl;

import org.mimmey.config.security.AuthorizedUserGetter;
import org.mimmey.entity.Track;
import org.mimmey.entity.User;
import org.mimmey.entity.associative.FavouriteAddition;
import org.mimmey.entity.associative.Purchase;
import org.mimmey.entity.associative.Subscription;
import org.mimmey.entity.associative.TrackInBasket;
import org.mimmey.entity.embedded_keys.FavouriteAdditionPK;
import org.mimmey.entity.embedded_keys.PurchasePK;
import org.mimmey.entity.embedded_keys.SubscriptionPK;
import org.mimmey.entity.embedded_keys.TrackInBasketPK;
import org.mimmey.repository.BasketRepository;
import org.mimmey.repository.FavouriteRepository;
import org.mimmey.repository.PurchaseRepository;
import org.mimmey.repository.SubscriptionRepository;
import org.mimmey.repository.TrackRepository;
import org.mimmey.repository.UserRepository;
import org.mimmey.service.common.impl.UserServiceImpl;
import org.mimmey.service.special.AuthorizedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
                                     @Autowired AuthorizedUserGetter authorizedUserGetter,
                                     @Autowired PurchaseRepository purchaseRepository,
                                     @Autowired FavouriteRepository favouriteRepository,
                                     @Autowired BasketRepository basketRepository) {
        super(userRepository, subscriptionRepository, trackRepository);

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
        User currentUser = userRepository.findById(authorizedUserGetter.getAuthorizedUser().getId()).orElseThrow(RuntimeException::new);
        User subscriptionUser = userRepository.findById(subscriptionUserId).orElseThrow(RuntimeException::new);

        Subscription subscription = new Subscription(new SubscriptionPK(currentUser, subscriptionUser));
        subscriptionRepository.save(subscription);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unsubscribe(long subscriptionUserId) {
        User currentUser = userRepository.findById(authorizedUserGetter.getAuthorizedUser().getId()).orElseThrow(RuntimeException::new);
        User subscriptionUser = userRepository.findById(subscriptionUserId).orElseThrow(RuntimeException::new);

        subscriptionRepository.deleteByPk(new SubscriptionPK(currentUser, subscriptionUser));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Track> getPurchasedTracks(int page, int unitsOnPage) {
        Pageable pageable = PageRequest.of(page, unitsOnPage);
        Page<Purchase> purchases = purchaseRepository.findAllByPurchaserId(authorizedUserGetter.getAuthorizedUser().getId(), pageable);

        List<Track> purchasedTracks = purchases.stream()
                .map(p -> trackRepository.findById(p.getPk().getTrack().getId()).orElseThrow(RuntimeException::new))
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
                .map(p -> trackRepository.findById(p.getPk().getTrack().getId()).orElseThrow(RuntimeException::new))
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
                .map(p -> trackRepository.findById(p.getPk().getTrack().getId()).orElseThrow(RuntimeException::new))
                .toList();

        return new PageImpl<>(basketTracks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void publishTrack(Track track) {
        User currentUser = userRepository.findById(authorizedUserGetter.getAuthorizedUser().getId()).orElseThrow(RuntimeException::new);
        track.setAuthor(currentUser);
        trackRepository.save(track);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void purchaseTrack(long trackId) {
        User currentUser = userRepository.findById(authorizedUserGetter.getAuthorizedUser().getId()).orElseThrow(RuntimeException::new);
        Track track = trackRepository.findById(trackId).orElseThrow(RuntimeException::new);

        purchaseRepository.save(new Purchase(new PurchasePK(currentUser, track), track.getCost(), LocalDateTime.now()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTrackToFavorites(long trackId) {
        User currentUser = userRepository.findById(authorizedUserGetter.getAuthorizedUser().getId()).orElseThrow(RuntimeException::new);
        Track track = trackRepository.findById(trackId).orElseThrow(RuntimeException::new);

        favouriteRepository.save(new FavouriteAddition(new FavouriteAdditionPK(currentUser, track), LocalDateTime.now()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTrackToBasket(long trackId) {
        User currentUser = userRepository.findById(authorizedUserGetter.getAuthorizedUser().getId()).orElseThrow(RuntimeException::new);
        Track track = trackRepository.findById(trackId).orElseThrow(RuntimeException::new);

        basketRepository.save(new TrackInBasket(new TrackInBasketPK(currentUser, track)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deletePublishedTrack(long trackId) {
        trackRepository.deleteById(trackId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeTrackFromFavourites(long trackId) {
        User currentUser = userRepository.findById(authorizedUserGetter.getAuthorizedUser().getId()).orElseThrow(RuntimeException::new);
        Track track = trackRepository.findById(trackId).orElseThrow(RuntimeException::new);

        favouriteRepository.deleteByPk(new FavouriteAdditionPK(currentUser, track));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeTrackFromBasket(long trackId) {
        User currentUser = userRepository.findById(authorizedUserGetter.getAuthorizedUser().getId()).orElseThrow(RuntimeException::new);
        Track track = trackRepository.findById(trackId).orElseThrow(RuntimeException::new);

        basketRepository.deleteByPk(new TrackInBasketPK(currentUser, track));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearBasket() {
        basketRepository.deleteByOwnerId(authorizedUserGetter.getAuthorizedUser().getId());
    }
}
