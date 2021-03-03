package com.laundry.app.service;

import com.laundry.app.domain.StoreInfo;
import com.laundry.app.entity.Settings;
import javassist.NotFoundException;

/**
 * @author Patrick Kwan
 * Created on 03/03/2021
 */
public interface SettingService {
    Settings getSettings() throws NotFoundException;
    Settings updateSettings(StoreInfo domain) throws NotFoundException;
}
