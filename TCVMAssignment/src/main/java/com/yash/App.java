package com.yash;

import java.util.Scanner;

import com.yash.controllers.IReport;
import com.yash.controllers.IVendingMachine;
import com.yash.controllers.ReportImpl;
import com.yash.controllers.VendingMachineImpl;
import com.yash.exceptions.ContainerOverflowException;
import com.yash.exceptions.MaterialOutOfStockException;
import com.yash.model.InputScanner;

public class App {
	InputScanner scan;
	IVendingMachine vendingMachine;
	IReport report;

	public App() throws ContainerOverflowException {

	
			new BootstrapApp().CreateAllData();

			scan = new InputScanner(new Scanner(System.in));
			report = new ReportImpl();

			vendingMachine = new VendingMachineImpl();
		
	}

/*	public App(InputScanner scan, IVendingMachine vendingMachine, IReport report) {
		super();
		this.scan = scan;
		this.vendingMachine = vendingMachine;
		this.report = report;

	}*/

	public void start() throws ContainerOverflowException {
		String option = "";
		boolean choice = true;

		while (choice) {

			System.out.println("-------------------------Welcome-------------------------------");
			System.out.println("1 - Tea");
			System.out.println("2 - Black Tea");
			System.out.println("3 - Coffee");
			System.out.println("4 - Black Coffee");
			System.out.println("5 - Rifill Container");
			System.out.println("6 - Reset Container ");
			System.out.println("7 - Total sale report");
			System.out.println("8 - Sale By beverages ");
			System.out.println("9 - Container Status ");
			System.out.println("10- Container Refill Report ");
			System.out.println("11- exit ");

			System.out.println("Please enter valid option:");

			option = scan.getString();

			switch (option) {

			case "1":
				prepareBeverage("TEA");
				break;

			case "2":
				prepareBeverage("BLACKTEA");
				break;

			case "3":
				prepareBeverage("COFFEE");
				break;

			case "4":
				prepareBeverage("BLACKCOFFEE");
				break;

			case "5":
				System.out.println("Please Enter The Container Name: ");
				String container = scan.getString();
				System.out.println("Please Enter The Quantity to be update: ");
				int quantity = scan.nextInt();
				System.out.println(
						"updated size of container is: " + vendingMachine.refillContainer(container, quantity));
				break;

			case "6":
				vendingMachine.resetContainer();

				System.out.println("current status of container is: ");
				break;

			case "7":
				report.generateTotalSaleReport();
				break;

			case "8":
				report.getSalesReportByBeverages();
				break;

			case "9":
				System.out.println("Please Enter The Container Name: ");
				container = scan.getString();
				System.out.println("current status of container is: " + report.checkContainerStatus(container));
				break;

			case "10":
				try {
					report.getContainerRefillReport();
				} catch (RuntimeException e) {
					System.out.println(e.getMessage());
				}
				break;

			case "11":
				choice = false;
				break;

			}

		}

	}

	public void prepareBeverage(String beverage) {
		try {
			System.out.println("Enter the quantity:");
			Integer quantity = scan.nextInt();
			Double price = vendingMachine.checkBeverageAvailabilityAndCalculateTotalPrice(beverage, quantity);

			Double enteredAmount = new Double(0.00);

			while (enteredAmount < price) { 

				System.out.println("Please Enter the Amount Rs: " + (price - enteredAmount));

				enteredAmount = enteredAmount + scan.nextDouble();

			}

			Double change = vendingMachine.placeBeverageOrderAndReturnChange(beverage, 1, price, enteredAmount);
			if (change > 0.0) {
				System.out.println("Please take Your Change Rs: " + change);
				System.out.println(".....");
			}
			System.out.println("Your Beverage is ready..!");
			System.out.println("Thank You ..!");
		} catch (MaterialOutOfStockException | ContainerOverflowException e) {

			System.out.println(e.getMessage()); 

		}
	}

}
