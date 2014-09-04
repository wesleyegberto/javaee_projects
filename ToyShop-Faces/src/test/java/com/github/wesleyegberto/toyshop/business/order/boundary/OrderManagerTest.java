package com.github.wesleyegberto.toyshop.business.order.boundary;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.wesleyegberto.toyshop.business.order.control.OrderMailer;
import com.github.wesleyegberto.toyshop.business.order.entity.Order;
import com.github.wesleyegberto.toyshop.business.order.entity.OrderStatus;

@SuppressWarnings("unchecked")
public class OrderManagerTest {
	
	private EntityManager em;
	private OrderMailer mailer;
	private OrderManager orderManager;

	@Before
	public void setUp() throws Exception {
		em = mock(EntityManager.class);
		mailer = mock(OrderMailer.class);
		orderManager = new OrderManager(em, mailer);
	}

	@Test
	public void testGetOrderById() {
		final long ID = 291;
		orderManager.getOrderById(ID);
		
		verify(em, times(1)).find(Order.class, ID);
	}
	
	@Test
	public void testLoadOrders() {
		TypedQuery<Order> query = mock(TypedQuery.class);
		when(em.createNamedQuery(Order.GET_ALL, Order.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(new ArrayList<Order>());
		
		List<Order> orders = orderManager.loadOrders();
		Assert.assertNotNull(orders);
		Assert.assertTrue(orders.isEmpty());
		verify(query, times(1)).getResultList();
	}

	@Test
	public void testFinishOrder() {
		Order order = mock(Order.class);
		orderManager.finishOrder(order);
		
		verify(order, times(1)).setStatus(OrderStatus.FINISHED);
		verify(em, times(1)).merge(order);
		verify(mailer, times(1)).mailOrder(order);
	}
}
