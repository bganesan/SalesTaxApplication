package com.app.salestax.beans.receipt;

import java.math.BigDecimal;

public class ReceiptItem {
	private int id;
	private int quantity;
	private String description;
	private BigDecimal price;
	private BigDecimal salesTax;
	private BigDecimal total;
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * @return the salesTax
	 */
	public BigDecimal getSalesTax() {
		return salesTax;
	}
	/**
	 * @param salesTax the salesTax to set
	 */
	public void setSalesTax(BigDecimal salesTax) {
		this.salesTax = salesTax;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
