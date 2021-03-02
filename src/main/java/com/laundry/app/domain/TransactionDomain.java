package com.laundry.app.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.criterion.Order;

import java.util.Date;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class TransactionDomain {
    private long userId;
    private long transactionTypeId;
    private String deliveryType;
    private Date pickUpDate;
    private String paymentType;
    private String submissionType;
    private String category;
    private Map<String, Integer> items;
}
