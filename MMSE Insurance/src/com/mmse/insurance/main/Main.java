package com.mmse.insurance.main;

import com.mmse.insurance.view.View;

public class Main {
	
	static View view;
	
	public static void main(String[] args) {
		view = new View();
	}
	
	/**
	 * Function added just for trying JUnit testing
	 */
	public int add(int a, int b) {
		return a+b;
	}

}
