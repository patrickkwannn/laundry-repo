package com.laundry.app.domain;

import lombok.Getter;
import lombok.Setter;

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
