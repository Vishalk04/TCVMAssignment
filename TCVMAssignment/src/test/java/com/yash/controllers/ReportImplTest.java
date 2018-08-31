package com.yash.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.services.IContainerServices;
import com.yash.services.ReportServicesImpl;

@RunWith(MockitoJUnitRunner.class)
public class ReportImplTest {

	@InjectMocks
	ReportImpl reportImpl;
	
	@Mock
	ReportServicesImpl reportServices;
	
	@Mock
	IContainerServices containerServices;
	
	
	@Test
	public void testGenerateTotalSaleReport() {
		Mockito.when(reportServices.generateTotalSaleReport()).thenReturn(true);
		
		reportImpl.generateTotalSaleReport();
		
		Mockito.verify(reportServices).generateTotalSaleReport();
	} 
	
	@Test
	public void testgetSalesReportByBeverages(){
		reportImpl.getSalesReportByBeverages();
	}
	

}
