package com.yash.dao;

import java.util.HashMap;
import java.util.Map;

import com.yash.exceptions.ContainerOverflowException;
import com.yash.model.MaterialTypes;

public class ContainerDao {
	
	private static Map<MaterialTypes, Integer> containerRefillTransactions = new HashMap<MaterialTypes, Integer>();
	
	private static Map<MaterialTypes, Integer> container = new HashMap<MaterialTypes, Integer>();
	
	public  void initialize() {

		
			container.put(MaterialTypes.TEA, 100);
			container.put(MaterialTypes.MILK, 100);
			container.put(MaterialTypes.SUGER, 100);
			container.put(MaterialTypes.COFFEE, 100);
			container.put(MaterialTypes.WATER, 100);
	
 
	} 
	
	public  Map<MaterialTypes, Integer> getAllRefillTransaction(){
		
		Map<MaterialTypes, Integer> allContainerRefillTransactions = new HashMap<MaterialTypes, Integer>(containerRefillTransactions);
		if(allContainerRefillTransactions != null && !(allContainerRefillTransactions.isEmpty()))
			return allContainerRefillTransactions;
		throw new RuntimeException("Data Not Available"); 
	}
	
	public boolean addRefillTransaction(MaterialTypes materials, Integer quantity){
		
		Integer value = 0;
		if(containerRefillTransactions.get(materials) != null)
			value  = containerRefillTransactions.get(materials); 
		 containerRefillTransactions.put(materials, value+1); 
		 return true;
		
	}
	
	public  Integer put(MaterialTypes materialName, int quantity) throws ContainerOverflowException {
		return container.put(materialName, quantity);
		
	}
	
	public Integer get(MaterialTypes materialName) {
		return container.get(materialName);
	}
	
	
	
	public Integer getSize(MaterialTypes materialName){
		return  container.get(materialName);
	}
	

}
