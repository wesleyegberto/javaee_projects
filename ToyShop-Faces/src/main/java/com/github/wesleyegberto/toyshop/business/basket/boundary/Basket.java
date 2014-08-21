package com.github.wesleyegberto.toyshop.business.basket.boundary;

import javax.ejb.Stateful;

@Stateful
public class Basket {
	public Basket() {
		System.out.println("[EJB] Basket created");
	}
}
