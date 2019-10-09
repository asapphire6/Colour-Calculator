package tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import calculator.ReferenceSpectrum;

@RunWith(Parameterized.class)
public class ReturnMatchingDataTest {

	private List<Double> wavelengths, reflectance, expectedReflectance;
	private Double[] dataRange;
	private Double interval;
	
	public ReturnMatchingDataTest(List<Double> wavelengths, Double[] dataRange, List<Double> reflectance,
			List<Double> expectedReflectance, Double interval) {
		super();
		this.wavelengths = wavelengths;
		this.dataRange = dataRange;
		this.reflectance = reflectance;
		this.expectedReflectance = expectedReflectance;
		this.interval = interval;
	}

	@Parameters
	public static List<Object> getData(){
		List<Double> wavelengths = new ArrayList<Double>();
		wavelengths.add(350.0);
		wavelengths.add(400.0);
		wavelengths.add(450.0);
		wavelengths.add(500.0);
		wavelengths.add(550.0);
		wavelengths.add(600.0);
		wavelengths.add(650.0);
		wavelengths.add(700.0);
		wavelengths.add(750.0);
		wavelengths.add(850.0);
		List<Double> reflectance = new ArrayList<Double>();
		reflectance.add(1.20);
		reflectance.add(1.15);
		reflectance.add(1.10);
		reflectance.add(1.05);
		reflectance.add(1.03);
		reflectance.add(1.01);
		reflectance.add(0.08);
		reflectance.add(0.06);
		reflectance.add(0.05);
		reflectance.add(0.02);
		List<Double> expectedReflectance = new ArrayList<Double>();
		expectedReflectance.add(1.15);
		expectedReflectance.add(1.10);
		expectedReflectance.add(1.05);
		expectedReflectance.add(1.03);
		expectedReflectance.add(1.01);
		expectedReflectance.add(0.08);
		expectedReflectance.add(0.06);
		Double interval = 50.0;
		List<Object> data = new ArrayList<Object>();
			data.add(new Object[] {wavelengths, new Double[] {400.0, 700.0}, reflectance, expectedReflectance, interval}); 
		return data;
	}
	
	@Test
	public void getDataInRangeTest() {
		ReferenceSpectrum r = new ReferenceSpectrum(wavelengths, reflectance);
		assertEquals(r.getMatchingReferenceData(dataRange, interval), expectedReflectance);
	}
	
	

}
