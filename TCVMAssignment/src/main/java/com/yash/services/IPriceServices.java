package com.yash.services;

import com.yash.model.BeverageTypes;

public interface IPriceServices {
	
	public Double calculateTotalPrice(BeverageTypes beverages, int quantity);
	
	public  Double calculateChange(Double payment, Double totalPrice);
	
}
