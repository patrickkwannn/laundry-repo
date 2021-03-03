package com.laundry.app.controller;

import com.laundry.app.config.TokenProvider;
import com.laundry.app.domain.AuthToken;
import com.laundry.app.domain.CustomerDomain;
import com.laundry.app.domain.LoginUser;
import com.laundry.app.domain.RoleDomain;
import com.laundry.app.entity.Customer;
import com.laundry.app.entity.Role;
import com.laundry.app.service.CustomerService;
import com.laundry.app.service.RoleService;
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
    private final RoleService roleService;

    @Autowired
    public CustomerController(CustomerService customerService,
                              AuthenticationManager authenticationManager,
                              TokenProvider tokenProvider,
                              RoleService roleService){
        this.customerService = customerService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.roleService = roleService;
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDomain customerDomain){
        return new ResponseEntity<>(customerService.saveCustomer(customerDomain), HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

}
