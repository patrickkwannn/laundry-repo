package com.laundry.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Setter
@Getter
@Table(name = "orders")
public class Orders implements Serializable {

    @Id
    @Column(name = "order_id")
    private long orderId;

    @Column(name = "kaos")
    private int kaos;

    @Column(name = "kemeja")
    private int kemeja;

    @Column(name = "jeans")
    private int jeans;

    @Column(name = "cleana_panjang")
    private int celanaPanjang;

    @Column(name = "celana_pendek")
    private int celanaPendek;

    @Column(name = "sepatu")
    private int sepatu;

    @Column(name = "kaos_kaki")
    private int kaosKaki;

    @Column(name = "tas")
    private int tas;

    @Column(name = "jaket")
    private int jaket;

    @Column(name = "bed_cover")
    private int bedCover;

    @Column(name = "selimut")
    private int selimut;

    @Column(name = "handuk")
    private int handuk;

    @Column(name = "sweater")
    private int sweater;

    @Column(name = "order_type")
    private String orderType; //piece / kilos

}
