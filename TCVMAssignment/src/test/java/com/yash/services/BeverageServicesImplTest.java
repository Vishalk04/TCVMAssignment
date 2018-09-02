package com.yash.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;

import com.yash.dao.BeverageDao;
import com.yash.dao.ContainerDao;
import com.yash.exceptions.ContainerOverflowException;
import com.yash.exceptions.MaterialOutOfStockException;
import com.yash.model.Beverage;
import com.yash.model.BeverageTypes;
import com.yash.model.Material;
import com.yash.model.MaterialTypes;

@RunWith(MockitoJUnitRunner.class)
public class BeverageServicesImplTest {

	@InjectMocks
	BeverageServicesImpl beverageServices;

	@Mock
	ContainerServicesImpl containerServicesImpl;

	@Mock
	ContainerDao containerDao;

	@Mock
	BeverageDao beverageDao;

	Beverage tea, coffee;

	@Before
	public void setup() throws ContainerOverflowException {

		tea = new Beverage();
		tea.setName(BeverageTypes.TEA);
		tea.setPrice(10.00);
		tea.setMaterial(Arrays.asList(new Material(MaterialTypes.TEA, 10, 10),

				new Material(MaterialTypes.MILK, 10, 10), new Material(MaterialTypes.SUGER, 10, 10)));

		coffee = new Beverage();
		// coffee.setPrice(10.00);
		coffee.setName(BeverageTypes.COFFEE);
		coffee.setMaterial(Arrays.asList(new Material(MaterialTypes.COFFEE, 10, 10), new Material(MaterialTypes.MILK, 10, 10),
				new Material(MaterialTypes.SUGER, 10, 10)));

		/*
		 * availableBeverages.put(Beverages.TEA, tea);
		 * availableBeverages.put(Beverages.COFFEE, coffee)
		 */

	}

	@After
	public void tearDown() {
		beverageServices = null;
		containerDao = null;
		beverageDao = null;
	}

	@Test
	public void shouldReturnTrueWhenAllMaterialAreAvailable()
			throws MaterialOutOfStockException, ContainerOverflowException {

		when(beverageDao.getBeverage(BeverageTypes.TEA)).thenReturn(tea);
		when(containerDao.getSize(MaterialTypes.TEA)).thenReturn(100);
		when(containerDao.getSize(MaterialTypes.MILK)).thenReturn(100);
		when(containerDao.getSize(MaterialTypes.SUGER)).thenReturn(100);

		assertTrue(beverageServices.checkBeverageAvailability(BeverageTypes.TEA, 1));

		verify(beverageDao).getBeverage(BeverageTypes.TEA);
		verify(containerDao).getSize(MaterialTypes.TEA);
		verify(containerDao).getSize(MaterialTypes.MILK);
		verify(containerDao).getSize(MaterialTypes.SUGER);

	} 

	@Test(expected = MaterialOutOfStockException.class)
	public void shuldThrowExceptionIfMaterialIsOutOfStock()
			throws MaterialOutOfStockException, ContainerOverflowException {

		when(beverageDao.getBeverage(BeverageTypes.TEA)).thenReturn(tea); 

	

		beverageServices.checkBeverageAvailability(BeverageTypes.TEA, 1);

		
	}

	@Test
	public void testDespenseBeverage() throws ContainerOverflowException, MaterialOutOfStockException {

		when(containerServicesImpl.despenseMaterial(tea, 1)).thenReturn(true);
		when(beverageDao.getBeverage(BeverageTypes.TEA)).thenReturn(tea);
		
		assertTrue(beverageServices.despenseBeverage(BeverageTypes.TEA, 1));

		verify(containerServicesImpl).despenseMaterial(tea, 1);
		verify(beverageDao).getBeverage(BeverageTypes.TEA);
	}

	
	@Test(expected = MaterialOutOfStockException.class)
	public void shouldThrowExceptionWhenMaterialIsOutOfStock()
			throws ContainerOverflowException, MaterialOutOfStockException {
		when(beverageDao.getBeverage(BeverageTypes.TEA)).thenReturn(tea);
	//	when(containerServicesImpl.despenseMaterial(tea, 1)).thenReturn(true);
		doThrow(MaterialOutOfStockException.class).when(containerServicesImpl).despenseMaterial(tea, 1);
	
		
		beverageServices.despenseBeverage(BeverageTypes.TEA, 1);

/*
		verify(containerServicesImpl).despenseMaterial(tea, 1);
		verify(beverageDao).getBeverage(Beverages.TEA);*/
	}
	
	

}
