package com.laundry.app.controller;

import com.laundry.app.domain.RoleDomain;
import com.laundry.app.domain.StoreInfo;
import com.laundry.app.entity.Customer;
import com.laundry.app.entity.Role;
import com.laundry.app.entity.Settings;
import com.laundry.app.service.CustomerService;
import com.laundry.app.service.RoleService;
import com.laundry.app.service.SettingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    private final CustomerService customerService;

    @Autowired
    public SettingsController(SettingService settingService, RoleService roleService,
                              CustomerService customerService){
        this.settingService = settingService;
        this.roleService = roleService;
        this.customerService = customerService;
    }

    @ApiOperation(
            value = "Api to update store info",
            authorizations = {@Authorization(value = HttpHeaders.AUTHORIZATION)},
            response = Settings.class)
    @CrossOrigin
    @PostMapping("/update")
    public ResponseEntity<Settings> updateSettings(@RequestBody StoreInfo domain) throws NotFoundException {
        return new ResponseEntity<>(settingService.updateSettings(domain), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Api to add role",
            authorizations = {@Authorization(value = HttpHeaders.AUTHORIZATION)},
            response = Role.class)
    @PostMapping("/roles")
    @CrossOrigin
    public ResponseEntity<Role> addRoles(@RequestBody RoleDomain roleDomain){
        return new ResponseEntity<>(roleService.addRole(roleDomain.getName(), roleDomain.getDescription()), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Api to get all roles available",
            authorizations = {@Authorization(value = HttpHeaders.AUTHORIZATION)},
            response = Role.class,
            responseContainer = "List")
    @GetMapping("/roles")
    @CrossOrigin
    public ResponseEntity<List<Role>> allRoles(){
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Api to get all users available",
            authorizations = {@Authorization(value = HttpHeaders.AUTHORIZATION)},
            response = Customer.class,
            responseContainer = "List")
    @GetMapping("/users")
    @CrossOrigin
    public ResponseEntity<List<Customer>> allUsers(){
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }
}
