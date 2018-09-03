package com.yash.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.model.Order;

@RunWith(MockitoJUnitRunner.class)
public class OrderDaoTest {

	OrderDao orderDao = new OrderDao();
	
	@Test
	public void testGetAllOrder() {
		orderDao.getAllOrder();
	 }

	@Test
	public void testSaveOrder(){
		Order order = new Order();
		orderDao.saveOrder(order);
	}
}
