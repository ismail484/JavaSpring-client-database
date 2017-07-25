package com.mohamedcodes.dao;

import java.util.List;


import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mohamedcodes.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	
	   //need to inject the session factory (session Factory in config file)
	  @Autowired
	  private SessionFactory sessionFactory ;
	
	
	
	@Override
	public List<Customer> getCustomers() {
		
		//get the current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
	
		//create query
	Query<Customer> theQuery=currentSession.createQuery("from Customer order by lastName" , Customer.class);
		
		
		//execute query and gest result list
	List<Customer> customers=theQuery.getResultList();
	
	
		//return the results
		return customers;
	}



	@Override
	public void saveCustomer(Customer theCustomer) {
		
		//get current hibernate session
		
		Session currentSession=sessionFactory.getCurrentSession();
		
		//save the customers
		currentSession.saveOrUpdate(theCustomer);
	}



	@Override
	public Customer getCustomer(int theId) {
		
		//get the current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		// then retrieve/read from database using the primary key id
		Customer theCustomer=currentSession.get(Customer.class, theId);
		return theCustomer ;
		
		
	}



	@Override
	public void deleteCustomer(int theId) {
		
		//get the hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		//delete the object with primary id
		Query theQuery=currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
		
	}

}
