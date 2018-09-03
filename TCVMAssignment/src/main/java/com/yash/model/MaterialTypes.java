package com.yash.model;

public enum MaterialTypes {
	
	TEA(100), COFFEE(100), MILK(100), SUGER(100), WATER(100);
	
	private int capacity;
	
	MaterialTypes(int capacity){
		this.capacity = capacity;
	}
	
	public int getCapacity(){
		return this.capacity;
	}

}
