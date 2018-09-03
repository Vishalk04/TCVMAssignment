package com.yash.services;

import java.util.List;

import com.yash.dao.ContainerDao;
import com.yash.exceptions.ContainerOverflowException;
import com.yash.exceptions.MaterialOutOfStockException;
import com.yash.model.Beverage;
import com.yash.model.Material;
import com.yash.model.MaterialTypes;

public class ContainerServicesImpl implements IContainerServices {

	ContainerDao containerDao = new ContainerDao();

	@Override
	public Integer addMaterial(MaterialTypes materialsName, int quantity) throws ContainerOverflowException {

		Integer size = 0;

		if (containerDao.get(materialsName) != null)

			size = containerDao.get(materialsName);

		if (materialsName.getCapacity() < (quantity + size)) {

			throw new ContainerOverflowException("Quantity Entered exeeds the container capacity");

		}

		return containerDao.put(materialsName, quantity) + quantity;

	}

	@Override
	public boolean despenseMaterial(Beverage beverage, int quantity)
			throws ContainerOverflowException, MaterialOutOfStockException {

		int totalRequiredQuantity;
		List<Material> materialList = beverage.getMaterial();
		for (Material materials : materialList) {
			totalRequiredQuantity = ((materials.getConsumptionOfMaterial() + materials.getWasteOfMaterial())
					* quantity);
			int currentSize = containerDao.getSize(materials.getMaterialName());
			if (totalRequiredQuantity > currentSize) {
				throw new MaterialOutOfStockException("Material is out of stock");
			}
			currentSize = currentSize - totalRequiredQuantity;
			containerDao.put(materials.getMaterialName(), currentSize);
		}
		return true;

	}

	@Override
	public Integer refillContainer(MaterialTypes material, int quantity) throws ContainerOverflowException {
		
		int  currentAvailableMaterial = containerDao.get(material);
		
		currentAvailableMaterial += quantity;
		if(currentAvailableMaterial > material.getCapacity())
			throw new ContainerOverflowException("entered quantity exceeds the container capacity");
		
		containerDao.put(material, currentAvailableMaterial);
		
		addrefillContainerTransaction(material , quantity);
		
		return currentAvailableMaterial; 
		
	} 

	@Override
	public void resetContainers() throws ContainerOverflowException{
		MaterialTypes[] materials = MaterialTypes.values();
		
		for (MaterialTypes materials2 : materials) {
			containerDao.put(materials2, materials2.getCapacity());
		}
		
	}

	@Override
	public Integer checkContainerStatus(MaterialTypes container) {
			
		return containerDao.getSize(container);
		
	}
	
	@Override
	public boolean addrefillContainerTransaction(MaterialTypes material, Integer quantity){
		return containerDao.addRefillTransaction(material, quantity);
	}



}
