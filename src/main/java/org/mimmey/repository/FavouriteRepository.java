package org.mimmey.repository;

import org.jetbrains.annotations.NotNull;
import org.mimmey.entity.associative.FavouriteAddition;
import org.mimmey.entity.embedded_keys.FavouriteAdditionPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FavouriteRepository extends JpaRepository<FavouriteAddition, FavouriteAdditionPK>,
        CrudRepository<FavouriteAddition, FavouriteAdditionPK> {

    @Query(value = "SELECT * FROM favourites_list WHERE owner_id=:owner_id", nativeQuery = true)
    Page<FavouriteAddition> findAllByOwnerId(@Param("owner_id") Long ownerId, Pageable pageable);

    @NotNull <S extends FavouriteAddition> S save(@NotNull S entity);

    @Query(value = "DELETE FROM favourites_list WHERE owner_id=:owner_id AND track_id=:track_id", nativeQuery = true)
    void deleteByOwnerIdAndTrackId(@Param("owner_id") Long ownerId,
                                   @Param("track_id") Long trackId);
}
