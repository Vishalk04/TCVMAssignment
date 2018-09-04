package com.yash.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.exceptions.ContainerOverflowException;
import com.yash.model.MaterialTypes;

@RunWith(MockitoJUnitRunner.class)
public class ContainerDaoTest {

	ContainerDao containerDao;
	
	private static Map<MaterialTypes, Integer> container = new HashMap<MaterialTypes, Integer>();
	public ContainerDaoTest() {
	
		containerDao = new ContainerDao();
		
		
	} 

	@Test
	public void shouldAddRefillTransation() {
		assertTrue(containerDao.addRefillTransaction(MaterialTypes.TEA, 10));
	}


	
	@Test
	public void testPut() throws ContainerOverflowException {
		containerDao.put(MaterialTypes.TEA, 1);
	}
	
	

	@Test(expected = RuntimeException.class)
	public void shouldThrowExceptionWhenDataIsNotAvailable() {
		containerDao.getAllRefillTransaction();
	}
	
	@Test
	public void testGetAllRefillTransaction() {
		
		containerDao.addRefillTransaction(MaterialTypes.TEA, 10);
		containerDao.getAllRefillTransaction();
	}
	
	@Test
	public void shouldReturnAvailabelMaterialFromContainerWhenContainerInitailized() throws ContainerOverflowException{
		
		containerDao.initialize();
		assertEquals(new Integer(100), containerDao.getSize(MaterialTypes.TEA));
		
	}
	
	@Test
	public void shouldReturnContainer(){
		containerDao.get(MaterialTypes.TEA); 
	}

	
}
