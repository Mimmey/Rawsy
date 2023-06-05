package org.mimmey.repository;

import org.jetbrains.annotations.NotNull;
import org.mimmey.entity.TrackGenre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrackGenreRepository extends CrudRepository<TrackGenre, Integer> {

    @NotNull
    List<TrackGenre> findAll();

    @NotNull
    Optional<TrackGenre> findById(@NotNull Integer id);
}
