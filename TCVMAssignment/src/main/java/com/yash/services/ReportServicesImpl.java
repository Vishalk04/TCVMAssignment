package com.yash.services;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.yash.dao.ContainerDao;
import com.yash.dao.OrderDao;
import com.yash.model.BeverageTypes;
import com.yash.model.MaterialTypes;
import com.yash.model.Order;

public class ReportServicesImpl implements IReportServices {

	OrderDao orderDao = new OrderDao();
	
	ContainerDao containerDao = new ContainerDao();

	@Override
	public boolean generateTotalSaleReport() { 
		Integer totalCups = 0;

		Double totalAmount = new Double(0);
		List<Order> orders = orderDao.getAllOrder();
		if (orders == null)
			throw new RuntimeException("Data Not Available");

		totalCups = orders.stream().collect(Collectors.summingInt(Order::getQuantity));
		totalAmount = orders.stream().collect(Collectors.summingDouble(Order::getTotalPrice));

		System.out.println("------------------------Total Sale Report----------------------------");
		System.out.println("Total Cup sale : " + totalCups);
		System.out.println("Total Amount sale : " + totalAmount.toString());
		return true;

	}

	@Override
	public boolean getSalesReportByBeverages() {

		List<Order> orders = orderDao.getAllOrder();
		if (orders == null)
			throw new RuntimeException("Data Not Available");

		Map<BeverageTypes, Integer> totalCupByBeverages = orders.stream()
				.collect(Collectors.groupingBy(Order::getBeverages, Collectors.summingInt(Order::getQuantity)));
		Map<BeverageTypes, Double> totalSaleBybeverages = orders.stream()
				.collect(Collectors.groupingBy(Order::getBeverages, Collectors.summingDouble(Order::getTotalPrice)));

		System.out.println("------------------------Sales Report By Beverages----------------------------");

		System.out.println("total Cup By Beverages: " + totalCupByBeverages.toString());

		System.out.println("total Sale By beverages: " + totalSaleBybeverages);

		return true;

	}

	@Override
	public boolean getContainerRefillReport() {
			
	System.out.println("------------------------Refill Containers Report ----------------------------");
	System.out.println(containerDao.getAllRefillTransaction());
	
	return true;
	
	}

}
