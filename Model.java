package calculator;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class Model {

	private File[] referenceFiles = new File[4];
	private ReferenceSpectrum stObs_xbar, stObs_ybar, stObs_zbar, illuminant;
	private DefaultListModel<String> fileNames = new DefaultListModel<String>();
	private List<Double> L_values = new ArrayList<Double>();
	private List<Double> a_values = new ArrayList<Double>();
	private List<Double> b_values = new ArrayList<Double>();
	private List<Double> X_values = new ArrayList<Double>();
	private List<Double> Y_values = new ArrayList<Double>();
	private List<Double> Z_values = new ArrayList<Double>();
	
	// initializing these here as they are used to calculate all three Lab values
	// so the methods used to calculate them are only called once
	private List<Double> matched_yBar = new ArrayList<Double>();
	private List<Double> matched_illuminantData = new ArrayList<Double>();
	private Double Yr = 0.0;
	private Double fy = 0.0;
	private Double spectrumInterval = 0.0;

	public Model() throws FileNotFoundException{
		this.referenceFiles[0] = new File("Illuminantd65.txt");				
		this.referenceFiles[1] = new File("StandardObserver_10deg_xbar.txt");
		this.referenceFiles[2] = new File("StandardObserver_10deg_ybar.txt");
		this.referenceFiles[3] = new File("StandardObserver_10deg_zbar.txt");
		
		// automatically import the illuminance and st obs spectra when application is opened
		if(referenceFiles[0].exists()) {
			try {
				this.illuminant = FileImport.createReferenceSpectrum(referenceFiles[0].toPath());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
//				System.out.println("Illuminant uploaded: " + illuminant.getWavelengthValues().get(0).toString() + " " + illuminant.getReflectanceValues().get(0).toString());
		} else { 
			throw new FileNotFoundException("Illuminant file not found.");
		}
		
		if(referenceFiles[1].exists()) {		
			try {
				this.stObs_xbar = FileImport.createReferenceSpectrum(referenceFiles[1].toPath());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
//				System.out.println("Xbar uploaded: " + stObs_xbar.getWavelengthValues().get(0).toString() + " " + stObs_xbar.getReflectanceValues().get(0).toString());
		} else {
			throw new FileNotFoundException("Xbar file not found.");
		}
		
		if(referenceFiles[1].exists()) {		
			try {
				this.stObs_ybar = FileImport.createReferenceSpectrum(referenceFiles[2].toPath());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
//				System.out.println("Ybar uploaded: " + stObs_ybar.getWavelengthValues().get(0).toString() + " " + stObs_ybar.getReflectanceValues().get(0).toString());
		} else {
			throw new FileNotFoundException("Ybar file not found.");
		}
		
		if(referenceFiles[1].exists()) {		
			try {
				this.stObs_zbar = FileImport.createReferenceSpectrum(referenceFiles[3].toPath());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
//				System.out.println("Zbar uploaded: " + stObs_zbar.getWavelengthValues().get(0).toString() + " " + stObs_zbar.getReflectanceValues().get(0).toString());
		} else {
			throw new FileNotFoundException("Zbar file not found.");
		}
	}
			
	public List<Double> getA_values() {
		return a_values;
	}

	public void setA_values(List<Double> a_values) {
		this.a_values = a_values;
	}

	public List<Double> getB_values() {
		return b_values;
	}

	public void setB_values(List<Double> b_values) {
		this.b_values = b_values;
	}

	public List<Double> getL_values() {
		return L_values;
	}
	
	public DefaultListModel<String> getFileNames() {
		return fileNames;
	}
	
	public boolean isFilenameListEmpty() {
		return fileNames.isEmpty();
	}

	public void setFileNames(ImportedSpectrum data) {
		String name = data.getFile();
		name = name.substring(0,name.lastIndexOf("."));
		fileNames.addElement(name);
	}

	public void clearData() {
		fileNames.removeAllElements();
//		System.out.println(fileNames.isEmpty());
		L_values.clear();
		a_values.clear();
		b_values.clear();
		X_values.clear();
		Y_values.clear();
		Z_values.clear();
		matched_yBar.clear();
		matched_illuminantData.clear();
		Yr = 0.0;
		fy = 0.0;
		spectrumInterval = 0.0;
	}
	
	private String removeFileExtension(ImportedSpectrum s) {
		String name = s.getFile();
		name = name.substring(0,name.lastIndexOf("."));
		return name;
	}
	
	public boolean checkIfFileAlreadyLoaded(ImportedSpectrum s) {
		return fileNames.contains(removeFileExtension(s));
	}
	
	public DefaultListModel<String> displayLabValues(int i){
		DefaultListModel<String> labValuesAsStrings = new DefaultListModel<String>();
		List<Double> labValues = new ArrayList<Double>();
		
		if(i == 0) {
			labValues = L_values;
		} else if (i == 1) {
			labValues = a_values;
		} else if (i == 2) {
			labValues = b_values;
		}
		
		for(Double d : labValues) {
			labValuesAsStrings.addElement(d.toString());
		}
		return labValuesAsStrings;
	}
	
	private void set_Lvalues(ImportedSpectrum s) {
		// get the required reference data in the range correspoding to that of the spectrum
		try {
			spectrumInterval = s.calculateInterval();
		} catch (DataFormatException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		matched_yBar = stObs_ybar.getMatchingReferenceData(s.getSpectrumDataRange(), spectrumInterval);

		matched_illuminantData = illuminant.getMatchingReferenceData(s.getSpectrumDataRange(), spectrumInterval);
		
		// calculate XYZ tristimulus values
		if(matched_yBar.isEmpty() || matched_illuminantData.isEmpty()) {
			System.out.println("Error: 1 or more reference spectra missing.");
		} else {
			Double _Nsum = Calculator.calculateN_sum(matched_yBar, matched_illuminantData, spectrumInterval);
			Double tristimulusSum = Calculator.calculateTristimulusSum(matched_yBar, matched_illuminantData, spectrumInterval, s.getReflectanceValues());
//			System.out.println("tristimulusSum = " + tristimulusSum.toString());
			Double spectrumY = Calculator.calculateTristimulusValue(tristimulusSum, _Nsum);
			Y_values.add(spectrumY);
			
//			System.out.println("spectrumY = " + spectrumY.toString());
			Yr = Calculator.calculateYratio(spectrumY);

			if(Calculator.compareToEpsilon(Yr)) {
				fy = Calculator.calculate_f_whenGreaterThanEpsilon(Yr);
			} else {fy = Calculator.calculate_f_whenSmallerThanEpsilon(Yr);}
//			System.out.println("L: " + ((116*fy)-16));
			L_values.add(Calculator.calculate_L(fy));
			
		}
	}
	
	private void set_aValues(ImportedSpectrum s) {
		
		List<Double> matched_xBar = stObs_xbar.getMatchingReferenceData(s.getSpectrumDataRange(), spectrumInterval);
		
		Double _Nsum = Calculator.calculateN_sum(matched_xBar, matched_illuminantData, spectrumInterval);
		Double tristimulusSum = Calculator.calculateTristimulusSum(matched_xBar, matched_illuminantData, spectrumInterval, s.getReflectanceValues());
		Double spectrumX = Calculator.calculateTristimulusValue(tristimulusSum, _Nsum);
		X_values.add(spectrumX);
		
		Double Xr = Calculator.calculateYratio(spectrumX);
		Double fx = 0.0;
		if(Calculator.compareToEpsilon(Xr)) {
			fx = Calculator.calculate_f_whenGreaterThanEpsilon(Xr);
		} else {fx = Calculator.calculate_f_whenSmallerThanEpsilon(Xr);}
		
		a_values.add(Calculator.calculate_a(fx, fy));
	}

	private void set_bValues(ImportedSpectrum s) {

		List<Double> matched_zBar = stObs_zbar.getMatchingReferenceData(s.getSpectrumDataRange(), spectrumInterval);

		Double _Nsum = Calculator.calculateN_sum(matched_zBar, matched_illuminantData, spectrumInterval);
		Double tristimulusSum = Calculator.calculateTristimulusSum(matched_zBar, matched_illuminantData, spectrumInterval, s.getReflectanceValues());
		Double spectrumZ = Calculator.calculateTristimulusValue(tristimulusSum, _Nsum);
		Z_values.add(spectrumZ);
		
		Double Zr = Calculator.calculateYratio(spectrumZ);
		Double fz = 0.0;
		if(Calculator.compareToEpsilon(Zr)) {
			fz = Calculator.calculate_f_whenGreaterThanEpsilon(Zr);
		} else {fz = Calculator.calculate_f_whenSmallerThanEpsilon(Zr);}

		b_values.add(Calculator.calculate_b(fy, fz));
	}

	public float[] createXYZFloatArray(int selectionIndex) {
		float[] XYZ_floats = new float[3];
		XYZ_floats[0] = X_values.get(selectionIndex).floatValue();
		XYZ_floats[1] = Y_values.get(selectionIndex).floatValue();
		XYZ_floats[2] = Z_values.get(selectionIndex).floatValue();
		return XYZ_floats;
	}

	public Color createColorObjectFromLab(int selectionIndex) {		
		// initialize CIELab colour space
		ColorSpace cs = new CIELabColourSpace(1, 3);
		float[] sRGB = cs.toRGB(createXYZFloatArray(selectionIndex));
		// create the Colour object
		Color colour = new Color((int)sRGB[0], (int)sRGB[1], (int)sRGB[2]);
		return colour;
	}

	public void set_Labvalues(ImportedSpectrum s) {
		set_Lvalues(s);
		set_aValues(s);
		set_bValues(s);
	}
}
