package com.laundry.app.controller;

import com.laundry.app.domain.StoreDomain;
import com.laundry.app.domain.StoreInfo;
import com.laundry.app.entity.Store;
import com.laundry.app.service.StoreService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Patrick Kwan
 * Created on 02/03/2021
 */
@RestController
@RequestMapping("/v1/store")
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService){
        this.storeService = storeService;
    }

    @ApiOperation(
            value = "Api to get store info",
            authorizations = {@Authorization(value = HttpHeaders.AUTHORIZATION)},
            response = StoreInfo.class)
    @GetMapping("/info-settings")
    @CrossOrigin
    public ResponseEntity<StoreInfo> storeInfo() throws NotFoundException {
        return new ResponseEntity<>(storeService.createStoreInfo(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Api to add store, only for admins",
            authorizations = {@Authorization(value = HttpHeaders.AUTHORIZATION)},
            response = Store.class)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    @CrossOrigin
    public ResponseEntity<Store> addStore(@RequestBody StoreDomain storeDomain){
        return new ResponseEntity<>(storeService.addStore(storeDomain), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Api to get all store",
            authorizations = {@Authorization(value = HttpHeaders.AUTHORIZATION)},
            response = Store.class,
            responseContainer = "List")
    @GetMapping("/list")
    @CrossOrigin
    public ResponseEntity<List<Store>> getAllStore(){
        return new ResponseEntity<>(storeService.getAllStore(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Api to get store by id",
            authorizations = {@Authorization(value = HttpHeaders.AUTHORIZATION)},
            response = Store.class)
    @GetMapping("/get-by-id")
    @CrossOrigin
    public ResponseEntity<Store> getStoreById(@RequestParam Long id) throws NotFoundException {
        return new ResponseEntity<>(storeService.getStoreById(id), HttpStatus.OK);
    }

}
