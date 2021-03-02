package com.laundry.app.controller;

import com.laundry.app.domain.StoreDomain;
import com.laundry.app.domain.StoreInfo;
import com.laundry.app.entity.Store;
import com.laundry.app.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/info")
    public ResponseEntity<StoreInfo> storeInfo(){
        return new ResponseEntity<>(storeService.createStoreInfo(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Store> addStore(@RequestBody StoreDomain storeDomain){
        return new ResponseEntity<>(storeService.addStore(storeDomain), HttpStatus.OK);
    }

}
