package com.github.wesleyegberto.toyshop.business.order.boundary;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import com.github.wesleyegberto.toyshop.business.order.control.OrderMailer;
import com.github.wesleyegberto.toyshop.business.order.entity.Order;

@SuppressWarnings("unchecked")
public class OrderProcessorTest {
	private EntityManager em;
	private OrderMailer mailer;
	private Event<Order> event;
	private OrderProcessor orderProcessor;

	@Before
	public void setUp() throws Exception {
		em = mock(EntityManager.class);
		mailer = mock(OrderMailer.class);
		event = mock(Event.class);
		orderProcessor = new OrderProcessor(em, mailer, event);
	}

	@Test
	public final void testProcessOrder() {
		Order order = mock(Order.class);
		orderProcessor.processOrder(order);
		
		verify(event, times(1)).fire(order);
		verify(em, times(1)).merge(order);
		verify(mailer, times(1)).mailOrder(order);
	}

}
