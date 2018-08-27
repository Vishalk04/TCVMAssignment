package com.yash.services;

public interface IPriceServies {
	
	public Double calculateTotalPrice(String drink, int quantity);
	
	public  Double calculateChange(Double payment);

}
