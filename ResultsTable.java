package calculator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class ResultsTable {

	public File tableToFile (List<Double> _L, List<Double> _a, List<Double> _b, DefaultListModel<String> fileNames) throws IOException {
		String line = null;
		FileWriter fileWriter = new FileWriter("Untitled.txt");
		BufferedWriter buffer = new BufferedWriter(fileWriter);
		try {
			int lineLength = longestFileNameLength(fileNames);
			buffer.write(String.format("%" + lineLength + "s", "File Names") + "      " + "L" + "        " + "a" + "        " + "b");
			buffer.newLine();
			buffer.write(String.format("%" + lineLength + "s","				 -----------------------------------------"));
			buffer.newLine();
			buffer.newLine();

			for (int i = 0; i < fileNames.size(); i++) {
				// Formats the file names so that they are all justified to the right
				String fname = String.format("%" + lineLength + "s", fileNames.get(i));
				line = (fname + "    " + _L.get(i) + "    " + _a.get(i) + "    " + _b.get(i));
				buffer.write(line);
				buffer.newLine();
			}
			buffer.close();
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, "A write error has occurred", "Error", JOptionPane.ERROR_MESSAGE);
		}
//			System.out.println("A write error has occurred");}
		File file = new File("Untitled.txt");
		return file;
	}

	// This method finds the longest fileName in the selected file list and returns its length
	private int longestFileNameLength(DefaultListModel<String> fileNames) {
		int length = 0;
		for(int i = 0; i < fileNames.size(); i++) {
			if(fileNames.get(i).length() > length) {
				length = fileNames.get(i).length();
			}
		}
		return length;
	}
}
