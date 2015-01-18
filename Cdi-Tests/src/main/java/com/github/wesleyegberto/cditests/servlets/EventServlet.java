package com.github.wesleyegberto.cditests.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.wesleyegberto.cditests.calculator.Calculator;
import com.github.wesleyegberto.cditests.qualifiers.DiscountObservable;
import com.github.wesleyegberto.cditests.qualifiers.High;
import com.github.wesleyegberto.cditests.qualifiers.TaxObservable;
import com.github.wesleyegberto.cditests.qualifiers.TypeCalculator;
import com.github.wesleyegberto.cditests.qualifiers.TypeCalculatorEnum;

@WebServlet("/EventServlet")
public class EventServlet extends HttpServlet {
	private static final long serialVersionUID = 143543534533300L;

	@Inject @High
	Calculator highCalculator;

	@Inject @TypeCalculator(type = TypeCalculatorEnum.LOW)
	Calculator lowCalculator;
	
	@Inject @TaxObservable
	Event<Double> taxObservers;
	
	@Inject @DiscountObservable
	Event<Double> discountEvents;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();

		writer.print("<html><body>");

		String typeCalculator = request.getParameter("type");
		String rawAmmount = request.getParameter("ammount");

		if (rawAmmount == null) {
			writer.print("<p><strong>Invalid ammount!</strong></p>");
		} else {
			double ammount = Double.parseDouble(rawAmmount);

			Calculator calc = ("high".equals(typeCalculator) ? highCalculator : lowCalculator);
			double tax = calc.applyTax(ammount);
			double discount = calc.applyDiscount(ammount);
			
			writer.print("Ammount: " + ammount);
			writer.print("<br/>Calculator: " + calc);
			writer.print("<br/>Ammount with high tax: " + tax);
			writer.print("<br/>Ammount with high discount: " + discount);
			
			taxObservers.fire(tax);
			discountEvents.fire(discount);
		}
		writer.print("</body></html>");
	}

}
