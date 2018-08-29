package com.yash.dao;

import com.yash.exceptions.ContainerOverflowException;
import com.yash.model.Container;
import com.yash.model.Materials;

public class ContainerDao {

	ContainerDao() {

		initialize();

	}

	private void initialize() {

		try {
			Container.put(Materials.TEA, 100);
			Container.put(Materials.MILK, 100);
			Container.put(Materials.SUGER, 100);
		} catch (ContainerOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
