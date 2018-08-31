package com.yash.services;

import com.yash.exceptions.ContainerOverflowException;
import com.yash.exceptions.MaterialOutOfStockException;
import com.yash.model.Beverage;
import com.yash.model.Materials;

public interface IContainerServices{

	public Integer addMaterial(Materials MaterialsName, int quantity) throws ContainerOverflowException;
	
	public boolean despenseMaterial(Beverage beverage, int quantity) throws ContainerOverflowException, MaterialOutOfStockException;

	public Integer refillContainer(Materials material, int quantity) throws ContainerOverflowException;
	
	public void resetContainers() throws ContainerOverflowException;
	
	public Integer checkContainerStatus(Materials container);

	public boolean addrefillContainerTransaction(Materials material, Integer quantity);

	
}
