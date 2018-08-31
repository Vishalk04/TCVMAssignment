package com.yash.model;


public class Order {

	Integer orderId;
	String beverageId;
	Beverages beverages;
	Integer quantity;
	Double totalPrice;
	
	
	 
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public String getBeverageId() {
		return beverageId;
	}
	public void setBeverageId(String beverageId) {
		this.beverageId = beverageId;
	}
	public Beverages getBeverages() {
		return beverages;
	}
	public void setBeverages(Beverages beverages) {
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
