package com.github.wesleyegberto.cditests.calculator;

import com.github.wesleyegberto.cditests.qualifiers.High;
import com.github.wesleyegberto.cditests.qualifiers.TypeCalculator;
import com.github.wesleyegberto.cditests.qualifiers.TypeCalculatorEnum;

@High
@TypeCalculator(type = TypeCalculatorEnum.HIGH)
public class HighCalculator implements Calculator {
	private double tax;
	private double percentageDiscount;

	public HighCalculator() {
		this.tax = 0.18;
		this.percentageDiscount = 0.19;
	}

	public double applyTax(double ammount) {
		return ammount + ammount * tax;
	}

	public double applyDiscount(double ammount) {
		return ammount - ammount * percentageDiscount;
	}
}
