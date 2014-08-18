package com.app.salestax.service.shoppingcart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.salestax.beans.receipt.ReceiptItem;
import com.app.salestax.beans.shoppingcart.ShoppingCartItem;
import com.app.salestax.service.salestax.SalesTaxBaseServiceImpl;
/**
* The ShoppingCartTotalCalculationImpl calculates the estimated
* tax and total for the items in the cart. The ShoppingCartTotalCalculationImpl 
* is extending SalesTaxBaseServiceImpl and it uses SalesTaxBaseServiceImpl which 
* defines the rules for the 
* tax calculation for the items in the cart.
*
* @author  Balasubramanian Ganesan
* @version 1.0
* @since   2014-08-16 
*/
public class ShoppingCartTotalCalculationImpl extends SalesTaxBaseServiceImpl implements ShoppingCartTotalCalculation {
	private final static String CLASS_NAME = ShoppingCartTotalCalculationImpl.class.getName();
	private final static Logger LOGGER = Logger.getLogger(CLASS_NAME); 
	/**
	 * Returns the list of the receipt items which is created after 
	 * the total and tax calculations in the shopping cart items. 
	 *
	 * @param  shoppingcartitems the list of the shopping items for which the 
	 * 			amount needs to be calculated
	 * @return list of the receipt items 
	 * @see    ReceiptItem
	 */
	@Override
	public List<ReceiptItem> calculateTotalAndTax (List<ShoppingCartItem> shoppingCartItems) throws Exception{
		final String METHOD_NAME = "calculateTotalAndTax";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);
		//create the receiptItems list for returning
		List<ReceiptItem> receiptItemList = new ArrayList<ReceiptItem>();
		//loop through the shopping cart and do the calculations
		for(ShoppingCartItem shoppingCartItem : shoppingCartItems){
			//calclulate tax for the shopping items
			if(LOGGER.isInfoEnabled()) LOGGER.info(CLASS_NAME+METHOD_NAME+": before calculating tax");
			calculateTax(shoppingCartItem);
			if(LOGGER.isInfoEnabled()) LOGGER.info(CLASS_NAME+METHOD_NAME+": after calculating tax");
			//build items for receipt
			ReceiptItem receiptItem = new ReceiptItem();
			receiptItem.setId(shoppingCartItem.getItem().getId());
			receiptItem.setDescription(shoppingCartItem.getItem().getDescription());
			receiptItem.setPrice(shoppingCartItem.getItem().getPrice());
			receiptItem.setQuantity(shoppingCartItem.getItem().getQuantity());
			//round off tax based on the rule
			BigDecimal salesTax = roundOff(shoppingCartItem.getSalesTaxAmount());
			if(null != shoppingCartItem.getAdditionalSalesTaxAmount()){
				salesTax = salesTax.add(shoppingCartItem.getAdditionalSalesTaxAmount());
			}
			if(LOGGER.isInfoEnabled()) LOGGER.info(CLASS_NAME+METHOD_NAME+": All calculations done");
			receiptItem.setSalesTax(salesTax);
			receiptItem.setTotal(shoppingCartItem.getItem().getPrice().multiply(new BigDecimal(shoppingCartItem.getItem().getQuantity())).add(salesTax));
			if(LOGGER.isInfoEnabled()) LOGGER.info(CLASS_NAME+METHOD_NAME+": adding to return list");
			//add receipt item to the final list
			receiptItemList.add(receiptItem);
		}
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);
		return receiptItemList;
	}

}
