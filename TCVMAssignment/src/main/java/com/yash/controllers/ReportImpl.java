package com.yash.controllers;

import com.yash.model.MaterialTypes;
import com.yash.services.ContainerServicesImpl;
import com.yash.services.IContainerServices;
import com.yash.services.ReportServicesImpl;

public class ReportImpl implements IReport {

	ReportServicesImpl reportServices;
	IContainerServices containerServices;

	public ReportImpl() {
		reportServices = new ReportServicesImpl();
		containerServices = new ContainerServicesImpl();
	}

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

	@Override
	public Integer checkContainerStatus(String container) {

		return containerServices.checkContainerStatus(MaterialTypes.valueOf(container.toUpperCase()));

	}
	
	@Override
	public boolean getContainerRefillReport() {

		return reportServices.getContainerRefillReport();
	}
	


	

}
