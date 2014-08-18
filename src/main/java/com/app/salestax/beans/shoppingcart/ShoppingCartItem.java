package com.app.salestax.beans.shoppingcart;

import java.math.BigDecimal;

import com.app.salestax.beans.items.Item;

public class ShoppingCartItem {
	private Item item;
	private boolean exempt;
	private BigDecimal salesTaxAmount;
	private BigDecimal additionalSalesTaxAmount;
	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}
	/**
	 * @return the exempt
	 */
	public boolean isExempt() {
		return exempt;
	}
	/**
	 * @param exempt the exempt to set
	 */
	public void setExempt(boolean exempt) {
		this.exempt = exempt;
	}
	public BigDecimal getSalesTaxAmount() {
		return salesTaxAmount;
	}
	public void setSalesTaxAmount(BigDecimal salesTaxAmount) {
		this.salesTaxAmount = salesTaxAmount;
	}
	public BigDecimal getAdditionalSalesTaxAmount() {
		return additionalSalesTaxAmount;
	}
	public void setAdditionalSalesTaxAmount(BigDecimal additionalSalesTaxAmount) {
		this.additionalSalesTaxAmount = additionalSalesTaxAmount;
	}
	
	
}
