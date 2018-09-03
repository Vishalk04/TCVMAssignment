package com.yash.model;


public class Order {

	Integer orderId;
	String beverageId;
	BeverageTypes beverages;
	Integer quantity;
	Double totalPrice;
	
	
	 
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setBeverageId(String beverageId) {
		this.beverageId = beverageId;
	}
	public BeverageTypes getBeverages() {
		return beverages;
	}
	public void setBeverages(BeverageTypes beverages) {
		this.beverages = beverages;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	
	
	

}
