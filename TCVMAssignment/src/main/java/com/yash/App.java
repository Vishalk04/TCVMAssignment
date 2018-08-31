package com.yash;

import java.util.Scanner;

import com.yash.controllers.IVendingMachine;
import com.yash.controllers.VendingMachineImpl;
import com.yash.exceptions.ContainerOverflowException;
import com.yash.exceptions.MaterialOutOfStockException;

public class App {

	Scanner scan = new Scanner(System.in);
	IVendingMachine vendingMachine;

	public App() {

		new BootstrapApp().CreateAllData();

		try {
			vendingMachine = new VendingMachineImpl();
		} catch (ContainerOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start() {

		try  {
			System.out.println("-------------------------Welcome-------------------------------");

			System.out.println("1 - menu");
			System.out.println("2 - setup");
			System.out.println("3 - reports");
			System.out.println("4- exit");
			System.out.println("please enter valid option");

			String option = scan.nextLine();

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
				scan.close();
				System.exit(1);
				break;
			default:break;

			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	private void reports() {
		try  {
			
		System.out.println("-------------------------Welcome-------------------------------");

		System.out.println("1 - Total sale report");
		System.out.println("2 - Sale By beverages ");
		System.out.println("3 - Container Status ");
		System.out.println("4 - Container Refill Report ");
		
		System.out.println("5 - Container Refill Report ");
		
		System.out.println("6- exit");
		System.out.println("please enter valid option");

		String option = scan.nextLine();

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
			scan.close();
			System.exit(1);
			break;
		default:break;

		}
	} catch (Exception e) {
		e.printStackTrace();

	}
		
	}

	private void setUpContainer() {
		try  {

			System.out.println("Please Select your option...");

			System.out.println("1 - Rifill Container");
			System.out.println("2 - Check Total Sale");
			System.out.println("3- Container Status");
			System.out.println("4- Reset Container ");
			System.out.println("5- Exit");
			String option = scan.nextLine();

			String container;
			int quantity;
			switch (option) {

			case "1":
				System.out.println("Please Enter The Container Name: ");
				container = scan.nextLine();
				System.out.println("Please Enter The Quantity to be update: ");
				quantity = scan.nextInt();
				System.out.println(
						"updated size of container is: " + vendingMachine.refillContainer(container, quantity));
				break;

			case "3":
				System.out.println("Please Enter The Container Name: ");
				container = scan.nextLine();
				System.out.println("current status of container is: " + vendingMachine.checkContainerStatus(container));
				break;

			case "4":
				vendingMachine.resetContainer();
				System.out.println("current status of container is: ");
				break;

			case "5":
				start();
				break;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void customerMenu() {
		try {
			while (true) {
				System.out.println("Please Select your Beverage...");
				System.out.println("1 - Tea");
				System.out.println("2 - Black Tea");
				System.out.println("3 - Coffee");
				System.out.println("4 - Black Coffee");
				System.out.println("5- exit");
				String option = scan.nextLine();

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
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void prepareBeverage(String beverage) {
		try {
			System.out.println("Enter the quantity:");
			int quantity = scan.nextInt();
			Double price = vendingMachine.checkBeverageAvailabilityAndCalculateTotalPrice(beverage, quantity);
			System.out.println("Please Enter the Amount Rs: " + price);
			double enteredAmount = scan.nextDouble();

			Double change = vendingMachine.placeBeverageOrderAndReturnChange("TEA", 1, enteredAmount);
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
