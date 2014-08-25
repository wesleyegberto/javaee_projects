package com.github.wesleyegberto.toyshop.presentation.admin;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.wesleyegberto.toyshop.business.order.boundary.OrderManager;
import com.github.wesleyegberto.toyshop.business.order.entity.Order;

@Named
@RequestScoped
public class OrderViewerController {
	@Inject
	private OrderManager orderManager;
	
	private List<Order> orders;
	
	public OrderViewerController() {
		System.out.println("[MB] OrderViewerController created");
	}

	public List<Order> getOrders() {
		if(orders == null) {
			orders = orderManager.loadOrders();
		}
		return orders;
	}
	
}
