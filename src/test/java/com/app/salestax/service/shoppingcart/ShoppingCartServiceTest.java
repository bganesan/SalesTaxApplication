package com.app.salestax.service.shoppingcart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.app.salestax.beans.items.Category;
import com.app.salestax.beans.items.Item;
import com.app.salestax.beans.receipt.Receipt;
import com.app.salestax.beans.receipt.ReceiptItem;
import com.app.salestax.service.util.TestUtil;

public class ShoppingCartServiceTest {
	private final static String CLASS_NAME = ShoppingCartServiceTest.class.getName();
	private final static Logger LOGGER = Logger.getLogger(CLASS_NAME);
	private ShoppingCartTotalSummaryService shoppingCartTotalSummaryService;

	@Before
	public void setUp(){
		final String METHOD_NAME = "setUp";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);
		shoppingCartTotalSummaryService = new ShoppingCartTotalSummaryServiceImpl();
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);
	}

	@Test
	public void testdetermineItemsTotal() {
		final String METHOD_NAME = "testdetermineItemsTotal";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);
		Item input1Item1 = TestUtil.createItem(1, "Book", "12.49", 1, Category.BOOKS, false);
		Item input1Item2 = TestUtil.createItem(2, "Music CD", "14.99", 1, Category.MOVIES_MUSIC, false);
		Item input1Item3 = TestUtil.createItem(3, "Chocolate Bar", "0.85", 1, Category.FOOD, false);

		List<Item> items = new ArrayList<Item>();
		items.add(input1Item1);
		items.add(input1Item2);
		items.add(input1Item3);

		Receipt receipt = shoppingCartTotalSummaryService.determineItemsTotal(items);

		Assert.assertNotNull(receipt);
		Assert.assertNotNull(receipt.getReceiptItems());
		Assert.assertNotNull(receipt.getSalesTax());
		Assert.assertNotNull(receipt.getTotal());
		Assert.assertNotEquals(receipt.getReceiptItems().size(), 0);

		for(ReceiptItem receiptItem : receipt.getReceiptItems()){
			Assert.assertNotNull(receiptItem.getPrice());
			Assert.assertNotNull(receiptItem.getDescription());
			Assert.assertNotNull(receiptItem.getQuantity());
			if(receiptItem.getId() == input1Item1.getId()){
				Assert.assertEquals(new BigDecimal("12.49"), receiptItem.getTotal());
			}
			else if(receiptItem.getId() == input1Item2.getId()){
				Assert.assertEquals(new BigDecimal("16.49"), receiptItem.getTotal());
			}
			else {
				Assert.assertEquals(new BigDecimal("0.85"), receiptItem.getTotal());
			}
		}

		Assert.assertEquals(new BigDecimal("1.50"), receipt.getSalesTax());
		Assert.assertEquals(new BigDecimal("29.83"), receipt.getTotal());
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);
	}

	@Test
	public void testdetermineItemsTotal2() {
		final String METHOD_NAME = "testdetermineItemsTotal2";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);
		Item input2Item1 = TestUtil.createItem(1, "Box of Chocolates", "10.00", 1, Category.FOOD, true);
		Item input2Item2 = TestUtil.createItem(2, "Bottle of Perfume", "47.50", 1, Category.FASHION, true);

		List<Item> items2 = new ArrayList<Item>();
		items2.add(input2Item1);
		items2.add(input2Item2);

		Receipt receipt2 = shoppingCartTotalSummaryService.determineItemsTotal(items2);

		Assert.assertNotNull(receipt2);
		Assert.assertNotNull(receipt2.getReceiptItems());
		Assert.assertNotNull(receipt2.getSalesTax());
		Assert.assertNotNull(receipt2.getTotal());
		Assert.assertNotEquals(receipt2.getReceiptItems().size(), 0);

		for(ReceiptItem receiptItem : receipt2.getReceiptItems()){
			Assert.assertNotNull(receiptItem.getPrice());
			Assert.assertNotNull(receiptItem.getDescription());
			Assert.assertNotNull(receiptItem.getQuantity());
			if(receiptItem.getId() == input2Item1.getId()){
				Assert.assertEquals(new BigDecimal("10.50"), receiptItem.getTotal());
			}
			else{
				Assert.assertEquals(new BigDecimal("54.65"), receiptItem.getTotal());
			}
		}

		Assert.assertEquals(new BigDecimal("7.65"), receipt2.getSalesTax());
		Assert.assertEquals(new BigDecimal("65.15"), receipt2.getTotal());
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);
	}

	@Test
	public void testdetermineItemsTotal3() {
		final String METHOD_NAME = "testdetermineItemsTotal3";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);
		Item input3Item1 = TestUtil.createItem(1, "Imported Bottle of Perfume", "27.99", 1, Category.FASHION, true);
		Item input3Item2 = TestUtil.createItem(2, "Bottle of Perfume", "18.99", 1, Category.FASHION, false);
		Item input3Item3 = TestUtil.createItem(3, "Packet of Headache Pills", "9.75", 1, Category.MEDICINE, false);
		Item input3Item4 = TestUtil.createItem(4, "Box of Imported Chocolates", "11.25", 1, Category.FOOD, true);

		List<Item> items3 = new ArrayList<Item>();
		items3.add(input3Item1);
		items3.add(input3Item2);
		items3.add(input3Item3);
		items3.add(input3Item4);

		Receipt receipt3 = shoppingCartTotalSummaryService.determineItemsTotal(items3);

		Assert.assertNotNull(receipt3);
		Assert.assertNotNull(receipt3.getReceiptItems());
		Assert.assertNotNull(receipt3.getSalesTax());
		Assert.assertNotNull(receipt3.getTotal());
		Assert.assertNotEquals(receipt3.getReceiptItems().size(), 0);

		for(ReceiptItem receiptItem : receipt3.getReceiptItems()){
			Assert.assertNotNull(receiptItem.getPrice());
			Assert.assertNotNull(receiptItem.getDescription());
			Assert.assertNotNull(receiptItem.getQuantity());
			if(receiptItem.getId() == input3Item1.getId()){
				Assert.assertEquals(new BigDecimal("32.19"), receiptItem.getTotal());
			}
			else if(receiptItem.getId() == input3Item2.getId()){
				Assert.assertEquals(new BigDecimal("20.89"), receiptItem.getTotal());
			}
			else if(receiptItem.getId() == input3Item3.getId()){
				Assert.assertEquals(new BigDecimal("9.75"), receiptItem.getTotal());
			}
			else{
				Assert.assertEquals(new BigDecimal("11.85"), receiptItem.getTotal());
			}
		}

		Assert.assertEquals(new BigDecimal("6.70"), receipt3.getSalesTax());
		Assert.assertEquals(new BigDecimal("74.68"), receipt3.getTotal());
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);

	}
	
	@Test
	public void testShoppingCartException(){
		List<Item> item = new ArrayList<Item>();
		Receipt receipt = shoppingCartTotalSummaryService.determineItemsTotal(item);
		Assert.assertEquals(false, receipt.isReceiptAvailable());
	}

}
