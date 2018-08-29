package com.yash.model;

import java.util.List;

public class Beverage {

	Beverages name;
	String beverageId;
	private List<Material> material;
	
	public Beverages getName() {
		return name;
	}

	public void setName(Beverages name) {
		this.name = name;
	}


	


	public List<Material> getMaterial() {
		return material;
	}

	public void setMaterial(List<Material> list) {
		this.material = list;
	}


}
