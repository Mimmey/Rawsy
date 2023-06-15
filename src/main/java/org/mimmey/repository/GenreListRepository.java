package org.mimmey.repository;

import org.jetbrains.annotations.NotNull;
import org.mimmey.entity.associative.TrackToGenreMatching;
import org.mimmey.entity.embedded_keys.TrackToGenreMatchingPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreListRepository extends CrudRepository<TrackToGenreMatching, TrackToGenreMatchingPK> {

    @Query(value = "DELETE FROM genre_list WHERE track_id=:track_id", nativeQuery = true)
    void deleteAllByTrackId(@NotNull @Param("track_id") Long trackId);
}
