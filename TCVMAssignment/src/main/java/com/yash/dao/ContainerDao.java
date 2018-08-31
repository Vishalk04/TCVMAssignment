package com.yash.dao;

import java.util.HashMap;
import java.util.Map;

import com.yash.exceptions.ContainerOverflowException;
import com.yash.model.Container;
import com.yash.model.Materials;

public class ContainerDao {
	
	private static Map<Materials, Integer> containerRefillTransactions = new HashMap<Materials, Integer>();
	
	public  void initialize() {

		try { 
			Container.put(Materials.TEA, 100);
			Container.put(Materials.MILK, 100);
			Container.put(Materials.SUGER, 100);
			Container.put(Materials.COFFEE, 100);
			Container.put(Materials.WATER, 100);
			
		} catch (ContainerOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	} 
	
	public  Map<Materials, Integer> getAllRefillTransaction(){
		
		Map<Materials, Integer> allContainerRefillTransactions = new HashMap<Materials, Integer>(containerRefillTransactions);
		if(allContainerRefillTransactions != null && !(allContainerRefillTransactions.isEmpty()))
			return allContainerRefillTransactions;
		throw new RuntimeException("Data Not Available");
	}
	
	public boolean addRefillTransaction(Materials materials, Integer quantity){
		
		Integer value = 0;
		if(containerRefillTransactions.get(materials) != null)
			value  = containerRefillTransactions.get(materials); 
		 containerRefillTransactions.put(materials, value+1); 
		 return true;
		
	}
	
	public  Integer put(Materials materialName, int quantity) throws ContainerOverflowException {
		return Container.put(materialName, quantity);
		
	}
	
	public Integer get(Materials materialName) {
		return Container.get(materialName);
	}
	
	/*public void clean() {
		Container.clean();
	}*/
	
	
	public Integer getSize(Materials materialName){
		return Container.getSize(materialName);
	}
	

}
