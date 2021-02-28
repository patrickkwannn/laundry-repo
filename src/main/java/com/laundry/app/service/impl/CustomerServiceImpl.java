package com.laundry.app.service.impl;

import com.laundry.app.domain.CustomerDomain;
import com.laundry.app.entity.Customer;
import com.laundry.app.repository.CustomerRepository;
import com.laundry.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(CustomerDomain newCustomer) {
        Customer customer = new Customer();
        customer.setAddress(newCustomer.getAddress());
        customer.setEmail(newCustomer.getEmail());
        customer.setDob(newCustomer.getDob());
        customer.setFullName(newCustomer.getFullName());
        customer.setGender(newCustomer.getGender());
        customer.setPassword(passwordEncoder.encode(newCustomer.getPassword()));
        customer.setPhoneNumber(newCustomer.getPhoneNumber());
        customer.setUsername(newCustomer.getUsername());
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(CustomerDomain customerDomain) {
        return null;
    }

    @Override
    public Customer getById(long customerId) {
        return customerRepository.getOne(customerId);
    }

    @Override
    public Customer getByUsername(String username) {
        return customerRepository.findByUsername(username);
    }
}
