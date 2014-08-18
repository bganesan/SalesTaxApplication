/**
 * 
 */
package com.app.salestax.service.salestax;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.salestax.beans.items.Item;
import com.app.salestax.beans.shoppingcart.ShoppingCartItem;
import com.app.salestax.constants.SalesTaxConstant;
import com.app.salestax.util.SalesTaxUtil;

/**
* The SalesTaxBaseServiceImpl is the abstract implementation 
* of the SalesTaxBaseService and is the rule engine for
* determining the tax. This class is the only external
* world contact point to decide the tax calculation rules.
*
* @author  Balasubramanian Ganesan
* @version 1.0
* @since   2014-08-16 
*/
public class SalesTaxBaseServiceImpl implements SalesTaxBaseService {
	private final static String CLASS_NAME = SalesTaxBaseServiceImpl.class.getName();
	private final static Logger LOGGER = Logger.getLogger(CLASS_NAME); 
	private SalesTaxUtil salesTaxUtil = new SalesTaxUtil();
	
	/**
	 * Returns shopping cart item after the tax calculations on the item.
	 * This class contacts the external properties to determine the tax 
	 * rates based on which the taxes are calculated for the item. 
	 *
	 * @param  shoppingcartitem the shopping cart item on which calculations
	 * 			have to be done
	 * @return the shopping cart item after tax is calculated 
	 * @see    ShoppingCartItem
	 */
	@Override
	public ShoppingCartItem calculateTax(ShoppingCartItem shoppingCartItem) throws Exception {
		final String METHOD_NAME = "calculateTax";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);
		//contact external world to get the taxrates
		String standardTaxPercentage = salesTaxUtil.getProperty(SalesTaxConstant.STANDARD_TAXRATE);
		String additionalTaxPercentage = salesTaxUtil.getProperty(SalesTaxConstant.ADDITIONAL_TAXRATE);
		//exempt rate set to zero for now
		String exemptTaxRatePercentage = salesTaxUtil.getProperty(SalesTaxConstant.EXEMPT_TAX_RATE);
		List<String> exemptCategories = salesTaxUtil.getExemptCategory();
		if(LOGGER.isDebugEnabled()) LOGGER.debug(CLASS_NAME+METHOD_NAME+": All tax rate rules retrieved");
		BigDecimal hundred = new BigDecimal(SalesTaxConstant.HUNDRED);
		Item item = shoppingCartItem.getItem();
		BigDecimal quantity = new BigDecimal(item.getQuantity());
		BigDecimal standardTaxRate = new BigDecimal(standardTaxPercentage);
		BigDecimal additionalTaxRate = new BigDecimal(additionalTaxPercentage);
		if(LOGGER.isDebugEnabled()) LOGGER.debug(CLASS_NAME+METHOD_NAME+": All tax rates set");
		//determine sales tax amount
		BigDecimal salesTaxAmount = item.getPrice().multiply(quantity).multiply(standardTaxRate).divide(hundred);
		if(LOGGER.isInfoEnabled()) LOGGER.info(CLASS_NAME+METHOD_NAME+": Sales tax calculated");
		//if item is imported additional salestax is required
		if(item.isImported()){
			if(LOGGER.isDebugEnabled()) LOGGER.debug(CLASS_NAME+METHOD_NAME+": Imported Item");
			BigDecimal additionalSalesTax = item.getPrice().multiply(quantity).multiply(additionalTaxRate).divide(hundred);
			shoppingCartItem.setAdditionalSalesTaxAmount(roundOff(additionalSalesTax));
		}
		
		//if item is exempted then sales tax is not required
		if(checkIfExemptItem(item, exemptCategories)){
			if(LOGGER.isDebugEnabled()) LOGGER.debug(CLASS_NAME+METHOD_NAME+": Exempt Item");
			BigDecimal exemptTaxRate = new BigDecimal(exemptTaxRatePercentage);
			shoppingCartItem.setExempt(true);
			salesTaxAmount = item.getPrice().multiply(quantity).multiply(exemptTaxRate).divide(hundred);
		}

		if(LOGGER.isDebugEnabled()) LOGGER.debug(CLASS_NAME+METHOD_NAME+": Rounding off tax");
		//set the sales tax to the shopping cart item
		shoppingCartItem.setSalesTaxAmount(roundOff(salesTaxAmount));
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);
		return shoppingCartItem;
	}

	/**
	 * This method checks if the category of the item is eligible for the 
	 * tax exemption. 
	 *
	 * @param  Item and the exempt categories list from the properties
	 * @return boolean value of eligibility 
	 * @see    boolean
	 */
	private boolean checkIfExemptItem(Item item, List<String> exemptCategories){
		final String METHOD_NAME = "checkIfExemptItem";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);
		boolean exemptItem = false;
		//check if item is in the exempt category list
		for(String exemptCategory : exemptCategories){
			if(null != item.getCategory() && null != exemptCategory
					&& exemptCategory.equalsIgnoreCase(item.getCategory().toString())){
				exemptItem = true;
				break;
			}
		}
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);
		return exemptItem;
	}

	/**
	 * This method does the rounding of the tax based on the tax calculation
	 * rules properties file. 
	 *
	 * @param  value that needs to be rounded off
	 * @return value after rounding off 
	 * @throws Exception 
	 * @see    BigDecimal
	 */
	protected BigDecimal roundOff(BigDecimal value) throws Exception {
		final String METHOD_NAME = "roundOff";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);
		//round off to set value
		String roundingValue = salesTaxUtil.getProperty(SalesTaxConstant.ROUNDING_VALUE);
		if(LOGGER.isDebugEnabled()) LOGGER.debug(CLASS_NAME+METHOD_NAME+": Retrieved rounding off value, going to round off");
		BigDecimal roundingDecimal = new BigDecimal(roundingValue);
		value = value.divide(roundingDecimal);
		value = new BigDecimal(Math.ceil(value.doubleValue()));
		value = value.multiply(roundingDecimal);
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);
		return value;
	}

}
