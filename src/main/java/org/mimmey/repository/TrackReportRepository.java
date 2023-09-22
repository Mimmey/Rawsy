package org.mimmey.repository;

import org.jetbrains.annotations.NotNull;
import org.mimmey.entity.associative.TrackReport;
import org.mimmey.entity.embedded_keys.TrackReportPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TrackReportRepository extends JpaRepository<TrackReport, TrackReportPK> {

    @NotNull <S extends TrackReport> S save(@NotNull S entity);

    @Query(value = "SELECT * FROM track_report WHERE track_subject_id=:track_subject_id", nativeQuery = true)
    Page<TrackReport> findAllByTrackSubjectId(@Param("track_subject_id") Long trackId,
                                              Pageable pageable);

    @Query(value = "SELECT * FROM track_report WHERE report_id=:report_id", nativeQuery = true)
    Optional<TrackReport> findById(@Param("report_id") Long id);

    @NotNull
    Page<TrackReport> findAll(@NotNull Pageable pageable);
}
