package com.laundry.app.service;

import com.laundry.app.domain.TransactionDomain;
import com.laundry.app.entity.Transaction;
import javassist.NotFoundException;

public interface TransactionService {
    Transaction createTransaction(TransactionDomain transactionDomain) throws NotFoundException;
    Transaction getById(long id) throws NotFoundException;
}
