package com.laundry.app.controller;

import com.laundry.app.domain.OrdersDomain;
import com.laundry.app.domain.TransactionDomain;
import com.laundry.app.entity.Transaction;
import com.laundry.app.service.OrderService;
import com.laundry.app.service.TransactionService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/transaction")
public class TransactionController {

    private final TransactionService transactionService;
    private final OrderService orderService;

    @Autowired
    public TransactionController(TransactionService transactionService,
                                 OrderService orderService){
        this.transactionService= transactionService;
        this.orderService = orderService;
    }

    @PostMapping("/add")
    public ResponseEntity<OrdersDomain> addTransaction(@RequestBody TransactionDomain transactionDomain) throws NotFoundException {
        Transaction newTransaction = transactionService.createTransaction(transactionDomain);

        return new ResponseEntity<>(orderService.setOrderDomain(newTransaction), HttpStatus.OK);
    }

}
