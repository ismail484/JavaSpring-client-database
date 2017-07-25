package com.mohamedcodes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mohamedcodes.dao.CustomerDAO;
import com.mohamedcodes.entity.Customer;
import com.mohamedcodes.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	//need to inject the customerservice
	@Autowired
	private CustomerService  customerService ;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel){
		
		
		//get customers from the service
		List<Customer>theCustomers=customerService.getCustomers();
		
		//add the customers to the model (name,value)
		theModel.addAttribute("customers", theCustomers);
		
		
		
		//return jsp page
		return "list-customers";
	}
	
	
	@GetMapping("/showFormForAdd")
   public String showFormforAdd(Model theModel){
		
		
		//get customers from the service
		//List<Customer>theCustomers=customerService.getCustomers();
		
		//create model attribute to bind from data
		Customer theCustomer=new Customer();
		//attribute name ,value (theCustomer)
		theModel.addAttribute("customer", theCustomer);
		
			return "customer-form";
	}
	
	
	@PostMapping("/saveCustomer")
	 public String saveCustomer(@ModelAttribute("customer") Customer theCustomer){
	
		//save the customer using our service and pass the customer object
				customerService.saveCustomer(theCustomer);
		
	return "redirect:/customer/list" ; 
	}
	
	@GetMapping("/showFormForUpdate")
	 public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel){
	
		//get the customer from our service
		
		Customer theCustomer=customerService.getCustomer(theId);
		
		//set customer as a model attribute to pre-populate the form
				theModel.addAttribute("customer", theCustomer) ;
		
       //send over to our form
		return "customer-form" ;
		
	 
	}
	
	
	@GetMapping("/delete")
	 public String showFormForUpdate(@RequestParam("customerId") int theId){
	
		//get the customer from our service
		
		Customer theCustomer=customerService.getCustomer(theId);
		
		//delete the customer
		customerService.deleteCustomer(theId);
		
      //send over to our form
		return "redirect:/customer/list" ;
		
	 
	}
	
	
	
	
}
