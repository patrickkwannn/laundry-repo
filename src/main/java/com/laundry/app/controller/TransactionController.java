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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/history")
    public ResponseEntity<List<OrdersDomain>> getHistory(@RequestParam long customerId){
        List<Transaction> transactions = transactionService.getByCustomerId(customerId);
        return new ResponseEntity<>(orderService.setListOrderDomain(transactions), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/update-status")
    public ResponseEntity<OrdersDomain> updateStatusTransaction(@RequestParam long transactionId) throws NotFoundException {
        Transaction transaction = transactionService.updateProgress(transactionId);
        return new ResponseEntity<>(orderService.setOrderDomain(transaction), HttpStatus.OK);
    }

    @GetMapping("/ongoing")
    public ResponseEntity<List<OrdersDomain>> getOngoing(@RequestParam long customerId){
        List<Transaction> transactions = transactionService.getOngoingTransaction(customerId);
        return new ResponseEntity<>(orderService.setListOrderDomain(transactions), HttpStatus.OK);
    }
}
