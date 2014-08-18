package com.app.salestax.factory;

import com.app.salestax.service.shoppingcart.ShoppingCartTotalCalculation;
import com.app.salestax.service.shoppingcart.ShoppingCartTotalCalculationImpl;

public final class ShoppingCartCalculatorFactory {

	private static volatile ShoppingCartTotalCalculation instance = null;

	//private constructor
	private ShoppingCartCalculatorFactory() {
	}

	/**
	 * Following method will return instance of ShoppingCartTotalCalculation
	 * Currently single thread is accessing instance so no need synchronization
	 * @param type
	 * @return ShoppingCartTotalCalculation
	 */
	public static ShoppingCartTotalCalculation getInstance() {

		if (instance == null) {
			synchronized (ShoppingCartTotalCalculationImpl.class) {
				instance = new ShoppingCartTotalCalculationImpl();
			}
		}
		return instance;
	}
}
