package com.yash.services;

import com.yash.exceptions.ContainerOverflowException;
import com.yash.exceptions.MaterialOutOfStockException;
import com.yash.model.Beverage;
import com.yash.model.BeverageTypes;

public interface IBeverageServices {
	
	public boolean checkBeverageAvailability(BeverageTypes beverage, int quantity) throws MaterialOutOfStockException, ContainerOverflowException;
	
	public boolean despenseBeverage(BeverageTypes beverage, int quantity) throws ContainerOverflowException, MaterialOutOfStockException;

}
