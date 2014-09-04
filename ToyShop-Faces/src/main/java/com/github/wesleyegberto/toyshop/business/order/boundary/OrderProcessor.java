package com.github.wesleyegberto.toyshop.business.order.boundary;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
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

	@Inject
	private Event<Order> events;

	public OrderProcessor() {
		System.out.println("[EJB] OrderProcessor created");
	}

	public OrderProcessor(EntityManager em, OrderMailer mailer, Event<Order> events) {
		this.em = em;
		this.mailer = mailer;
		this.events = events;
	}

	/**
	 * Process the customer's orders.
	 * @param order
	 */
	public void processOrder(Order order) {
		events.fire(order);
		em.merge(order);
		mailer.mailOrder(order);
	}
}
