package com.yash.dao;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.model.Container;
import com.yash.model.Materials;

@RunWith(MockitoJUnitRunner.class)
public class ContainerDaoTest {

	ContainerDao containerDao;
	
	public ContainerDaoTest() {
	
		containerDao = new ContainerDao();
		
		
	}

	@Test
	public void shouldAddRefillTransation() {
		assertTrue(containerDao.addRefillTransaction(Materials.TEA, 10));
	}

	@Ignore
	@Test
	public void shouldReturnAllContainerRefillTransaction() {
		// assertThat(containerDao.getAllRefillTransaction(), hasSize(2));
	}
	
	

	@Test(expected = RuntimeException.class)
	public void shouldThrowExceptionWhenDataIsNotAvailable() {
		containerDao.getAllRefillTransaction();
	}
	
	@Test
	public void shouldReturnAvailabelMaterialFromContainerWhenContainerInitailized(){
		
		containerDao.initialize();
		assertEquals(new Integer(100), containerDao.getSize(Materials.TEA));
		
	}
	
/*	@Test
	public void shouldReturnZeroFromContainerWhenContainerInitailized(){
		
		assertEquals(new Integer(0), containerDao.getSize(Materials.TEA));
		
	}
*/	
	@Test
	public void shouldReturnContainer(){
		containerDao.get(Materials.TEA);
	}

	
}
