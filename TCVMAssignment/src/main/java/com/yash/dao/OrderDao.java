package com.yash.dao;

import java.util.ArrayList;
import java.util.List;

import com.yash.model.BeverageTypes;
import com.yash.model.Order;

public class OrderDao {
	
	private static List<Order> orders = new ArrayList<Order>();
	
	
	
	public boolean saveOrder(Order order){
		return orders.add(order);	
	} 
	
	public List<Order> getAllOrder(){
		 List<Order> list = new ArrayList<Order>(orders);
		 return list;
	}
	
}
