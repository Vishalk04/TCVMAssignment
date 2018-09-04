package com.yash.controllers;

import com.yash.exceptions.ContainerOverflowException;
import com.yash.exceptions.MaterialOutOfStockException;
import com.yash.model.BeverageTypes;
import com.yash.model.MaterialTypes;
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

	private Order order;

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

		

		beveregeServices.checkBeverageAvailability(BeverageTypes.valueOf(beverage), quantity);
	

		Double totalPrice = priceServices.calculateTotalPrice(BeverageTypes.valueOf(beverage), quantity);

		

		return totalPrice;

	}

	@Override
	public Double placeBeverageOrderAndReturnChange(String beverage, int quantity, Double price, double enteredAmount)
			throws MaterialOutOfStockException, ContainerOverflowException {
	
		beveregeServices.despenseBeverage(BeverageTypes.valueOf(beverage), quantity);
		
		order = new Order(); 
		 
		
		order.setBeverages(BeverageTypes.valueOf(beverage));
		order.setQuantity(quantity);
		order.setTotalPrice(price);
		
		orderServices.saveOrder(order);
		
		return priceServices.calculateChange(enteredAmount, price);
		
	}

	@Override
	public Integer refillContainer(String container, int quantity) throws ContainerOverflowException {

		return containerServices.refillContainer(MaterialTypes.valueOf(container.toUpperCase()), quantity);

	}

	@Override
	public void resetContainer() throws ContainerOverflowException {
	
		containerServices.resetContainers();
	}



}
