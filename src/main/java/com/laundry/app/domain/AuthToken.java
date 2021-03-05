package com.laundry.app.domain;

import com.laundry.app.entity.Customer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Patrick Kwan
 * Created on 01/03/2021
 */
@Getter
@Setter
public class AuthToken {
    private String token;
    private Customer customer;
    public AuthToken(Customer customer, String token){
        this.customer = customer;
        this.token = token;
    }
}
