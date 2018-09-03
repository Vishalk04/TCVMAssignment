package com.yash.services;

import com.yash.dao.BeverageDao;
import com.yash.model.Beverage;
import com.yash.model.BeverageTypes;

public class PriceServicesImpl implements IPriceServices {
	
	BeverageDao bevereagesDao = new BeverageDao();

	@Override
	public Double calculateTotalPrice(BeverageTypes beverages, int quantity) {
		
		Beverage beverage = bevereagesDao.getBeverage(beverages);
		
		if(beverage.getPrice()!=null) 
			return beverage.getPrice() * quantity;
		
		throw new RuntimeException("Price Not Available For selected beverage");
		
	}

	@Override
	public Double calculateChange(Double payment, Double totalPrice) {
	
		return payment-totalPrice;
		
	}

}
