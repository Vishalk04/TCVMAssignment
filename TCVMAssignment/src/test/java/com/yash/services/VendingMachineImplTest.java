package com.yash.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.controllers.VendingMachineImpl;
import com.yash.exceptions.ContainerOverflowException;
import com.yash.exceptions.MaterialOutOfStockException;
import com.yash.model.Beverages;
import com.yash.model.Order;

@RunWith(MockitoJUnitRunner.class)
public class VendingMachineImplTest {

	@InjectMocks
	VendingMachineImpl vendingMachineImpl;

	@Mock
	BeverageServicesImpl beverageServicesImpl;

	@Mock
	PriceServicesImpl priceServicesImpl;

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
		/*
		 * Order order = new Order(); order.setBeverageId("01");
		 * order.setOrderId("01"); order.setQuantity(1); order.setTotalPrice(new
		 * Double(10.00)); order.setBeverages(Beverages.TEA);
		 */
		doNothing().when(beverageServicesImpl).despenseBeverage(Beverages.TEA, 1);

		when(priceServicesImpl.calculateChange(new Double(10.00), new Double(10.00))).thenReturn(new Double(10.00));
		// when(order.getTotalPrice()).thenReturn(new Double(10.00));

		when(beverageServicesImpl.checkBeverageAvailability(Beverages.TEA, 1)).thenReturn(true);

		when(priceServicesImpl.calculateTotalPrice(Beverages.TEA, 1)).thenReturn(new Double(10.0));

		vendingMachineImpl.checkBeverageAvailabilityAndCalculateTotalPrice("TEA", 1);

		assertEquals(new Double(10.00),
				vendingMachineImpl.placeBeverageOrderAndReturnChange("TEA", 1, new Double(20.00)));
		
		
		Mockito.verify(beverageServicesImpl).despenseBeverage(Beverages.TEA, 1);
		
		Mockito.verify(priceServicesImpl).calculateChange(new Double(10.00), new Double(10.00));

		Mockito.verify(beverageServicesImpl).checkBeverageAvailability(Beverages.TEA, 1);
		
		Mockito.verify(priceServicesImpl).calculateTotalPrice(Beverages.TEA, 1);
		
	
		
	}

}
