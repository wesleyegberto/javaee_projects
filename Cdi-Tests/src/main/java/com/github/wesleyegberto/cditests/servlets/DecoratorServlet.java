package com.github.wesleyegberto.cditests.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.wesleyegberto.cditests.calculator.Calculator;
import com.github.wesleyegberto.cditests.qualifiers.Low;

@WebServlet("/DecoratorServlet")
public class DecoratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject @Low
	Calculator calculator;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("Price with decorated tax: " + calculator.applyTax(101.00));
	}

}
