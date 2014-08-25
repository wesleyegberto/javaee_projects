package com.github.wesleyegberto.toyshop.presentation.admin;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.wesleyegberto.toyshop.business.order.boundary.OrderManager;
import com.github.wesleyegberto.toyshop.business.order.entity.Order;
import com.github.wesleyegberto.toyshop.presentation.jsf.FacesUtil;

@Named
@RequestScoped
public class OrderEditorController {

	@Inject
	private OrderManager orderManager;

	private long orderId;
	private Order order;

	public OrderEditorController() {
		System.out.println("[MB] OrderEditorController created");
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Order getOrder() {
		return order;
	}

	public void loadOrder() {
		order = orderManager.getOrderById(orderId);
		if(order == null) {
			order = new Order();
			FacesUtil.addWarnMessage("Order not found.");
		}
	}
	
	public void finishOrder() {
		loadOrder();
		if(order != null) {
			orderManager.finishOrder(order);
		}
	}
}
