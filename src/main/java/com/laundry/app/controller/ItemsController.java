package com.laundry.app.controller;

import com.laundry.app.domain.ItemsDomain;
import com.laundry.app.entity.Items;
import com.laundry.app.service.ItemsService;
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
 * Created on 01/03/2021
 */
@RestController
@RequestMapping("/v1/items")
public class ItemsController {

    private final ItemsService itemsService;

    @Autowired
    public ItemsController(ItemsService itemsService){
        this.itemsService = itemsService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Items> addItems(@RequestBody ItemsDomain itemsDomain){
        return new ResponseEntity<>(itemsService.createItem(itemsDomain), HttpStatus.OK);
    }
}
