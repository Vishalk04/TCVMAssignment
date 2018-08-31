package com.yash.controllers;

import java.util.List;

import com.yash.exceptions.ContainerOverflowException;
import com.yash.exceptions.MaterialOutOfStockException;
import com.yash.model.Materials;
import com.yash.model.Order;

public interface IVendingMachine{
	
	public Double checkBeverageAvailabilityAndCalculateTotalPrice(String drink, int quantity) throws MaterialOutOfStockException, ContainerOverflowException;

	public Double placeBeverageOrderAndReturnChange(String beverage, int quantity, Double amount) throws MaterialOutOfStockException, ContainerOverflowException;

	public Integer refillContainer(String container, int quantity) throws ContainerOverflowException;
	
	public void resetContainer() throws ContainerOverflowException;
	
	public Integer checkContainerStatus(String container);

	
	
	
	//public Double calculateTotalPrice(String drink, int quantity);
	//public Double makePaymentAndChange(Double amount, Double Price);
	
}
