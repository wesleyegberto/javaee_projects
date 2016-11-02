package com.github.wesleyegberto.cditests.produtors;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

import com.github.wesleyegberto.cditests.calculator.Calculator;
import com.github.wesleyegberto.cditests.calculator.CustomCalculator;
import com.github.wesleyegberto.cditests.qualifiers.TypeCalculator;
import com.github.wesleyegberto.cditests.qualifiers.TypeCalculatorEnum;

public class CustomCalculatorProducer {

	@Produces
	@RequestScoped
	@TypeCalculator(type = TypeCalculatorEnum.CUSTOM)
	public Calculator produces() {
		return new CustomCalculator(0.9, 0.10);
	}
}
