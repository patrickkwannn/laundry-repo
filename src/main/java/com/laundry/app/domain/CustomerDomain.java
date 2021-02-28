package com.laundry.app.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CustomerDomain {
    private String address;
    private Date dob;
    private String gender;
    private String email;
    private String password;
    private String phoneNumber;
    private String username;
    private String fullName;
}
