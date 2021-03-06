package com.laundry.app.repository;

import com.laundry.app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
