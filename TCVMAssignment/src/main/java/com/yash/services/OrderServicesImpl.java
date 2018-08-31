package com.yash.services;

import java.util.List;

import com.yash.dao.OrderDao;
import com.yash.model.Order;

public class OrderServicesImpl implements IOrderServices{
	OrderDao orderDao = new OrderDao();

	public boolean saveOrder(Order order) {

		return orderDao.saveOrder(order);
	}
	
	public List<Order> getAllOrder(){
		
		return orderDao.getAllOrder();
		
	}

}
