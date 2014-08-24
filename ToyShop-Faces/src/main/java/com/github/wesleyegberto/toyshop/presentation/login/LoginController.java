package com.github.wesleyegberto.toyshop.presentation.login;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.github.wesleyegberto.toyshop.business.customer.entity.Customer;
import com.github.wesleyegberto.toyshop.business.security.control.Authenticator;
import com.github.wesleyegberto.toyshop.presentation.basket.BasketController;
import com.github.wesleyegberto.toyshop.presentation.jsf.FacesUtil;

@Named
@RequestScoped
public class LoginController {
	@Inject
	private Authenticator authenticator;

	@Inject @SessionScoped
	private BasketController basketController;
	
	private Customer customer;
	
	public LoginController() {
		System.out.println("[MB] LoginController created");
	}
	
	public Customer getCustomer() {
		if(customer == null) {
			customer = new Customer();
		}
		return customer;
	}

	public String authenticate() {
		if(authenticator.validateCustomer(customer)) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("customer", customer);
			basketController.setCustomer(customer);
			return "basket?faces-redirect=true";
		}
		FacesUtil.addWarnMessage("Invalid e-mail and/or password.");
		return null;
	}
	
	public String loggout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if(session != null) {
			session.invalidate();
		}
		return "index?faces-redirect=true";
	}
}
