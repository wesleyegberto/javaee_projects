package com.github.wesleyegberto.toyshop.presentation.admin;

import java.util.Date;
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

	private Date initialDate;
	private Date finalDate;

	public OrderViewerController() {
		System.out.println("[MB] OrderViewerController created");
	}

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

	public List<Order> getOrders() {
		if(orders == null) {
			if(initialDate != null && finalDate != null) {
				orders = orderManager.loadOrders(initialDate, finalDate);
			} else {
				orders = orderManager.loadOrders();
			}
		}
		return orders;
	}

}
