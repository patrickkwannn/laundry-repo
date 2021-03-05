package com.laundry.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "transaction")
public class Transaction implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private long transactionId;

    @Column(name = "status") //status apakah done atau masih wip
    private String status;

    @Column(name = "progress") //progres laundry (lagi dicuci/dikeringin)
    private String progress;

    @Column(name = "ongkir")
    private long ongkir;

    @Column(name = "total_price")
    private long totalPrice;

    @Column(name = "created_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone="Asia/Jakarta")
    private Date createdDate;

    @Column(name = "finish_date") //kapan bisa diambil
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone="Asia/Jakarta")
    private Date finishDate;

    @ManyToOne
    @JoinColumn(name = "transaction_type_id") //tipe transaksi / tipe paket ( cepat - 3 hari )
    private TransactionType transactionType;

    @Column(name = "delivery_type") //tipe pengambilan barang
    private String deliveryType;

    @Column(name = "submission_type") //tipe penyerahan
    private String submissionType;

    @Column(name = "payment_type") //tipe pembayaran
    private String paymentType;

    @Column(name = "order_type")
    private String orderType; //piece / kilos

    @Column(name = "pick_up_date")
    private Date pickUpDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}
