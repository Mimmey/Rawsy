package org.mimmey.repository;

import org.jetbrains.annotations.NotNull;
import org.mimmey.entity.Comment;
import org.mimmey.entity.embedded_keys.CommentPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, CommentPK>, CrudRepository<Comment, CommentPK> {

    @NotNull <S extends Comment> S save(@NotNull S entity);

    @Query(value = "SELECT * FROM comment WHERE track_id=:track_id", nativeQuery = true)
    Page<Comment> findAllByTrackId(@Param("track_id") Long trackId,
                                   Pageable pageable);

    @Query(value = "DELETE FROM comment WHERE author_id=:author_id AND track_id=:track_id", nativeQuery = true)
    void deleteByAuthorIdAndTrackId(@Param("author_id") Long authorId,
                                    @Param("track_id") Long trackId);

    @Query(value = "SELECT * FROM comment WHERE author_id=:author_id AND track_id=:track_id", nativeQuery = true)
    Optional<Comment> findByAuthorIdAndTrackId(@Param("author_id") Long authorId,
                                               @Param("track_id") Long trackId);
}
