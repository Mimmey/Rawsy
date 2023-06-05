package org.mimmey.repository;

import org.jetbrains.annotations.NotNull;
import org.mimmey.entity.TrackType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrackTypeRepository extends CrudRepository<TrackType, Integer> {

    @NotNull
    List<TrackType> findAll();

    @NotNull
    Optional<TrackType> findById(@NotNull Integer id);
}
