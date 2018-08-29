package com.yash.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
	IBeverageServices beverageServices ;
	
	@Mock
	ContainerServicesImpl containerServicesImpl;
	
	ContainerDao containerDao ;
	BeverageDao beverageDao;

	@Before
	public void setup() throws ContainerOverflowException {
		
		//beverageServices = new BeverageServicesImpl();
		 containerDao = new ContainerDao();
		 containerDao.initialize();
		 beverageDao = new BeverageDao();
		 beverageDao.initialize();
		 
	}
	@After
	public void tearDown(){
		beverageServices = null;
		containerDao = null;
		beverageDao = null;
	}
	
	@Test
	public void shouldReturnTrueWhenAllMaterialAreAvailable() throws MaterialOutOfStockException, ContainerOverflowException {


		assertTrue(beverageServices.checkBeverageAvailability(Beverages.TEA, 1));
		
	}

	
	@Test(expected = MaterialOutOfStockException.class)
	public void shuldThrowExceptionIfMaterialIsOutOfStock() throws MaterialOutOfStockException, ContainerOverflowException {
		containerDao.put(Materials.TEA, 0);
		containerDao.put(Materials.MILK, 0);
		containerDao.put(Materials.SUGER, 0);
		beverageServices.checkBeverageAvailability(Beverages.TEA, 1);
	}
	
	@Test
	public void testDespenseBeverage() {
		//Beverage selectedBeverage =  beverageDao.getBeverage(Beverages.TEA);
		
		List<Material> material = new ArrayList<Material>();
		material.add(new Material(Materials.TEA, 10, 10));
		//Mockito.when(containerServicesImpl.updateMaterial(material, 1)
		doNothing().when(containerServicesImpl.updateMaterial(Mockito.any(Beverage.class),1));
		 
		beverageServices.despenseBeverage(Beverages.TEA, 1);
		
	}
	
	@Ignore
	@Test
	public void  shouldAdjustAllMaterialWhenDespenceBeverage() throws ContainerOverflowException{
		
		beverageServices.despenseBeverage(Beverages.TEA, 1);
		assertEquals(80, containerDao.getSize(Materials.TEA ));
		
	}

}
