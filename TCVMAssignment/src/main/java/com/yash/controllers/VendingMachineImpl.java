package com.yash.controllers;

import java.util.List;

import com.yash.exceptions.ContainerOverflowException;
import com.yash.exceptions.MaterialOutOfStockException;
import com.yash.model.Beverages;
import com.yash.model.Order;
import com.yash.services.BeverageServicesImpl;
import com.yash.services.IBeverageServices;

public class VendingMachineImpl implements IVendingMachine{
	
	IBeverageServices beveregeServices;
	public VendingMachineImpl() throws ContainerOverflowException{
		 beveregeServices = new BeverageServicesImpl();
	}
	



	@Override
	public Double checkBeverageAvailabilityAndCalculateTotalPrice(String beverage, int quantity)
			throws MaterialOutOfStockException, ContainerOverflowException {
		// TODO Auto-generated method stub
		if( beveregeServices.checkBeverageAvailability(Beverages.valueOf(beverage), quantity))
			return 0.0;
		return 0.0;
	}





	@Override
	public boolean placeBeverageOrderAndReturnChange(String beverage, int quantity, Double amount)
			throws MaterialOutOfStockException, ContainerOverflowException {
		// TODO Auto-generated method stub
		return false;
	}




	@Override
	public boolean refillContainer(String container) {
		// TODO Auto-generated method stub
		return false;
	}




	@Override
	public boolean resetContainer(String container) {
		// TODO Auto-generated method stub
		return false;
	}




	@Override
	public List<Order> generateTotalSaleReport() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public int checkContainerStatus(String container) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	

	


	/*@Override
	public boolean checkDrinkAvailability(String beverage, int quantity) throws MaterialOutOfStockException, ContainerOverflowException {
	
		return beveregeServices.checkDrinkAvailability(Beverages.valueOf(beverage), quantity);
	}
*/


	

}
