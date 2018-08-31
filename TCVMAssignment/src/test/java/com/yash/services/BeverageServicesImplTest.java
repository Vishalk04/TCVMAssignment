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
import com.yash.model.Beverages;
import com.yash.model.Material;
import com.yash.model.Materials;

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
		tea.setName(Beverages.TEA);
		tea.setPrice(10.00);
		tea.setMaterial(Arrays.asList(new Material(Materials.TEA, 10, 10),

				new Material(Materials.MILK, 10, 10), new Material(Materials.SUGER, 10, 10)));

		coffee = new Beverage();
		// coffee.setPrice(10.00);
		coffee.setName(Beverages.COFFEE);
		coffee.setMaterial(Arrays.asList(new Material(Materials.COFFEE, 10, 10), new Material(Materials.MILK, 10, 10),
				new Material(Materials.SUGER, 10, 10)));

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

		when(beverageDao.getBeverage(Beverages.TEA)).thenReturn(tea);
		when(containerDao.getSize(Materials.TEA)).thenReturn(100);
		when(containerDao.getSize(Materials.MILK)).thenReturn(100);
		when(containerDao.getSize(Materials.SUGER)).thenReturn(100);

		assertTrue(beverageServices.checkBeverageAvailability(Beverages.TEA, 1));

		verify(beverageDao).getBeverage(Beverages.TEA);
		verify(containerDao).getSize(Materials.TEA);
		verify(containerDao).getSize(Materials.MILK);
		verify(containerDao).getSize(Materials.SUGER);

	}

	@Test(expected = MaterialOutOfStockException.class)
	public void shuldThrowExceptionIfMaterialIsOutOfStock()
			throws MaterialOutOfStockException, ContainerOverflowException {

		when(beverageDao.getBeverage(Beverages.TEA)).thenReturn(tea); 
		when(containerDao.getSize(Materials.TEA)).thenReturn(0);
		when(containerDao.getSize(Materials.MILK)).thenReturn(0);
		when(containerDao.getSize(Materials.SUGER)).thenReturn(0);

		beverageServices.checkBeverageAvailability(Beverages.TEA, 1);

		verify(beverageDao).getBeverage(Beverages.TEA);
		verify(containerDao).getSize(Materials.TEA);
		verify(containerDao).getSize(Materials.MILK);
		verify(containerDao).getSize(Materials.SUGER);
	}

	@Test
	public void testDespenseBeverage() throws ContainerOverflowException, MaterialOutOfStockException {

		when(containerServicesImpl.despenseMaterial(tea, 1)).thenReturn(true);
		when(beverageDao.getBeverage(Beverages.TEA)).thenReturn(tea);
		
		assertTrue(beverageServices.despenseBeverage(Beverages.TEA, 1));

		verify(containerServicesImpl).despenseMaterial(tea, 1);
		verify(beverageDao).getBeverage(Beverages.TEA);
	}

	
	@Test(expected = MaterialOutOfStockException.class)
	public void shouldThrowExceptionWhenMaterialIsOutOfStock()
			throws ContainerOverflowException, MaterialOutOfStockException {
		when(beverageDao.getBeverage(Beverages.TEA)).thenReturn(tea);
	//	when(containerServicesImpl.despenseMaterial(tea, 1)).thenReturn(true);
		doThrow(MaterialOutOfStockException.class).when(containerServicesImpl).despenseMaterial(tea, 1);
	
		
		beverageServices.despenseBeverage(Beverages.TEA, 1);


		verify(containerServicesImpl).despenseMaterial(tea, 1);
		verify(beverageDao).getBeverage(Beverages.TEA);
	}
	
	

}
