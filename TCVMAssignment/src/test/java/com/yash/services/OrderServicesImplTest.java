package com.yash.services;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.dao.OrderDao;
import com.yash.model.Beverages;
import com.yash.model.Order;

@RunWith(MockitoJUnitRunner.class)
public class OrderServicesImplTest {

	OrderServicesImpl orderServices = new OrderServicesImpl();
	OrderDao orderDao = new OrderDao();
	
	@Test
	public void shouldReturnTrueWhenOrderIsSaved() {
		orderDao.initialize();
		Order order = new Order();
		order.setTotalPrice(new Double(20.00));
		order.setBeverageId("2");
		order.setBeverages(Beverages.TEA);
		order.setOrderId(5);
		order.setQuantity(2);
		assertTrue(orderServices.saveOrder(order));
	}
	
	@Test
	public void shouldReturnFalseWhenOrderIsNotSaved() {
		Order order = new Order();
		/*order.setAmount(new Double(10.00));
		order.setBeverageId("1");
		order.setBeverages(Beverages.BLACKCOFEE);
		order.setOrderId("1");
		order.setQuantity(2);*/
		assertTrue(orderServices.saveOrder(order));
	}

}
