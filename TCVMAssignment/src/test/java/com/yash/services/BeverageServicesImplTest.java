package com.yash.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.exceptions.ContainerOverflowException;
import com.yash.exceptions.MaterialOutOfStockException;
import com.yash.model.Beverages;
import com.yash.model.Container;
import com.yash.model.Materials;

@RunWith(MockitoJUnitRunner.class)
public class BeverageServicesImplTest {

	IBeverageServices beverageServices ;



	@Before
	public void setup() throws ContainerOverflowException {
		
		beverageServices = new BeverageServicesImpl();

	}
	@After
	public void tearDown(){
		beverageServices = null;
	}
	
	@Test
	public void shouldReturnTrueWhenAllMaterialAreAvailable() throws MaterialOutOfStockException, ContainerOverflowException {


		assertTrue(beverageServices.checkBeverageAvailability(Beverages.TEA, 1));
		
	}

	
	@Test(expected = MaterialOutOfStockException.class)
	public void shuldThrowExceptionIfMaterialIsOutOfStock() throws MaterialOutOfStockException, ContainerOverflowException {
		Container.put(Materials.TEA, 0);
		Container.put(Materials.MILK, 0);
		Container.put(Materials.SUGER, 0);
		when(Container.getSize(any(Materials.class))).thenReturn(100);
		
		beverageServices.checkBeverageAvailability(Beverages.TEA, 1);
	}
	
	@Test
	public void  shouldAdjustAllMaterialWhenDespenceBeverage() throws ContainerOverflowException{
		

		
		beverageServices.despenseBeverage(Beverages.TEA, 1);
		assertEquals(80, Container.getSize(Materials.TEA ));
		
	}

}
