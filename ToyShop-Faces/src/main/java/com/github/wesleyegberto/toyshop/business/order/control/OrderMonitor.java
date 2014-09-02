package com.github.wesleyegberto.toyshop.business.order.control;

import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;

import com.github.wesleyegberto.toyshop.business.order.entity.Order;

public class OrderMonitor {

	public OrderMonitor() {
	}

	public void onSuccessfulOrder(@Observes(during = TransactionPhase.AFTER_SUCCESS) Order order) {
		System.out.println("Successful order: " + order);
	}

	public void onFailedfulOrder(@Observes(during = TransactionPhase.AFTER_FAILURE) Order order) {
		System.out.println("Failed order: " + order);
	}
}
