package com.app.salestax.service.util;

import java.math.BigDecimal;

import com.app.salestax.beans.items.Category;
import com.app.salestax.beans.items.Item;
import com.app.salestax.beans.shoppingcart.ShoppingCartItem;

public class TestUtil {
	public static Item createItem(int id, String description, String price, int quantity, Category category, boolean imported){
		Item item = new Item();
		item.setId(id);
		item.setDescription(description);
		item.setPrice(new BigDecimal(price));
		item.setQuantity(quantity);
		item.setCategory(category);
		item.setImported(imported);
		return item;
	}
	

	public static ShoppingCartItem createInput1Item1(){
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
		Item item = TestUtil.createItem(1, "Book", "12.49", 1, Category.BOOKS, false);
		shoppingCartItem.setItem(item);
		return shoppingCartItem;
	}
	
	public static ShoppingCartItem createInput1Item2(){
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
		Item item = TestUtil.createItem(2, "Music CD", "14.99", 1, Category.MOVIES_MUSIC, false);
		shoppingCartItem.setItem(item);
		return shoppingCartItem;
	}
	
	public static ShoppingCartItem createInput1Item3(){
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
		Item item = TestUtil.createItem(3, "Chocolate Bar", "0.85", 1, Category.FOOD, false);
		shoppingCartItem.setItem(item);
		return shoppingCartItem;
	}
	
	public static ShoppingCartItem createInput2Item1(){
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
		Item item = TestUtil.createItem(1, "Box of Chocolates", "10.00", 1, Category.FOOD, true);
		shoppingCartItem.setItem(item);
		return shoppingCartItem;
	}
	
	public static ShoppingCartItem createInput2Item2(){
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
		Item item = TestUtil.createItem(2, "Bottle of Perfume", "47.50", 1, Category.FASHION, true);
		shoppingCartItem.setItem(item);
		return shoppingCartItem;
	}
	
	public static ShoppingCartItem createInput3Item1(){
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
		Item item = TestUtil.createItem(1, "Imported Bottle of Perfume", "27.99", 1, Category.FASHION, true);
		shoppingCartItem.setItem(item);
		return shoppingCartItem;
	}
	
	public static ShoppingCartItem createInput3Item2(){
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
		Item item = TestUtil.createItem(2, "Bottle of Perfume", "18.99", 1, Category.FASHION, false);
		shoppingCartItem.setItem(item);
		return shoppingCartItem;
	}
	
	public static ShoppingCartItem createInput3Item3(){
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
		Item item = TestUtil.createItem(3, "Packet of Headache Pills", "9.75", 1, Category.MEDICINE, false);
		shoppingCartItem.setItem(item);
		return shoppingCartItem;
	}
	
	public static ShoppingCartItem createInput3Item4(){
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
		Item item = TestUtil.createItem(4, "Box of Imported Chocolates", "11.25", 1, Category.FOOD, true);
		shoppingCartItem.setItem(item);
		return shoppingCartItem;
	}
}
