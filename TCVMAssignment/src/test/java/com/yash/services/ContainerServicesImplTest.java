package com.yash.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
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
import com.yash.model.BeverageTypes;
import com.yash.model.Material;
import com.yash.model.MaterialTypes;

@RunWith(MockitoJUnitRunner.class)
public class ContainerServicesImplTest {

	@InjectMocks
	ContainerServicesImpl containerServicesImpl = new ContainerServicesImpl();

	@Mock
	ContainerDao containerDao;

	ContainerDao containers;
	BeverageDao beverageDao;
	Beverage tea;
	@Before
	public void setup() throws ContainerOverflowException {

	containers = new ContainerDao();
		containers.initialize();
		beverageDao = new BeverageDao();
		beverageDao.initialize();
		 tea = new Beverage(); 
		tea.setName(BeverageTypes.TEA);
		tea.setPrice(10.00);
		tea.setMaterial(Arrays.asList(new Material(MaterialTypes.TEA, 10, 10),
				
				new Material(MaterialTypes.MILK, 10, 10), new Material(MaterialTypes.SUGER, 10, 10)));
	}

	@After
	public void tearDown() {

		beverageDao = null;
	}

	/*
	 * @Test public void test() { fail("Not yet implemented"); }
	 */

	@Test(expected = ContainerOverflowException.class)
	public void shouldThrowExceptionWhenQuantityEnteredExceedTheCapasity() throws ContainerOverflowException {

		when(containerDao.get(MaterialTypes.TEA)).thenReturn(110);

		containerServicesImpl.addMaterial(MaterialTypes.TEA, 10);

	/*	Mockito.verify(containerDao).get(MaterialTypes.TEA);*/

	}

	@Test
	public void shouldAddMaterialWhenQuantityEnteredisLessThanCapasity() throws ContainerOverflowException {

		when(containerDao.get(MaterialTypes.TEA)).thenReturn(0);
		when(containerDao.put(MaterialTypes.TEA, 50)).thenReturn(0);

		assertEquals(new Integer(50), containerServicesImpl.addMaterial(MaterialTypes.TEA, 50));

		Mockito.verify(containerDao, Mockito.atLeast(2)).get(MaterialTypes.TEA);
		Mockito.verify(containerDao).put(MaterialTypes.TEA, 50);

	}

	@Test
	public void shouldAddEnteredQuantityWhenContainerIsEmpty() throws ContainerOverflowException {

		when(containerDao.get(MaterialTypes.TEA)).thenReturn(0);
		when(containerDao.put(MaterialTypes.TEA, 50)).thenReturn(0);

		assertEquals(new Integer(50), containerServicesImpl.addMaterial(MaterialTypes.TEA, 50));

		Mockito.verify(containerDao, Mockito.atLeast(2)).get(MaterialTypes.TEA);
		Mockito.verify(containerDao).put(MaterialTypes.TEA, 50);
	}

	@Test
	public void shouldConsiderExistingMaterialWhenAvailable() throws ContainerOverflowException {

		when(containerDao.get(MaterialTypes.TEA)).thenReturn(50);
		when(containerDao.put(MaterialTypes.TEA, 50)).thenReturn(50);

		containerServicesImpl.addMaterial(MaterialTypes.TEA, 30);

		assertEquals(new Integer(100), containerServicesImpl.addMaterial(MaterialTypes.TEA, 50));

	}

	@Test
	public void shouldDespenseBeverageWhenAllMaterialAvailable()
			throws ContainerOverflowException, MaterialOutOfStockException {

		when(containerDao.getSize(MaterialTypes.TEA)).thenReturn(100);
		when(containerDao.getSize(MaterialTypes.SUGER)).thenReturn(100);
		when(containerDao.getSize(MaterialTypes.MILK)).thenReturn(100);
		when(containerDao.put(MaterialTypes.TEA, 80)).thenReturn(100);

		containerServicesImpl.despenseMaterial(beverageDao.getBeverage(BeverageTypes.TEA), 1);

		Mockito.verify(containerDao).getSize(MaterialTypes.TEA);
/*		Mockito.verify(containerDao).getSize(MaterialTypes.MILK);
		Mockito.verify(containerDao).getSize(MaterialTypes.SUGER);
		Mockito.verify(containerDao).put(MaterialTypes.TEA, 80);*/

	}

	@Test(expected = MaterialOutOfStockException.class)
	public void shouldThrowExceptionWhenAllMaterialNotAvailable()
			throws ContainerOverflowException, MaterialOutOfStockException {
		when(containerDao.getSize(MaterialTypes.TEA)).thenReturn(100);
		when(containerDao.getSize(MaterialTypes.SUGER)).thenReturn(100);
		when(containerDao.getSize(MaterialTypes.MILK)).thenReturn(100);
		when(containerDao.put(MaterialTypes.TEA, 80)).thenReturn(80);

		containerServicesImpl.despenseMaterial(tea, 10); 
 
		/*Mockito.verify(containerDao).getSize(MaterialTypes.TEA);
		Mockito.verify(containerDao).getSize(MaterialTypes.SUGER);
		Mockito.verify(containerDao).getSize(MaterialTypes.MILK);
		Mockito.verify(containerDao).put(MaterialTypes.TEA, 80);*/

	}

	@Test
	public void shouldRefillConatainerIfNotFilled() throws ContainerOverflowException {

		when(containerDao.get(MaterialTypes.TEA)).thenReturn(50);
		when(containerDao.put(MaterialTypes.TEA, 100)).thenReturn(50);

		assertEquals(new Integer(100), containerServicesImpl.refillContainer(MaterialTypes.TEA, 50));

		Mockito.verify(containerDao).get(MaterialTypes.TEA);
		Mockito.verify(containerDao).put(MaterialTypes.TEA, 100);

	}

	@Test(expected = ContainerOverflowException.class)
	public void shouldThrowExceptionIfContainerAlreadyFilled() throws ContainerOverflowException {

		when(containerDao.get(MaterialTypes.TEA)).thenReturn(100);
		
		containerServicesImpl.refillContainer(MaterialTypes.TEA, 50);
/*
		Mockito.verify(containerDao).get(MaterialTypes.TEA);
*/
	}
 
	@Test
	public void testResetContainers() throws ContainerOverflowException {

		when(containerDao.put(MaterialTypes.TEA, 100)).thenReturn(100);
		when(containerDao.put(MaterialTypes.COFFEE, 100)).thenReturn(100);
		when(containerDao.put(MaterialTypes.MILK, 100)).thenReturn(100);
		when(containerDao.put(MaterialTypes.SUGER, 100)).thenReturn(100);
		when(containerDao.put(MaterialTypes.WATER, 100)).thenReturn(100);

		containerServicesImpl.resetContainers();

		Mockito.verify(containerDao).put(MaterialTypes.TEA, 100);
		Mockito.verify(containerDao).put(MaterialTypes.COFFEE, 100);
		Mockito.verify(containerDao).put(MaterialTypes.MILK, 100);
		Mockito.verify(containerDao).put(MaterialTypes.SUGER, 100);
		Mockito.verify(containerDao).put(MaterialTypes.WATER, 100);

	}

	@Test
	public void shouldReturncurrentAvailableMaterial() {
		
		when(containerDao.getSize(MaterialTypes.TEA)).thenReturn(100);
		
		assertEquals( new Integer(100), containerServicesImpl.checkContainerStatus(MaterialTypes.TEA));
		
		Mockito.verify(containerDao).getSize(MaterialTypes.TEA); 
	} 
	
	@Test
	public void shouldAddRefillContainerTransaction(){
		
		when(containerDao.addRefillTransaction(MaterialTypes.TEA, 50)).thenReturn(true);
		containerDao.addRefillTransaction(MaterialTypes.TEA, 50);
		Mockito.verify(containerDao).addRefillTransaction(MaterialTypes.TEA, 50);
		
	}

}
