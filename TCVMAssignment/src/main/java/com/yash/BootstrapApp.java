package com.yash;

import com.yash.dao.BeverageDao;
import com.yash.dao.ContainerDao;
import com.yash.dao.OrderDao;
import com.yash.exceptions.ContainerOverflowException;

public class BootstrapApp {
	
	public void CreateAllData(){
	
		System.out.println("Starting vending Machine.... ");
		
		new ContainerDao().initialize();
		
	//	new OrderDao().initialize();
		
		System.out.println("All Container are filled .!");
		
		try {
			
			new BeverageDao().initialize(); 
			 
			System.out.println("Beverages are prepared...! and Good to Go");
			
		} catch (ContainerOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
