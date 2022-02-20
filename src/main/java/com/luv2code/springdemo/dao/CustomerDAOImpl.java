package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.Customer;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

  // need to inject session factory
  @Autowired private SessionFactory sessionFactory;

  @Override
  public List<Customer> getCustomers() {

    // get the current hibernate session
    Session currentSession = sessionFactory.getCurrentSession();

    // create query --- sort by the last name
    Query<Customer> query = currentSession.createQuery("from Customer order by lastName", Customer.class);

    // execute query and get result list
    List<Customer> customers = query.getResultList();

    // return the result
    return customers;
  }

  @Override
  public void saveCustomer(Customer customer) {
    // get the current hibernate session
    Session currentSession = sessionFactory.getCurrentSession();

    // save customer to db
    currentSession.save(customer);
  }
}
