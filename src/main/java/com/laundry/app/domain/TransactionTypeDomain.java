package com.laundry.app.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Patrick Kwan
 * Created on 01/03/2021
 */
@Getter
@Setter
public class TransactionTypeDomain {
    private String name;
    private long price;
    private String description;
    private int daysNeeded;
    private long transactionTypeId;
}
