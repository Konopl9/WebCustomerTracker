package com.luv2code.springdemo.controller;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customer")
public class CustomerController {

  // need to inject the customer dao
  @Autowired private CustomerService customerService;

  @GetMapping("/list")
  public String listCustomers(Model model) {

    // get customers from the service
    List<Customer> customers = customerService.getCustomers();

    // add the customers to the model
    model.addAttribute("customers", customers);

    return "list-customer";
  }

  @GetMapping("/showFormForAdd")
  public String showFormForAdd(Model model) {

    // create model attribute to bind form data
    Customer customer = new Customer();

    model.addAttribute("customer", customer);

    return "customer-form";
  }

  @PostMapping("/saveCustomer")
  public String saveCustomer(@ModelAttribute("customer") Customer customer) {

    customerService.saveCustomer(customer);

    return "redirect:/customer/list";
  }

  @GetMapping("/showFormForUpdate")
  public String showformForUpdate(@RequestParam("customerId") int id, Model model) {

    // get the customer from the services
    Customer customer = customerService.getCustomer(id);
    // set customer as a model attribute to pre-populate the form
    model.addAttribute("customer", customer);
    // send over to our form
    return "customer-form";
  }

  @GetMapping("/delete")
  public String deleteCustomer(@RequestParam("customerId") int id) {

    // delete customer

    customerService.deleteCustomer(id);

    return "redirect:/customer/list";
  }

  @GetMapping("/search")
  public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
      Model theModel) {
    // search customers from the service
    List<Customer> theCustomers = customerService.searchCustomers(theSearchName);

    // add the customers to the model
    theModel.addAttribute("customers", theCustomers);
    return "list-customer";
  }
}
