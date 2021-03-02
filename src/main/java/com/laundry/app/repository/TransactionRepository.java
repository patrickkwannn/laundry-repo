package com.laundry.app.repository;

import com.laundry.app.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByCustomer_CustomerIdOrderByCreatedDateDesc(long customerId);
    List<Transaction> findAllByCustomer_CustomerIdAndStatusOrderByCreatedDateDesc(long customerId, String status);
}
