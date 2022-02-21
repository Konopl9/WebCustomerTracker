package com.luv2code.springdemo.service;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

  // need to inject customer dao
  @Autowired private CustomerDAO customerDAO;

  @Override
  @Transactional
  public List<Customer> getCustomers(int sortField) {
    return customerDAO.getCustomers(sortField);
  }

  @Override
  @Transactional
  public void saveCustomer(Customer customer) {
    customerDAO.saveCustomer(customer);
  }

  @Override
  @Transactional
  public Customer getCustomer(int id) {
    return customerDAO.getCustomer(id);
  }

  @Override
  @Transactional
  public void deleteCustomer(int id) {
    customerDAO.deleteCustomer(id);
  }

  @Override
  @Transactional
  public List<Customer> searchCustomers(String theSearchName) {
    return customerDAO.searchCustomers(theSearchName);
  }
}
