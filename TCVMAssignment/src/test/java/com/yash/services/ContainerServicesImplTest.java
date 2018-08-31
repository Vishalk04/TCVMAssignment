package com.yash.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
import com.yash.model.Beverages;
import com.yash.model.Materials;

@RunWith(MockitoJUnitRunner.class)
public class ContainerServicesImplTest {

	@InjectMocks
	ContainerServicesImpl containerServicesImpl = new ContainerServicesImpl();

	@Mock
	ContainerDao containerDao;

	ContainerDao containers;
	BeverageDao beverageDao;

	@Before
	public void setup() throws ContainerOverflowException {

	containers = new ContainerDao();
		containers.initialize();
		beverageDao = new BeverageDao();
		beverageDao.initialize();
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

		when(containerDao.get(Materials.TEA)).thenReturn(110);

		containerServicesImpl.addMaterial(Materials.TEA, 10);

		Mockito.verify(containerDao).get(Materials.TEA);

	}

	@Test
	public void shouldAddMaterialWhenQuantityEnteredisLessThanCapasity() throws ContainerOverflowException {

		when(containerDao.get(Materials.TEA)).thenReturn(0);
		when(containerDao.put(Materials.TEA, 50)).thenReturn(0);

		assertEquals(new Integer(50), containerServicesImpl.addMaterial(Materials.TEA, 50));

		Mockito.verify(containerDao, Mockito.atLeast(2)).get(Materials.TEA);
		Mockito.verify(containerDao).put(Materials.TEA, 50);

	}

	@Test
	public void shouldAddEnteredQuantityWhenContainerIsEmpty() throws ContainerOverflowException {

		when(containerDao.get(Materials.TEA)).thenReturn(0);
		when(containerDao.put(Materials.TEA, 50)).thenReturn(0);

		assertEquals(new Integer(50), containerServicesImpl.addMaterial(Materials.TEA, 50));

		Mockito.verify(containerDao, Mockito.atLeast(2)).get(Materials.TEA);
		Mockito.verify(containerDao).put(Materials.TEA, 50);
	}

	@Test
	public void shouldConsiderExistingMaterialWhenAvailable() throws ContainerOverflowException {

		when(containerDao.get(Materials.TEA)).thenReturn(50);
		when(containerDao.put(Materials.TEA, 50)).thenReturn(50);

		containerServicesImpl.addMaterial(Materials.TEA, 30);

		assertEquals(new Integer(100), containerServicesImpl.addMaterial(Materials.TEA, 50));

	}

	@Test
	public void shouldDespenseBeverageWhenAllMaterialAvailable()
			throws ContainerOverflowException, MaterialOutOfStockException {

		when(containerDao.getSize(Materials.TEA)).thenReturn(100);
		when(containerDao.getSize(Materials.SUGER)).thenReturn(100);
		when(containerDao.getSize(Materials.MILK)).thenReturn(100);
		when(containerDao.put(Materials.TEA, 80)).thenReturn(100);

		containerServicesImpl.despenseMaterial(beverageDao.getBeverage(Beverages.TEA), 1);

		Mockito.verify(containerDao).getSize(Materials.TEA);
		Mockito.verify(containerDao).getSize(Materials.MILK);
		Mockito.verify(containerDao).getSize(Materials.SUGER);
		Mockito.verify(containerDao).put(Materials.TEA, 80);

	}

	@Test(expected = MaterialOutOfStockException.class)
	public void shouldThrowExceptionWhenAllMaterialNotAvailable()
			throws ContainerOverflowException, MaterialOutOfStockException {
		when(containerDao.getSize(Materials.TEA)).thenReturn(100);
		when(containerDao.getSize(Materials.SUGER)).thenReturn(100);
		when(containerDao.put(Materials.TEA, 80)).thenReturn(100);

		containerServicesImpl.despenseMaterial(beverageDao.getBeverage(Beverages.TEA), 1);

		Mockito.verify(containerDao).getSize(Materials.TEA);
		Mockito.verify(containerDao).getSize(Materials.SUGER);
		Mockito.verify(containerDao).put(Materials.TEA, 80);

	}

	@Test
	public void shouldRefillConatainerIfNotFilled() throws ContainerOverflowException {

		when(containerDao.get(Materials.TEA)).thenReturn(50);
		when(containerDao.put(Materials.TEA, 100)).thenReturn(50);

		assertEquals(new Integer(100), containerServicesImpl.refillContainer(Materials.TEA, 50));

		Mockito.verify(containerDao).get(Materials.TEA);
		Mockito.verify(containerDao).put(Materials.TEA, 100);

	}

	@Test(expected = ContainerOverflowException.class)
	public void shouldThrowExceptionIfContainerAlreadyFilled() throws ContainerOverflowException {

		when(containerDao.get(Materials.TEA)).thenReturn(100);
		
		containerServicesImpl.refillContainer(Materials.TEA, 50);

		Mockito.verify(containerDao).get(Materials.TEA);

	}
 
	@Test
	public void testResetContainers() throws ContainerOverflowException {

		when(containerDao.put(Materials.TEA, 100)).thenReturn(100);
		when(containerDao.put(Materials.COFFEE, 100)).thenReturn(100);
		when(containerDao.put(Materials.MILK, 100)).thenReturn(100);
		when(containerDao.put(Materials.SUGER, 100)).thenReturn(100);
		when(containerDao.put(Materials.WATER, 100)).thenReturn(100);

		containerServicesImpl.resetContainers();

		Mockito.verify(containerDao).put(Materials.TEA, 100);
		Mockito.verify(containerDao).put(Materials.COFFEE, 100);
		Mockito.verify(containerDao).put(Materials.MILK, 100);
		Mockito.verify(containerDao).put(Materials.SUGER, 100);
		Mockito.verify(containerDao).put(Materials.WATER, 100);

	}

	@Test
	public void shouldReturncurrentAvailableMaterial() {
		
		when(containerDao.getSize(Materials.TEA)).thenReturn(100);
		
		assertEquals( new Integer(100), containerServicesImpl.checkContainerStatus(Materials.TEA));
		
		Mockito.verify(containerDao).getSize(Materials.TEA); 
	} 
	
	@Test
	public void shouldAddRefillContainerTransaction(){
		
		when(containerDao.addRefillTransaction(Materials.TEA, 50)).thenReturn(true);
		containerDao.addRefillTransaction(Materials.TEA, 50);
		Mockito.verify(containerDao).addRefillTransaction(Materials.TEA, 50);
		
	}

}
