package calculator;

import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

public class ColourCalculatorMain {

	public static void main (String[] args) {
		Viewer theView = new Viewer();
		Model theModel;
		try {
			theModel = new Model();
			Controller theController = new Controller(theView, theModel);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
        theView.setVisible(true);
	}
}
