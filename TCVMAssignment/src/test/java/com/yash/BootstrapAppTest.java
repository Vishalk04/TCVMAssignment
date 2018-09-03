package com.yash;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.dao.BeverageDao;
import com.yash.dao.ContainerDao;
import com.yash.exceptions.ContainerOverflowException;

@RunWith(MockitoJUnitRunner.class)
public class BootstrapAppTest {

	@InjectMocks
	BootstrapApp app = new BootstrapApp();
	@Mock
	BeverageDao beverageDao;
	@Mock
	ContainerDao contianerDao;

	@Test//(expected = ContainerOverflowException.class)
	public void testCreateAll() throws ContainerOverflowException {
		Mockito.doNothing().when(contianerDao).initialize();
		Mockito.doThrow(ContainerOverflowException.class).when(beverageDao).initialize();
		app.CreateAllData();
	}

}
