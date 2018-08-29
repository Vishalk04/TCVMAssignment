package com.yash.dao;

import com.yash.exceptions.ContainerOverflowException;
import com.yash.model.Container;
import com.yash.model.Materials;

public class ContainerDao {
	
	public  void initialize() {

		try {
			Container.put(Materials.TEA, 100);
			Container.put(Materials.MILK, 100);
			Container.put(Materials.SUGER, 100);
		} catch (ContainerOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public  Integer put(Materials materialName, int quantity) throws ContainerOverflowException {
		return Container.put(materialName, quantity);
		
	}
	
	public Integer get(Materials materialName) {
		return Container.get(materialName);
	}
	
	public void clean() {
		Container.clean();
	}
	
	
	public int getSize(Materials materialName){
		return Container.getSize(materialName);
	}
	

}
