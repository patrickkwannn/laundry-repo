package com.laundry.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Setter
@Getter
public class Order implements Serializable {

    @Id
    @Column("order_id")
    private long orderId;

    @Column("kaos")
    private int kaos;

    @Column("kemeja")
    private int kemeja;

    @Column("jeans")
    private int jeans;

    @Column("cleana_panjang")
    private int celanaPanjang;

    @Column("celana_pendek")
    private int celanaPendek;

    @Column("sepatu")
    private int sepatu;

    @Column("kaos_kaki")
    private int kaosKaki;

    @Column("tas")
    private int tas;

    @Column("jaket")
    private int jaket;

    @Column("bed_cover")
    private int bedCover;

    @Column("selimut")
    private int selimut;

    @Column("handuk")
    private int handuk;

    @Column("sweater")
    private int sweater;

    @Column("order_type")
    private String orderType; //piece / kilos

}
