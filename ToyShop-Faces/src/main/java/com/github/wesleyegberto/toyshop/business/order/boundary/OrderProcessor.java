package com.github.wesleyegberto.toyshop.business.order.boundary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.github.wesleyegberto.toyshop.business.order.control.OrderMailer;
import com.github.wesleyegberto.toyshop.business.order.entity.Order;

@Stateless
public class OrderProcessor {
	@PersistenceContext
	private EntityManager em;

	@Inject
	private OrderMailer mailer;
	
	public OrderProcessor() {
		System.out.println("[EJB] OrderProcessor created");
	}

	/**
	 * Process the customer's orders.
	 * @param order
	 */
	public void processOrder(Order order) {
		em.merge(order);
		mailer.mailOrder(order);
	}
}
