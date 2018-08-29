package com.yash.services;

import java.util.List;

import com.yash.dao.ContainerDao;
import com.yash.exceptions.ContainerOverflowException;
import com.yash.model.Beverage;
import com.yash.model.Container;
import com.yash.model.Material;
import com.yash.model.Materials;

public class ContainerServicesImpl implements IContainerServices{

	ContainerDao containerDao = new ContainerDao();
	@Override
	public boolean addMaterial(Materials materialsName, int quantity) throws ContainerOverflowException {
		
		Integer size = 0;
		
		if (containerDao.get(materialsName) != null)
			
			size = containerDao.get(materialsName);

		if (materialsName.getCapacity() < (quantity + size)) {

			throw new ContainerOverflowException("Quantity Entered exeeds the container capacity");
			
		}
			containerDao.put(materialsName, quantity);

		return true;
	}

	@Override
	public void updateMaterial(Beverage beverage,int quantity) throws ContainerOverflowException {
		List<Material> materialList = beverage.getMaterial();
		for (Material materials : materialList) {
			int newSize =  containerDao.getSize(materials.getMaterialName());
			newSize = newSize - ((materials.getConsumptionOfMaterial()+materials.getWasteOfMaterial())*quantity);
			containerDao.put(materials.getMaterialName(), newSize);
		}
	
	}

}
