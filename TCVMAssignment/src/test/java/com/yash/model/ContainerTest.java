package com.yash.model;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.exceptions.ContainerOverflowException;

@RunWith(MockitoJUnitRunner.class)
public class ContainerTest {
	
	@Ignore
	@Test
	public void shouldPutMaterialWhenNotAvailable() throws ContainerOverflowException{
		
		//assertTrue(Container.put(Materials.TEA, 100));
		
	}
	
	@Test(expected = ContainerOverflowException.class)
	public void shouldThrowContainerOverFolwExceptionWhenQuantityEnteredIsMoreThanCapacity() throws ContainerOverflowException{
		
		//assertFalse(container.put(Materials.TEA, 110));
		Container.put(Materials.MILK, 110);
		
	}
	
	@Test(expected = ContainerOverflowException.class )
	public void shouldThrowContainerOverFolwExceptionWhenQuantityEnteredForRefillExceedTheCapacity() throws ContainerOverflowException{
		
		Container.put(Materials.MILK, 90);
		Container.put(Materials.MILK, 50);
		
		
	}

	
}
