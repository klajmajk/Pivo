package com.mmse.insurance.main;

import com.mmse.insurance.control.Controller;
import com.mmse.insurance.model.Model;
import com.mmse.insurance.view.View;

public class Main {
	
	static Model model;
	static Controller controller;
	static View view;
	
	
	public static void main(String[] args) {
		model = new Model();
		controller = new Controller(model);
		view = new View(controller);
	}
	
	/**
	 * Function added just for trying JUnit testing
	 */
	public int add(int a, int b) {
		return a+b;
	}

}
