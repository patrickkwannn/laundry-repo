package com.laundry.app.controller;

import com.laundry.app.domain.RoleDomain;
import com.laundry.app.domain.StoreInfo;
import com.laundry.app.entity.Role;
import com.laundry.app.entity.Settings;
import com.laundry.app.service.RoleService;
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
@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/v1/settings")
public class SettingsController {

    private final SettingService settingService;
    private final RoleService roleService;

    @Autowired
    public SettingsController(SettingService settingService, RoleService roleService){
        this.settingService = settingService;
        this.roleService = roleService;
    }

    @PostMapping("/update")
    public ResponseEntity<Settings> updateSettings(@RequestBody StoreInfo domain) throws NotFoundException {
        return new ResponseEntity<>(settingService.updateSettings(domain), HttpStatus.OK);
    }

    @PostMapping("/roles")
    public ResponseEntity<Role> addRoles(@RequestBody RoleDomain roleDomain){
        return new ResponseEntity<>(roleService.addRole(roleDomain.getName(), roleDomain.getDescription()), HttpStatus.OK);
    }
}
