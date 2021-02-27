package com.laundry.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
public class Transaction implements Serializable {
    @Id
    @Column("transaction_id")
    private long transactionId;

    @Column("status") //status apakah done atau masih wip
    private String status;

    @Column("progress") //progres laundry (lagi dicuci/dikeringin)
    private String progress;

    @Column("total_price")
    private long totalPrice;

    @Column("created_date")
    private Date createdDate;

    @Column("finish_date") //kapan bisa diambil
    private Date finishDate;

    @Column("transaction_type")
    private String transactionType;

    @Column("delivery_type") //tipe pengambilan barang
    private String deliveryType;

    @Column("submission_type") //tipe penyerahan
    private String submissionType;

    @Column("payment_type") //tipe pembayaran
    private String paymentType;
}
