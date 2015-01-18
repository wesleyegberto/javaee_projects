package com.github.wesleyegberto.cditests.decorators;

import javax.annotation.Priority;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Decorated;
import javax.enterprise.inject.spi.Bean;
import javax.inject.Inject;

import com.github.wesleyegberto.cditests.calculator.Calculator;

@Decorator @Priority(value = javax.interceptor.Interceptor.Priority.APPLICATION)
public class DiscountCalculatorDecorator implements Calculator {

	@Inject @Delegate @Any
	Calculator delegate;
	
	@Inject @Decorated
	private Bean<Calculator> bean;
	
	@Override
	public double applyTax(double ammount) {
		System.out.println("Decorator " + bean.getBeanClass().getName() + ": applyTax()");
		return delegate.applyTax(ammount) - 1.0;
	}

	@Override
	public double applyDiscount(double ammount) {
		System.out.println("Decorator " + bean.getBeanClass().getName() + ": applyDiscount()");
		return delegate.applyDiscount(ammount) - 2.0;
	}

}
