package com.app.salestax.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.app.salestax.constants.SalesTaxConstant;
import com.app.salestax.exceptions.SalesTaxApplicationException;
/**
* The util method for the SalesTax application
* to read the properties file which has the 
* rules for calculating the taxes.
*
* @author  Balasubramanian Ganesan
* @version 1.0
* @since   2014-08-15 
*/
public class SalesTaxUtil {
	private final static String CLASS_NAME = SalesTaxUtil.class.getName();
	private final static Logger LOGGER = Logger.getLogger(CLASS_NAME); 
	public String getProperty(String key) throws Exception{
		final String METHOD_NAME = "getProperty";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);
		InputStream inputStream = null;
		String propertiesValue = null;
		try {
			Properties prop = new Properties();
			String propFileName = "salesTax.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			prop.load(inputStream);
			// get value from key
			propertiesValue = prop.getProperty(key);
			if(LOGGER.isInfoEnabled()) LOGGER.info(CLASS_NAME+METHOD_NAME+": propertiesValue fetched for key :"+key+" value :"+propertiesValue);

		} catch (Exception e) {
			throw new SalesTaxApplicationException(SalesTaxConstant._ERR_PROPERTY_NOT_FOUND);
		}finally{
			try{
				inputStream.close();
			}
			catch(Exception e){
				throw new SalesTaxApplicationException(SalesTaxConstant._ERR_GENERIC_EXCEPTION);
			}
			
		}
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);
		return propertiesValue;
	}

	public List<String> getExemptCategory() throws Exception{
		final String METHOD_NAME = "getExemptCategory";
		if(LOGGER.isInfoEnabled()) LOGGER.info("Entering :"+CLASS_NAME+" "+METHOD_NAME);
		List<String> exemptList = new ArrayList<String>();
		String exemptCategory = getProperty(SalesTaxConstant.EXEMPT_CATEGORIES);
		if(LOGGER.isInfoEnabled()) LOGGER.info(CLASS_NAME+METHOD_NAME+": exemptCategory from properties file :"+exemptCategory);
		String [] exemptCategoryArray = exemptCategory.split(":");

		for(String category:exemptCategoryArray){
			exemptList.add(category);
		}
		if(LOGGER.isInfoEnabled()) LOGGER.info("Exiting :"+CLASS_NAME+" "+METHOD_NAME);
		return exemptList;
	}
}
