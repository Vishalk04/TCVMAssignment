package com.yash;

import java.util.Scanner;

public class App {

	Scanner scan = new Scanner(System.in);

	public void start() {

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

	private void menu() {

		System.out.println("1 - Tea");
		System.out.println("2 - Black Tea");
		System.out.println("3 - Coffee");
		System.out.println("4 - Black Coffee");
		System.out.println("5- exit");

		String option = scan.nextLine();

		switch (option) {
		
		case "1":
			getQuanity();
			break;
			
		case "5":
			start();
			break;

		}

	}

}
