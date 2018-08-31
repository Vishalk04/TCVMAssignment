package com.yash.controllers;

public interface IReport {
	
	public boolean generateTotalSaleReport();

	public boolean getSalesReportByBeverages(); 

	public Integer checkContainerStatus(String container);
	
	public boolean getContainerRefillReport();
	
}
