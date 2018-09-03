package com.yash.dao;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.exceptions.ContainerOverflowException;
import com.yash.model.Container;
import com.yash.model.MaterialTypes;

@RunWith(MockitoJUnitRunner.class)
public class ContainerDaoTest {

	ContainerDao containerDao;
	
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
	public void shouldReturnAvailabelMaterialFromContainerWhenContainerInitailized(){
		
		containerDao.initialize();
		assertEquals(new Integer(100), containerDao.getSize(MaterialTypes.TEA));
		
	}
	
/*	@Test
	public void shouldReturnZeroFromContainerWhenContainerInitailized(){
		
		assertEquals(new Integer(0), containerDao.getSize(Materials.TEA));
		
	}
*/	
	@Test
	public void shouldReturnContainer(){
		containerDao.get(MaterialTypes.TEA);
	}

	
}
