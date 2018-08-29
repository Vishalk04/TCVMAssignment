package com.yash.services;

import com.yash.exceptions.ContainerOverflowException;
import com.yash.exceptions.MaterialOutOfStockException;
import com.yash.model.Beverage;
import com.yash.model.Beverages;

public interface IBeverageServices {
	
	public boolean checkBeverageAvailability(Beverages beverage, int quantity) throws MaterialOutOfStockException, ContainerOverflowException;
	
	public void despenseBeverage(Beverages beverage, int quantity) throws ContainerOverflowException;

}
