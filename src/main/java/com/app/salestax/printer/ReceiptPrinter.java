package com.app.salestax.printer;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import com.app.salestax.beans.items.Category;
import com.app.salestax.beans.items.Item;
import com.app.salestax.beans.receipt.Receipt;
import com.app.salestax.beans.receipt.ReceiptItem;
import com.app.salestax.service.shoppingcart.ShoppingCartTotalSummaryService;
import com.app.salestax.service.shoppingcart.ShoppingCartTotalSummaryServiceImpl;

/**
* The ReceiptPrinter class which needs to be executed 
* to calculate the total and tax for the required input
* items.
* 
* Please execute this class for verfication of output.
*
* @author  Balasubramanian Ganesan
* @version 1.0
* @since   2014-08-16 
*/
public class ReceiptPrinter {
	
	public static void main(String[] args) {
		
		List<Item> items = createInput1();
		ShoppingCartTotalSummaryService shopService = new ShoppingCartTotalSummaryServiceImpl();
		Receipt receipt = shopService.determineItemsTotal(items);
		printReceipt(receipt);
		List<Item> items2 = createInput2();
		ShoppingCartTotalSummaryService shopService2 = new ShoppingCartTotalSummaryServiceImpl();
		Receipt receipt2 = shopService2.determineItemsTotal(items2);
		printReceipt(receipt2);
		List<Item> items3 = createInput3();
		ShoppingCartTotalSummaryService shopService3 = new ShoppingCartTotalSummaryServiceImpl();
		Receipt receipt3 = shopService3.determineItemsTotal(items3);
		printReceipt(receipt3);
	}
	
	/**
	* Prints the received receipt displaying the 
	* quantity, description, item total, sales tax and grand total.
	*/
	private static void printReceipt(Receipt receipt){
		if(receipt.isReceiptAvailable()){
			List<ReceiptItem> receiptItems = receipt.getReceiptItems();
			for(ReceiptItem receiptItem:receiptItems){
				System.out.println(receiptItem.getQuantity()+" "+receiptItem.getDescription()+" : "+receiptItem.getTotal());
			}
			System.out.println("Sales Taxes :"+receipt.getSalesTax());
			System.out.println("Total :"+receipt.getTotal());
		}
		else{
			System.out.println("Error in generating receipt, please try later.");
		}
		System.out.println();
		System.out.println();
	}

	private static List<Item> createInput1() {
		List<Item> items = new ArrayList<Item>();
		Item book = new Item();
		book.setId(1);
		book.setCategory(Category.BOOKS);
		book.setQuantity(1);
		book.setDescription("Book");
		book.setPrice(new BigDecimal("12.49").setScale(2, BigDecimal.ROUND_CEILING));
		items.add(book);
		Item musicCd = new Item();
		musicCd.setId(2);
		musicCd.setCategory(Category.MOVIES_MUSIC);
		musicCd.setQuantity(1);
		musicCd.setDescription("Music CD");
		musicCd.setPrice(new BigDecimal("14.99").setScale(2, BigDecimal.ROUND_CEILING));
		items.add(musicCd);
		Item chocolateBar = new Item();
		chocolateBar.setId(3);
		chocolateBar.setCategory(Category.FOOD);
		chocolateBar.setDescription("Chocolate Bar");
		chocolateBar.setQuantity(1);
		chocolateBar.setPrice(new BigDecimal("0.85").setScale(2, BigDecimal.ROUND_CEILING));
		items.add(chocolateBar);
		return items;
	}
	
	private static List<Item> createInput2() {
		List<Item> items = new ArrayList<Item>();
		Item chocolates = new Item();
		chocolates.setId(1);
		chocolates.setCategory(Category.FOOD);
		chocolates.setQuantity(1);
		chocolates.setDescription("Imported Box of Chocolates");
		chocolates.setPrice(new BigDecimal("10.00").setScale(2, BigDecimal.ROUND_CEILING));
		chocolates.setImported(true);
		items.add(chocolates);
		Item perfume = new Item();
		perfume.setId(2);
		perfume.setCategory(Category.FASHION);
		perfume.setQuantity(1);
		perfume.setDescription("Imported Bottle of Perfume");
		perfume.setPrice(new BigDecimal("47.50").setScale(2, BigDecimal.ROUND_CEILING));
		perfume.setImported(true);
		items.add(perfume);
		return items;
	}
	
	private static List<Item> createInput3() {
		List<Item> items = new ArrayList<Item>();
		Item importedPerfume = new Item();
		importedPerfume.setId(1);
		importedPerfume.setCategory(Category.FASHION);
		importedPerfume.setQuantity(1);
		importedPerfume.setDescription("Imported Bottle of Pefume");
		importedPerfume.setPrice(new BigDecimal("27.99").setScale(2, BigDecimal.ROUND_CEILING));
		importedPerfume.setImported(true);
		items.add(importedPerfume);
		Item perfume = new Item();
		perfume.setId(3);
		perfume.setCategory(Category.FASHION);
		perfume.setQuantity(1);
		perfume.setDescription("Bottle of Perfume");
		perfume.setPrice(new BigDecimal("18.99").setScale(2, BigDecimal.ROUND_CEILING));
		items.add(perfume);
		Item headPills = new Item();
		headPills.setId(3);
		headPills.setCategory(Category.MEDICINE);
		headPills.setQuantity(1);
		headPills.setDescription("Packet of Head Pills");
		headPills.setPrice(new BigDecimal("9.75").setScale(2, BigDecimal.ROUND_CEILING));
		items.add(headPills);
		Item chocolates = new Item();
		chocolates.setId(4);
		chocolates.setCategory(Category.FOOD);
		chocolates.setQuantity(1);
		chocolates.setDescription("Imported Box of Chocolates");
		chocolates.setPrice(new BigDecimal("11.25").setScale(2, BigDecimal.ROUND_CEILING));
		chocolates.setImported(true);
		items.add(chocolates);
		return items;
	}
	
}
