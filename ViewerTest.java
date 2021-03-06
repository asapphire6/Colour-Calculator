package calculator;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;

public class ViewerTest extends JFrame {

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
	public ViewerTest() {
		setTitle("CIELab Colour Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 728, 475);
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
		gbl_panel.columnWidths = new int[]{72, 43, 63, 77, 61, 12, 84, 35, 84, 35, 85, 0};
		gbl_panel.rowHeights = new int[]{23, 398, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblFiles.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFiles.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblFiles = new GridBagConstraints();
		gbc_lblFiles.anchor = GridBagConstraints.EAST;
		gbc_lblFiles.insets = new Insets(0, 0, 5, 5);
		gbc_lblFiles.gridx = 0;
		gbc_lblFiles.gridy = 0;
		panel.add(lblFiles, gbc_lblFiles);
		
		btnImportFiles.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_btnImportFiles = new GridBagConstraints();
		gbc_btnImportFiles.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnImportFiles.insets = new Insets(0, 0, 5, 5);
		gbc_btnImportFiles.gridx = 2;
		gbc_btnImportFiles.gridy = 0;
		panel.add(btnImportFiles, gbc_btnImportFiles);
		
		btnUnload.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_btnUnload = new GridBagConstraints();
		gbc_btnUnload.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnUnload.insets = new Insets(0, 0, 5, 5);
		gbc_btnUnload.gridx = 3;
		gbc_btnUnload.gridy = 0;
		panel.add(btnUnload, gbc_btnUnload);
		
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 4;
		gbc_btnSave.gridy = 0;
		panel.add(btnSave, gbc_btnSave);
		
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		panel_1.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.WEST;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.gridx = 5;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		
		lblL.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblL = new GridBagConstraints();
		gbc_lblL.insets = new Insets(0, 0, 5, 5);
		gbc_lblL.gridx = 6;
		gbc_lblL.gridy = 0;
		panel.add(lblL, gbc_lblL);
		
		lblA.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblA = new GridBagConstraints();
		gbc_lblA.insets = new Insets(0, 0, 5, 5);
		gbc_lblA.gridx = 8;
		gbc_lblA.gridy = 0;
		panel.add(lblA, gbc_lblA);
		
		lblB.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblB = new GridBagConstraints();
		gbc_lblB.insets = new Insets(0, 0, 5, 0);
		gbc_lblB.gridx = 10;
		gbc_lblB.gridy = 0;
		panel.add(lblB, gbc_lblB);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.insets = new Insets(0, 0, 0, 5);
		gbc_list.gridwidth = 5;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		panel.add(list, gbc_list);
		GridBagConstraints gbc_list_1 = new GridBagConstraints();
		gbc_list_1.fill = GridBagConstraints.BOTH;
		gbc_list_1.insets = new Insets(0, 0, 0, 5);
		gbc_list_1.gridx = 6;
		gbc_list_1.gridy = 1;
		panel.add(list_1, gbc_list_1);
		GridBagConstraints gbc_list_2 = new GridBagConstraints();
		gbc_list_2.fill = GridBagConstraints.BOTH;
		gbc_list_2.insets = new Insets(0, 0, 0, 5);
		gbc_list_2.gridx = 8;
		gbc_list_2.gridy = 1;
		panel.add(list_2, gbc_list_2);
		GridBagConstraints gbc_list_3 = new GridBagConstraints();
		gbc_list_3.fill = GridBagConstraints.BOTH;
		gbc_list_3.gridx = 10;
		gbc_list_3.gridy = 1;
		panel.add(list_3, gbc_list_3);
	}
	
	public void addImportButtonListener(ActionListener listenForImportButton){
		btnImportFiles.addActionListener(listenForImportButton);
	}
	
	public void addSaveButtonListener(ActionListener listenForSaveButton){
		btnSave.addActionListener(listenForSaveButton);
	}
	
	public void addListSelectionListener(ListSelectionListener listenForListSelection) {
		list.addListSelectionListener(listenForListSelection);
	}

	public void setFilenameList(ListModel<String> fileList) {
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
	
	public void setSquareColour(Color c) {
		panel_1.setBackground(c);
	}

	public int getSelectionIndex() {
		return list.getSelectedIndex();
	}

}
