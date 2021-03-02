package com.laundry.app.service;

import com.laundry.app.domain.TransactionDomain;
import com.laundry.app.entity.Transaction;
import javassist.NotFoundException;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(TransactionDomain transactionDomain) throws NotFoundException;
    Transaction getById(long id) throws NotFoundException;
    List<Transaction> getByCustomerId(long customerId);
    Transaction updateProgress(long id) throws NotFoundException;
    List<Transaction> getOngoingTransaction(long customerId);
}
