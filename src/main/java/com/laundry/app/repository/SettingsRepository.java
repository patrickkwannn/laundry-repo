package com.laundry.app.repository;

import com.laundry.app.entity.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Patrick Kwan
 * Created on 03/03/2021
 */
@Repository
public interface SettingsRepository extends JpaRepository<Settings, Long> {
}
