package com.laundry.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Patrick Kwan
 * Created on 02/03/2021
 */
@Entity
@Setter
@Getter
@Table(name = "store")
public class Store implements Serializable {
    //TODO
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private long storeId;

    @Column(name = "store_name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;
}
