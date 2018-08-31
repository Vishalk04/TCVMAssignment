package com.yash.services;

import com.yash.model.Beverages;

public interface IPriceServices {
	
	public Double calculateTotalPrice(Beverages beverages, int quantity);
	
	public  Double calculateChange(Double payment, Double totalPrice);
	
}
