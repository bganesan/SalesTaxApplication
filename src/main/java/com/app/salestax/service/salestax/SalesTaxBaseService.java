package com.app.salestax.service.salestax;

import com.app.salestax.beans.shoppingcart.ShoppingCartItem;
/**
 *The contract for the SalesTaxBaseServiceImpl.
 */
public interface SalesTaxBaseService {
	public ShoppingCartItem calculateTax(ShoppingCartItem item) throws Exception;
}
