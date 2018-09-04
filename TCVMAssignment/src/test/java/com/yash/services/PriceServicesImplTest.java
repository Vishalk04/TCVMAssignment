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
import com.yash.model.BeverageTypes;
import com.yash.model.Material;
import com.yash.model.MaterialTypes;

@RunWith(MockitoJUnitRunner.class)
public class PriceServicesImplTest {

	@InjectMocks
	PriceServicesImpl priceServices;
	
	@Mock
	BeverageDao beverageDao;


	@Test
	public void shoudReturnPriceWhenAvailable() {
		Beverage tea = new Beverage(); 
		tea.setName(BeverageTypes.TEA);
		tea.setPrice(10.00);
		tea.setMaterial(Arrays.asList(new Material(MaterialTypes.TEA, 10, 10),
				
				new Material(MaterialTypes.MILK, 10, 10), new Material(MaterialTypes.SUGER, 10, 10)));
		
		
		Mockito.when(beverageDao.getBeverage(BeverageTypes.TEA)).thenReturn(tea);
	
		
		assertEquals(new Double(10.00), priceServices.calculateTotalPrice(BeverageTypes.TEA, 1));

		
		
		Mockito.verify(beverageDao).getBeverage(BeverageTypes.TEA);
	}

	@Test(expected = RuntimeException.class)
	public void shouldThrowExceptionWhenPriceNotAvailable() {
		Beverage tea = new Beverage(); 
		tea.setName(BeverageTypes.TEA);
	//	tea.setPrice(10.00);
		tea.setMaterial(Arrays.asList(new Material(MaterialTypes.TEA, 10, 10),
				
				new Material(MaterialTypes.MILK, 10, 10), new Material(MaterialTypes.SUGER, 10, 10)));
		
		Mockito.when(beverageDao.getBeverage(BeverageTypes.TEA)).thenReturn(tea);
		
		priceServices.calculateTotalPrice(BeverageTypes.TEA, 1);
		
	//	Mockito.verify(beverageDao).getBeverage(BeverageTypes.TEA);
	
	}
	
	@Test
	public void shouldCalculateChange(){
		assertEquals(new Double(40.00), priceServices.calculateChange(new Double(50.00), new Double(10.00)));
	}

}
