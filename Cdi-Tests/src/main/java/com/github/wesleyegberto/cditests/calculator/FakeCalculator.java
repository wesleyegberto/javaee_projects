package com.github.wesleyegberto.cditests.calculator;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;

import com.github.wesleyegberto.cditests.qualifiers.TypeCalculator;
import com.github.wesleyegberto.cditests.qualifiers.TypeCalculatorEnum;

@Alternative @TypeCalculator(type = TypeCalculatorEnum.LOW) @Priority(value = javax.interceptor.Interceptor.Priority.APPLICATION)
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
