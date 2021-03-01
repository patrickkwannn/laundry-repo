package com.laundry.app.repository;

import com.laundry.app.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    Set<Orders> findAllByTransaction_TransactionId(long transactionId);
}
