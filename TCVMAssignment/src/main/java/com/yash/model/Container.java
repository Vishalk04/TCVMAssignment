package com.yash.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.yash.exceptions.ContainerOverflowException;




public class Container{
	
	
	
	private static Map<Materials, Integer> container = new HashMap<Materials, Integer>();
	
	
	public static boolean put(Materials materialName, int quantity) throws ContainerOverflowException {

		Integer size = 0;
		
		if (container.get(materialName) != null)
			
			size = container.get(materialName);

		if (materialName.getCapacity() < (quantity + size)) {

			throw new ContainerOverflowException("Quantity Entered exeeds the container capacity");
	
		}
		container.put(materialName, quantity);

		return true;

	}         
	
	public static int getSize(Materials materialName){
		
		
		if(container.get(materialName) != null)
			return container.get(materialName);
		return 0;

		
	}
	
}
