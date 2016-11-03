package com.github.wesleyegberto.cditests.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.wesleyegberto.cditests.calculator.Calculator;
import com.github.wesleyegberto.cditests.qualifiers.High;
import com.github.wesleyegberto.cditests.qualifiers.TypeCalculator;
import com.github.wesleyegberto.cditests.qualifiers.TypeCalculatorEnum;

@WebServlet(name = "QualifierServlet", urlPatterns = { "/QualifierServlet" })
public class QualifierServlet extends HttpServlet {
	private static final long serialVersionUID = 143543534533300L;

	// Using qualifier
	@Inject @High
	Calculator highCalculator;
	
	// Using a producer
	@Inject
	@TypeCalculator(type = TypeCalculatorEnum.CUSTOM)
	// @New if it was concrete force to not reuse an existing instance (RequestScoped)
	Calculator mediumCalculator;

	// Using qualifier with an attribute
	@Inject @TypeCalculator(type = TypeCalculatorEnum.LOW)
	Calculator lowCalculator;

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
			
			writer.print("Ammount: " + ammount);
			writer.print("<br/>Calculator: " + calc);
			writer.print("<br/>Ammount with high tax: " + calc.applyTax(ammount));
			writer.print("<br/>Ammount with high discount: " + calc.applyDiscount(ammount));
		}
		writer.print("</body></html>");
	}

}
