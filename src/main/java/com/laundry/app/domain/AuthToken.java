package com.laundry.app.domain;

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

    public AuthToken(String token){
        this.token = token;
    }
}
