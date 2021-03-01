package com.laundry.app.repository;

import com.laundry.app.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Patrick Kwan
 * Created on 01/03/2021
 */
@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
}
