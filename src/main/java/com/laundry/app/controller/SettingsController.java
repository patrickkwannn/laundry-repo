package com.laundry.app.controller;

import com.laundry.app.domain.StoreInfo;
import com.laundry.app.entity.Settings;
import com.laundry.app.service.SettingService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Patrick Kwan
 * Created on 03/03/2021
 */
@RestController
@RequestMapping("/v1/settings")
public class SettingsController {

    private final SettingService settingService;

    @Autowired
    public SettingsController(SettingService settingService){
        this.settingService = settingService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public ResponseEntity<Settings> updateSettings(@RequestBody StoreInfo domain) throws NotFoundException {
        return new ResponseEntity<>(settingService.updateSettings(domain), HttpStatus.OK);
    }
}
