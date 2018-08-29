package com.yash.model;

public class Order {

	String orderId;
	String beverageId;
	Beverages beverages;
	int quantity;
	double amount;
	
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	

}
