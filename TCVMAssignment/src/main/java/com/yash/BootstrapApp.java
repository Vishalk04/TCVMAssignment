package com.yash;

import com.yash.dao.BeverageDao;
import com.yash.dao.ContainerDao;
import com.yash.dao.OrderDao;
import com.yash.exceptions.ContainerOverflowException;

public class BootstrapApp {

	BeverageDao beverageDao;

	ContainerDao containerDao;

	public BootstrapApp() {

		beverageDao = new BeverageDao();
		containerDao = new ContainerDao();

	}

	public void CreateAllData()  {

		System.out.println("Starting vending Machine.... ");

		containerDao.initialize();

		System.out.println("All Container are filled .!");
		try{
		beverageDao.initialize();
		}catch(ContainerOverflowException e){
	e.printStackTrace();
		}
		System.out.println("Beverages are prepared...! and Good to Go");

	}

}
