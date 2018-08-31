package com.yash.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.yash.exceptions.ContainerOverflowException;

public class Container {

	private static Map<Materials, Integer> container = new HashMap<Materials, Integer>();

	public static Integer put(Materials materialName, int quantity) throws ContainerOverflowException {

		return container.put(materialName, quantity);

	}

	public static int getSize(Materials materialName) {

		if (container.get(materialName) != null)
			return container.get(materialName);
		return 0;

	}

	public static void clean() {

		Set<Materials> keyset = container.keySet();

		for (Materials materials : keyset) {
			container.put(materials, 0);
		}

	}

	public static Integer get(Materials materialName) {
		// TODO Auto-generated method stub
		return container.get(materialName); 
	}

}
