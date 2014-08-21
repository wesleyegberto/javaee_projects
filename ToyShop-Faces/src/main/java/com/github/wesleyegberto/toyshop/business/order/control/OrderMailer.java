package com.github.wesleyegberto.toyshop.business.order.control;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

import com.github.wesleyegberto.toyshop.business.order.entity.Order;

@Stateless
public class OrderMailer {

	public OrderMailer() {
		System.out.println("[EJB] OrderMailer created");
	}

	@Asynchronous
	public void mailOrder(Order order) {
		try {
			// just to simulate
			Thread.sleep(2500);
		} catch(InterruptedException e) {
		}
		System.out.println("This should sent an e-mail to the customer notifying the status");
	}
}
