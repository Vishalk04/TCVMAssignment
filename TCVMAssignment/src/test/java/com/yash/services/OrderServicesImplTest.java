package com.yash.services;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.dao.OrderDao;
import com.yash.model.BeverageTypes;
import com.yash.model.Order;

@RunWith(MockitoJUnitRunner.class)
public class OrderServicesImplTest {
	@InjectMocks
	OrderServicesImpl orderServices;
	
	@Mock
	OrderDao orderDao;
	
	@Test
	public void shouldReturnTrueWhenOrderIsSaved() {
		
		Order order = new Order();
		order.setTotalPrice(new Double(20.00));
		order.setBeverageId("2");
		order.setBeverages(BeverageTypes.TEA);
		order.setOrderId(5);
		order.setQuantity(2);
		
		Mockito.when(orderDao.saveOrder(order)).thenReturn(true);
		assertTrue(orderServices.saveOrder(order));
		Mockito.verify(orderDao).saveOrder(order);
	} 
	
	@Test
	public void shouldReturnFalseWhenOrderIsNotSaved() {
		Order order = new Order();
		order.setTotalPrice(new Double(20.00));
		order.setBeverageId("2");
		order.setBeverages(BeverageTypes.TEA);
		order.setOrderId(5);
		order.setQuantity(2);
		
		Mockito.when(orderDao.saveOrder(order)).thenReturn(true);

		assertTrue(orderServices.saveOrder(order));
		
		Mockito.verify(orderDao).saveOrder(order);
		
	}
	
	@Test
	public void testGetAllOrder(){
		
		List<Order> orders = new ArrayList<Order>();
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
		 
		Mockito.when(orderDao.getAllOrder()).thenReturn(orders);
		orderServices.getAllOrder();
		Mockito.verify(orderDao).getAllOrder();
		
	}

}
