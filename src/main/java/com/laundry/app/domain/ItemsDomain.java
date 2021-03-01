package com.laundry.app.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Patrick Kwan
 * Created on 01/03/2021
 */
@Setter
@Getter
public class ItemsDomain {
    private String name;
    private long pricePerKilo;
    private long pricePerPiece;
    private String description;
}
