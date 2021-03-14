package com.laundry.app.service.impl;

import com.laundry.app.domain.TransactionTypeDomain;
import com.laundry.app.en.Const;
import com.laundry.app.entity.TransactionType;
import com.laundry.app.repository.TransactionTypeRepository;
import com.laundry.app.service.TransactionTypeService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Patrick Kwan
 * Created on 01/03/2021
 */
@Service
public class TransactionTypeServiceImpl implements TransactionTypeService {

    @Value("${laundry.transaction.type}")
    private String[] transactionTypes;

    @Value("${laundry.paket.cepat}")
    private long paket_cepat;

    @Value("${laundry.paket.regular}")
    private long paket_regular;

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

    @Override
    public List<TransactionType> getAll() {
        return transactionTypeRepository.findAll();
    }

    @Override
    public TransactionType update(TransactionTypeDomain domain) {
        TransactionType transactionType = transactionTypeRepository.getOne(domain.getTransactionTypeId());
        transactionType.setPrice(domain.getPrice());
        transactionType.setDaysNeeded(domain.getDaysNeeded());
        transactionType.setTransactionTypeName(domain.getName());
        transactionType.setDescription(domain.getDescription());

        return transactionTypeRepository.save(transactionType);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeTransactionType(){
        if(transactionTypeRepository.findAll().isEmpty()){
            for(String type : transactionTypes){
                switch(type){
                    case Const.REGULAR : {
                        TransactionType transactionType = new TransactionType();
                        transactionType.setPrice(paket_regular);
                        transactionType.setDaysNeeded(3);
                        transactionType.setCreatedDate(new Date());
                        transactionType.setDescription(Const.REGULAR);
                        transactionType.setTransactionTypeName(Const.REGULAR);
                        transactionTypeRepository.save(transactionType);
                        break;
                    }
                    case Const.CEPAT : {
                        TransactionType transactionType = new TransactionType();
                        transactionType.setPrice(paket_cepat);
                        transactionType.setDaysNeeded(1);
                        transactionType.setCreatedDate(new Date());
                        transactionType.setDescription(Const.CEPAT);
                        transactionType.setTransactionTypeName(Const.CEPAT);
                        transactionTypeRepository.save(transactionType);
                        break;
                    }
                }
            }
        }
    }

}
