package com.github.wesleyegberto.cditests.calculator;

import com.github.wesleyegberto.cditests.qualifiers.High;
import com.github.wesleyegberto.cditests.qualifiers.TypeCalculator;
import com.github.wesleyegberto.cditests.qualifiers.TypeCalculatorEnum;

@High
@TypeCalculator(type = TypeCalculatorEnum.CUSTOM)
public class CustomCalculator implements Calculator {
	private double tax;
	private double percentageDiscount;

	public CustomCalculator(double tax, double percentageDiscount) {
		this.tax = tax;
		this.percentageDiscount = percentageDiscount;
	}
	
	public double applyTax(double ammount) {
		return ammount + ammount * tax;
	}

	public double applyDiscount(double ammount) {
		return ammount - ammount * percentageDiscount;
	}
}
