package com.laundry.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Patrick Kwan
 * Created on 03/03/2021
 */
@Entity
@Setter
@Getter
@Table(name = "settings")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Settings implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "setting_id")
    private long settingId;

    @Column(name = "price_per_kilo")
    private long pricePerKilo;

    @Column(name = "price_per_piece")
    private long pricePerPiece;

    @Column(name = "basic_delivery")
    private long basicDeliveryPrice;

    @Column(name = "max_delievery")
    private long maxDelivery;

    @Column(name = "operational_hours")
    private String operationalHours;

    @ElementCollection
    @Column(name = "payment_options")
    private List<String> paymentOptions = new ArrayList<>();

}
