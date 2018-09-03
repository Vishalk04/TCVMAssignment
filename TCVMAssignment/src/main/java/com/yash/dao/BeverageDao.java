package com.yash.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.yash.exceptions.ContainerOverflowException;
import com.yash.model.Beverage;
import com.yash.model.BeverageTypes;
import com.yash.model.Material;
import com.yash.model.MaterialTypes;

public class BeverageDao {

	private static Map<BeverageTypes, Beverage> availableBeverages = new HashMap<BeverageTypes, Beverage>();

	public void initialize() throws ContainerOverflowException {

		Beverage tea = new Beverage(); 
		tea.setName(BeverageTypes.TEA);
		tea.setPrice(10.00);
		tea.setMaterial(Arrays.asList(new Material(MaterialTypes.TEA, 10, 10),
				
				new Material(MaterialTypes.MILK, 10, 10), new Material(MaterialTypes.SUGER, 10, 10)));
		
		Beverage coffee = new Beverage();
		//coffee.setPrice(10.00);
		coffee.setName(BeverageTypes.COFFEE);
		coffee.setMaterial(Arrays.asList(new Material(MaterialTypes.COFFEE, 10, 10), new Material(MaterialTypes.MILK, 10, 10),
				new Material(MaterialTypes.SUGER, 10, 10)));

		availableBeverages.put(BeverageTypes.TEA, tea);
		availableBeverages.put(BeverageTypes.COFFEE, coffee);

	}

	public Beverage getBeverage(BeverageTypes beverages) {

		return availableBeverages.get(beverages);

	}

}
