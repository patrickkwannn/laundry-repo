package com.laundry.app.controller;

import com.laundry.app.domain.TransactionTypeDomain;
import com.laundry.app.entity.TransactionType;
import com.laundry.app.service.TransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Patrick Kwan
 * Created on 01/03/2021
 */
@RestController
@RequestMapping("/v1/transaction-type")
public class TransactionTypeController {

    private final TransactionTypeService transactionTypeService;

    @Autowired
    public TransactionTypeController(TransactionTypeService transactionTypeService){
        this.transactionTypeService = transactionTypeService;
    }

    @PostMapping("/add")
    public ResponseEntity<TransactionType> addTransactionType(@RequestBody TransactionTypeDomain transactionTypeDomain){
        return new ResponseEntity<>(transactionTypeService.createTransactionType(transactionTypeDomain), HttpStatus.OK);
    }

}
