package com.laundry.app.controller;

import com.laundry.app.domain.ItemsDomain;
import com.laundry.app.entity.Items;
import com.laundry.app.service.ItemsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(
            value = "Api to add new clothing items, only for admins",
            authorizations = {@Authorization(value = HttpHeaders.AUTHORIZATION)},
            response = Items.class)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    @CrossOrigin
    public ResponseEntity<Items> addItems(@RequestBody ItemsDomain itemsDomain){
        return new ResponseEntity<>(itemsService.createItem(itemsDomain), HttpStatus.OK);
    }
}
