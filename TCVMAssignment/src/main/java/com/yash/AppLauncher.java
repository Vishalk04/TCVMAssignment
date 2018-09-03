package com.yash;

import org.apache.log4j.chainsaw.Main;

import com.yash.exceptions.ContainerOverflowException;

public class AppLauncher {

	public static void main(String[] args) throws ContainerOverflowException {
		new App().start();
	}
}
