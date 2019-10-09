package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import calculator.ImportedSpectrum;

@RunWith(Parameterized.class)
public class IncorrectIntervalTest {

	private List<Double> wavelength;
	
	public IncorrectIntervalTest(List<Double> wavelength) {
		this.wavelength = wavelength;
	}
		
	@Parameters
	public static Object[] getIncorrectWavelengthList() {
		List<Double> list1 = new ArrayList<Double>();
		list1.add(390.0);
		list1.add(420.0);
		list1.add(445.0);
		list1.add(446.0);
		list1.add(470.0);
		List<Double> list2 = new ArrayList<Double>();
		list2.add(400.0);
		list2.add(505.0);
		list2.add(610.0);
		list2.add(415.0);
		list2.add(320.0);
		list2.add(425.0);
		list2.add(430.0);
		Object[] data = new Object[] {list2, list2};
		return data;
	}
	
	@Test (expected = DataFormatException.class)
	public void calculate_incorrect1nmIntervalTest() throws DataFormatException {
		ImportedSpectrum s = new ImportedSpectrum(" ", wavelength, new ArrayList<Double>());
		s.calculateInterval();
	}
}
