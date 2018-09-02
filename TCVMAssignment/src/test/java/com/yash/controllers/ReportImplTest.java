package com.yash.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.model.Materials;
import com.yash.services.ContainerServicesImpl;
import com.yash.services.IContainerServices;
import com.yash.services.ReportServicesImpl;

@RunWith(MockitoJUnitRunner.class)
public class ReportImplTest {

	@InjectMocks
	ReportImpl reportImpl;

	@Mock
	ReportServicesImpl reportServices;

	@Mock
	ContainerServicesImpl containerServices;

	@Test
	public void testGenerateTotalSaleReport() {
		Mockito.when(reportServices.generateTotalSaleReport()).thenReturn(true);

		assertTrue(reportImpl.generateTotalSaleReport());

		Mockito.verify(reportServices).generateTotalSaleReport();
	}

	@Test
	public void testgetSalesReportByBeverages() {
		Mockito.when(reportServices.getSalesReportByBeverages()).thenReturn(true);

		assertTrue(reportImpl.getSalesReportByBeverages());

		Mockito.verify(reportServices).getSalesReportByBeverages();

	}

	@Test
	public void testheckContainerStatus() {
		Mockito.when(containerServices.checkContainerStatus(Materials.TEA)).thenReturn(1);
		assertEquals(new Integer(1), reportImpl.checkContainerStatus("TEA"));
		Mockito.verify(containerServices).checkContainerStatus(Materials.TEA);
	}
	
	@Test
	public void testTetContainerRefillReport(){
		
		Mockito.when(reportServices.getContainerRefillReport()).thenReturn(true);
		assertTrue(reportImpl.getContainerRefillReport());
		Mockito.verify(reportServices).getContainerRefillReport();
		
	}
}
