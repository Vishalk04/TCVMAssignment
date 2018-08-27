package com.yash.services;

public interface IDrinkServices {
	
	public boolean checkDrinkAvailability(String drink, int quantity);
	
	public boolean PrapareDrink(Drink drink);

}
