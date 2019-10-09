package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import calculator.Calculator;

@RunWith(Parameterized.class)
public class YRatioTest {

	private Double yValue;
	private Double expected;
	
	public YRatioTest(Double yValue, Double expected) {
		this.yValue = yValue;
		this.expected = expected;
	}
	
	@Parameters
	public static Double[][] getXYZdata(){
		Double[][] xyzData = new Double[][]{
				{44.48,0.4448},	//violet
				{51.6,0.516},	//orange
				{12.23,0.1223},	//red
				{40.64,0.4064},	//pink
				{78.82,0.7881999999999999},	//white
				{27.66,0.2766},	//yellow
				{1.66,0.0166},	//blue
				{59.04,0.5904}};	//green
		return xyzData;
	}
	
	@Test
	public void calculateYratioTest() {
		assertEquals(Calculator.calculateYratio(yValue), expected);
	}
}
