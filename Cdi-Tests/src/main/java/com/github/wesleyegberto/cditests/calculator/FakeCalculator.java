package com.github.wesleyegberto.cditests.calculator;

import com.github.wesleyegberto.cditests.qualifiers.Loggable;
import com.github.wesleyegberto.cditests.stereotypes.Mock;

@Mock
@Loggable
public class FakeCalculator implements Calculator {
	private double tax;
	private double percentageDiscount;

	public FakeCalculator() {
		this.tax = 0.5;
		this.percentageDiscount = 0.0;
	}
	
	public double applyTax(double ammount) {
		return ammount + ammount * tax;
	}

	public double applyDiscount(double ammount) {
		return ammount - ammount * percentageDiscount;
	}

}
