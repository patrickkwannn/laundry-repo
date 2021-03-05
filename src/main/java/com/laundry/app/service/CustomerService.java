package com.laundry.app.service;

import com.laundry.app.domain.CustomerDomain;
import com.laundry.app.entity.Customer;
import javassist.NotFoundException;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(CustomerDomain newCustomer);
    Customer updateCustomer(CustomerDomain customerDomain);
    Customer getById(long customerId) throws NotFoundException;
    List<Customer> findAll();
    Customer getByUsername(String username) throws NotFoundException;
}
