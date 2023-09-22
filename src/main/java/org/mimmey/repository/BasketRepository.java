package org.mimmey.repository;

import org.jetbrains.annotations.NotNull;
import org.mimmey.entity.associative.TrackInBasket;
import org.mimmey.entity.embedded_keys.TrackInBasketPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BasketRepository extends JpaRepository<TrackInBasket, TrackInBasketPK>,
        CrudRepository<TrackInBasket, TrackInBasketPK> {

    @Query(value = "SELECT * FROM tracks_in_basket_list WHERE owner_id=:owner_id", nativeQuery = true)
    Page<TrackInBasket> findAllByOwnerId(@Param("owner_id") Long ownerId, Pageable pageable);

    @NotNull <S extends TrackInBasket> S save(@NotNull S entity);

    @Query(value = "DELETE FROM tracks_in_basket_list WHERE owner_id=:owner_id AND track_id=:track_id", nativeQuery = true)
    void deleteByOwnerIdAndTrackId(@Param("owner_id") Long ownerId,
                                   @Param("track_id") Long trackId);

    @Query(value = "DELETE FROM tracks_in_basket_list WHERE owner_id=:owner_id", nativeQuery = true)
    void deleteByOwnerId(@NotNull @Param("owner_id") Long ownerId);
}
