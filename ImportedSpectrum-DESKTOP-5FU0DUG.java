package calculator;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

public class ImportedSpectrum implements Spectrum {

	private String fileName;
	private List<Double> wavelengthValues;
	private List<Double> reflectanceValues;
	
	public ImportedSpectrum(String file, List<Double> wavelengthValues, List<Double> reflectanceValues) {
		super();
		this.fileName = file;
		this.wavelengthValues = wavelengthValues;
		this.reflectanceValues = reflectanceValues;
	}
	
	public ImportedSpectrum() {
		
	}

	public String getFile() {
		if(fileName == null) {
			 throw new NoSuchElementException("This spectrum does not contain file info.");
		}
		return fileName;
	}

//	public void setFile(String file) {
//		this.fileName = file;
//	}

	public List<Double> getWavelengthValues() {
		return wavelengthValues;
	}

	public void setWavelengthValues(List<Double> wavelengthValues) {
		this.wavelengthValues = wavelengthValues;
	}

	public List<Double> getReflectanceValues() {
		return reflectanceValues;
	}

	public void setReflectanceValues(List<Double> reflectanceValues) {
		this.reflectanceValues = reflectanceValues;
	}

	public Double[] getSpectrumDataRange() {
		Double[] dataRange = new Double[2];
		dataRange[0] = wavelengthValues.get(0);
		dataRange[1] = wavelengthValues.get(wavelengthValues.size()-1);
		return dataRange;
	}
	
	public Double calculateInterval() throws DataFormatException {
		int interval = 0;
		int previous = 0;
		for(int i = 0; i < wavelengthValues.size()-2; i+=2) {
			previous = wavelengthValues.get(i+1).intValue() - wavelengthValues.get(i).intValue();
			interval = wavelengthValues.get(i+2).intValue() - wavelengthValues.get(i+1).intValue();
			if(interval != previous) {
				throw new DataFormatException("Wrong data format - data not in equal intervals");
			}
		}
		return Double.valueOf(interval);
	}
}
