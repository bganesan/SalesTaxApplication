package com.app.salestax.service.shoppingcart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.salestax.beans.items.Item;
import com.app.salestax.beans.receipt.Receipt;
import com.app.salestax.beans.receipt.ReceiptItem;
import com.app.salestax.beans.shoppingcart.ShoppingCartItem;
import com.app.salestax.constants.SalesTaxConstant;
import com.app.salestax.exceptions.SalesTaxApplicationException;
import com.app.salestax.factory.ShoppingCartCalculatorFactory;
/**
 * The ShoppingCartServiceImpl is the implementation class for the 
 * ShoppingCartService interface. This class handles the operation of 
 * determining the estimated sales tax and total for the 
 * items in the cart using the SalesTaxBaseService.
 *
 * @author  Balasubramanian Ganesan
 * @version 1.0
 * @since   2014-08-16 
 */
public class ShoppingCartTotalSummaryServiceImpl implements ShoppingCartTotalSummaryService {
	private final static String CLASS_NAME = ShoppingCartTotalSummaryServiceImpl.class.getName();
	private final static Logger LOGGER = Logger.getLogger(CLASS_NAME); 
	/**
	 * Returns the Receipt that needs to be printed to 
	 * display the total summary for the items in the 
	 * shopping cart. 
	 *
	 * @param  items the list of the items for which the total has 
	 * 			to be calculated based on the tax calculation rules
	 * @return the receipt that needs to be printed 
	 * @see    Receipt
	 */
	@Override
	public Receipt determineItemsTotal(List<Item> items) {
		final String METHOD_NAME = "determineItemsTotal";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);
		//initate the receipt that would be returned
		Receipt receipt = new Receipt();
		
		try{
			
			if(null != items && items.size() > 0){
				//convert items to shopping cart for calculation
				List<ShoppingCartItem> shoppingCartItems = itemToShoppingCartItemConverter(items);
				if(LOGGER.isInfoEnabled()) LOGGER.info(CLASS_NAME+METHOD_NAME+": Shopping cart size :"+shoppingCartItems.size());
				//getting the total and tax calculator from the factory 
				//using the SingleTon factory to get the calculator
				ShoppingCartTotalCalculation shoppingCartTotalCalculation = ShoppingCartCalculatorFactory.getInstance();
				if(LOGGER.isInfoEnabled()) LOGGER.info(CLASS_NAME+METHOD_NAME+": Shopping Calculation factory created");
				//calculate tax and total
				List<ReceiptItem> receiptItems = shoppingCartTotalCalculation.calculateTotalAndTax(shoppingCartItems);
				if(LOGGER.isInfoEnabled()) LOGGER.info(CLASS_NAME+METHOD_NAME+": Shopping cart tax calculation completed");
				receipt.setReceiptItems(receiptItems);
				receipt.setSalesTax(calculateTotalSalesTax(receiptItems));
				receipt.setTotal(calculateTotal(receiptItems));
				receipt.setReceiptAvailable(true);
				if(LOGGER.isDebugEnabled()) LOGGER.debug(CLASS_NAME+METHOD_NAME+": receiptItems size :"+receiptItems.size());
			}
			else{
				LOGGER.error("Item is null or empty.");
				throw new SalesTaxApplicationException(SalesTaxConstant._ERR_INVALID_REQUEST);
			}
		}
		catch(Exception e){
			LOGGER.error("Error in calculating Tax"+e.getMessage());
			return receipt;
		}
		
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);
		//return the receipt
		return receipt;
	}

	/**
	 * Returns the computed total Sales tax for the 
	 * ReceiptItems that will be displayed in the receipt. 
	 *
	 * @param  receiptitems the list of the receipt items for which the total sales tax needs to be calculated
	 * @return the computed total sales tax value
	 * @see    BigDecimal
	 */
	private BigDecimal calculateTotalSalesTax(List<ReceiptItem> receiptItems){
		final String METHOD_NAME = "calculateTotalSalesTax";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);
		//calculate the total tax of the items
		BigDecimal totalSalesTax = new BigDecimal("0.00");
		for(ReceiptItem receiptItem:receiptItems){
			BigDecimal itemTax = receiptItem.getSalesTax();
			if(null != itemTax){
				totalSalesTax = totalSalesTax.add(itemTax);
			}
		}
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);
		return totalSalesTax;
	}

	/**
	 * Returns the computed total price for the 
	 * ReceiptItems that will be displayed in the receipt. 
	 *
	 * @param  receiptitems the list of the receipt items for which the amount needs to be calculated
	 * @return the computed total amount value
	 * @see    BigDecimal
	 */
	private BigDecimal calculateTotal(List<ReceiptItem> receiptItems){
		final String METHOD_NAME = "calculateTotal";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);
		//calculate the total  of the items
		BigDecimal total = new BigDecimal("0.00");
		for(ReceiptItem receiptItem:receiptItems){
			BigDecimal itemTotal = receiptItem.getTotal();
			if(null != itemTotal){
				total = total.add(itemTotal);
			}
		}
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);
		return total;
	}

	/**
	 * Converts the items from the basic item to the shopping cart item which
	 * could be used for further calculation
	 * @param  items the list of the items
	 * @return the list of the shopping cart items
	 * @see    ShoppingCartItem
	 */
	private List<ShoppingCartItem> itemToShoppingCartItemConverter(List<Item> items){
		final String METHOD_NAME = "itemToShoppingCartItemConverter";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);
		//create ShoppingCartItem from the Item object
		List<ShoppingCartItem> shoppingCartItems = new ArrayList<ShoppingCartItem>();
		for(Item item : items){
			ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
			shoppingCartItem.setItem(item);
			shoppingCartItems.add(shoppingCartItem);
		}
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);
		return shoppingCartItems;
	}

}
