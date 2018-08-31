package com.yash;

import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.controllers.IReport;
import com.yash.controllers.ReportImpl;
import com.yash.controllers.VendingMachineImpl;
import com.yash.exceptions.ContainerOverflowException;
import com.yash.exceptions.MaterialOutOfStockException;
import com.yash.model.InputScanner;

@RunWith(MockitoJUnitRunner.class)
public class AppTest extends App {

	
	@InjectMocks
	App app = new App();
	
	@Mock
	VendingMachineImpl vendingMachine;
	
	@Mock
	ReportImpl report = new ReportImpl();

	
	
@Test
public void testprepareBeverage() throws MaterialOutOfStockException, ContainerOverflowException{
	
	Mockito.when(vendingMachine.checkBeverageAvailabilityAndCalculateTotalPrice("TEA", 1)).thenReturn(10.00);
	Mockito.when(vendingMachine.placeBeverageOrderAndReturnChange("TEA", 1, 10.00)).thenReturn(0.00);
	app.prepareBeverage("TEA");
}


@Mock
InputScanner scan;
@Test
public void testReports() {
	
	Mockito.when(scan.getString()).thenReturn("1").thenReturn("5");
	Mockito.when(report.generateTotalSaleReport()).thenReturn(true);
//	Mockito.when(report.generateTotalSaleReport()).thenReturn(true);
	
	
	app.reports();
	Mockito.verify(report).generateTotalSaleReport();
	
	
}





}
