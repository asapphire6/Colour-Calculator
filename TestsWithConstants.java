package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import calculator.Calculator;

public class TestsWithConstants {

	@Test
	public void compareToEpsilonTestTrue() {
		assertTrue(Calculator.compareToEpsilon(0.4448));
	}
	
	@Test
	public void compareToEpsilonTestFalse() {
		assertFalse(Calculator.compareToEpsilon(216.00/24390));
	}
	
	@Test
	public void calculate_f_when_Xr_GreaterThanEpsilon() {
		assertEquals(Calculator.calculate_f_whenGreaterThanEpsilon(0.4448), (Double)0.7633462788792914);
	}
	
	@Test
	public void calculate_f_when_Xr_SmallerThanEpsilon() {
		assertEquals(Calculator.calculate_f_whenSmallerThanEpsilon(0.0088560886), (Double)0.2068937244142401);
	}
}
