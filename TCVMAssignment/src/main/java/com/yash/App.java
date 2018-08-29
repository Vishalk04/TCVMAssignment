package com.yash;

import java.util.Scanner;

import com.yash.exceptions.ContainerOverflowException;
import com.yash.exceptions.MaterialOutOfStockException;

public class App {

	Scanner scan = new Scanner(System.in);
	com.yash.controllers.IVendingMachine vendingMachine;


	public void start() {
		try {
			vendingMachine = new com.yash.controllers.VendingMachineImpl();
		} catch (ContainerOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("welcome");

		System.out.println("1 - menu");
		System.out.println("2 - setup");
		System.out.println("3- exit");
		System.out.println("please enter valid option");

		String option = scan.nextLine();
		switch (option) {

		case "1":
			menu();
			break;

		case "2":
			setUp();

		}

	}

	private void setUp() {

		System.out.println("1 - Rifill Container");
		System.out.println("2 - Check Total Sale");
		System.out.println("3- Container Status");
		System.out.println("4- Reset Container ");
		System.out.println("5- Exit");
		String option = scan.nextLine();

		switch (option) {

		case "1":
			break;

		case "5":
			start();
			break;

		}

	}
	
	private int getQuanity(){
		System.out.println("Enter the quantity:");
		return scan.nextInt();
	}

	private void menu()  {

		System.out.println("1 - Tea");
		System.out.println("2 - Black Tea");
		System.out.println("3 - Coffee");
		System.out.println("4 - Black Coffee");
		System.out.println("5- exit");

		String option = scan.nextLine();
   
		switch (option) {
		
		case "1":
			
			getQuanity();
			try {
				System.out.println(vendingMachine.checkBeverageAvailabilityAndCalculateTotalPrice("TEA", 1));
				//if(vendingMachine.checkBeverageAvailabilityAndCalculateTotalPrice("TEA", 1)){
					vendingMachine.placeBeverageOrderAndReturnChange("TEA", 1, 10.00);
				//}
				
			} catch (MaterialOutOfStockException |ContainerOverflowException e) {
				System.out.println(e.getMessage());
			}
			break;
			
		case "5":
			start();
			break;

		}

	}

}
