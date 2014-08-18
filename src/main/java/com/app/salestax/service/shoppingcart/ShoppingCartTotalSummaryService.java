package com.app.salestax.service.shoppingcart;

import java.util.List;

import com.app.salestax.beans.items.Item;
import com.app.salestax.beans.receipt.Receipt;
/**
 *The contract for the ShoppingCartTotalSummaryServiceImpl.
 */
public interface ShoppingCartTotalSummaryService {
	public Receipt determineItemsTotal(List<Item> items);
}
