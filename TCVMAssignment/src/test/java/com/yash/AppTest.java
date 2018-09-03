package com.yash;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.controllers.ReportImpl;
import com.yash.controllers.VendingMachineImpl;
import com.yash.exceptions.ContainerOverflowException;
import com.yash.exceptions.MaterialOutOfStockException;
import com.yash.model.InputScanner;

@RunWith(MockitoJUnitRunner.class)
public class AppTest {  


	@InjectMocks
	 App app;
	
	@Mock
	VendingMachineImpl vendingMachine;
	
	
	
	@Mock
	ReportImpl report ;
	

@Test
public void testStartSwitchCase1() throws ContainerOverflowException, MaterialOutOfStockException{
	
	Mockito.when(scan.getString()).thenReturn("1").thenReturn("11");
	Mockito.when(vendingMachine.checkBeverageAvailabilityAndCalculateTotalPrice("TEA",1)).thenReturn(10.00);
	
	Mockito.when(vendingMachine.placeBeverageOrderAndReturnChange("TEA", 1, 10.00, 10.00)).thenReturn(0.00);
	
	
	Mockito.when(scan.nextDouble()).thenReturn(5.00).thenReturn(5.00);
	
	app.start();
	
}

@Test
public void testStartSwitchCase2() throws ContainerOverflowException, MaterialOutOfStockException{
	
	Mockito.when(scan.getString()).thenReturn("2").thenReturn("11");
	
	Mockito.when(scan.nextInt()).thenReturn(1); 
	
	Mockito.when(vendingMachine.checkBeverageAvailabilityAndCalculateTotalPrice("BLACKTEA",1)).thenReturn(new Double(10.00));
	
	Mockito.when(scan.nextDouble()).thenReturn(50.00);
	
	Mockito.when(vendingMachine.placeBeverageOrderAndReturnChange("BLACKTEA", 1, new Double(10.00), new Double(50.00))).thenReturn(new Double(40.00));
	
	 app.start();
}

@Test
public void testStartSwitchCase3() throws ContainerOverflowException, MaterialOutOfStockException{
	
	Mockito.when(scan.getString()).thenReturn("3").thenReturn("11");
	Mockito.when(vendingMachine.checkBeverageAvailabilityAndCalculateTotalPrice("COFFEE",1)).thenReturn(10.00);
	
	Mockito.when(vendingMachine.placeBeverageOrderAndReturnChange("COFFEE", 1, 10.00, 20.00)).thenReturn(10.00);
	
	Mockito.when(scan.nextDouble()).thenReturn(20.00);

	 app.start();
}



	
@Test
public void testStartSwitchCase4() throws MaterialOutOfStockException, ContainerOverflowException{
	
	Mockito.when(scan.getString()).thenReturn("4").thenReturn("11");
	
	Mockito.when(vendingMachine.checkBeverageAvailabilityAndCalculateTotalPrice("BLACKCOFFEE",1)).thenReturn(10.00);
	
	Mockito.when(vendingMachine.placeBeverageOrderAndReturnChange("BLACKCOFFEE", 1, 10.00, 20.00)).thenReturn(10.00);
	
	Mockito.when(scan.nextDouble()).thenReturn(20.00);
	
	app.start();
	
}


@Mock
InputScanner scan;

@Test
public void testStartSwitchCase5() throws ContainerOverflowException {
	
	Mockito.when(scan.getString()).thenReturn("5").thenReturn("TEA").thenReturn("11");
	Mockito.when(scan.nextInt()).thenReturn(10); 
	Mockito.when(vendingMachine.refillContainer("TEA", 1)).thenReturn(90);
	app.start();
}


@Test
public void testStartSwitchCase6() throws ContainerOverflowException {
	

	Mockito.when(scan.getString()).thenReturn("6").thenReturn("11");
	Mockito.doNothing().when(vendingMachine).resetContainer();
	app.start();

}
@Test
public void testStartSwitchCase7() throws ContainerOverflowException {
	Mockito.when(scan.getString()).thenReturn("7").thenReturn("11");

	Mockito.when(report.generateTotalSaleReport()).thenReturn(true);
		 
	app.start();
	
	
	}
	
@Test
public void testStartSwitchCase8() throws ContainerOverflowException {


	Mockito.when(scan.getString()).thenReturn("8").thenReturn("11");
	Mockito.when(report.getSalesReportByBeverages()).thenReturn(true);
	app.start();
	
}

@Test
public void testStartSwitchCase9() throws ContainerOverflowException {


	Mockito.when(scan.getString()).thenReturn("9").thenReturn("11");
	Mockito.when(report.checkContainerStatus("TEA")).thenReturn(90);
	app.start();
	
}




	
@Test
public void testStartSwitchCase10() throws ContainerOverflowException{
	
	Mockito.when(scan.getString()).thenReturn("10").thenReturn("1").thenReturn("TEA").thenReturn("11");
	
	Mockito.when(scan.nextInt()).thenReturn(10); 
	Mockito.when(vendingMachine.refillContainer("TEA", 1)).thenReturn(90);
	app.start();
}

/*@Test
public void testSetUpContainerSwitchCase2() throws ContainerOverflowException{
	
	Mockito.when(scan.getString()).thenReturn("2").thenReturn("3");
	Mockito.doNothing().when(vendingMachine).resetContainer();
	app.start();
}

@Test
public void testSetUpContainerSwitchCase3() throws ContainerOverflowException{
	
	Mockito.when(scan.getString()).thenReturn("3").thenReturn("4");
	app.start();
}


@Test
public void testCustomerMenuSwitchCase6() throws ContainerOverflowException{
	Mockito.when(scan.getString()).thenReturn("6");
	app.start();
}
*/


}
