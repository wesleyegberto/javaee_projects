package com.github.wesleyegberto.cditests.decorators;

import javax.annotation.Priority;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

import com.github.wesleyegberto.cditests.calculator.Calculator;
import com.github.wesleyegberto.cditests.qualifiers.Low;

@Decorator @Priority(value = javax.interceptor.Interceptor.Priority.APPLICATION)
public class DiscountCalculatorDecorator implements Calculator {

	@Inject @Delegate @Low
	Calculator delegate;
	
	@Override
	public double applyTax(double ammount) {
		System.out.println("Decorator: applyTax()");
		return delegate.applyTax(ammount) - 1.0;
	}

	@Override
	public double applyDiscount(double ammount) {
		System.out.println("Decorator: applyDiscount()");
		return delegate.applyDiscount(ammount) - 2.0;
	}

}
