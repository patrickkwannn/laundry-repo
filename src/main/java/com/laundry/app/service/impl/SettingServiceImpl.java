package com.laundry.app.service.impl;

import com.laundry.app.domain.StoreInfo;
import com.laundry.app.entity.Settings;
import com.laundry.app.repository.SettingsRepository;
import com.laundry.app.service.SettingService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author Patrick Kwan
 * Created on 03/03/2021
 */
@Service
public class SettingServiceImpl implements SettingService {

    private final SettingsRepository settingsRepository;

    @Value("${laundry.payment.options}")
    private String[] paymentOptions;

    @Value("${laundry.operational.hours}")
    private String operationalHours;

    @Autowired
    public SettingServiceImpl(SettingsRepository settingsRepository){
        this.settingsRepository = settingsRepository;
    }

    @Override
    public Settings getSettings() throws NotFoundException {
        if(settingsRepository.findAll().isEmpty()){
            throw new NotFoundException("Settings not found");
        }

        return settingsRepository.getOne(1L);
    }

    @Override
    public Settings updateSettings(StoreInfo domain) throws NotFoundException {
        Settings settings = getSettings();
        settings.setPaymentOptions(domain.getPaymentMethod());
        settings.setMaxDelivery(domain.getMaxDelivery());
        settings.setOperationalHours(domain.getOperationalHours());
        settings.setPricePerPiece(domain.getPricePerPiece());
        settings.setBasicDeliveryPrice(domain.getBasicDeliveryPrice());
        settings.setPricePerKilo(domain.getPricePerKilo());

        return settingsRepository.save(settings);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createSetting() {
        if(settingsRepository.findAll().isEmpty()){
            Settings settings = new Settings();
            settings.setOperationalHours(operationalHours);
            settings.setMaxDelivery(10000);
            settings.setBasicDeliveryPrice(10000);
            settings.setPricePerKilo(10000);
            settings.setPricePerPiece(10000);
            settings.setPaymentOptions(Arrays.asList(paymentOptions.clone()));

            settingsRepository.save(settings);
        }
    }
}
