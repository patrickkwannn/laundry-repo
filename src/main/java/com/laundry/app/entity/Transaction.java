package com.laundry.app.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "transaction")
public class Transaction implements Serializable {
    @Id
    @Column(name = "transaction_id")
    private long transactionId;

    @Column(name = "status") //status apakah done atau masih wip
    private String status;

    @Column(name = "progress") //progres laundry (lagi dicuci/dikeringin)
    private String progress;

    @Column(name = "total_price")
    private long totalPrice;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "finish_date") //kapan bisa diambil
    private Date finishDate;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "delivery_type") //tipe pengambilan barang
    private String deliveryType;

    @Column(name = "submission_type") //tipe penyerahan
    private String submissionType;

    @Column(name = "payment_type") //tipe pembayaran
    private String paymentType;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}
