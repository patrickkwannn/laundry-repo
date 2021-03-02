package com.laundry.app.service.impl;

import com.laundry.app.domain.OrdersDomain;
import com.laundry.app.domain.TransactionDomain;
import com.laundry.app.entity.Orders;
import com.laundry.app.entity.Transaction;
import com.laundry.app.repository.OrderRepository;
import com.laundry.app.service.ItemsService;
import com.laundry.app.service.OrderService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ItemsService itemsService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            ItemsService itemsService){
        this.orderRepository = orderRepository;
        this.itemsService = itemsService;
    }

    @Override
    public void createOrder(TransactionDomain domain, Transaction transaction) throws NotFoundException {

        for(Map.Entry<String, Integer> entry : domain.getItems().entrySet()){
            Orders orders = new Orders();
            orders.setItems(itemsService.getById(Long.parseLong(entry.getKey())));
            orders.setQuantity(entry.getValue());
            orders.setTransaction(transaction);
            orderRepository.save(orders);
        }

    }

    @Override
    public Set<Orders> getOrdersByTransactionId(long transactionId) {
        return orderRepository.findAllByTransaction_TransactionId(transactionId);
    }

    @Override
    public OrdersDomain setOrderDomain(Transaction transaction) {
        OrdersDomain ordersDomain = new OrdersDomain();
        Set<Orders> orders = getOrdersByTransactionId(transaction.getTransactionId());
        Map<String, Integer> order = new HashMap<>();

        for(Orders o : orders){
            order.put(o.getItems().getItemName(), o.getQuantity());
        }

        ordersDomain.setOngkir(transaction.getOngkir());
        ordersDomain.setTotalPrice(transaction.getTotalPrice());
        ordersDomain.setOrders(order);
        ordersDomain.setCategory(transaction.getOrderType());
        ordersDomain.setPaymentType(transaction.getPaymentType());
        ordersDomain.setFinishDate(transaction.getFinishDate());
        ordersDomain.setUser(transaction.getCustomer());
        ordersDomain.setTransactionType(transaction.getTransactionType().getTransactionTypeName());
        ordersDomain.setDeliveryType(transaction.getDeliveryType());
        ordersDomain.setSubmissionType(transaction.getSubmissionType());
        ordersDomain.setPickUpDate(transaction.getPickUpDate());

        return ordersDomain;
    }

    @Override
    public List<OrdersDomain> setListOrderDomain(List<Transaction> transactions) {
        List<OrdersDomain> ordersDomains = new ArrayList<>();

        for(Transaction t : transactions){
            OrdersDomain ordersDomain = setOrderDomain(t);
            ordersDomains.add(ordersDomain);
        }

        return ordersDomains;
    }
}
