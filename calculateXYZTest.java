package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import calculator.Calculator;

@RunWith(Parameterized.class)
public class calculateXYZTest {

	private Double triStimulusSum, _Nsum, expectedTristimulusValue;
	
	public calculateXYZTest(Double triStimulusSum, Double _Nsum, Double expectedTristimulusValue) {
		super();
		this.triStimulusSum = triStimulusSum;
		this._Nsum = _Nsum;
		this.expectedTristimulusValue = expectedTristimulusValue;
	}

	@Parameters
	// data taken from 400 nm for x, y and zBar respectively
	public static Double[][] getData(){
		Double[][] spectralData = new Double[][]{{0.086821507, 7.042441990, 1.232832405624118}, 
			{0.011961048, 7.042441990, 0.16984233618089056}, {0.393005864, 7.042441990, 5.580533919314542}};
		return spectralData;	
	}

	@Test
	public void CalculateTristimulusTest() {
		assertEquals(Calculator.calculateTristimulusValue(triStimulusSum, _Nsum), expectedTristimulusValue);
	}

}
