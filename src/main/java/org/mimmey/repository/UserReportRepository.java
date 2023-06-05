package org.mimmey.repository;

import org.jetbrains.annotations.NotNull;
import org.mimmey.entity.associative.UserReport;
import org.mimmey.entity.embedded_keys.UserReportPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserReportRepository extends JpaRepository<UserReport, UserReportPK> {

    @NotNull <S extends UserReport> S save(@NotNull S entity);

    @Query(value = "SELECT * FROM user_report WHERE user_subject_id=:user_subject_id", nativeQuery = true)
    Page<UserReport> getUserReportsByUserSubjectId(@Param("user_subject_id") long userId,
                                                   Pageable pageable);

    @Query(value = "SELECT * FROM user_report WHERE report_id=:report_id", nativeQuery = true)
    Optional<UserReport> findById(@Param("report_id") Long id);

    @NotNull
    Page<UserReport> findAll(@NotNull Pageable pageable);
}
