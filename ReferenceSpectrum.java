package calculator;

import java.util.ArrayList;
import java.util.List;

public class ReferenceSpectrum implements Spectrum{

	private List<Double> wavelengthValues;
	private List<Double> reflectanceValues;
//	private Double interval = 1.0;
	
	public ReferenceSpectrum(List<Double> wavelengthValues, List<Double> reflectanceValues) {
		super();
		this.wavelengthValues = wavelengthValues;
		this.reflectanceValues = reflectanceValues;
	}

	public List<Double> getWavelengthValues() {
		return wavelengthValues;
	}

	public List<Double> getReflectanceValues() {
		return reflectanceValues;
	}
	
//	public void setInterval(int[] wavelength) throws DataFormatException {
//	try {
//		interval = Calculator.calculateInterval(wavelength);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//}
	
	private List<Double> returnReflectanceValuesInRange(Double[] dataRange){
//		System.out.println("Reference spectrum wavelengths: \n" + wavelengthValues.get(0).toString() + ", " + wavelengthValues.get(wavelengthValues.size()-1).toString());
//		System.out.println("Data range: " + dataRange[0].toString() + ", " + dataRange[1].toString());
//		System.out.println("Upper index: " + wavelengthValues.get(wavelengthValues.indexOf(dataRange[0])));
//		System.out.println("Lower index: " + wavelengthValues.get(wavelengthValues.indexOf(dataRange[1])));
		return reflectanceValues.subList(wavelengthValues.indexOf(dataRange[0]), wavelengthValues.indexOf(dataRange[1])+1);
	}
	
	private List<Double> returnWavelengthValuesInRange(Double[] dataRange){
		return wavelengthValues.subList(wavelengthValues.indexOf(dataRange[0]), wavelengthValues.indexOf(dataRange[1])+1);
	}

	// this method is to be used on the standard observer and illuminant data when
	// the data imported is at different intervals than 1nm 
	private List<Double> changeInterval(List<Double> wavelengthsInRange, List<Double> reflectanceInRange, Double interval){
		// create a List object to hold the extracted reflectance values
		List<Double> newReflectanceValues = new ArrayList<Double>();
		// extract reflectance values at given intervals
		for(int i = 0; i < wavelengthsInRange.size(); i++) {
			if(wavelengthsInRange.get(i).intValue() % interval.intValue() == 0) {
				newReflectanceValues.add(reflectanceInRange.get(i));
			}
		}
		return newReflectanceValues;
	}
	
	// use this method to match the range and datapoint intervals of the imported spectrum
	public List<Double> getMatchingReferenceData(Double[] dataRange, Double interval){
		List<Double> reflectanceInRange = returnReflectanceValuesInRange(dataRange);
		List<Double> wavelengthInRange = returnWavelengthValuesInRange(dataRange);
		List<Double> matchingReferenceData = changeInterval(wavelengthInRange, reflectanceInRange, interval);
		return matchingReferenceData;
	}
}
