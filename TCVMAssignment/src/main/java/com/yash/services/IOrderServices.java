package com.yash.services;

import java.util.List;

import com.yash.model.Order;

public interface IOrderServices {

	public boolean saveOrder(Order order);
	
	public List<Order> getAllOrder();
	
}
