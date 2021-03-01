package com.laundry.app.service;

import com.laundry.app.domain.OrdersDomain;
import com.laundry.app.domain.TransactionDomain;
import com.laundry.app.entity.Orders;
import com.laundry.app.entity.Transaction;
import javassist.NotFoundException;

import java.util.List;
import java.util.Set;

public interface OrderService {
    Set<Orders> createOrder(TransactionDomain domain, Transaction transaction) throws NotFoundException;
    Orders getById(long id) throws NotFoundException;
    Set<Orders> getOrdersByTransactionId(long transactionId);
    OrdersDomain setOrderDomain(Transaction transaction);
}
