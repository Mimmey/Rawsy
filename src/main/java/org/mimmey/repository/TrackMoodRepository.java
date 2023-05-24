package org.mimmey.repository;

import org.jetbrains.annotations.NotNull;
import org.mimmey.entity.TrackMood;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TrackMoodRepository extends CrudRepository<TrackMood, Integer> {

    @NotNull
    List<TrackMood> findAll();

    @NotNull
    Optional<TrackMood> findById(@NotNull Integer id);
}
