package com.github.wesleyegberto.cditests.observers;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.EventMetadata;

import com.github.wesleyegberto.cditests.qualifiers.DiscountObservable;
import com.github.wesleyegberto.cditests.qualifiers.TaxObservable;

public class CalculatorObserver {
	
	public void registerTax(@Observes @TaxObservable double newValue) {
		System.out.println("[" + System.currentTimeMillis() + "] Value after apply tax: " + newValue);
	}
	
	public void registerDiscount(@Observes @DiscountObservable double newValue, EventMetadata metadata) {
		System.out.println("[ " + metadata.getType() + " - " + System.currentTimeMillis() + "] Value after apply discount: " + newValue);
	}
}
