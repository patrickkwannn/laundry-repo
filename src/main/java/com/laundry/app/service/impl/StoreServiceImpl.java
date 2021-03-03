package com.laundry.app.service.impl;

import com.laundry.app.domain.StoreDomain;
import com.laundry.app.domain.StoreInfo;
import com.laundry.app.entity.Settings;
import com.laundry.app.entity.Store;
import com.laundry.app.repository.StoreRepository;
import com.laundry.app.service.SettingService;
import com.laundry.app.service.StoreService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Patrick Kwan
 * Created on 02/03/2021
 */
@Service
public class StoreServiceImpl implements StoreService {

    private final SettingService settingService;
    private final StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(SettingService settingService,
                            StoreRepository storeRepository){
        this.storeRepository = storeRepository;
        this.settingService = settingService;
    }

    @Override
    public StoreInfo createStoreInfo() throws NotFoundException {

        Settings settings = settingService.getSettings();
        StoreInfo info = new StoreInfo();
        info.setOperationalHours(settings.getOperationalHours());
        info.setPaymentMethod(settings.getPaymentOptions());
        info.setBasicDeliveryPrice(settings.getBasicDeliveryPrice());
        info.setMaxDelivery(settings.getMaxDelivery());
        info.setPricePerKilo(settings.getPricePerKilo());
        info.setPricePerPiece(settings.getPricePerPiece());

        return info;
    }

    @Override
    public Store addStore(StoreDomain domain) {
        Store store = new Store();
        store.setAddress(domain.getAddress());
        store.setName(domain.getName());
        store.setLatitude(domain.getLatitude());
        store.setLongitude(domain.getLongitude());

        return storeRepository.save(store);
    }
}
