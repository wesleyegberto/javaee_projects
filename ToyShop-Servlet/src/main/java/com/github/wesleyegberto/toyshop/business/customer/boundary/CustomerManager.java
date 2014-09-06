package com.github.wesleyegberto.toyshop.business.customer.boundary;

import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

import com.github.wesleyegberto.toyshop.business.customer.entity.Customer;

@Stateless
public class CustomerManager {

	@PersistenceContext
	private EntityManager em;

	public CustomerManager() {
		System.out.println("[EJB] CustomerManager created");
	}

	public CustomerManager(EntityManager em) {
		this.em = em;
	}

	public void createNewCustomer(Customer customer) {
		em.persist(customer);
	}

	public Customer getCustomerById(long id) {
		return em.find(Customer.class, id);
	}
}
