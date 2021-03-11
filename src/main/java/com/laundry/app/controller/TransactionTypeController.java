package com.laundry.app.controller;

import com.laundry.app.domain.TransactionTypeDomain;
import com.laundry.app.entity.Transaction;
import com.laundry.app.entity.TransactionType;
import com.laundry.app.service.TransactionTypeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation(
            value = "API to add transactiontype, (REGULER EXPRESS DLL)",
            authorizations = {@Authorization(value = HttpHeaders.AUTHORIZATION)},
            response = TransactionType.class)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    @CrossOrigin
    public ResponseEntity<TransactionType> addTransactionType(@RequestBody TransactionTypeDomain transactionTypeDomain){
        return new ResponseEntity<>(transactionTypeService.createTransactionType(transactionTypeDomain), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Api to get all transaction type)",
            authorizations = {@Authorization(value = HttpHeaders.AUTHORIZATION)},
            response = TransactionType.class)
    @GetMapping("/get-all")
    @CrossOrigin
    public ResponseEntity<List<TransactionType>> getAllTransactionType(){
        return new ResponseEntity<>(transactionTypeService.getAll(), HttpStatus.OK);
    }

}
