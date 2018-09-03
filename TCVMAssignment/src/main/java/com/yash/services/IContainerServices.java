package com.yash.services;

import com.yash.exceptions.ContainerOverflowException;
import com.yash.exceptions.MaterialOutOfStockException;
import com.yash.model.Beverage;
import com.yash.model.MaterialTypes;

public interface IContainerServices{

	public Integer addMaterial(MaterialTypes MaterialsName, int quantity) throws ContainerOverflowException;
	
	public boolean despenseMaterial(Beverage beverage, int quantity) throws ContainerOverflowException, MaterialOutOfStockException;

	public Integer refillContainer(MaterialTypes material, int quantity) throws ContainerOverflowException;
	
	public void resetContainers() throws ContainerOverflowException;
	
	public Integer checkContainerStatus(MaterialTypes container);

	public boolean addrefillContainerTransaction(MaterialTypes material, Integer quantity);

	
}
