package com.yash.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.yash.exceptions.ContainerOverflowException;
import com.yash.model.Beverage;
import com.yash.model.Beverages;
import com.yash.model.Material;
import com.yash.model.Materials;

public class BeverageDao {

	private static Map<Beverages, Beverage> availableBeverages = new HashMap<Beverages, Beverage>();
	
	//private  BeverageDao beverageDao;

	

	public void initialize() throws ContainerOverflowException {

		Beverage tea = new Beverage();
		tea.setName(Beverages.TEA);
		tea.setMaterial(Arrays.asList(new Material(Materials.TEA, 10, 10),

				new Material(Materials.MILK, 10, 10), new Material(Materials.SUGER, 10, 10)));
		Beverage coffee = new Beverage();
		coffee.setName(Beverages.COFFEE);
		coffee.setMaterial(Arrays.asList(new Material(Materials.COFFEE, 10, 10), new Material(Materials.MILK, 10, 10),
				new Material(Materials.SUGER, 10, 10)));

		availableBeverages.put(Beverages.TEA, tea);
		availableBeverages.put(Beverages.COFFEE, coffee);

	}

	public Beverage getBeverage(Beverages beverages) {

		return availableBeverages.get(beverages);

	}
	
/*	public static BeverageDao getInstance(){
		 if(beverageDao == null)
			 beverageDao = new BeverageDao();
		 return beverageDao;
		
	}*/

}
