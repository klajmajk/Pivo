package com.mmse.insurance.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mmse.insurance.main.Main;

public class testMain {

	Main tester = new Main();
	
	@Test
	public void testAdd() {
		assertEquals(4, tester.add(2, 2) );
	}

}
