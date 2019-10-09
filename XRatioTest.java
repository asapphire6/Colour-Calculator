package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized;
import calculator.Calculator;

@RunWith(Parameterized.class)
public class XRatioTest {
	
	private Double xValue;
	private Double expected;

	public XRatioTest(Double xValue, Double expected) {
		this.xValue = xValue;
		this.expected = expected;
	}

	@Parameters
	public static Double[][] getXYZdata(){
		Double[][] xyzData = new Double[][]{
				{42.87,0.45218827083853924},		//violet
				{52.94,0.5584055763515807},		//orange
				{22.57,0.23806599656696598},	//red
				{39.31,0.4146377636263816},		//pink
				{74.04,0.7809661668506053},		//white
				{23.93,0.2524111341536329},		//yellow
				{2.32,0.024471117059608374},	//blue
				{43.22,0.4558800341880491}};	//green
		return xyzData;
	}
	
	@Test
	public void calculateXratioTest() {
		assertEquals(Calculator.calculateXratio(xValue), expected);
	}
}
