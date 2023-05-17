package org.mimmey.repository;

import org.jetbrains.annotations.NotNull;
import org.mimmey.entity.associative.Purchase;
import org.mimmey.entity.embedded_keys.PurchasePK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PurchaseRepository extends JpaRepository<Purchase, PurchasePK>, CrudRepository<Purchase, PurchasePK> {

    @Query(value = "SELECT * FROM purchase_list WHERE purchaser_id=:purchaser_id", nativeQuery = true)
    <S extends Purchase> Page<Purchase> findAllByPurchaserId(@Param("purchaser_id") Long purchaserId,
                                                             Pageable pageable);

    @NotNull <S extends Purchase> S save(@NotNull S entity);
}
