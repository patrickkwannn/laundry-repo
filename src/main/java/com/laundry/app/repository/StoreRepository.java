package com.laundry.app.repository;

import com.laundry.app.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Patrick Kwan
 * Created on 02/03/2021
 */
@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
}
