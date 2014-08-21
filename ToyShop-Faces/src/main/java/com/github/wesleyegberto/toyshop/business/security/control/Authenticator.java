package com.github.wesleyegberto.toyshop.business.security.control;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.github.wesleyegberto.toyshop.business.customer.entity.Customer;

@Stateless
public class Authenticator {
	@PersistenceContext
	private EntityManager em;

	public Authenticator() {
		System.out.println("[EJB] Authenticator created");
	}

	public boolean validateCustomer(Customer customer) {
		TypedQuery<Customer> query = em.createNamedQuery(Customer.GET_BY_IDENTIFY, Customer.class);
		query.setParameter(1, customer.getEmail());
		query.setParameter(2, customer.getPassword());
		
		Customer result = query.getSingleResult();
		if(result != null) {
			customer.setId(result.getId());
			customer.setName(result.getName());
			customer.setAddress(result.getAddress());
			customer.setCity(result.getCity());
			customer.setZipCode(result.getZipCode());
			em.detach(result);
			return true;
		}
		return false;
	}
}
