package com.app.salestax.service.shoppingcart;

import java.util.List;

import com.app.salestax.beans.receipt.ReceiptItem;
import com.app.salestax.beans.shoppingcart.ShoppingCartItem;

public interface ShoppingCartTotalCalculation {
	public List<ReceiptItem> calculateTotalAndTax(List<ShoppingCartItem> shoppingCartItems) throws Exception;
}
