package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import static javax.swing.GroupLayout.Alignment.*;
import javax.swing.*;



public class Interface extends JFrame {
	private JPanel mainPanel;
	private JPanel fieldsPanel;
	//private JTabbedPane tabbedPanel;
	private JMenuItem openFileItem;
	private JMenuItem exitFileItem;
	private JMenuItem searchFileItem;
	private JMenuItem downCatalFileItem;
	private JMenuItem upCatalFileItem;
	private JMenuBar menuBar;
	///////////////////////////////
	JLabel labelCoord;
	JLabel labelAnyText;
	JLabel labelRA;
	JLabel labelDec;
	JLabel labelRadius;
	JTextField raTextField;
	JTextField decTextField;
	JTextField radiusTextField;
	JTextField searchTextField;
	JButton findButton1;
	JButton findButton2;
	JFrame searchPanel;
	JTextArea resultTextArea;
	JScrollPane scrollPanel; 
	
	
	private static Catalog Info = new Catalog();

	
	public static void main(String[] args) {
		Interface mainClass = new Interface();

	}

	public Interface(){
		initialize();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	private void initialize() {
		this.setTitle("WDS Catalog");
		this.setEnabled(true);
		this.setVisible(true);
		this.setSize(500, 500);
		this.setJMenuBar(getMenBar());
		this.setContentPane(globalPanel());
		this.validate();
		
		
	}
	
	private JPanel globalPanel() {
		/*************Borrar************/
		labelCoord = new JLabel("Coordinates:");
		labelAnyText = new JLabel("Any text:");
		labelRA = new JLabel("Right Ascension");
		labelDec = new JLabel("Declination");
		labelRadius = new JLabel("Radius");
		raTextField = new JTextField();
		decTextField = new JTextField();
		radiusTextField = new JTextField();
		searchTextField = new JTextField();
		findButton1 = new JButton("Find");
		findButton2 = new JButton("Find");

		resultTextArea = new JTextArea();
		resultTextArea.setWrapStyleWord(true);
		resultTextArea.setLineWrap(true);
		
		scrollPanel = new JScrollPane(resultTextArea); 
		scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		searchTextField.setMaximumSize(new Dimension(1000,20));
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		menuBar=new JMenuBar();
		menuBar.add(getFileMenu());
		menuBar.add(getCatalogMenu());
		
		fieldsPanel = new JPanel();
		fieldsPanel.setEnabled(true);
		fieldsPanel.setVisible(true);
		
		GroupLayout fieldsLayout = new GroupLayout(fieldsPanel);
	
		fieldsPanel.setLayout(fieldsLayout);
		fieldsLayout.setAutoCreateGaps(true);
		fieldsLayout.setAutoCreateContainerGaps(true);
		
		fieldsLayout.setHorizontalGroup(fieldsLayout.createSequentialGroup()
				.addGroup(fieldsLayout.createParallelGroup(LEADING)
						.addComponent(labelCoord)
						.addComponent(labelAnyText))
				.addGroup(fieldsLayout.createParallelGroup(LEADING)
					.addGroup(fieldsLayout.createSequentialGroup()
						.addGroup(fieldsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(labelRA)
							.addComponent(raTextField))
						.addGroup(fieldsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(labelDec)
							.addComponent(decTextField))
						.addGroup(fieldsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(labelRadius)
								.addComponent(radiusTextField)))
					.addComponent(searchTextField)
					.addComponent(scrollPanel))
				.addGroup(fieldsLayout.createParallelGroup(TRAILING)
					.addComponent(findButton1)
					.addComponent(findButton2))
		);
		fieldsLayout.linkSize(SwingConstants.HORIZONTAL, findButton1);

		fieldsLayout.setVerticalGroup(fieldsLayout.createSequentialGroup()
				.addGroup(fieldsLayout.createParallelGroup(BASELINE)
					.addComponent(labelCoord)
					.addGroup(fieldsLayout.createSequentialGroup()
			            .addGroup(fieldsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
			                .addComponent(labelRA)
			                .addComponent(labelDec)
			                .addComponent(labelRadius))
			            .addGroup(fieldsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			                .addComponent(raTextField)
			                .addComponent(decTextField)
			                .addComponent(radiusTextField))) 
			            .addComponent(findButton1))
				.addGroup(fieldsLayout.createParallelGroup(LEADING)
					.addComponent(labelAnyText)
					.addComponent(searchTextField)
					.addComponent(findButton2))
				.addGroup(fieldsLayout.createParallelGroup(LEADING)
					.addComponent(scrollPanel))	
		);	
		
		mainPanel.add("North",menuBar);
		mainPanel.add("Center",fieldsPanel);
		/////////////////////////////
		//mainPanel.validate();
		return mainPanel;
	}
	
	private JMenuBar getMenBar() {
		return menuBar;
	}


	private JMenu getFileMenu() {
		JMenu fileMenu=new JMenu("File");
		fileMenu.add(getOpenFileItem());
		fileMenu.add(getSearchFileItem());
		fileMenu.add(getExitFileItem());
		return fileMenu;
	}
	
	private JMenuItem getOpenFileItem() {
		openFileItem=new JMenuItem("Open");
		openFileItem.setEnabled(true);
		/*openFileItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

			}
			});*/
		return openFileItem;
	}
	
	private JMenuItem getExitFileItem() {
		exitFileItem=new JMenuItem("Exit");
		exitFileItem.setEnabled(true);
		/*openFileItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

			}
			});*/
		return exitFileItem;
	}
	
	private JMenuItem getSearchFileItem() {
		searchFileItem=new JMenuItem("Search");
		searchFileItem.setEnabled(true);
		searchFileItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				searchPanel=new JFrame();
				searchPanel.setTitle("Searching");
				searchPanel.setEnabled(true);
				searchPanel.setVisible(true);
				searchPanel.setSize(500, 100);
				//searchPanel.setContentPane(getSearchPanel());
			}
			});

		return searchFileItem;
	}
	
/*	private JPanel getSearchPanel(){
				fieldsPanel = new JPanel();
				JButton button=new JButton();
				button.setText("ACEPTAR");
				JLabel label1=new JLabel();
				JLabel label2=new JLabel();
				label2.setText("Coordinates");
				label1.setText("Radius");
				final JTextField cordinates=new JTextField(3);
				cordinates.addKeyListener(new KeyAdapter()
				{
				   public void keyTyped(KeyEvent e)
				   {
				      char caracter = e.getKeyChar();
				      if(((caracter < '0') ||
				         (caracter > '9')) &&
				         (caracter != '\b')) //Backspace
				      {
				         e.consume();  // Ignore
				      }
				   }
				});
				final JTextField radius=new JTextField(3);
				radius.addKeyListener(new KeyAdapter()
				{
				   public void keyTyped(KeyEvent e)
				   {
				      char caracter = e.getKeyChar();
				      if(((caracter < '0') ||
				         (caracter > '9')) &&
				         (caracter != '\b')) 
				      {
				         e.consume();  
				      }
				   }
				});
				fieldsPanel.add(label1);
				fieldsPanel.add(cordinates);
				fieldsPanel.add(label2);
				fieldsPanel.add(radius);
				fieldsPanel.add(button);
				fieldsPanel.add(fieldsPanel);

			}
}
		return fieldsPanel;
	}*/
	
	private JMenu getCatalogMenu() {
		JMenu catalogMenu=new JMenu("Catalog");
		catalogMenu.add(getDownloadCatalogItem());
		catalogMenu.add(getUploadCatalogItem());
		return catalogMenu;
	}
	
	private JMenuItem getDownloadCatalogItem() {
		downCatalFileItem=new JMenuItem("Download WDS Catalog");
		downCatalFileItem.setEnabled(true);
		downCatalFileItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFileChooser selecFile=new JFileChooser();
				int i=selecFile.showSaveDialog(Interface.this);
				try{
				String fileName=selecFile.getSelectedFile().getAbsolutePath();
				Info.saveCatalog(fileName);
				}
				catch(Exception e2){};
				
				
			}
				

			});
		return downCatalFileItem;
	}
	
	private JMenuItem getUploadCatalogItem() {
		upCatalFileItem=new JMenuItem("Upload WDS Catalog");
		upCatalFileItem.setEnabled(true);

		return upCatalFileItem;
	}
}
