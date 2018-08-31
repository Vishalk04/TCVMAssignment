package com.yash.controllers;

import java.util.List;

import com.yash.exceptions.ContainerOverflowException;
import com.yash.exceptions.MaterialOutOfStockException;
import com.yash.model.Beverages;
import com.yash.model.Materials;
import com.yash.model.Order;
import com.yash.services.BeverageServicesImpl;
import com.yash.services.ContainerServicesImpl;
import com.yash.services.IBeverageServices;
import com.yash.services.IContainerServices;
import com.yash.services.IPriceServices;
import com.yash.services.OrderServicesImpl;
import com.yash.services.PriceServicesImpl;

public class VendingMachineImpl implements IVendingMachine {

	IBeverageServices beveregeServices;

	IPriceServices priceServices;
	 
	IContainerServices containerServices;
	
	Order order;
	
	OrderServicesImpl orderServices;

	public VendingMachineImpl() throws ContainerOverflowException {

		beveregeServices = new BeverageServicesImpl();
		priceServices = new PriceServicesImpl();
		containerServices = new ContainerServicesImpl();
		orderServices = new OrderServicesImpl();  
	}

	@Override
	public Double checkBeverageAvailabilityAndCalculateTotalPrice(String beverage, int quantity)
			throws MaterialOutOfStockException, ContainerOverflowException {
		
		order = new Order();
		
		beveregeServices.checkBeverageAvailability(Beverages.valueOf(beverage), quantity);
		order.setBeverages(Beverages.valueOf(beverage));
		order.setQuantity(quantity);		

		Double totalPrice = priceServices.calculateTotalPrice(Beverages.valueOf(beverage), quantity);
				
		order.setTotalPrice(totalPrice);

		
		
		return totalPrice;
		
	} 

	@Override
	public Double placeBeverageOrderAndReturnChange(String beverage, int quantity, Double amount)
			throws MaterialOutOfStockException, ContainerOverflowException {
				
		beveregeServices.despenseBeverage(Beverages.valueOf(beverage), quantity);
		Double totalPrice = order.getTotalPrice();
		orderServices.saveOrder(order);
		return	 priceServices.calculateChange(amount, totalPrice);			
	}
	
	@Override
	public Integer refillContainer(String container, int quantity) throws ContainerOverflowException {

		return containerServices.refillContainer(Materials.valueOf(container.toUpperCase()), quantity);
		
	}

	@Override
	public void resetContainer() throws ContainerOverflowException {
	
	containerServices.resetContainers();
		
	}


	@Override
	public Integer checkContainerStatus(String container) {
		
		return containerServices.checkContainerStatus(Materials.valueOf(container.toUpperCase()));
		
	}



}
