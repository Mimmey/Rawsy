package org.mimmey.repository;

import org.jetbrains.annotations.NotNull;
import org.mimmey.entity.MediaLink;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MediaLinkRepository extends CrudRepository<MediaLink, Long> {

    @NotNull <S extends MediaLink> S save(@NotNull S entity);

    @Query(value = "DELETE FROM media_link WHERE owner_id=:owner_id", nativeQuery = true)
    void deleteAllByOwnerId(@NotNull @Param("owner_id") Long ownerId);
}
