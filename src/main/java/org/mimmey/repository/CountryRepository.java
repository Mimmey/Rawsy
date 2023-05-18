package org.mimmey.repository;

import org.jetbrains.annotations.NotNull;
import org.mimmey.entity.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends CrudRepository<Country, Integer> {

    @NotNull
    List<Country> findAll();

    @NotNull
    Optional<Country> findById(@NotNull Integer id);
}
