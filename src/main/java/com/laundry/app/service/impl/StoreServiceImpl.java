package com.laundry.app.service.impl;

import com.laundry.app.domain.StoreDomain;
import com.laundry.app.domain.StoreInfo;
import com.laundry.app.entity.Store;
import com.laundry.app.repository.StoreRepository;
import com.laundry.app.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Patrick Kwan
 * Created on 02/03/2021
 */
@Service
public class StoreServiceImpl implements StoreService {

    @Value("${laundry.payment.options}")
    private String[] paymentOptions;

    @Value("${laundry.pricing}")
    private String[] paymentPricing;

    @Value("{laundry.operational.hours}")
    private String operationalHours;

    private final Environment environment;
    private final StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(Environment environment,
                            StoreRepository storeRepository){
        this.environment = environment;
        this.storeRepository = storeRepository;
    }

    @Override
    public StoreInfo createStoreInfo() {
        StoreInfo info = new StoreInfo();
        info.setOperationalHours(operationalHours);
        info.setPaymentMethod(Arrays.asList(paymentOptions.clone()));
        Map<String, Long> pricing = new HashMap<>();

        for(String s : paymentPricing){
            pricing.put(s, Long.parseLong(Objects.requireNonNull(environment.getProperty(s))));
        }
        info.setPricing(pricing);

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
