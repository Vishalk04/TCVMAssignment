package com.yash.controllers;

import com.yash.services.ReportServicesImpl;

public class ReportImpl implements IReport {

	ReportServicesImpl reportServices = new ReportServicesImpl(); 
	@Override
	public boolean generateTotalSaleReport() {
		return reportServices.generateTotalSaleReport();
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public boolean getSalesReportByBeverages() {
		return reportServices.getSalesReportByBeverages();
		// TODO Auto-generated method stub
		
	}

}
