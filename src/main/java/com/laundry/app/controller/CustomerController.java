package com.laundry.app.controller;

import com.laundry.app.config.TokenProvider;
import com.laundry.app.domain.AuthToken;
import com.laundry.app.domain.CustomerDomain;
import com.laundry.app.domain.LoginUser;
import com.laundry.app.entity.Customer;
import com.laundry.app.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secured")
public class CustomerController {

    private final AuthenticationManager authenticationManager;
    private final CustomerService customerService;
    private final TokenProvider tokenProvider;
    @Autowired
    public CustomerController(CustomerService customerService,
                              AuthenticationManager authenticationManager,
                              TokenProvider tokenProvider){
        this.customerService = customerService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @ApiOperation(
            value = "Register API",
            response = Customer.class)
    @PostMapping("/register")
    @CrossOrigin
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDomain customerDomain){
        return new ResponseEntity<>(customerService.saveCustomer(customerDomain), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Login API",
            response = AuthToken.class)
    @PostMapping("/authenticate")
    @CrossOrigin
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException, NotFoundException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Customer customer = customerService.getByUsername(loginUser.getUsername());

        final String token = tokenProvider.generateToken(authentication, customer);

        return ResponseEntity.ok(new AuthToken(token));
    }
}
