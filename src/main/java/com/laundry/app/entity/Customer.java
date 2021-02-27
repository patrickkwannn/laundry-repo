package com.laundry.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
public class Customer implements Serializable {
    @Id
    @Column("customer_id")
    private long customerId;

    @Column("real_name")
    private String fullName;

    @Column("username")
    private String username;

    @Column("gender")
    private String gender;

    @Column("email")
    @Email
    private String email;

    @Column("address")
    private String address;

    @Column("dob")
    private Date dob;

    @Column("password")
    @JsonIgnore
    private String password;

    @Column("phone_number")
    private String phoneNumber;

    @Column("created_date")
    private Date createdDate;
}
