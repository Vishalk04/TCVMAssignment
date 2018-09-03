package com.yash.dao;

import java.util.ArrayList;
import java.util.List;

import com.yash.model.BeverageTypes;
import com.yash.model.Order;

public class OrderDao {
	
	private static List<Order> orders = new ArrayList<Order>();
	
	
	public void initialize(){
		
		Order order = new Order();
		order.setTotalPrice(new Double(10.00));
		order.setBeverageId("1");
		order.setBeverages(BeverageTypes.BLACKCOFEE);
		order.setOrderId(1);
		order.setQuantity(2);
		orders.add(order);
		
		Order order1 = new Order();
		order1.setTotalPrice(new Double(10.00));
		order1.setBeverageId("1");
		order1.setBeverages(BeverageTypes.BLACKCOFEE);
		order1.setOrderId(1);
		order1.setQuantity(2);
		orders.add(order1);
		
		Order order2 = new Order();
		order2.setTotalPrice(new Double(20.00));
		order2.setBeverageId("2");
		order2.setBeverages(BeverageTypes.TEA);
		order2.setOrderId(5);
		order2.setQuantity(2);
		orders.add(order2);

		
	}
	
	public boolean saveOrder(Order order){
		return orders.add(order);	
	}
	
	public List<Order> getAllOrder(){
		
		 List<Order> list = new ArrayList<Order>(orders);
		 return list;
	}
}
