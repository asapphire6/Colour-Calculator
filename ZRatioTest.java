package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import calculator.Calculator;

@RunWith(Parameterized.class)
public class ZRatioTest {

	private Double zValue;
	private Double expected;
	
	public ZRatioTest(Double zValue, Double expected) {
		this.zValue = zValue;
		this.expected = expected;
	}
	
	@Parameters
	public static Double[][] getXYZdata(){
		Double[][] xyzData = new Double[][]{
				{56.24,0.5241018915806446},		//violet
				{33.03,0.3078073520431844},		//orange
				{0.34,0.003168468050096358},	//red
				{45.25,0.42168582137311816},		//pink
				{85.3,0.7949127196271155},		//white
				{3.28,0.030566397659753095},		//yellow
				{12.28,0.11443761075053903},	//blue
				{24.99,0.23288240168208227}};	//green
		return xyzData;
	}
	
	@Test
	public void calculateZratioTest() {
		assertEquals(Calculator.calculateZratio(zValue), expected);
	}
}
