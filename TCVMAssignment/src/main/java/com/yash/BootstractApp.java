package com.yash;

import com.yash.dao.BeverageDao;
import com.yash.dao.ContainerDao;
import com.yash.exceptions.ContainerOverflowException;

public class BootstractApp {
	
	public void CreateAllData(){
	
		System.out.println("Initalizing vending Machine.... ");
		
		new ContainerDao().initialize();
		
		System.out.println("All Container Initalized and filled with full capacity.!");
		
		try {
			new BeverageDao().initialize();
			
			System.out.println("Beverages are prepared.!");
			
		} catch (ContainerOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
