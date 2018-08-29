package com.yash.services;

import java.util.List;

import com.yash.dao.BeverageDao;
import com.yash.exceptions.ContainerOverflowException;
import com.yash.exceptions.MaterialOutOfStockException;
import com.yash.model.Beverage;
import com.yash.model.Beverages;
import com.yash.model.Container;
import com.yash.model.Material;

public class BeverageServicesImpl implements IBeverageServices {
	

	@Override
	public boolean checkBeverageAvailability(Beverages beverageName, int quantity) throws MaterialOutOfStockException, ContainerOverflowException {
		

		Beverage selectedBeverage =  BeverageDao.getInstance().getBeverage(beverageName);
		
		List<Material> material = selectedBeverage.getMaterial();
		
		for (Material materials : material) {
 
			int requiredQuantity = (materials.getConsumptionOfMaterial()+materials.getWasteOfMaterial())*quantity ;

			if(Container.getSize(materials.getMaterialName()) <= requiredQuantity ){
				
				throw new MaterialOutOfStockException(material.toString()+" is Out Of Stock");
			}
			
		}
		
		return true;
		
	}

	@Override
	public void despenseBeverage(Beverages beverageName, int quantity) throws ContainerOverflowException {

	Beverage selectedBeverage =  BeverageDao.getInstance().getBeverage(beverageName);
			
	List<Material> material = selectedBeverage.getMaterial();
		
		for (Material materials : material) {
			int newSize =  Container.getSize(materials.getMaterialName());
			newSize = newSize - ((materials.getConsumptionOfMaterial()+materials.getWasteOfMaterial())*quantity);
			Container.put(materials.getMaterialName(), newSize);
		}
	
	}

}
