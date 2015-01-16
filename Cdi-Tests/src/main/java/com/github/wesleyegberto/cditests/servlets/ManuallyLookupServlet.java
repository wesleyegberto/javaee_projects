package com.github.wesleyegberto.cditests.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.wesleyegberto.cditests.calculator.Calculator;
import com.github.wesleyegberto.cditests.qualifiers.High;
import com.github.wesleyegberto.cditests.qualifiers.Low;

@WebServlet(name = "ManuallyLookupServlet", urlPatterns = { "/ManuallyLookupServlet" })
public class ManuallyLookupServlet extends HttpServlet {
	private static final long serialVersionUID = 4939295347665064046L;

	@Inject @Any
	Instance<Calculator> calculator;
	
	@SuppressWarnings("serial")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();

		writer.print("<html><body>");

		String typeCalculator = request.getParameter("type");
		String rawAmmount = request.getParameter("ammount");

		if (rawAmmount == null) {
			writer.print("<p><strong>Invalid ammount!</strong></p>");
		} else {
			double ammount = Double.parseDouble(rawAmmount);

			Instance<Calculator> calc = null;
			
			// lookup the choosen calculator
			if("high".equals(typeCalculator)) {
				calc = calculator.select(new AnnotationLiteral<High>(){});
			} else if("low".equals(typeCalculator)) {
				calc = calculator.select(new AnnotationLiteral<Low>(){});
			} else {
				calc = calculator.select(new AnnotationLiteral<Alternative>(){});
			}
			
			writer.print("Ammount: " + ammount);
			writer.print("<br/>Calculator: " + calc.get());
			writer.print("<br/>Ammount with tax: " + calc.get().applyTax(ammount));
			writer.print("<br/>Ammount with discount: " + calc.get().applyDiscount(ammount));
		}
		writer.print("</body></html>");
	}
}
