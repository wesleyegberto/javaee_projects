package com.github.wesleyegberto.toyshop.presentation.basket;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.github.wesleyegberto.toyshop.business.catalog.entity.Product;
import com.github.wesleyegberto.toyshop.business.customer.entity.Customer;

@Named
@SessionScoped
public class BasketController implements Serializable {
	private static final long serialVersionUID = 6891988745873240820L;
	
	private Customer customer;
	private List<Product> products = new LinkedList<Product>();

	public BasketController() {
		System.out.println("[MB] BasketController created");
	}

	public Customer getCustomer() {
		if(customer == null) {
			customer = new Customer();
		}
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public boolean isLogged() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		return session != null && session.getAttribute("customer") != null;
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void addProduct(Product prod) {
		this.products.add(prod);
	}

	public void removeProduct(Product prod) {
		this.products.remove(prod);
	}

	public double getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for(Product p : products) {
			total = total.add(new BigDecimal(p.getPrice()));
		}
		return total.doubleValue();
	}

	public boolean isAllowedCheckout() {
		return products.size() > 0;
	}
	
	@PreDestroy
	public void clear() {
		System.out.println("[MB] BasketControlled destroyed");
		products.clear();
	}

	public String startCheckout() {
		if(isLogged()) {
			return "checkout/shipping?faces-redirect=true"; 
		}
		return "checkout/identification?faces-redirect=true";
	}
	
}
