package com.yash.services;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.xml.sax.DocumentHandler;

import com.yash.dao.ContainerDao;
import com.yash.dao.OrderDao;
import com.yash.model.Beverages;
import com.yash.model.Order;

@RunWith(MockitoJUnitRunner.class)
public class ReportServicesImplTest {

	@InjectMocks
	ReportServicesImpl reportServices;

	@Mock
	OrderDao orderDao;

	@Mock
	ContainerDao containerDao;

	List<Order> orders;

	@Before
	public void setUp() {
		orders = new ArrayList<Order>();
		Order order = new Order();
		order.setTotalPrice(new Double(10.00));
		order.setBeverageId("1");
		order.setBeverages(Beverages.BLACKCOFEE);
		order.setOrderId(1);
		order.setQuantity(2);
		orders.add(order);

		Order order1 = new Order();
		order1.setTotalPrice(new Double(10.00));
		order1.setBeverageId("1");
		order1.setBeverages(Beverages.BLACKCOFEE);
		order1.setOrderId(1);
		order1.setQuantity(2);
		orders.add(order1);

		Order order2 = new Order();
		order2.setTotalPrice(new Double(20.00));
		order2.setBeverageId("2");
		order2.setBeverages(Beverages.TEA);
		order2.setOrderId(5);
		order2.setQuantity(2);
		orders.add(order2);
	}

	@Test
	public void shouldPrintTotalSaleReportWhenOrderDataIsAvailable() {
		when(orderDao.getAllOrder()).thenReturn(orders);
		assertTrue(reportServices.generateTotalSaleReport());
		verify(orderDao).getAllOrder();
	}

	@Test(expected = RuntimeException.class)
	public void shouldThrowExceptionWhenOrderDataIsNotAvailable() {
		orders = null;
		when(orderDao.getAllOrder()).thenReturn(orders);
		reportServices.generateTotalSaleReport();
		verify(orderDao).getAllOrder();
	}

	@Test
	public void shouldPrintSaleReportOrderDataIsAvailable() {
		when(orderDao.getAllOrder()).thenReturn(orders);
		reportServices.getSalesReportByBeverages();
		verify(orderDao).getAllOrder();
	}

	@Test(expected = RuntimeException.class)
	public void shouldThrowRuntimeExceptionWhenOrderDataIsNotAvailable() {

		orders = null;
		when(orderDao.getAllOrder()).thenReturn(orders);
		reportServices.getSalesReportByBeverages();
		verify(orderDao).getAllOrder();

	}

	@Test(expected = RuntimeException.class)
	public void shouldThrowRuntimeExceptionWhenRefillDataIsNotAvailable() {
	//	Mockito.doThrow(RuntimeException.class).when(containerDao).getAllRefillTransaction();
		reportServices.getContainerRefillReport();
		//verify(containerDao).getAllRefillTransaction();

	}
	
	@Test
	public void shouldPrintReport(){
		assertTrue(reportServices.getContainerRefillReport());
	}

}
