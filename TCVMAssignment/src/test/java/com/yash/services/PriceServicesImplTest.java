package com.yash.services;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.dao.BeverageDao;
import com.yash.model.Beverage;
import com.yash.model.Beverages;
import com.yash.model.Material;
import com.yash.model.Materials;

@RunWith(MockitoJUnitRunner.class)
public class PriceServicesImplTest {

	@InjectMocks
	PriceServicesImpl priceServices;
	
	@Mock
	BeverageDao beverageDao;

	@Before
	public void setUp() {
		//priceServices = new PriceServicesImpl();
		//beverageDao = new BeverageDao();
		
		
	}

	@Test
	public void shoudReturnPriceWhenAvailable() {
		Beverage tea = new Beverage(); 
		tea.setName(Beverages.TEA);
		tea.setPrice(10.00);
		tea.setMaterial(Arrays.asList(new Material(Materials.TEA, 10, 10),
				
				new Material(Materials.MILK, 10, 10), new Material(Materials.SUGER, 10, 10)));
		
		
		Mockito.when(beverageDao.getBeverage(Beverages.TEA)).thenReturn(tea);
	
		
		assertEquals(new Double(10.00), priceServices.calculateTotalPrice(Beverages.TEA, 1));

		
		
		Mockito.verify(beverageDao).getBeverage(Beverages.TEA);
	}

	@Test(expected = RuntimeException.class)
	public void shouldThrowExceptionWhenPriceNotAvailable() {
		Beverage tea = new Beverage(); 
		tea.setName(Beverages.TEA);
	//	tea.setPrice(10.00);
		tea.setMaterial(Arrays.asList(new Material(Materials.TEA, 10, 10),
				
				new Material(Materials.MILK, 10, 10), new Material(Materials.SUGER, 10, 10)));
		
		Mockito.when(beverageDao.getBeverage(Beverages.TEA)).thenReturn(tea);
		
		priceServices.calculateTotalPrice(Beverages.COFFEE, 1);
		
		Mockito.verify(beverageDao).getBeverage(Beverages.TEA);
		
	}

}
