package com.github.wesleyegberto.toyshop.business.order.boundary;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.github.wesleyegberto.toyshop.business.order.control.OrderMailer;
import com.github.wesleyegberto.toyshop.business.order.entity.Order;
import com.github.wesleyegberto.toyshop.business.order.entity.OrderStatus;

@Stateless
public class OrderManager {

	@PersistenceContext
	private EntityManager em;
	@Inject
	private OrderMailer mailer;
	
	public OrderManager() {
		System.out.println("[EJB] OrderManager created");
	}
	
	public OrderManager(EntityManager em, OrderMailer mailer) {
		this.em = em;
		this.mailer = mailer;
	}

	public Order getOrderById(long id) {
		return em.find(Order.class, id);
	}

	public List<Order> loadOrders() {
		TypedQuery<Order> query = em.createNamedQuery(Order.GET_ALL, Order.class);
		return query.getResultList();
	}
	
	/**
	 * Finish the given order and update the data.
	 * @param order
	 */
	public void finishOrder(Order order) {
		order.setStatus(OrderStatus.FINISHED);
		em.merge(order);
		mailer.mailOrder(order);
	}
}
