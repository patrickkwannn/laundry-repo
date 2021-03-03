package com.laundry.app.service.impl;

import com.laundry.app.domain.CustomerDomain;
import com.laundry.app.en.Const;
import com.laundry.app.entity.Customer;
import com.laundry.app.entity.Role;
import com.laundry.app.repository.CustomerRepository;
import com.laundry.app.service.CustomerService;
import com.laundry.app.service.RoleService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(value = "customerService")
public class CustomerServiceImpl implements UserDetailsService, CustomerService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;
    private final RoleService roleService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               RoleService roleService,
                               BCryptPasswordEncoder bCryptPasswordEncoder){
        this.passwordEncoder = bCryptPasswordEncoder;
        this.customerRepository = customerRepository;
        this.roleService = roleService;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer user = customerRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(Customer user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        return authorities;
    }

    @Override
    public Customer saveCustomer(CustomerDomain newCustomer) {
        Customer customer = new Customer();
        validateUser(newCustomer.getEmail(), newCustomer.getUsername());
        customer.setAddress(newCustomer.getAddress());
        customer.setEmail(newCustomer.getEmail());
        customer.setDob(newCustomer.getDob());
        customer.setFullName(newCustomer.getFullName());
        customer.setGender(newCustomer.getGender());
        customer.setPassword(passwordEncoder.encode(newCustomer.getPassword()));
        customer.setPhoneNumber(newCustomer.getPhoneNumber());
        customer.setUsername(newCustomer.getUsername());
        customer.setCreatedDate(new Date());

        Role role = roleService.getByRoleName(Const.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        customer.setRoles(roles);

        if(customer.getUsername().equalsIgnoreCase(Const.ADMIN)){
            Role rolex = roleService.getByRoleName(Const.ADMIN);
            roles.add(rolex);
            customer.setRoles(roles);
        }

        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(CustomerDomain customerDomain) {
        return null;
    }

    @Override
    public Customer getById(long customerId) throws NotFoundException {
        if(!customerRepository.existsById(customerId)){
            throw new NotFoundException("Customer with id " + customerId + " is not found");
        }
        return customerRepository.getOne(customerId);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    private void validateUser(String email, String username){
        if(customerRepository.existsByEmail(email)){
            throw new IllegalArgumentException("User already exists for email : " + email);
        }

        if(customerRepository.existsByUsername(username)){
            throw new IllegalArgumentException("User already exists for username : " + username);
        }
    }
}
