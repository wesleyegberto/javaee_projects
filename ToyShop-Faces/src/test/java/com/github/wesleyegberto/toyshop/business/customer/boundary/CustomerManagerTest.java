package com.github.wesleyegberto.toyshop.business.customer.boundary;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import com.github.wesleyegberto.toyshop.business.customer.entity.Customer;

public class CustomerManagerTest {

	private CustomerManager customerManager;
	private EntityManager em;
	
	@Before
	public void setUp() throws Exception {
		em = mock(EntityManager.class);
		customerManager = new CustomerManager(em);
	}

	@Test
	public final void testCreateNewCustomer() {
		final String SOME_WEIRD_PASSWORD = "some-weird-password";
		final String SOME_VALID_EMAIL_EMAIL_COM = "some-valid-email@email.com";
		final String WESLEY_EGBERTO = "Wesley Egberto";
		
		final Customer customer = new Customer();
		customer.setName(WESLEY_EGBERTO);
		customer.setEmail(SOME_VALID_EMAIL_EMAIL_COM);
		customer.setPassword(SOME_WEIRD_PASSWORD);
		
		customerManager.createNewCustomer(customer);
		
		verify(em, times(1)).persist(customer);
	}

	@Test
	public final void testGetCustomerById() {
		final long ID = 1249;
		
		customerManager.getCustomerById(ID);
		
		verify(em, times(1)).find(Customer.class, ID);
	}

}
