package com.yash.services;

import java.util.List;

import com.yash.exceptions.ContainerOverflowException;
import com.yash.model.Beverage;
import com.yash.model.Material;
import com.yash.model.Materials;

public interface IContainerServices{

	public boolean addMaterial(Materials MaterialsName, int quantity) throws ContainerOverflowException;
	
	public void updateMaterial(Beverage beverage, int quantity) throws ContainerOverflowException;

}
