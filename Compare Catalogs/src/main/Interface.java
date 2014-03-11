package main;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.ScrollPaneConstants;

import main.Catalog;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class Interface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JTextField textFieldP;
	private JTextField textCoorP;
	private JTextField textRadiusP;
	private JTextField textFieldS;
	private JTextField textRadiusS;
	private JTable table;
	private DefaultTableModel tableModel;
	
	private JButton buttonLoad;
	private JButton btnStart;
	private JMenuItem mntmAbout;
	
	private boolean open=false;
	private boolean load;
	
	private static Catalog Info;
	
	private String path;
	private DescriptionData primaryData;
	private DescriptionData secondaryData;


	/**
	 * Create the frame.
	 */
	public Interface() {
		setFont(new Font("Calibri", Font.BOLD, 28));
		setTitle("Compare Catalog");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 1009);
		setEnabled(true);
		//setMinimumSize(new Dimension(1200,1009));
		//pack();
		load=false;
		validate();
		setLocationRelativeTo(null);
		Info = new Catalog();
		
		
		
		contentPane = new JPanel();
		contentPane.setForeground(new Color(146, 208,80));
		contentPane.setBackground(new Color(0, 102, 102));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("Export");
		mnFile.setMnemonic(KeyEvent.VK_E);
		mnFile.setFont(new Font("Calibri", Font.BOLD, 15));
		
		menuBar.add(mnFile);
		
		JMenuItem mntmExportResult = new JMenuItem("Export result");
		mntmExportResult.setFont(new Font("Calibri", Font.PLAIN, 14));
		mnFile.add(mntmExportResult);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int response = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    if (response == JOptionPane.NO_OPTION) {
				    } else if (response == JOptionPane.YES_OPTION) {
				      System.exit(0);
				    } else if (response == JOptionPane.CLOSED_OPTION) {
				    }
			}
		});
		mntmExit.setFont(new Font("Calibri", Font.PLAIN, 14));
		mnFile.add(mntmExit);
		
		JMenu mnFu = new JMenu("Functions for filter");
		mnFu.setFont(new Font("Calibri", Font.BOLD, 14));
		menuBar.add(mnFu);
		
		JMenuItem mntmAbsolute = new JMenuItem("Absolute");
		mntmAbsolute.setFont(new Font("Calibri", Font.PLAIN, 14));
		mnFu.add(mntmAbsolute);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Distance");
		mntmNewMenuItem.setFont(new Font("Calibri", Font.PLAIN, 14));
		mnFu.add(mntmNewMenuItem);
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setMnemonic(KeyEvent.VK_H);
		mnHelp.setFont(new Font("Calibri", Font.BOLD, 15));
		menuBar.add(mnHelp);
		
		JMenuItem mntmCatalogSelection = new JMenuItem("Catalog Selection");
		mntmCatalogSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"The two catalogs to be compared. Usually WDS and another VizieR catalog \n\n"+
							"a) Catalog P:  The VizieR identifier of the primary catalog with which to compare. By default WDS. \n"+
							"b) Catalog S:  The VizieR identifier of secondary catalog.\n"+
							"c) Catalog P:  Coordinates and Radius(��):\n"+
								"           Consider only P stars within these coordinates and radius (interpreted in seconds)\n"+
								"           If the user writes ALL in the coordinates field, then the whole catalog is considered.\n"+
							"d) Catalog S:  Radius(��): \n"
								+"          For each P star, consider only S stars within the given radius (interpreted in seconds).\n"+
								"           In the case of WDS we consider that each WDS row contains in fact two stars: the primary and the secondary.\n"+
								"           Therefore, the system will look for S stars around the primary, and also for S stars around the secondary. \n"+
							"e) The button load consults VizieR loading internally the data");

			}
		});
		mntmCatalogSelection.setFont(new Font("Calibri", Font.PLAIN, 14));
		mnHelp.add(mntmCatalogSelection);
		
		JMenuItem mntmCatalogDescription = new JMenuItem("Catalog Description");
		mntmCatalogDescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"a) Catalog P: Label of each column in catalog. P \n"+
							"     If the primary catalog is WDS, this column additionally includes the coordinates for B star.\n"+
						"b) Catalog S: Label of each column in catalog S.\n"+
							"     This information is obtained automatically from VizieR by the application"
						  + "     (except the coordinates of the secondary star in the case of WDS,\n"
						  + "     which are computed from the primary coordinates, the last separation and the last PA in the catalog).\n"+
						    "     The names of the fields are used in the rest of the application.");

				
			}
		});
		mntmCatalogDescription.setFont(new Font("Calibri", Font.PLAIN, 14));
		mnHelp.add(mntmCatalogDescription);
		
		JMenuItem mntmFilters = new JMenuItem("Filters");
		mntmFilters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,
						"a) Catalog P: \n"
					+  "          In this field, you can apply filters for selecting P rows (before comparing).\n"
					+   "b) Catalog S: \n"
					+   "         In this one, you can apply filters for selecting S rows (before comparing too). \n"
					
			);
			}
			
		});
		mntmFilters.setFont(new Font("Calibri", Font.PLAIN, 14));
		mnHelp.add(mntmFilters);
		
		JMenuItem mntmCriteriumForSelecting = new JMenuItem("Criterium for selecting S rows");
		mntmCriteriumForSelecting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"In the text field, you can define which criteria will be used to compare rows between both catalogs.");
			}
		});
		mntmCriteriumForSelecting.setFont(new Font("Calibri", Font.PLAIN, 14));
		mnHelp.add(mntmCriteriumForSelecting);
		
		JMenuItem mntmCriterionForDetecting = new JMenuItem("Criterium for detecting errors");
		mntmCriterionForDetecting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						" Here, you can define in which case you want the program to detect some error. \n"
					+   " For instance we consider that magnitude difference over 0.5 should be reported. \n\n"
				    +   " The One to One check-box indicates that the WDS star must be selected only if after the previous criterium\n"
					+   " (the selection of S rows) only one row remains for the star.\n"
				    +   " The second check-box 'Show Closest Candidate' is used when the previous check-box is not selected,\n"
					+   " that is several S candidates are allowed, but we only want to select the closest one (in terms of distance).");

			}
		});
		mntmCriterionForDetecting.setFont(new Font("Calibri", Font.PLAIN, 14));
		mnHelp.add(mntmCriterionForDetecting);
		
		JSeparator separator_1 = new JSeparator();
		mnHelp.add(separator_1);
		
		
		mnHelp.add(getMenuAbout());
		
		path =  System.getProperty("user.dir");
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 161, 967, 161);
		panel.setForeground(new Color(146, 208,80));
		panel.setBackground(new Color(0, 102, 102));
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 51),3), "Filters", 
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
				javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 20), new java.awt.Color(255, 153, 51)));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label_5 = new JLabel("Catalog P");
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Calibri", Font.PLAIN, 15));
		label_5.setBounds(30, 38, 58, 14);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Catalog S");
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("Calibri", Font.PLAIN, 15));
		label_6.setBounds(429, 38, 111, 14);
		panel.add(label_6);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Interface.class.getResource("/javax/swing/plaf/metal/icons/ocean/question.png")));
		lblNewLabel_1.setToolTipText("Example: \r\n MagA > 11 AND LAST_SEP>5");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(111, 27, 37, 25);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Filter");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 14));
		btnNewButton.setBounds(845, 74, 89, 23);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(30, 63, 360, 65);
		panel.add(scrollPane_4);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane_4.setViewportView(textArea);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(439, 61, 360, 65);
		panel.add(scrollPane_5);
		
		JTextArea textArea_5 = new JTextArea();
		textArea_5.setLineWrap(true);
		scrollPane_5.setViewportView(textArea_5);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if("comoBoxChanged".equals(event.getActionCommand())){
					
				}
			}
		});
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 14));
		comboBox.setBackground(new Color(0,102,102));
		comboBox.setForeground(new Color (154,200,153));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Funtion of filter", "Absolute", "Distance"}));
		comboBox.setBounds(202, 27, 128, 25);
		panel.add(comboBox);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 333, 472, 572);
		panel_1.setForeground(new Color(146, 208,80));
		panel_1.setBackground(new Color(0, 102, 102));
		panel_1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 51),3), "Criterium for selecting S rows", 
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
				javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 20), new java.awt.Color(255, 153, 51)));				
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("Result");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		label_1.setBounds(23, 230, 58, 14);
		panel_1.add(label_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 255, 426, 293);
		panel_1.add(scrollPane);
		
		JTextArea textArea_4 = new JTextArea();
		textArea_4.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(textArea_4);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setBounds(23, 86, 426, 123);
		panel_1.add(scrollPane_2);
		
		JTextArea textArea_2 = new JTextArea();
		scrollPane_2.setViewportView(textArea_2);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setToolTipText("aqui ponemos un ejemplo ;)");
		lblNewLabel_2.setIcon(new ImageIcon(Interface.class.getResource("/javax/swing/plaf/metal/icons/ocean/question.png")));
		lblNewLabel_2.setBounds(23, 35, 37, 25);
		panel_1.add(lblNewLabel_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(5, 11, 967, 133);		
		panel_2.setForeground(new Color(146, 208,80));
		panel_2.setBackground(new Color(0, 102, 102));
		panel_2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 51),3), "Catalog Selection", 
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
				javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 20), new java.awt.Color(255, 153, 51)));
		panel_2.setLayout(null);
		contentPane.add(panel_2);
		
		JLabel label = new JLabel("Catalog P");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Calibri", Font.PLAIN, 15));
		label.setBounds(20, 50, 69, 14);
		panel_2.add(label);
		
		textFieldP = new JTextField();
		textFieldP.setColumns(10);
		textFieldP.setBounds(20, 75, 86, 20);
		panel_2.add(textFieldP);
		
		textCoorP = new JTextField();
		textCoorP.setColumns(10);
		textCoorP.setBounds(148, 75, 207, 20);
		panel_2.add(textCoorP);
		
		textRadiusP = new JTextField();
		textRadiusP.setColumns(10);
		textRadiusP.setBounds(353, 75, 86, 20);
		panel_2.add(textRadiusP);
		
		JLabel label_11 = new JLabel("Coordinates of Catalog P");
		label_11.setForeground(Color.WHITE);
		label_11.setFont(new Font("Calibri", Font.PLAIN, 15));
		label_11.setBounds(148, 50, 162, 14);
		panel_2.add(label_11);
		
		JLabel label_12 = new JLabel("Radius (\")");
		label_12.setForeground(Color.WHITE);
		label_12.setFont(new Font("Calibri", Font.PLAIN, 15));
		label_12.setBounds(356, 50, 65, 14);
		panel_2.add(label_12);
		
		JLabel label_13 = new JLabel("Catalog S");
		label_13.setForeground(Color.WHITE);
		label_13.setFont(new Font("Calibri", Font.PLAIN, 15));
		label_13.setBounds(481, 50, 58, 14);
		panel_2.add(label_13);
		
		JLabel label_14 = new JLabel("Radius around each P star (\") ");
		label_14.setForeground(Color.WHITE);
		label_14.setFont(new Font("Calibri", Font.PLAIN, 15));
		label_14.setBounds(610, 50, 195, 14);
		panel_2.add(label_14);
		
		ButtonListener o = new ButtonListener();
		
		buttonLoad = new JButton("Load");
		buttonLoad.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonLoad.addActionListener(o);		
		buttonLoad.setFont(new Font("Calibri", Font.BOLD, 15));
		buttonLoad.setBorderPainted(true);
		buttonLoad.setBounds(852, 74, 89, 23);
		panel_2.add(buttonLoad);
		
		
		//Info.addObserver(statusTextTable);
		textFieldS = new JTextField();
		textFieldS.setColumns(10);
		textFieldS.setBounds(481, 75, 86, 20);
		panel_2.add(textFieldS);
		
		textRadiusS = new JTextField();
		textRadiusS.setColumns(10);
		textRadiusS.setBounds(610, 75, 207, 20);
		panel_2.add(textRadiusS);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(500, 333, 472, 572);
		panel_3.setForeground(new Color(146, 208,80));
		panel_3.setBackground(new Color(0, 102, 102));
		panel_3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 51),3), "Criterium for detecting errors", 
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
				javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 20), new java.awt.Color(255, 153, 51)));
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel label_4 = new JLabel("Result");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Calibri", Font.PLAIN, 15));
		label_4.setBounds(23, 230, 58, 14);
		panel_3.add(label_4);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("One to One");
		chckbxNewCheckBox.setFont(new Font("Calibri", Font.PLAIN, 14));
		chckbxNewCheckBox.setForeground(Color.WHITE);
		chckbxNewCheckBox.setBackground(new Color(0, 102, 102));	
		chckbxNewCheckBox.setBounds(176, 180, 146, 23);
		panel_3.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Show Closest Candidate");
		chckbxNewCheckBox_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		chckbxNewCheckBox_1.setBounds(176, 206, 251, 26);
		chckbxNewCheckBox_1.setForeground(Color.WHITE);
		chckbxNewCheckBox_1.setBackground(new Color(0, 102, 102));
		panel_3.add(chckbxNewCheckBox_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(23, 255, 426, 293);
		panel_3.add(scrollPane_1);
		
		JTextArea textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_3.setBounds(23, 50, 426, 123);
		panel_3.add(scrollPane_3);
		
		JTextArea textArea_3 = new JTextArea();
		scrollPane_3.setViewportView(textArea_3);
		
		btnStart = new JButton("Start");
		btnStart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnStart.setFont(new Font("Calibri", Font.BOLD, 15));
		btnStart.setBorderPainted(true);
		btnStart.setBounds(445, 916, 89, 23);
		contentPane.add(btnStart);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_4.setBounds(980, 0, 193, 905);
	//	panel_4.setForeground(new Color(146, 208,80));
		panel_4.setForeground(new Color(153, 200,153));
		//panel_4.setBackground(new Color(102, 204, 51));	
		panel_4.setBackground(new Color(153, 200, 153));	
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel label_2 = new JLabel("Catalog P");
		label_2.setForeground(new Color(0, 102, 102));
		label_2.setFont(new Font("Calibri", Font.PLAIN, 15));
		label_2.setBounds(10, 60, 69, 14);
		label_2.setBackground(new Color(0, 102, 102));
		panel_4.add(label_2);
		
		JLabel label_3 = new JLabel("Catalog S");
		label_3.setForeground(new Color(0, 102, 102));
		label_3.setFont(new Font("Calibri", Font.PLAIN, 15));
		label_3.setBounds(125, 60, 58, 14);
		label_3.setBackground(new Color(0, 102, 102));
		panel_4.add(label_3);
		
		JLabel lblNewLabel = new JLabel("Catalog Description");
		lblNewLabel.setForeground(new Color(0, 102, 102));
		lblNewLabel.setBackground(new Color(0, 102, 102));
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 11, 173, 24);
		panel_4.add(lblNewLabel);
		
		table = new JTable();
		table.setShowGrid(false);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tableModel = new DefaultTableModel(
				new Object[][] {
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
						{"", ""},
					},
					new String[] {
						"New column", "New column"
					});
				
		table.setModel(tableModel);
		table.setBounds(10, 85, 173, 480);
		table.setBackground(new Color(154, 200, 153));
		panel_4.add(table);		
	
	}
	
	

	private JMenuItem getMenuAbout() {
		mntmAbout = new JMenuItem("About");
		mntmAbout.setFont(new Font("Calibri", Font.PLAIN, 14));
		mntmAbout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null,"application developed by: \n "+
												"Alicia Mireya Daza Castillo \n"+ "Jorge Gonz�lez L�pez \n" +
												"Rosa Rodr�guez Navarro\n"+
												"Rafael Caballero Rold�n\n"+
												"date: 2014/11/30 \n"+
												"version 2.1.1");
			}
			
			});		
		return mntmAbout;
	}

	/**
	 * 
	 * class ButtonGroupListener
	 *
	 */
	public class ButtonGroupListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//Do whatever with e.getActionCommand() 
		}
	}	
	
	/**
	 * 
	 * class ButtonListener LOAD/STAR
	 *
	 */
	public class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if (o == buttonLoad){
				load=true;
				Map<String,DescriptionData> mp=new LinkedHashMap<String,DescriptionData>();
				String sourceP = textFieldP.getText();
				String coordP = textCoorP.getText();
				String radP = textRadiusP.getText();
				
				String sourceS = textFieldS.getText();
				String coordS = textCoorP.getText();
				String radS = textRadiusS.getText();
				
				JFileChooser selecFile=new JFileChooser(path);
				@SuppressWarnings("unused")
				int i=selecFile.showSaveDialog(Interface.this);
				try{
					String fileName=selecFile.getSelectedFile().getAbsolutePath();
					String fileNotes = fileName+".notes";
					if (alreadyexists(fileName)/* && alreadyexists(fileNotes)*/){
					    Info.saveCatalogFile(fileName,fileNotes,sourceP,coordP,radP);
					    Info.setCatalogPath(fileName);
					    
					    open=true;					   
					    primaryData = new DescriptionData(fileName);
					    System.out.println("hola");
					    primaryData.parser();
					    insertNames(primaryData.getDt());
					  
					    Set s=mp.entrySet();
						Iterator it=s.iterator();

					        while(it.hasNext())
					        {
					            // ENTRADA DEL MAP Map.Entry PATA OBTENER key & value
					            Map.Entry m =(Map.Entry)it.next();
					            // getKey PARA OBTENER key 
					            String key=(String)m.getKey();
					           	table.setToolTipText(key);         

					        }
					    table.update(table.getGraphics());
					}
				}
				catch(Exception e2){};
			}else if (o == btnStart){
				//Start
			}
		}

		private void insertNames(LinkedHashMap<String, DataStructure> dt) {
			/*Iterator<String> it = dt.keySet().iterator();
			int row = 0;
			//.setToolTipText("aqui ponemos un ejemplo ;)");
			while (it.hasNext()){
				
				String name = it.next();
				tableModel.setValueAt(name, row, 0);
				row++;
			}*/
			int row = 0;
			for (Map.Entry<String,DataStructure> entry : dt.entrySet()) {
			    String key = entry.getKey();
			    DataStructure value = entry.getValue();
			    tableModel.setValueAt(key, row, 0);
			    //table.getComponentAt(row, 0).setToolTipText
			    row++;
			}
			
		}
	}
	
		
	private boolean alreadyexists(String file ) {
		boolean value = true;
		File f = new File(file);
		
		if (f.exists()) {
			 int result = JOptionPane.showConfirmDialog(Interface.this, 
					       new String("The file \n"+file+"\n exists, overwrite?"),
                     "Existing file", JOptionPane.YES_NO_CANCEL_OPTION);
             if (result == JOptionPane.YES_OPTION) 
                 value = true;
             else value = false;    
             
		}
		return value;
	}
	
	/************************************ AUXILIAR CLASESS ***************************************/
	

}
