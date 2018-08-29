package com.yash.services;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.exceptions.ContainerOverflowException;

@RunWith(MockitoJUnitRunner.class)
public class VendingMachineImplTest {

	VendingMachineImplTest vendingMachineImpl ;
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testInitialize() throws ContainerOverflowException{
		vendingMachineImpl = new VendingMachineImplTest();
		
	//	vendingMachineImpl.initialize();
	}

}
