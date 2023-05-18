package org.mimmey.repository;

import org.jetbrains.annotations.NotNull;
import org.mimmey.entity.TrackType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TrackTypeRepository extends CrudRepository<TrackType, Integer> {

    @NotNull
    List<TrackType> findAll();

    @NotNull
    Optional<TrackType> findById(@NotNull Integer id);
}
