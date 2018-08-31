package com.yash.services;

import java.util.List;

import com.yash.dao.BeverageDao;
import com.yash.dao.ContainerDao;
import com.yash.exceptions.ContainerOverflowException;
import com.yash.exceptions.MaterialOutOfStockException;
import com.yash.model.Beverage;
import com.yash.model.Beverages;
import com.yash.model.Container;
import com.yash.model.Material;

public class BeverageServicesImpl implements IBeverageServices { 
	
	ContainerServicesImpl  containerServicesImpl = new ContainerServicesImpl();

	BeverageDao beverageDao = new BeverageDao();	
	ContainerDao containerDao = new ContainerDao();
	
	@Override
	public boolean checkBeverageAvailability(Beverages beverageName, int quantity) throws MaterialOutOfStockException, ContainerOverflowException {
		 

		Beverage selectedBeverage =  beverageDao.getBeverage(beverageName);
		
		List<Material> material = selectedBeverage.getMaterial();
		
	
		for (Material materials : material) {  
 
			int requiredQuantity = (materials.getConsumptionOfMaterial()+materials.getWasteOfMaterial())*quantity ;

			if(containerDao.getSize(materials.getMaterialName()) < requiredQuantity ){
				
				throw new MaterialOutOfStockException(material.toString()+" is Out Of Stock");
			}
			
		}
		
		return true;
		
	}

	@Override
	public boolean despenseBeverage(Beverages beverageName, int quantity) throws ContainerOverflowException, MaterialOutOfStockException {

		Beverage selectedBeverage =  beverageDao.getBeverage(beverageName);
			
		
		containerServicesImpl.despenseMaterial(selectedBeverage,quantity);
	
		return true;
	}

}
