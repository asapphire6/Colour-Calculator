package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import calculator.Calculator;

@RunWith(Parameterized.class)
public class calculateNTest {

	private Double yBar, illuminant, interval, expectedN;
	
	public calculateNTest(Double yBar, Double illuminant, Double interval, Double expectedN) {
		super();
		this.yBar = yBar;
		this.illuminant = illuminant;
		this.interval = interval;
		this.expectedN = expectedN;
	}

	@Parameters
	// values obtained from the 400-405 range of test files
	public static Double[][] getData(){
		Double[][] data = new Double[][] {{0.00259, 82.7549, 1.0, 0.214335191}, 
			{0.00304, 83.628, 1.0, 0.25422912000000003}, {0.00354, 84.5011, 1.0, 0.299133894}, {0.00411, 85.3742, 1.0, 0.350887962},
			{0.00475, 86.2473, 1.0, 0.409674675}, {0.00547, 87.1204, 1.0, 0.476548588}};
		return data;
	}

	@Test
	public void test() {
//		assertEquals(Calculator.calculateN(yBar, illuminant, interval), expectedN);
	}
}
