package com.laundry.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Patrick Kwan
 * Created on 01/03/2021
 */
@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "items")
public class Items implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private long itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "price_per_kilo")
    private long pricePerKilo;

    @Column(name = "price_per_piece")
    private long pricePerPiece;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "description")
    private String description;
}
