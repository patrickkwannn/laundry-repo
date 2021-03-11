package com.laundry.app.service;

import com.laundry.app.domain.TransactionTypeDomain;
import com.laundry.app.entity.TransactionType;
import javassist.NotFoundException;

import java.util.List;

/**
 * @author Patrick Kwan
 * Created on 01/03/2021
 */
public interface TransactionTypeService {
    TransactionType getById(long id) throws NotFoundException;
    TransactionType createTransactionType(TransactionTypeDomain domain);
    List<TransactionType> getAll();
}
