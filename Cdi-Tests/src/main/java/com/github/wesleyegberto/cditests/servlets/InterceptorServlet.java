package com.github.wesleyegberto.cditests.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.wesleyegberto.cditests.calculator.Calculator;
import com.github.wesleyegberto.cditests.qualifiers.TypeCalculator;
import com.github.wesleyegberto.cditests.qualifiers.TypeCalculatorEnum;

@WebServlet("/InterceptorServlet")
public class InterceptorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject @TypeCalculator(type = TypeCalculatorEnum.HIGH)
	Calculator calculator;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("Price with tax: " + calculator.applyTax(101.00));
	}

}
