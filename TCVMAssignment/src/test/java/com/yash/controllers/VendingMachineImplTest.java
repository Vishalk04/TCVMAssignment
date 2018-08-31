package com.yash.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.exceptions.ContainerOverflowException;
import com.yash.exceptions.MaterialOutOfStockException;
import com.yash.model.Beverages;
import com.yash.model.Materials;
import com.yash.model.Order;
import com.yash.services.BeverageServicesImpl;
import com.yash.services.ContainerServicesImpl;
import com.yash.services.OrderServicesImpl;
import com.yash.services.PriceServicesImpl;

@RunWith(MockitoJUnitRunner.class)
public class VendingMachineImplTest {

	@InjectMocks
	VendingMachineImpl vendingMachineImpl;

	@Mock
	BeverageServicesImpl beverageServicesImpl;

	@Mock
	PriceServicesImpl priceServicesImpl;

	@Mock
	OrderServicesImpl orderServices;

	@Mock
	ContainerServicesImpl containerServciesImpl;

	@Mock
	Order order;

	@Test
	public void shouldReturnTotalPriceWhenBeveragesIsAvailable()
			throws MaterialOutOfStockException, ContainerOverflowException {

		when(beverageServicesImpl.checkBeverageAvailability(Beverages.TEA, 1)).thenReturn(true);

		when(priceServicesImpl.calculateTotalPrice(Beverages.TEA, 1)).thenReturn(new Double(10.0));

		assertEquals(new Double(10.00), vendingMachineImpl.checkBeverageAvailabilityAndCalculateTotalPrice("TEA", 1));


		Mockito.verify(beverageServicesImpl).checkBeverageAvailability(Beverages.TEA, 1);

		Mockito.verify(priceServicesImpl).calculateTotalPrice(Beverages.TEA, 1);

	} 

	@Test(expected = MaterialOutOfStockException.class)
	public void shouldThrowExceptionWhenMaterialIsNotAvailable()
			throws MaterialOutOfStockException, ContainerOverflowException {

		when(beverageServicesImpl.checkBeverageAvailability(Beverages.TEA, 1))
		.thenThrow(MaterialOutOfStockException.class);

		vendingMachineImpl.checkBeverageAvailabilityAndCalculateTotalPrice("TEA", 1);

		verify(beverageServicesImpl).checkBeverageAvailability(Beverages.TEA, 1);
	}


	@Ignore
	@Test
	public void shouldReturnChangeAndDespencebeverage() throws MaterialOutOfStockException, ContainerOverflowException {

//		Order order1 = new Order();
//		order.setBeverageId("1");

		when(beverageServicesImpl.despenseBeverage(Beverages.TEA, 1)).thenReturn(true);



		//	when(order.getTotalPrice()).thenReturn(new Double(10.00));

		when(orderServices.saveOrder(order)).thenReturn(true);

		//	when(priceServicesImpl.calculateTotalPrice(Beverages.TEA, 1)).thenReturn(new Double(10.0));

		//vendingMachineImpl.checkBeverageAvailabilityAndCalculateTotalPrice("TEA", 1);

		when(priceServicesImpl.calculateChange(new Double(20.00), new Double(10.00))).thenReturn(new Double(10.00));

		Double actualValue = vendingMachineImpl.placeBeverageOrderAndReturnChange("TEA", 1, new Double(20.00));

		assertEquals(new Double(10.00),	actualValue); 

/* 
		Mockito.verify(beverageServicesImpl).despenseBeverage(Beverages.TEA, 1);

		Mockito.verify(priceServicesImpl).calculateChange(new Double(20.00), new Double(10.00));

			Mockito.verify(beverageServicesImpl).checkBeverageAvailability(Beverages.TEA, 1);

		Mockito.verify(priceServicesImpl).calculateTotalPrice(Beverages.TEA, 1);*/

	}

	/*	@Test
	public void shouldRefillContainer(){
		containerServicesImpl.refillContainer(Materials.valueOf(container.toUpperCase()), quantity)
	}
	 */
	
	
	
	@Test
	public void testrefillContainer() throws ContainerOverflowException{
		when(containerServciesImpl.refillContainer(Materials.TEA, 10)).thenReturn(1);
		vendingMachineImpl.refillContainer("TEA", 10);
		verify(containerServciesImpl).refillContainer(Materials.TEA, 10);
	}
	
	@Test
	public void testresetContainer() throws ContainerOverflowException{
		
		vendingMachineImpl.resetContainer();
		
	}
}
