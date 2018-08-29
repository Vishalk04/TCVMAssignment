package com.yash.model;

public enum Materials {
	
	TEA(100), COFFEE(100), MILK(100), SUGER(100), WATER(100);
	
	private int capacity;
	
	Materials(int capacity){
		this.capacity = capacity;
	}
	
	public int getCapacity(){
		return this.capacity;
	}

}
