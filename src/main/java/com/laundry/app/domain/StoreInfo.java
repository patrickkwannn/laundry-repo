package com.laundry.app.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * @author Patrick Kwan
 * Created on 02/03/2021
 */
@Getter
@Setter
public class StoreInfo {
    private String operationalHours;
    private List<String> paymentMethod;
    private long pricePerKilo;
    private long pricePerPiece;
    private long basicDeliveryPrice;
    private long maxDelivery;
}
