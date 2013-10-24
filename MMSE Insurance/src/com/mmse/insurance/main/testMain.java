package com.mmse.insurance.main;

import static org.junit.Assert.*;

import org.junit.Test;

public class testMain {

	Main tester = new Main();
	
	@Test
	public void testAdd() {
		assertEquals(4, tester.add(2, 2) );
	}

}
