package org.mimmey.repository;

import org.jetbrains.annotations.NotNull;
import org.mimmey.entity.Track;
import org.mimmey.entity.TrackGenre;
import org.mimmey.entity.TrackMood;
import org.mimmey.entity.TrackType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TrackRepository extends JpaRepository<Track, Long>, CrudRepository<Track, Long> {

    Page<Track> findAll(Specification<Track> searchCriteria, Pageable pageable);

    @Query(value = "SELECT * FROM track WHERE author_id=:author_id", nativeQuery = true)
    Page<Track> findAllByAuthorId(@Param("author_id") Long authorId, Pageable pageable);

    @NotNull <S extends Track> S save(@NotNull S entity);

    @NotNull
    Optional<Track> findById(@NotNull Long id);

    void deleteById(@NotNull Long id);

    @Query(value = "SELECT * FROM track WHERE publishing_timestamp >= NOW() - 7 * interval '1 day' " +
            "ORDER BY rating DESC LIMIT 100",
            nativeQuery = true)
    Page<Track> getHottestPerWeek(Specification<Track> specification, Pageable pageable);

    @Query(value = "SELECT * FROM track WHERE publishing_timestamp >= NOW() - 7 * interval '1 day' " +
            "ORDER BY publishing_timestamp DESC LIMIT 100",
            nativeQuery = true)
    Page<Track> getNewPerWeek(Specification<Track> specification, Pageable pageable);

    @Query(value = "SELECT * FROM track_genre", nativeQuery = true)
    List<TrackGenre> findAllGenres();

    @Query(value = "SELECT * FROM track_mood", nativeQuery = true)
    List<TrackMood> findAllMoods();

    @Query(value = "SELECT * FROM track_type", nativeQuery = true)
    List<TrackType> findAllTypes();

    @Query(value = "SELECT * FROM track_genre WHERE id=:id", nativeQuery = true)
    TrackGenre findGenreById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM track_mood WHERE id=:id", nativeQuery = true)
    TrackMood findMoodById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM track_type WHERE id=:id", nativeQuery = true)
    TrackType findTypeById(@Param("id") Integer id);
}
