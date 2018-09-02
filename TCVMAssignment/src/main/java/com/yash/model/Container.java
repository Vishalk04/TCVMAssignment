package com.yash.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.yash.exceptions.ContainerOverflowException;

public class Container {

	private static Map<MaterialTypes, Integer> container = new HashMap<MaterialTypes, Integer>();

	public static Integer put(MaterialTypes materialName, int quantity) throws ContainerOverflowException {

		return container.put(materialName, quantity);

	}

	public static int getSize(MaterialTypes materialName) {

		if (container.get(materialName) != null)
			return container.get(materialName);
		return 0;

	}


	public static Integer get(MaterialTypes materialName) {
		// TODO Auto-generated method stub
		return container.get(materialName); 
	}

}
