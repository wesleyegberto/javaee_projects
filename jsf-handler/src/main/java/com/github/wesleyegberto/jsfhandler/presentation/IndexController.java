package com.github.wesleyegberto.jsfhandler.presentation;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class IndexController {
	
	public void lancarNFE() {
		System.out.println("IndexController.lancarNFE");
		throw new NumberFormatException();
	}

	public void lancarNPE() {
		System.out.println("IndexController.lancarNPE");
		throw new NullPointerException();
	}
	
}
