package calculator;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileImport {
	
	private static List<ImportedSpectrum> data = new ArrayList<ImportedSpectrum>();
	
	public static List<ImportedSpectrum> getData() {
		return data;
	}
	
	public static void resetData() {
		data.clear();
	}

	public static void fileImport() throws IOException{

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(null, "txt");
		chooser.setMultiSelectionEnabled(true);
		chooser.addChoosableFileFilter(filter);
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);

		switch (returnVal) {
		case JFileChooser.APPROVE_OPTION : 
			File[] selectedFiles = chooser.getSelectedFiles();
			for(File file : selectedFiles) {
				Path filePath = Paths.get(file.getAbsolutePath());
				data.add(createImportedSpectrum(filePath));
			}
			
		case JFileChooser.CANCEL_OPTION : 
			System.out.println("Import cancelled"); 
			break;
			
		case JFileChooser.ERROR_OPTION :
			throw new IOException("Import error occurred");
		}
	}

	private static List<String> readFile(Path path) throws IOException{
		List<String> spectrum = new ArrayList<String>();
		spectrum = Files.readAllLines(path, Charset.defaultCharset());
//		try {spectrum = Files.readAllLines(path, Charset.defaultCharset());
//		} 
//		catch (IOException e) {
//			e.printStackTrace();
//		}
		return spectrum;
	}
	
	private static List<String[]> prepareDataForSpectrum(Path p) throws IOException{
		List<String> spectrum = readFile(p);
		List<String[]> spectralData = extractDataInCorrectRange(spectrum);
		return spectralData;
	}
	
	public static ImportedSpectrum createImportedSpectrum(Path p) throws IOException{
		List<String[]> spectralData = prepareDataForSpectrum(p);
		System.out.println(p.getFileName().toString() + " - file imported");
		return new ImportedSpectrum(p.getFileName().toString(), extractWavelength(spectralData), getReflectance(spectralData));
	}
	
	public static ReferenceSpectrum createReferenceSpectrum(Path p) throws IOException{
		List<String[]> spectralData = prepareDataForSpectrum(p);
		System.out.println(p.getFileName().toString() + " - file imported");
		return new ReferenceSpectrum(extractWavelength(spectralData), getReflectance(spectralData));
	}
	
	private static List<Double> extractWavelength(List<String[]> spectralData) {
		List<Double> wavelength = new ArrayList<Double>();
		for(String[] s : spectralData) {
			wavelength.add(Double.parseDouble(s[0]));
		}
		return wavelength;
	}

	private static List<String[]> extractDataInCorrectRange(List<String> spectrum) {
		List<String[]> spectralData = new ArrayList<String[]>(); 
		for (String value : spectrum) {
			String[] formattedValue = formatDataString(value);
			if(checkData(formattedValue)) {
				spectralData.add(formatDataString(value));
//				System.out.println(value);
			}
		}
//		System.out.println("Start of file: " + spectralData.get(0)[0].toString() + ", " + spectralData.get(0)[1].toString() +
//				" End of file: " + spectralData.get(spectralData.size()-1)[0].toString() + ", " + spectralData.get(spectralData.size()-1)[1].toString());
		return spectralData;
	}
	
	private static boolean checkData(String[] formattedValue) {
		return checkIfLineInTwoColumns(formattedValue) && checkIfLineIsData(formattedValue) 
				&& checkIfDataInRange(formattedValue[0]);
	}
	
	// checks if imported data is in the same range as the standard observer and illuminant
	private static boolean checkIfDataInRange(String wavelengthString) {
		Double wavelength = Double.parseDouble(wavelengthString);
		return wavelength >= 380 && wavelength <= 830;
	}

	//checks if the line contains data to be imported or just headings, etc.
	private static boolean checkIfLineInTwoColumns(String[] line) {
		return line.length == 2;
	}
	
	private static boolean checkIfLineIsData(String[] line) {
//		Double.parseDouble(line[0]);
//		Double.parseDouble(line[1]);
		try {
			Double.parseDouble(line[0]);
			Double.parseDouble(line[1]);
		} catch (NumberFormatException e) {
//			System.out.println("checkIfLineIsData(): not a number");
			return false;
		}
		return true;
	}
	
	private static String[] formatDataString(String value){
		value = value.trim(); 
		String[] splitString = value.split("[\\s,]+");
		return splitString;
	}

	private static List<Double> getReflectance(List<String[]> spectralData) {
		List<Double> reflectanceValues = new ArrayList<Double>(); 
		for (String[] s : spectralData) {
			reflectanceValues.add(Double.parseDouble(s[1]));
		}
		return reflectanceValues; 		
	}
}