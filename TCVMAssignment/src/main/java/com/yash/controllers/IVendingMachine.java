package com.yash.controllers;

import com.yash.exceptions.ContainerOverflowException;
import com.yash.exceptions.MaterialOutOfStockException;

public interface IVendingMachine{
	
	public Double checkBeverageAvailabilityAndCalculateTotalPrice(String drink, int quantity) throws MaterialOutOfStockException, ContainerOverflowException;

	public Double placeBeverageOrderAndReturnChange(String beverage, int quantity, Double price, double enteredAmount) throws MaterialOutOfStockException, ContainerOverflowException;

	public Integer refillContainer(String container, int quantity) throws ContainerOverflowException;
	
	public void resetContainer() throws ContainerOverflowException;

}
