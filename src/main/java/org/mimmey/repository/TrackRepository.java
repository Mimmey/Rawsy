package org.mimmey.repository;

import org.jetbrains.annotations.NotNull;
import org.mimmey.entity.Track;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long>, CrudRepository<Track, Long> {

    Page<Track> findAll(Specification<Track> searchCriteria, Pageable pageable);

    @Query(value = "SELECT * FROM track WHERE author_id=:author_id", nativeQuery = true)
    Page<Track> findAllByAuthorId(@Param("author_id") Long authorId, Pageable pageable);

    @NotNull <S extends Track> S save(@NotNull S entity);

    @NotNull
    Optional<Track> findById(@NotNull Long id);

    @NotNull
    Optional<Track> findByName(@NotNull String name);

    void deleteById(@NotNull Long id);
}
