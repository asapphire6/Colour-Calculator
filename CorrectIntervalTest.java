package tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import calculator.ImportedSpectrum;

@RunWith(Parameterized.class)
public class CorrectIntervalTest {

	private List<Double> wavelength;
	private Double expectedInterval;
	
	public CorrectIntervalTest(List<Double> wavelength, Double expectedInterval) {
		this.wavelength = wavelength;
		this.expectedInterval = expectedInterval;
	}
	
	@Parameters
	public static List<Object> getCorrectWavelengthList() {
		List<Double> list1 = new ArrayList<Double>();
		list1.add(390.0);
		list1.add(400.0);
		list1.add(410.0);
		list1.add(420.0);
		list1.add(430.0);
		Double interval1 = 10.0;
		List<Double> list2 = new ArrayList<Double>();
		list2.add(400.0);
		list2.add(405.0);
		list2.add(410.0);
		list2.add(415.0);
		list2.add(420.0);
		list2.add(425.0);
		list2.add(430.0);
		Double interval2 = 5.0;
		List<Object> data = new ArrayList<Object>();
			data.add(new Object[] {list1, interval1}); 
			data.add(new Object[] {list2, interval2});
		return data;
	}
	
	@Test
	public void calculate_correct1nmIntervalTest() throws DataFormatException {
		ImportedSpectrum s = new ImportedSpectrum(" ", wavelength, new ArrayList<Double>());
		assertEquals(s.calculateInterval(),Double.valueOf(expectedInterval));
	}
}
