package com.github.wesleyegberto.toyshop.presentation.checkout;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.github.wesleyegberto.toyshop.business.customer.boundary.CustomerManager;
import com.github.wesleyegberto.toyshop.business.customer.entity.Customer;
import com.github.wesleyegberto.toyshop.business.order.boundary.OrderProcessor;
import com.github.wesleyegberto.toyshop.business.order.entity.Order;
import com.github.wesleyegberto.toyshop.business.security.control.Authenticator;
import com.github.wesleyegberto.toyshop.presentation.basket.BasketController;
import com.github.wesleyegberto.toyshop.presentation.jsf.FacesUtil;

import java.io.Serializable;

@Named
@ConversationScoped
public class CheckoutController implements Serializable {
	private static final long serialVersionUID = -618040998720791064L;

	@Inject @SessionScoped
	private BasketController basketController;
	@Inject
	private Conversation conversation;
	
	// I injected all EJBs here because I'm using only one controller to three pages
	@Inject
	private Authenticator authenticator;
	@Inject
	private CustomerManager customerManager;
	@Inject
	private OrderProcessor orderProcessor;

	private Order order;

	public CheckoutController() {
		System.out.println("[MB] CheckoutController created");
	}

	public Customer getCustomer() {
		return basketController.getCustomer();
	}
	
	
	public Order getOrder() {
		return order;
	}

	public boolean isOrderProcessed() {
		return order != null && order.getId() > 0;
	}
	
	public String authenticate() {
		// start the conversation
		if(conversation.isTransient()) {
			conversation.begin();
		}
		if(authenticator.validateCustomer(basketController.getCustomer())) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("customer", basketController.getCustomer());
			return "shipping?faces-redirect=true";
		}
		FacesUtil.addWarnMessage("Invalid e-mail and/or password.");
		return null;
	}

	public String signUp() {
		// start the conversation
		if(conversation.isTransient()) {
			conversation.begin();
		}
		customerManager.createNewCustomer(basketController.getCustomer());
		if(authenticator.validateCustomer(basketController.getCustomer())) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("customer", basketController.getCustomer());
			return "shipping?faces-redirect=true";
		}
		return null;
	}
	
	public String proceedConfirmation() {
		return "confirmation?faces-redirect=true";
	}
	
	public String finishOrder() {
		
		try {
			order = new Order();
			order.setCustomer(basketController.getCustomer());
			order.setProducts(basketController.getProducts());
			order.setAddress(basketController.getCustomer().getAddress());
			order.setCity(basketController.getCustomer().getCity());
			order.setZipCode(basketController.getCustomer().getCity());
			order.setTotal(basketController.getTotal());
			orderProcessor.processOrder(order);
			// ends the conversation
			if(!conversation.isTransient()) {
				conversation.end();
			}
			basketController.clear();
			return "processed?faces-redirect=true";
		} catch(Exception ex) {
			FacesUtil.addErrorMessage("Erro at processing the order.");
		}
		
		return null;
	}
	
}
