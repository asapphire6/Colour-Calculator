package calculator;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionListener;

public class Viewer extends JFrame {

	private JPanel contentPane = new JPanel();
	private JPanel panel = new JPanel();
	private JList<String> list = new JList<String>();
	private JList<String> list_1 = new JList<String>();
	private JList<String> list_2 = new JList<String>();
	private JList<String> list_3 = new JList<String>();
	private JLabel lblL = new JLabel("L");
	private JLabel lblA = new JLabel("a");
	private JLabel lblB = new JLabel("b");
	private JButton btnImportFiles = new JButton("LOAD");
	private JButton btnUnload = new JButton("UNLOAD");
	private JButton btnSave = new JButton("SAVE");
	private JPanel panel_1 = new JPanel();
	private JLabel lblFiles = new JLabel("Files:");
	private final JScrollPane scrollPane = new JScrollPane();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Viewer frame = new Viewer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Viewer() {
		setTitle("CIELab Colour Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 475);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);
		scrollPane.setViewportView(panel);
		
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridwidth = 4;
		gbc_list.insets = new Insets(0, 0, 0, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		panel.add(list, gbc_list);
				
		GridBagConstraints gbc_list_1 = new GridBagConstraints();
		gbc_list_1.insets = new Insets(0, 0, 0, 5);
		gbc_list_1.fill = GridBagConstraints.BOTH;
		gbc_list_1.gridx = 5;
		gbc_list_1.gridy = 1;
		panel.add(list_1, gbc_list_1);
		
		GridBagConstraints gbc_list_2 = new GridBagConstraints();
		gbc_list_2.insets = new Insets(0, 0, 0, 5);
		gbc_list_2.fill = GridBagConstraints.BOTH;
		gbc_list_2.gridx = 7;
		gbc_list_2.gridy = 1;
		panel.add(list_2, gbc_list_2);
		
		GridBagConstraints gbc_list_3 = new GridBagConstraints();
		gbc_list_3.fill = GridBagConstraints.BOTH;
		gbc_list_3.gridx = 9;
		gbc_list_3.gridy = 1;
		panel.add(list_3, gbc_list_3);
		
		lblL.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblL = new GridBagConstraints();
		gbc_lblL.insets = new Insets(0, 0, 5, 5);
		gbc_lblL.gridx = 5;
		gbc_lblL.gridy = 0;
		panel.add(lblL, gbc_lblL);
		
		lblA.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblA = new GridBagConstraints();
		gbc_lblA.insets = new Insets(0, 0, 5, 5);
		gbc_lblA.gridx = 7;
		gbc_lblA.gridy = 0;
		panel.add(lblA, gbc_lblA);
		
		lblB.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblB = new GridBagConstraints();
		gbc_lblB.insets = new Insets(0, 0, 5, 0);
		gbc_lblB.gridx = 9;
		gbc_lblB.gridy = 0;
		panel.add(lblB, gbc_lblB);
		
		btnImportFiles.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_btnImportFiles = new GridBagConstraints();
		gbc_btnImportFiles.insets = new Insets(0, 0, 5, 5);
		gbc_btnImportFiles.gridx = 1;
		gbc_btnImportFiles.gridy = 0;
		panel.add(btnImportFiles, gbc_btnImportFiles);
		
		btnUnload.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_btnUnload = new GridBagConstraints();
		gbc_btnUnload.insets = new Insets(0, 0, 5, 5);
		gbc_btnUnload.gridx = 2;
		gbc_btnUnload.gridy = 0;
		panel.add(btnUnload, gbc_btnUnload);
		
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 3;
		gbc_btnSave.gridy = 0;
		panel.add(btnSave, gbc_btnSave);
		
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(534, 32, 72, 67);
		panel.add(panel_1);
		
		lblFiles.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblFiles = new GridBagConstraints();
		gbc_lblFiles.insets = new Insets(0, 0, 5, 5);
		gbc_lblFiles.gridx = 0;
		gbc_lblFiles.gridy = 0;
		panel.add(lblFiles, gbc_lblFiles);
	}
	
	public void addImportButtonListener(ActionListener listenForImportButton){
		btnImportFiles.addActionListener(listenForImportButton);
	}
	
	public void addSaveButtonListener(ActionListener listenForSaveButton){
		btnSave.addActionListener(listenForSaveButton);
	}
	
	public void addUnloadButtonListener(ActionListener listenForUnloadButton){
		btnUnload.addActionListener(listenForUnloadButton);
	}
	
	public void addListSelectionListener(ListSelectionListener listenForListSelection) {
		list.addListSelectionListener(listenForListSelection);
	}

	public void displayFilenames(ListModel<String> fileList) {
		list.setModel(fileList);		
	}
	
	public void setL_List(ListModel<String> L_List) {
		list_1.setModel(L_List);		
	}

	public void seta_List(ListModel<String> a_List) {
		list_2.setModel(a_List);
	}
	
	public void setb_List(ListModel<String> b_List) {
		list_3.setModel(b_List);
	}
	
	public void clearLists() {
		list.setModel(new DefaultListModel<String>());
		list_1.setModel(new DefaultListModel<String>());	// this isn't very elegant but it's the best I can do right now
		list_2.setModel(new DefaultListModel<String>());
		list_3.setModel(new DefaultListModel<String>());
	}
	
	public void setSquareColour(Color c) {
		panel_1.setBackground(c);
	}

	public int getSelectionIndex() {
		return list.getSelectedIndex();
	}

}
