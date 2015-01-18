package com.github.wesleyegberto.cditests.calculator;

import com.github.wesleyegberto.cditests.qualifiers.Low;
import com.github.wesleyegberto.cditests.qualifiers.TypeCalculator;
import com.github.wesleyegberto.cditests.qualifiers.TypeCalculatorEnum;

@Low
@TypeCalculator(type = TypeCalculatorEnum.LOW)
public class LowCalculator implements Calculator {
	private double tax;
	private double percentageDiscount;
	
	public LowCalculator() {
		this.tax = 0.10;
		this.percentageDiscount = 0.14;
	}

	public double applyTax(double ammount) {
		return ammount + ammount * tax;
	}

	public double applyDiscount(double ammount) {
		return ammount - ammount * percentageDiscount;
	}

}
