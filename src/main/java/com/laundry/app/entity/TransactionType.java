package com.laundry.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "transaction_type")
public class TransactionType implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_type_id")
    private long transactionTypeId;

    @Column(name = "transaction_type_name")
    private String transactionTypeName;

    @Column(name = "description")
    private String description;

    @Column(name = "created_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone="Asia/Jakarta")
    private Date createdDate;

    @Column(name = "base_price")
    private long price;

    @Column(name = "days_needed")
    private int daysNeeded;
}
