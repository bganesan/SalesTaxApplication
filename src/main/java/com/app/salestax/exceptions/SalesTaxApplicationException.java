package com.app.salestax.exceptions;

public class SalesTaxApplicationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -175627055123783968L;

	//Parameterless Constructor
	public SalesTaxApplicationException() {}

	//Constructor that accepts a message
	public SalesTaxApplicationException(String message)
	{
		super(message);
	}
}
