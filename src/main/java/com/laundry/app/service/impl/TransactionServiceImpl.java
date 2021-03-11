package com.laundry.app.service.impl;

import com.laundry.app.domain.TransactionDomain;
import com.laundry.app.en.Const;
import com.laundry.app.entity.*;
import com.laundry.app.repository.TransactionRepository;
import com.laundry.app.service.*;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private final OrderService orderService;
    private final CustomerService customerService;
    private final TransactionTypeService transactionTypeService;
    private final SettingService settingService;

    public TransactionServiceImpl(TransactionRepository repository,
                                  OrderService orderService,
                                  CustomerService customerService,
                                  TransactionTypeService transactionTypeService,
                                  SettingService settingService){
        this.repository = repository;
        this.orderService = orderService;
        this.customerService = customerService;
        this.transactionTypeService = transactionTypeService;
        this.settingService = settingService;
    }

    @Override
    public Transaction createTransaction(TransactionDomain transactionDomain) throws NotFoundException {

        Customer customer = customerService.getById(transactionDomain.getUserId());
        TransactionType transactionType = transactionTypeService.getById(transactionDomain.getTransactionTypeId());
        Settings settings = settingService.getSettings();

        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionType);
        transaction.setCreatedDate(new Date());
        transaction.setCustomer(customer);
        transaction.setFinishDate(
                Date.from(LocalDate.now()
                .plusDays(transactionType.getDaysNeeded())
                .atStartOfDay().toInstant(ZoneOffset.MIN)));
        transaction.setProgress(Const.STARTING);
        transaction.setStatus(Const.WIP);
        transaction.setDeliveryType(transactionDomain.getDeliveryType());
        transaction.setSubmissionType(transactionDomain.getSubmissionType());
        transaction.setOngkir(Const.ZERO);
        transaction.setOrderType(transactionDomain.getCategory());
        transaction.setPickUpDate(transactionDomain.getPickUpDate());
        transaction.setAddress(transactionDomain.getAddress());

        if(transaction.getDeliveryType().equals(Const.DELIVERY_DIANTAR)){
            transaction.setOngkir(transaction.getOngkir() + settings.getBasicDeliveryPrice());
        }

        if(transaction.getSubmissionType().equals(Const.DEPOSIT_DIJEMPUT)){
            transaction.setOngkir(transaction.getOngkir() + settings.getBasicDeliveryPrice());
        }

        transaction.setPaymentType(transactionDomain.getPaymentType());

        Transaction t = repository.save(transaction);

        //Create Order / transaction details
        orderService.createOrder(transactionDomain, t);

        //Calculate Price
        transaction.setTotalPrice(Const.ZERO + calculatePrice(t));
        return repository.save(t);
    }

    @Override
    public Transaction getById(long id) throws NotFoundException {
        if(!repository.existsById(id)){
            throw new NotFoundException("Transaction with id " + id + " is not found");
        }
        return repository.getOne(id);
    }

    @Override
    public List<Transaction> getByCustomerId(long customerId) {
        return repository.findAllByCustomer_CustomerIdOrderByCreatedDateDesc(customerId);
    }

    @Override
    public Transaction updateProgress(long id) throws NotFoundException {
        Transaction transaction = getById(id);

        switch (transaction.getProgress()){
            case Const.STARTING:
                transaction.setProgress(Const.STARTED);
                break;
            case Const.STARTED:
                transaction.setProgress(Const.FINISHED_IRONING);
                break;
            case Const.FINISHED_IRONING:
                transaction.setProgress(Const.FINISHED_WASHING);
                transaction.setStatus(Const.DONE);
                break;
        }

        return repository.save(transaction);
    }

    @Override
    public List<Transaction> getOngoingTransaction(long customerId) {
        return repository.findAllByCustomer_CustomerIdAndStatusOrderByCreatedDateDesc(customerId, Const.WIP);
    }

    private Long calculatePrice(Transaction transaction) {
        long price = 0L;
        Set<Orders> orders = orderService.getOrdersByTransactionId(transaction.getTransactionId());

        //Tambah biaya paket
        price = price + transaction.getTransactionType().getPrice();

        //Biaya basic per baju
        for(Orders o : orders){
            if(transaction.getOrderType().equals(Const.PER_KILO)){
                price = price + (o.getItems().getPricePerKilo() * o.getQuantity());
            } else {
                price = price + (o.getItems().getPricePerPiece() * o.getQuantity());
            }
        }

        //Biaya ongkir
        if(transaction.getOngkir() > 0) {
            price = price + transaction.getOngkir();
        }

        return price;
    }
}
