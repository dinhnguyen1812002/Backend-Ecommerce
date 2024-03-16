package com.Project.Store.repository;

import com.Project.Store.entity.SalesTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
@Repository
public interface SalesTransactionRepository extends JpaRepository<SalesTransaction, Long> {
    @Query("SELECT s.product.id, SUM(s.quantity) " +
            "FROM SalesTransaction s " +
            "GROUP BY s.product.id " +
            "ORDER BY SUM(s.quantity) DESC")
    List<Object[]> findBestSellingProducts(Pageable pageable);

}
