package com.app.salestax.service.salestax;



import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.app.salestax.beans.shoppingcart.ShoppingCartItem;
import com.app.salestax.service.shoppingcart.ShoppingCartTotalCalculationImpl;
import com.app.salestax.service.util.TestUtil;

/**
 * The SalesTaxBaseServiceTest is the test class for the the SalesTaxBaseService.
 *
 * @author  Balasubramanian Ganesan
 * @version 1.0
 * @since   2014-08-16 
 */
public class SalesTaxBaseServiceTest {
	private final static String CLASS_NAME = SalesTaxBaseServiceTest.class.getName();
	private final static Logger LOGGER = Logger.getLogger(CLASS_NAME); 
	private SalesTaxBaseService salesTaxBaseService;

	@Before
	public void setUp(){
		final String METHOD_NAME = "setUp";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);
		salesTaxBaseService = new ShoppingCartTotalCalculationImpl();
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);
	}

	@Test
	public void testCalculatePercentageInput1I1() throws Exception {
		final String METHOD_NAME = "testCalculatePercentageInput1I1";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);
		//create input 1
		ShoppingCartItem shoppingCartItem1 = TestUtil.createInput1Item1();
		shoppingCartItem1 = salesTaxBaseService.calculateTax(shoppingCartItem1);
		//assert based on the input, if imported additionalTax is required
		Assert.assertNotNull(shoppingCartItem1.getSalesTaxAmount());
		Assert.assertNull(shoppingCartItem1.getAdditionalSalesTaxAmount());
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);
	}

	@Test
	public void testCalculatePercentageInput1I2() throws Exception {
		final String METHOD_NAME = "testCalculatePercentageInput1I2";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);
		//create input 2
		ShoppingCartItem shoppingCartItem2 = TestUtil.createInput1Item2();
		shoppingCartItem2 = salesTaxBaseService.calculateTax(shoppingCartItem2);
		//assert based on the input
		Assert.assertNotNull(shoppingCartItem2.getSalesTaxAmount());
		Assert.assertNull(shoppingCartItem2.getAdditionalSalesTaxAmount());
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);
	}

	@Test
	public void testCalculatePercentageInput1I3() throws Exception {
		final String METHOD_NAME = "testCalculatePercentageInput1I3";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);
		//create input 3
		ShoppingCartItem shoppingCartItem3 = TestUtil.createInput1Item3();
		shoppingCartItem3 = salesTaxBaseService.calculateTax(shoppingCartItem3);
		//assert based on the input
		Assert.assertNotNull(shoppingCartItem3.getSalesTaxAmount());
		Assert.assertNull(shoppingCartItem3.getAdditionalSalesTaxAmount());
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);
	}

	@Test
	public void testCalculatePercentageInput2I1() throws Exception {
		final String METHOD_NAME = "testCalculatePercentageInput2I1";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);
		//create input 4
		ShoppingCartItem shoppingCartItem4 = TestUtil.createInput2Item1();
		shoppingCartItem4 = salesTaxBaseService.calculateTax(shoppingCartItem4);
		//assert based on the input
		Assert.assertNotNull(shoppingCartItem4.getSalesTaxAmount());
		Assert.assertNotNull(shoppingCartItem4.getAdditionalSalesTaxAmount());
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);
	}

	@Test
	public void testCalculatePercentageInput3I1() throws Exception {
		final String METHOD_NAME = "testCalculatePercentageInput3I1";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);
		//create input 5
		ShoppingCartItem shoppingCartItem5 = TestUtil.createInput2Item2();
		shoppingCartItem5 = salesTaxBaseService.calculateTax(shoppingCartItem5);
		//assert based on the input
		Assert.assertNotNull(shoppingCartItem5.getSalesTaxAmount());
		Assert.assertNotNull(shoppingCartItem5.getAdditionalSalesTaxAmount());
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);
	}

	@Test
	public void testCalculatePercentageInput3I2() throws Exception {
		final String METHOD_NAME = "testCalculatePercentageInput3I2";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);
		//create input 1
		ShoppingCartItem shoppingCartItem1 = TestUtil.createInput1Item1();
		shoppingCartItem1 = salesTaxBaseService.calculateTax(shoppingCartItem1);
		//assert based on the input, if imported additionalTax is required
		Assert.assertNotNull(shoppingCartItem1.getSalesTaxAmount());
		Assert.assertNull(shoppingCartItem1.getAdditionalSalesTaxAmount());
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);
	}

	@Test
	public void testCalculatePercentageInput4I1() throws Exception {
		final String METHOD_NAME = "testCalculatePercentageInput4I1";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);

		//create input 6
		ShoppingCartItem shoppingCartItem6 = TestUtil.createInput3Item1();
		shoppingCartItem6 = salesTaxBaseService.calculateTax(shoppingCartItem6);
		//assert based on the input
		Assert.assertNotNull(shoppingCartItem6.getSalesTaxAmount());
		Assert.assertNotNull(shoppingCartItem6.getAdditionalSalesTaxAmount());
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);
	}

	@Test
	public void testCalculatePercentageInput4I2() throws Exception {
		final String METHOD_NAME = "testCalculatePercentageInput4I2";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);
		//create input 7
		ShoppingCartItem shoppingCartItem7 = TestUtil.createInput3Item2();
		shoppingCartItem7 = salesTaxBaseService.calculateTax(shoppingCartItem7);
		//assert based on the input
		Assert.assertNotNull(shoppingCartItem7.getSalesTaxAmount());
		Assert.assertNull(shoppingCartItem7.getAdditionalSalesTaxAmount());		
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);
	}

	@Test
	public void testCalculatePercentageInput4I3() throws Exception {
		final String METHOD_NAME = "testCalculatePercentageInput4I3";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);
		//create input 8
		ShoppingCartItem shoppingCartItem8 = TestUtil.createInput3Item3();
		shoppingCartItem8 = salesTaxBaseService.calculateTax(shoppingCartItem8);
		//assert based on the input
		Assert.assertNotNull(shoppingCartItem8.getSalesTaxAmount());
		Assert.assertNull(shoppingCartItem8.getAdditionalSalesTaxAmount());
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);
	}


	@Test
	public void testCalculatePercentageInput4I4() throws Exception {
		final String METHOD_NAME = "testCalculatePercentageInput4I4";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);
		//create input 9
		ShoppingCartItem shoppingCartItem9 = TestUtil.createInput3Item4();
		shoppingCartItem9 = salesTaxBaseService.calculateTax(shoppingCartItem9);
		//assert based on the input
		Assert.assertNotNull(shoppingCartItem9.getSalesTaxAmount());
		Assert.assertNotNull(shoppingCartItem9.getAdditionalSalesTaxAmount());
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);
	}

}
