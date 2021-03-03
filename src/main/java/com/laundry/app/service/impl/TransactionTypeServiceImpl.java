package com.laundry.app.service.impl;

import com.laundry.app.domain.TransactionTypeDomain;
import com.laundry.app.entity.TransactionType;
import com.laundry.app.repository.TransactionTypeRepository;
import com.laundry.app.service.TransactionTypeService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Patrick Kwan
 * Created on 01/03/2021
 */
@Service
public class TransactionTypeServiceImpl implements TransactionTypeService {

    private final TransactionTypeRepository transactionTypeRepository;

    @Autowired
    public TransactionTypeServiceImpl(TransactionTypeRepository transactionTypeRepository){
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @Override
    public TransactionType getById(long id) throws NotFoundException {
        if(!transactionTypeRepository.existsById(id)){
            throw new NotFoundException("Transaction type for id : " + id + " is not found");
        }
        return transactionTypeRepository.getOne(id);
    }

    @Override
    public TransactionType createTransactionType(TransactionTypeDomain domain) {
        TransactionType transactionType = new TransactionType();
        transactionType.setTransactionTypeName(domain.getName());
        transactionType.setDescription(domain.getDescription());
        transactionType.setPrice(domain.getPrice());
        transactionType.setCreatedDate(new Date());
        transactionType.setDaysNeeded(domain.getDaysNeeded());

        return transactionTypeRepository.save(transactionType);
    }


}
