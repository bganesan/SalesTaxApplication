package com.app.salestax.beans.receipt;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {
	private boolean receiptAvailable;
	private BigDecimal salesTax;
	private BigDecimal total;
	private List<ReceiptItem> receiptItems;
	public BigDecimal getSalesTax() {
		return salesTax;
	}
	public void setSalesTax(BigDecimal salesTax) {
		this.salesTax = salesTax;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public List<ReceiptItem> getReceiptItems() {
		return receiptItems;
	}
	public void setReceiptItems(List<ReceiptItem> receiptItems) {
		this.receiptItems = receiptItems;
	}
	public boolean isReceiptAvailable() {
		return receiptAvailable;
	}
	public void setReceiptAvailable(boolean receiptAvailable) {
		this.receiptAvailable = receiptAvailable;
	}
	
}
