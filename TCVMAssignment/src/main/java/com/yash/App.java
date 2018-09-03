package com.yash;

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

	public App() {

		new BootstrapApp().CreateAllData();
		scan = new InputScanner();
		this.report = new ReportImpl();

		try {
			vendingMachine = new VendingMachineImpl();
		} catch (ContainerOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public App(InputScanner scan, IVendingMachine vendingMachine, IReport report) {
		super();
		this.scan = scan;
		this.vendingMachine = vendingMachine;
		this.report = report;

	}

	public void start() {

		System.out.println("-------------------------Welcome-------------------------------");

		System.out.println("1 - menu");
		System.out.println("2 - setup");
		System.out.println("3 - reports");
		System.out.println("4- exit");
		System.out.println("please enter valid option");

		String option = scan.getString();

		switch (option) {

		case "1":
			customerMenu();
			break;

		case "2":
			setUpContainer();
			break;

		case "3":
			reports();
			break;

		case "4":
			System.out.println("exited");
			break;
		default:
			System.out.println("enter valid option");
			break;

		}

	}

	public void reports() {

		System.out.println("-------------------------Welcome-------------------------------");

		System.out.println("1 - Total sale report");
		System.out.println("2 - Sale By beverages ");
		System.out.println("3 - Container Status ");
		System.out.println("4 - Container Refill Report ");

		// System.out.println("5 - waste Report ");

		System.out.println("5- exit");
		System.out.println("please enter valid option");

		String option = scan.getString();

		String container;

		switch (option) {

		case "1":
			report.generateTotalSaleReport();

			break;

		case "2":
			report.getSalesReportByBeverages();
			break;

		case "3":
			System.out.println("Please Enter The Container Name: ");
			container = scan.getString();
			System.out.println("current status of container is: " + report.checkContainerStatus(container));

			break;

		case "4":
			try {
				report.getContainerRefillReport();
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
			break;

		case "5":
			start();
			break;

		default:
			break;
		}

	}

	public void setUpContainer() {

		System.out.println("Please Select your option...");

		System.out.println("1 - Rifill Container");
		System.out.println("2- Reset Container ");
		System.out.println("3- Exit");
		String option = scan.getString();

		String container;
		int quantity;
		switch (option) {

		case "1":
			try {
				System.out.println("Please Enter The Container Name: ");
				container = scan.getString();
				System.out.println("Please Enter The Quantity to be update: ");
				quantity = scan.nextInt();
				System.out.println(
						"updated size of container is: " + vendingMachine.refillContainer(container, quantity));
			} catch (ContainerOverflowException e) {
				System.out.println(e.getMessage());
			}

			break;

		case "2":
			try {
				vendingMachine.resetContainer();
			} catch (ContainerOverflowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("current status of container is: ");
			break;

		case "3":
			start();
			break;
		}

	}

	public void customerMenu() {
		String option = "";

		System.out.println("Please Select your Beverage...");
		System.out.println("1 - Tea");
		System.out.println("2 - Black Tea");
		System.out.println("3 - Coffee");
		System.out.println("4 - Black Coffee");
		System.out.println("5- exit");
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
			start();
			break;

		default:
			break;
		}

	}

	public void prepareBeverage(String beverage) {
		try {
			System.out.println("Enter the quantity:");
			int quantity = scan.nextInt();
			Double price = vendingMachine.checkBeverageAvailabilityAndCalculateTotalPrice(beverage, quantity);

			double enteredAmount = 0;

			while (enteredAmount < price) {

				System.out.println("Please Enter the Amount Rs: " + (price - enteredAmount));

				enteredAmount = enteredAmount + scan.nextDouble();

			}

			Double change = vendingMachine.placeBeverageOrderAndReturnChange("TEA", 1, price, enteredAmount);
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
