package org.mimmey.repository;

import org.jetbrains.annotations.NotNull;
import org.mimmey.entity.Country;
import org.mimmey.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, CrudRepository<User, Long> {

    @NotNull <S extends User> S save(@NotNull S entity);

    @NotNull
    Optional<User> findById(@NotNull Long id);

    @NotNull
    Optional<User> findByNickname(@NotNull String nickname);

    void deleteById(@NotNull Long id);

    @Query(value = "SELECT * FROM country", nativeQuery = true)
    List<Country> findAllCountries();

    @Query(value = "SELECT * FROM country WHERE id=:id", nativeQuery = true)
    Country findCountryById(@Param("id") Integer id);
}


