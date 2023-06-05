package org.mimmey.repository;

import org.jetbrains.annotations.NotNull;
import org.mimmey.entity.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {

    @NotNull <S extends Report> S save(@NotNull S entity);

    @NotNull
    Optional<Report> findById(@NotNull Long id);

    void deleteById(@NotNull Long id);
}
