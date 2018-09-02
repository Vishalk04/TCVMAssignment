package com.yash.dao;

import java.util.HashMap;
import java.util.Map;

import com.yash.exceptions.ContainerOverflowException;
import com.yash.model.Container;
import com.yash.model.MaterialTypes;

public class ContainerDao {
	
	private static Map<MaterialTypes, Integer> containerRefillTransactions = new HashMap<MaterialTypes, Integer>();
	
	public  void initialize() {

		try { 
			Container.put(MaterialTypes.TEA, 100);
			Container.put(MaterialTypes.MILK, 100);
			Container.put(MaterialTypes.SUGER, 100);
			Container.put(MaterialTypes.COFFEE, 100);
			Container.put(MaterialTypes.WATER, 100);
			
		} catch (ContainerOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
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
		return Container.put(materialName, quantity);
		
	}
	
	public Integer get(MaterialTypes materialName) {
		return Container.get(materialName);
	}
	
	/*public void clean() {
		Container.clean();
	}*/
	
	
	public Integer getSize(MaterialTypes materialName){
		return Container.getSize(materialName);
	}
	

}
