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
	Mockito.when(vendingMachine.placeBeverageOrderAndReturnChange("TEA", 1, 10.00,10.00)).thenReturn(0.00);
	app.prepareBeverage("TEA");
}


@Mock
InputScanner scan;

@Test
public void testReportsSwitchCase1() {
	

	
	Mockito.when(scan.getString()).thenReturn("1").thenReturn("5").thenReturn("4");
	Mockito.when(report.generateTotalSaleReport()).thenReturn(true);
	
	
	app.reports();
	
	Mockito.verify(report).generateTotalSaleReport();

}


@Test
public void testReportsSwitchCase2() {
	

	
	Mockito.when(scan.getString()).thenReturn("2").thenReturn("5").thenReturn("4");
	Mockito.when(report.getSalesReportByBeverages()).thenReturn(true);
//	Mockito.when(report.generateTotalSaleReport()).thenReturn(true);
	
	
	app.reports();
	
	Mockito.verify(report).getSalesReportByBeverages();

}
@Test
public void testReportsSwitchCase3() {
	Mockito.when(scan.getString()).thenReturn("3").thenReturn("TEA").thenReturn("5").thenReturn("4");
	Mockito.when( report.checkContainerStatus("TEA")).thenReturn(100);
//	Mockito.when(report.generateTotalSaleReport()).thenReturn(true);
	
	 
	app.reports();
	Mockito.verify(report).checkContainerStatus("TEA");
	
	}
	
@Test
public void testReportsSwitchCase4() {


	Mockito.when(scan.getString()).thenReturn("4").thenReturn("5").thenReturn("4");
	Mockito.when(report.getContainerRefillReport()).thenReturn(true);
	
	app.reports();
	
	Mockito.verify(report).getContainerRefillReport();

}

@Test
public void testReportsSwitchCase5() {


	Mockito.when(scan.getString()).thenReturn("5").thenReturn("4");
	app.reports();
	
}


	
@Test
public void testSetUpContainerSwitchCase1() throws ContainerOverflowException{
	
	Mockito.when(scan.getString()).thenReturn("1").thenReturn("TEA");
	Mockito.when(scan.nextInt()).thenReturn(10); 
	Mockito.when(vendingMachine.refillContainer("TEA", 1)).thenReturn(90);
	app.setUpContainer();
}

@Test
public void testSetUpContainerSwitchCase2() throws ContainerOverflowException{
	
	Mockito.when(scan.getString()).thenReturn("2").thenReturn("3");
	Mockito.doNothing().when(vendingMachine).resetContainer();
	app.setUpContainer();
}

@Test
public void testSetUpContainerSwitchCase3() throws ContainerOverflowException{
	
	Mockito.when(scan.getString()).thenReturn("3").thenReturn("4");
	app.setUpContainer();
}





@Test
public void testStartSwitchCase1(){
	
	Mockito.when(scan.getString()).thenReturn("4");//.thenReturn("5").thenReturn("4");
	//Mockito.doNothing().when(app).customerMenu();
	 app.start();
}

@Test
public void testStartSwitchCase5(){
	
	Mockito.when(scan.getString()).thenReturn("5");//.thenReturn("5").thenReturn("4");
	//Mockito.doNothing().when(app).customerMenu();
	 app.start();
}

@Test
public void testCustomerMenuSwitchCase6(){
	Mockito.when(scan.getString()).thenReturn("6");
	app.customerMenu();
}

@Test
public void testCustomerMenuSwitchCase1() throws MaterialOutOfStockException, ContainerOverflowException{
	Mockito.when(scan.getString()).thenReturn("1");

	Mockito.when(scan.nextInt()).thenReturn(1);
	
	Mockito.when(vendingMachine.checkBeverageAvailabilityAndCalculateTotalPrice("TEA",1)).thenReturn(10.00);
	
	Mockito.when(vendingMachine.placeBeverageOrderAndReturnChange("TEA", 1, 10.00, 10.00)).thenReturn(0.00);
	
	
	Mockito.when(scan.nextDouble()).thenReturn(5.00).thenReturn(5.00);

	app.customerMenu();
}


@Test
public void testCustomerMenuSwitchCase2() throws MaterialOutOfStockException, ContainerOverflowException{
	Mockito.when(scan.getString()).thenReturn("2");

	Mockito.when(scan.nextInt()).thenReturn(1);
	
	Mockito.when(vendingMachine.checkBeverageAvailabilityAndCalculateTotalPrice("TEA",1)).thenReturn(10.00);
	
	Mockito.when(vendingMachine.placeBeverageOrderAndReturnChange("TEA", 1,10.00, 20.00)).thenReturn(10.00);
	
	Mockito.when(scan.nextDouble()).thenReturn(20.00);

	app.customerMenu();
}


@Test
public void testCustomerMenuSwitchCase3() throws MaterialOutOfStockException, ContainerOverflowException{
	Mockito.when(scan.getString()).thenReturn("3");

	Mockito.when(scan.nextInt()).thenReturn(1);
	
	Mockito.when(vendingMachine.checkBeverageAvailabilityAndCalculateTotalPrice("TEA",1)).thenReturn(10.00);
	
	Mockito.when(vendingMachine.placeBeverageOrderAndReturnChange("TEA", 1,10.00, 20.00)).thenReturn(10.00);
	
	Mockito.when(scan.nextDouble()).thenReturn(20.00);

	app.customerMenu();
}


@Test
public void testCustomerMenuSwitchCase4() throws MaterialOutOfStockException, ContainerOverflowException{
	Mockito.when(scan.getString()).thenReturn("4");

	Mockito.when(scan.nextInt()).thenReturn(1);
	
	Mockito.when(vendingMachine.checkBeverageAvailabilityAndCalculateTotalPrice("TEA",1)).thenReturn(10.00);
	
	Mockito.when(vendingMachine.placeBeverageOrderAndReturnChange("TEA", 1,10.00, 20.00)).thenReturn(10.00);
	
	Mockito.when(scan.nextDouble()).thenReturn(20.00);

	app.customerMenu();
	
}


@Test
public void testCustomerMenuSwitchCase5() throws MaterialOutOfStockException, ContainerOverflowException{
	Mockito.when(scan.getString()).thenReturn("5");

	Mockito.when(scan.nextInt()).thenReturn(1);
	
	Mockito.when(vendingMachine.checkBeverageAvailabilityAndCalculateTotalPrice("TEA",1)).thenReturn(10.00);
	
	Mockito.when(vendingMachine.placeBeverageOrderAndReturnChange("TEA", 1, 10.00, 20.00)).thenReturn(10.00);
	
	Mockito.when(scan.nextDouble()).thenReturn(20.00);

	app.customerMenu();
}

}
