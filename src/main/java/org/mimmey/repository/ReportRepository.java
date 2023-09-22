package org.mimmey.repository;

import org.jetbrains.annotations.NotNull;
import org.mimmey.entity.Report;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ReportRepository extends CrudRepository<Report, Long> {

    @NotNull <S extends Report> S save(@NotNull S entity);

    @NotNull
    Optional<Report> findById(@NotNull Long id);

    void deleteById(@NotNull Long id);
}
