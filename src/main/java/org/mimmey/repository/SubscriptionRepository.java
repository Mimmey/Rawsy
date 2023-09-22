package org.mimmey.repository;

import org.jetbrains.annotations.NotNull;
import org.mimmey.entity.associative.Subscription;
import org.mimmey.entity.embedded_keys.SubscriptionPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface SubscriptionRepository extends PagingAndSortingRepository<Subscription, SubscriptionPK>, CrudRepository<Subscription, SubscriptionPK> {

    @Query(value = "SELECT * FROM subscription_list WHERE subject_id=:subject_id", nativeQuery = true)
    Page<Subscription> findAllBySubjectId(@Param("subject_id") long subjectId,
                                          Pageable pageable);

    @Query(value = "SELECT * FROM subscription_list WHERE subscriber_id=:subscriber_id", nativeQuery = true)
    Page<Subscription> findAllBySubscriberId(@Param("subscriber_id") long subscriberId,
                                             Pageable pageable);

    @NotNull <S extends Subscription> S save(@NotNull S entity);

    @Query(value = "DELETE FROM subscription_list WHERE subscriber_id=:subscriber_id AND subject_id=:subject_id", nativeQuery = true)
    void deleteBySubscriberIdAndSubjectId(@Param("subscriber_id") long subscriberId,
                                          @Param("subject_id") long subjectId);
}
