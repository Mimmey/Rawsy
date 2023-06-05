package org.mimmey.repository;

import org.jetbrains.annotations.NotNull;
import org.mimmey.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CrudRepository<User, Long> {

    @NotNull <S extends User> S save(@NotNull S entity);

    @NotNull
    Optional<User> findById(@NotNull Long id);

    @NotNull
    Optional<User> findByNickname(@NotNull String nickname);

    void deleteById(@NotNull Long id);
}


