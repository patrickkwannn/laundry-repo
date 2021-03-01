package com.laundry.app.domain;

import com.laundry.app.entity.Customer;
import com.laundry.app.entity.Orders;
import com.laundry.app.entity.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class OrdersDomain {
    private String category;
    private Map<String, Integer> orders;
    private Long totalPrice;
    private Long ongkir;
    private Customer user;
    private String transactionType;
    private Date finishDate;
    private String deliveryType;
    private String paymentType;
    private String submissionType;
}
