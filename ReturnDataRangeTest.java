package tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import calculator.ImportedSpectrum;

@RunWith(Parameterized.class)
public class ReturnDataRangeTest {

	private List<Double> wavelength;
	private Double[] expectedRange;
	
	public ReturnDataRangeTest(List<Double> wavelength, Double[] expectedRange) {
		this.wavelength = wavelength;
		this.expectedRange = expectedRange;
	}
	
	@Parameters
	public static List<Object> getData(){
		List<Double> list1 = new ArrayList<Double>();
		list1.add(390.0);
		list1.add(400.0);
		list1.add(410.0);
		list1.add(450.0);
		list1.add(830.0);
		List<Double> list2 = new ArrayList<Double>();
		list2.add(400.0);
		list2.add(450.0);
		list2.add(500.0);
		list2.add(550.0);
		list2.add(600.0);
		list2.add(650.0);
		list2.add(700.0);
		List<Object> data = new ArrayList<Object>();
			data.add(new Object[] {list1, new Double[] {390.0, 830.0}}); 
			data.add(new Object[] {list2, new Double[] {400.0, 700.0}});
		return data;
	}
	
	@Test
	public void getDataRangeTest() {
		ImportedSpectrum s = new ImportedSpectrum(" ", wavelength, new ArrayList<Double>());
		assertArrayEquals(expectedRange, s.getSpectrumDataRange());
	}

}
