package com.laundry.app.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Patrick Kwan
 * Created on 02/03/2021
 */
@Setter
@Getter
public class StoreDomain {
    private String name;
    private String address;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String state;
}
