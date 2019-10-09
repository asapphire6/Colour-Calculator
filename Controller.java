package calculator;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Controller {
		
	private Viewer theView;
	private Model theModel;
	
	public Controller(Viewer theView, Model theModel) {
		this.theView = theView;
		this.theModel = theModel;
		this.theView.addImportButtonListener(new importButtonListener());
		this.theView.addSaveButtonListener(new saveButtonListener());
		this.theView.addUnloadButtonListener(new unloadButtonListener());
		this.theView.addListSelectionListener(new selectionListener());
	}
	
	class importButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				FileImport.fileImport();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			for(ImportedSpectrum s : FileImport.getData()) {
				if (!theModel.checkIfFileAlreadyLoaded(s)){
					theModel.setFileNames(s);
					theModel.set_Labvalues(s);				
				} else {
					System.out.println("File already loaded");
				}
			}
			theView.displayFilenames(theModel.getFileNames());
			theView.setL_List(theModel.displayLabValues(0));
			theView.seta_List(theModel.displayLabValues(1));
			theView.setb_List(theModel.displayLabValues(2));
		}
	}
	
	class saveButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!theModel.isFilenameListEmpty()) {
				try {
					ResultsTable table = new ResultsTable();
					File tableFile = table.tableToFile(theModel.getL_values(), theModel.getA_values(), theModel.getB_values(), theModel.getFileNames());
					java.awt.Desktop.getDesktop().edit(tableFile);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error opening text editor", "Error", JOptionPane.ERROR_MESSAGE);
					//				System.out.println("Error opening text editor");
				}			
			}
		}

	}
	
	class unloadButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			FileImport.resetData();
			theModel.clearData();
			theView.clearLists();
		}
		
	}
	
	class selectionListener implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent e) {
			Color c = theModel.createColorObjectFromLab(theView.getSelectionIndex());
			theView.setSquareColour(c);
		}
	}
	
}
