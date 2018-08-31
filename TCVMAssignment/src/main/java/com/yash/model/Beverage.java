package com.yash.model;

import java.util.List;

public class Beverage {

	Beverages name;
	String beverageId;
	Double price;
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

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
