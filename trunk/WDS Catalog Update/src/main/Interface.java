package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import static javax.swing.GroupLayout.Alignment.*;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


/**
 * 
 * class Interface
 * 
 * @author Alicia Mireya Daza castillo
 * @author Jorge González López
 * @author Rosa Rodríguez Navarro
 * @since 1.1.1
 * @see class Catalog
 */


public class Interface extends JFrame {
	/**
	 * attributes
	 */
	private static final long serialVersionUID = -5507453381739405246L;
	private JPanel mainPanel;
	private JPanel fieldsPanel;
	
	private JMenuItem openFileItem;
	private JMenuItem exitFileItem;
	private JMenuItem downCatalFileItem;
	
	private JMenuBar menuBar;
	
	private JLabel labelCoord;
	private JLabel labelAnyText;
	private JLabel labelRA;
	private JLabel labelDec;
	private JLabel labelRadius;
	private JLabel labelCombo;
	private JLabel labelButton; // only for the space before constellation

	private JLabel labelWDShead1;
	private JLabel labelWDShead2;
	
	private JTextField raTextField;
	private JTextField decTextField;
	private JTextField radiusTextField;
	private JTextField searchTextField;	
	
	private JButton searchButton1;
	private JButton searchButton2;
	@SuppressWarnings("rawtypes")
	private JComboBox combo;
	private JButton selectButton;
	private JButton clearButton;
	private JButton notesButton;
	private JButton aladin;
	private JButton sdss;
	private JButton excelButton;
	
	private JTextPane resultTextPane;
	
	private JScrollPane scrollPanel; 
	private JScrollPane scrollstatusPanel; 
	
	private static StatusTextArea statusTextArea;
	
	private String statusMessage;
	
	private boolean open=false;	
	private boolean searchText;
	private boolean searchCoordinates;
	private boolean searchConst;
	
	// path to the application
	String path;
	
	
	private static Catalog Info;

	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Info = new Catalog();
		Interface mainClass = new Interface();
		

	}

	
	/** construction */
	public Interface(){
		statusMessage = "No selected file. Please, select one from your computer or download it.";
		searchText = false;
		searchCoordinates = false;
		initialize();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	

	/** interface initialization method */
	private void initialize() {
		this.setTitle("WDS Catalog");
		this.setEnabled(true);
		this.setVisible(true);
		this.setSize(1120, 640);
		this.setJMenuBar(getMenBar());
		this.setContentPane(globalPanel());
		this.validate();
		path =  System.getProperty("user.dir"); //new File(".").getCanonicalPath();
		checkExistingFile("wds.txt");
	}
	
	// try to open the name file by default
	private void checkExistingFile(String name) {
		File f = new File(path, name);
		if(f.exists())
			try {
				Info.setCatalogPath(f.getCanonicalPath());	
				open=true;

			} catch (IOException e) {				
				e.printStackTrace();
				Info = new Catalog();
			}				

	}
	
	/** interface creation method */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private JPanel globalPanel() {
		labelCoord = new JLabel("Coordinates:");
		labelAnyText = new JLabel("Any text:");
		labelRA = new JLabel("Right Ascension (e.g. 192141.60)");
		labelDec = new JLabel("   Declination (e.g. -222820.4)");
		labelRadius = new JLabel("   Radius (seconds)");
		labelButton = new JLabel("                       ");
		labelCombo = new JLabel("Filter by constellation");
		
		raTextField = new JTextField();
		raTextField.setMaximumSize(new Dimension(70,20));
		decTextField = new JTextField();
		decTextField.setMaximumSize(new Dimension(70,20));
		radiusTextField = new JTextField();
		radiusTextField.setMaximumSize(new Dimension(50,20));
		
		searchTextField = new JTextField();		
		searchTextField.setMaximumSize(new Dimension(1300,20));
		int condition = JComponent.WHEN_FOCUSED;
		  InputMap iMap = searchTextField.getInputMap(condition);
		  ActionMap aMap = searchTextField.getActionMap();

		  // the search text allows enter
		  String enter = "enter";
		  iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), enter);
		  aMap.put(enter, new AbstractAction() {
		     /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		     public void actionPerformed(ActionEvent arg0) {
		        searchAnyText();
		     }
		  });

		labelWDShead1 = new JLabel("    WDS   Discovr Comp  EPOCH      #  THETA       RHO     Magnitudes Spectral  Prop Mot  2nd PM   DM Desig Note     Precise");
		labelWDShead2 = new JLabel("Identifier             Frst Last      Fst Lst First  Last  Pri   Sec  Type      RA\" DEC\" RA\" DEC\"                 Coordinates");
		
		ButtonListener o = new ButtonListener();
		searchButton1 = new JButton("Search");
		searchButton1.addActionListener(o);
		
		searchButton2 = new JButton("Search");
		searchButton2.addActionListener(o);

		ComboListener c = new ComboListener();
		combo = new JComboBox(Constellations.names);
		combo.setMaximumSize(new Dimension(10,15));
		combo.addActionListener(c);

		selectButton = new JButton("Select All");
		selectButton.addActionListener(o);

		clearButton = new JButton("Clear");
		clearButton.addActionListener(o);

		notesButton = new JButton("Get Notes/Grab Coords.");
		notesButton.addActionListener(o);
		
		excelButton = new JButton("result to .xls");
		excelButton.addActionListener(o);

		aladin = new JButton("Aladin");
		aladin.addActionListener(o);

		sdss = new JButton("SDSS");
		sdss.addActionListener(o);

		resultTextPane = new JTextPane();

		
		scrollPanel = new JScrollPane(resultTextPane); 
		scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		Font font = new Font( "Monospaced", Font.PLAIN, 12 );  
		resultTextPane.setFont( font );
		labelWDShead1.setFont(font);
		labelWDShead2.setFont(font);

		
		statusTextArea = new StatusTextArea(statusMessage);
		
		scrollstatusPanel = new JScrollPane(statusTextArea); 
		scrollstatusPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		Info.addObserver(statusTextArea);
		scrollstatusPanel.setVisible(true);
		statusTextArea.setVisible(true);
		statusTextArea.setEditable(false);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		menuBar=new JMenuBar();
		menuBar.add(getFileMenu());
		menuBar.add(getMenuAbout());
		
		fieldsPanel = new JPanel();
		fieldsPanel.setEnabled(true);
		fieldsPanel.setVisible(true);
		
		GroupLayout fieldsLayout = new GroupLayout(fieldsPanel);
	
		fieldsPanel.setLayout(fieldsLayout);
		fieldsLayout.setAutoCreateGaps(true);
		fieldsLayout.setAutoCreateContainerGaps(true);
		fieldsLayout.setHorizontalGroup(fieldsLayout.createSequentialGroup()
				.addGroup(fieldsLayout.createParallelGroup(TRAILING)
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
								.addComponent(radiusTextField)) 		
						.addGroup(fieldsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(searchButton1))
						.addGroup(fieldsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(labelButton)
								.addComponent(aladin))
						.addGroup(fieldsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(labelButton)
								.addComponent(sdss))								
						.addGroup(fieldsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(labelCombo)
								.addComponent(combo)))
				.addGroup(fieldsLayout.createParallelGroup(LEADING)
					.addGroup(fieldsLayout.createSequentialGroup()								
					     .addComponent(searchTextField)
					     .addComponent(searchButton2)))
					.addComponent(labelWDShead1)
				    .addComponent(labelWDShead2)
					.addComponent(scrollPanel)
					.addGroup(fieldsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addGroup(fieldsLayout.createSequentialGroup()
									.addComponent(selectButton)
									.addComponent(clearButton)
									.addComponent(notesButton)
									.addComponent(excelButton)))
					.addComponent(scrollstatusPanel))
				.addGroup(fieldsLayout.createParallelGroup(CENTER))				
//					.addComponent(searchButton2))				
		);
		fieldsLayout.linkSize(SwingConstants.HORIZONTAL, searchButton2);

		fieldsLayout.setVerticalGroup(fieldsLayout.createSequentialGroup()
				.addGroup(fieldsLayout.createParallelGroup(CENTER)
					.addComponent(labelCoord)
					.addGroup(fieldsLayout.createSequentialGroup()
			            .addGroup(fieldsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
			                .addComponent(labelRA)
			                .addComponent(labelDec)
			                .addComponent(labelRadius)
			                .addComponent(labelButton)
			                .addComponent(labelCombo))

			            .addGroup(fieldsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
			                .addComponent(raTextField)
			                .addComponent(decTextField)
			                .addComponent(radiusTextField)			                
			                .addComponent(searchButton1)
			                .addComponent(aladin)
			                .addComponent(sdss)
			                .addComponent(combo))))    
				.addGroup(fieldsLayout.createParallelGroup(LEADING)
					.addComponent(labelAnyText)
					.addComponent(searchTextField)
					.addComponent(searchButton2))
				.addGroup(fieldsLayout.createParallelGroup(LEADING)
					.addComponent(labelAnyText)
					.addComponent(searchTextField))
				.addGroup(fieldsLayout.createParallelGroup(LEADING)					
    				    .addComponent(labelWDShead1))
                .addGroup(fieldsLayout.createParallelGroup(LEADING)					
    					.addComponent(labelWDShead2))
				.addGroup(fieldsLayout.createParallelGroup(LEADING)
						.addComponent(scrollPanel))
				.addGroup(fieldsLayout.createParallelGroup(LEADING)						
						    .addComponent(selectButton)
					   	    .addComponent(clearButton)
					   	    .addComponent(notesButton)
					   	    .addComponent(excelButton))
                .addGroup(fieldsLayout.createParallelGroup(LEADING)					   	    
				    .addComponent(scrollstatusPanel))
		);	
		mainPanel.add("North",menuBar);
		mainPanel.add("Center",fieldsPanel);
		return mainPanel;
	}
	
	
	private JMenuItem getMenuAbout() {
		JMenuItem fileMenuAbout= new JMenuItem("About...");
		fileMenuAbout.setMnemonic(KeyEvent.VK_A);
		fileMenuAbout.setEnabled(true);
		fileMenuAbout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null,"application developed by: \n "+
												"Alicia Mireya Daza Castillo \n"+ "Jorge González López \n" +
												"Rosa Rodríguez Navarro\n"+
												"Rafael Caballero Roldán\n"+
												"date: 2014/11/30 \n"+
												"version 2.1.1");
			}
			
			});		
		return fileMenuAbout;
	}
	
	
	private JMenuBar getMenBar() {
		
		return menuBar;
	}


	private JMenu getFileMenu() {
		JMenu fileMenu=new JMenu("File");		
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.add(getOpenFileItem());
		fileMenu.add(getDownloadCatalogItem());
		fileMenu.addSeparator();
		fileMenu.add(getExitFileItem());  
		
		return fileMenu;
	}
	
	/*method to open a file*/
	private JMenuItem getOpenFileItem() {
		openFileItem=new JMenuItem("Open");
		openFileItem.setMnemonic(KeyEvent.VK_O);
		openFileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
	            ActionEvent.CTRL_MASK));
		openFileItem.setEnabled(true);
		openFileItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser selecFile= new JFileChooser(path);				
				int status=selecFile.showOpenDialog(Interface.this);
				if(status == JFileChooser.APPROVE_OPTION){
					String file=selecFile.getSelectedFile().getAbsolutePath();	
					Info.setCatalogPath(file);				
				}
				open=true;
			}	
			});
		return openFileItem;
	}


	/* exit the application method */
	private JMenuItem getExitFileItem() {
		exitFileItem=new JMenuItem("Exit");		
		exitFileItem.setMnemonic(KeyEvent.VK_E);
		exitFileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
	           ActionEvent.CTRL_MASK));		
		exitFileItem.setEnabled(true);		
		exitFileItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				    int response = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    if (response == JOptionPane.NO_OPTION) {
				    } else if (response == JOptionPane.YES_OPTION) {
				      System.exit(0);
				    } else if (response == JOptionPane.CLOSED_OPTION) {
				    }
				}
			});
		return exitFileItem;
	}

	/* file download method */
	private JMenuItem getDownloadCatalogItem() {
		downCatalFileItem=new JMenuItem("Download WDS Catalog");
		downCatalFileItem.setMnemonic(KeyEvent.VK_D);
		downCatalFileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
		           ActionEvent.CTRL_MASK));	
		downCatalFileItem.setEnabled(true);
		downCatalFileItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFileChooser selecFile=new JFileChooser(path);
				@SuppressWarnings("unused")
				int i=selecFile.showSaveDialog(Interface.this);
				try{
					String fileName=selecFile.getSelectedFile().getAbsolutePath();
					String fileNotes = fileName+".notes";
					if (alreadyexists(fileName) && alreadyexists(fileNotes)){
					    Info.saveCatalog(fileName,fileNotes);	
					    Info.setCatalogPath(fileName);
					    open=true;
					}
				}
				catch(Exception e2){};
			}
			});
		return downCatalFileItem;
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

	
	/**********************************AUXILIAR CLASSES************************************************/
	
	/**
	 * class StatusTextArea
	 * 
	 */
	public class StatusTextArea extends JTextArea implements Observer{
		

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public StatusTextArea(String text){
			this.setText(text);
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			if (arg1.equals("append")){
				this.append(Info.getMessage());
			}else if (arg1.equals("clear")){
				this.setText(Info.getMessage());
			}else if (searchText || searchCoordinates || searchConst){// arg1 is the line to show
				if (searchText  || searchConst)
				this.setText(searchText ? "Search any text from Catalog": "Search by constellation");
				String searchText = searchTextField.getText();
				
				int index1 = ((String) arg1).indexOf(searchText);
				int index2 = index1 + searchText.length();
				int end = ((String) arg1).length();
				
				StyledDocument doc = resultTextPane.getStyledDocument();

		        Style style = resultTextPane.addStyle("I'm a Style", null);
		        StyleConstants.setFontFamily(style, "Lucida Console");
		        StyleConstants.setForeground(style, Color.black);

		        if (index1!=-1) {
		        try { doc.insertString(doc.getLength(), ((String) arg1).substring(0,index1),style); }
		        catch (Exception e){}

		        StyleConstants.setForeground(style, Color.red);

		        try { doc.insertString(doc.getLength(), ((String) arg1).substring(index1,index2),style); }
		        catch (BadLocationException e){}
		        
		        StyleConstants.setForeground(style, Color.black);

		        try { doc.insertString(doc.getLength(), ((String) arg1).substring(index2,end),style); }
		        catch (BadLocationException e){}

		        } else try { doc.insertString(doc.getLength(), ((String) arg1),style); }
                catch (BadLocationException e){}
		        
		        try { doc.insertString(doc.getLength(), "\n", style); }
		        catch (BadLocationException e){}
 
		        resultTextPane.revalidate();
		        resultTextPane.update(resultTextPane.getGraphics());
		        
		        
			}else if(searchCoordinates){// arg1 is the line to show
				this.setText("Search from Right Ascention, Declination and Radius");
				int index1 = 112;
				int index2 = 130;
				int end = ((String) arg1).length();
				
				StyledDocument doc = resultTextPane.getStyledDocument();

		        Style style = resultTextPane.addStyle("I'm a Style", null);
		        StyleConstants.setFontFamily(style, "Lucida Console");
		        StyleConstants.setForeground(style, Color.black);

		        try { doc.insertString(doc.getLength(), ((String) arg1).substring(0,index1),style); }
		        catch (BadLocationException e){}

		        StyleConstants.setForeground(style, Color.blue);

		        try { doc.insertString(doc.getLength(), ((String) arg1).substring(index1,index2),style); }
		        catch (BadLocationException e){}
		        
		        StyleConstants.setForeground(style, Color.black);

		        try { doc.insertString(doc.getLength(), ((String) arg1).substring(index2,end),style); }
		        catch (BadLocationException e){}
		        
		        try { doc.insertString(doc.getLength(), "\n", style); }
		        catch (BadLocationException e){}
		        
		        resultTextPane.revalidate();
		        resultTextPane.update(resultTextPane.getGraphics());
			}
			
			this.revalidate();
			this.update(this.getGraphics());
		}

	}
	
	
	/**
	 *  class ComboListener
	 */
	public class ComboListener implements ActionListener {
	    /** Listens to the combo box. */
	    @SuppressWarnings("rawtypes")
		public void actionPerformed(ActionEvent e) {
	        JComboBox cb = (JComboBox)e.getSource();
	        int n = cb.getSelectedIndex();
	        if (n!=0) {
	          String abr =	Constellations.abrev[n-1];
				if (open==false  ){
					JOptionPane.showMessageDialog(null,"must open a file");
				}
				else{
					searchConst = true;
					searchText =  searchCoordinates =  false;
					resultTextPane.setText("");
					raTextField.setText("");
					decTextField.setText("");
		            radiusTextField.setText("");
		            searchTextField.setText("");
					Info.searchInFile(Catalog.SearchMode.CONST,abr);
					if(resultTextPane.getText().length()==0){
						JOptionPane.showMessageDialog(null,"No pair found in constellation '" + cb.getSelectedItem());
					}


				
				}

	        }
	        
	    }
	}

	/**
	 * 
	 * class ButtonListener
	 *
	 */
	public class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			String ra = raTextField.getText();
			String dec = decTextField.getText();
			String radius = radiusTextField.getText();
			
			if (o==aladin)//launch aladin			
				 aladin(path,ra,dec);
			if (o==sdss)//launch sdss 
				  sdss(ra,dec);		
			if (o == searchButton1){//Search from coordinates and radius			
				if (open==false  ){
					JOptionPane.showMessageDialog(null,"must open a file");
				}
				else{
					searchCoordinates = true;
					searchText =  searchConst =  false;
					resultTextPane.setText("");
					combo.setSelectedIndex(0);
					//try{
						Info.searchInFile(Catalog.SearchMode.COORDS,ra, dec, radius);
					/*}catch (java.lang.Exception ex){
						JOptionPane.showMessageDialog(null,"Incorrect coordinate format ");
						incorrectFormat = true;
					}*/
					
					if((resultTextPane.getText().length()==0) /*&& !incorrectFormat*/){
						JOptionPane.showMessageDialog(null,"Coordinates not found in file");
					}
				}
			}
			else if (o == searchButton2){//Search any text
				  searchAnyText();
				/*searchCoordinates = false;
				searchText = true;
				resultTextPane.setText("");
				Info.searchInFile(Catalog.SearchMode.TEXT,searchTextField.getText());*/
				}else if(o == clearButton){
							resultTextPane.setText("");
							combo.setSelectedIndex(0);
							resultTextPane.setText("");
					       raTextField.setText("");
					       decTextField.setText("");
		                   radiusTextField.setText("");
		                   searchTextField.setText("");
							resultTextPane.revalidate();
					        resultTextPane.update(resultTextPane.getGraphics());
				}else if(o == selectButton){
					resultTextPane.requestFocusInWindow();
					resultTextPane.selectAll();
				}else if(o == notesButton){
					resultTextPane.requestFocusInWindow();						
					String line = resultTextPane.getSelectedText();
					if(line==null) JOptionPane.showMessageDialog(null,"Select coordinate");
					else{
					if (line.length()==130) {
  					   String id  = line.substring(10, 22);
					   String notes = line.substring(107, 111);
  					   String ras = line.substring(112, 121);
  					   String decs = line.substring(121, 130);
  					   raTextField.setText(ras);
  					   decTextField.setText(decs);
  					   Info.showNotes(id,notes);
				     }
				}}
				
				else if(o == excelButton){
					resultTextPane.requestFocusInWindow();						
					String line = resultTextPane.getText();
					//Dimension lines= resultTextPane.getSize();
					writeExcel(line);
				}
		}
	}

	/**
	 * @param line
	 * @param lines
	 */
	public void writeExcel(String line){
		try{
			//create Excel workbook
			WritableWorkbook  workbook = Workbook.createWorkbook(new File("wds.xls"));		
			
			
			//create a new sheet within the workbook
			WritableSheet sheet = workbook.createSheet("WDS1", 0);
			
			WritableFont tipo = new WritableFont
					(WritableFont.TIMES, 10, WritableFont.BOLD, false);
			WritableCellFormat  formato = new WritableCellFormat(tipo);
			formato.setBorder(Border.ALL,BorderLineStyle.MEDIUM);
			formato.setAlignment(Alignment.CENTRE);
			formato.setBackground(Colour.AQUA);	
			
			WritableFont tipo1 = new WritableFont
					(WritableFont.TIMES,10, WritableFont.NO_BOLD, false);
			WritableCellFormat  formato1 = new WritableCellFormat(tipo1);
			
			//Create cells of various types
			sheet.addCell(new jxl.write.Label(0, 0, "WDS",formato));
			sheet.addCell(new jxl.write.Label(1,0,"Discovr Comp",formato));
			sheet.addCell(new jxl.write.Label(2,0,"EPOCH",formato));
			sheet.addCell(new jxl.write.Label(3, 0,"",formato));
			sheet.addCell(new jxl.write.Label(4, 0,"#",formato));
			sheet.addCell(new jxl.write.Label(5, 0,"THETA",formato));
			sheet.addCell(new jxl.write.Label(6, 0,"",formato));
			sheet.addCell(new jxl.write.Label(7, 0, "RHO",formato));
			sheet.addCell(new jxl.write.Label(8, 0,"",formato));
			sheet.addCell(new jxl.write.Label(9, 0, "Magnitudes",formato));
			sheet.addCell(new jxl.write.Label(10, 0,"",formato));
			sheet.addCell(new jxl.write.Label(11, 0, "Spectral",formato));
			sheet.addCell(new jxl.write.Label(12, 0, "Prop Mot",formato));
			sheet.addCell(new jxl.write.Label(13, 0, "2nd PM",formato));
			sheet.addCell(new jxl.write.Label(14, 0, "DM",formato));
			sheet.addCell(new jxl.write.Label(15, 0, "Desig",formato));
			sheet.addCell(new jxl.write.Label(16, 0, "Note",formato));
			sheet.addCell(new jxl.write.Label(17, 0, "Precise",formato));
			
			sheet.addCell(new jxl.write.Label(0, 1, "Identifier",formato));
			sheet.addCell(new jxl.write.Label(1, 1, "",formato));
			sheet.addCell(new jxl.write.Label(2,1,"Frst",formato));
			sheet.addCell(new jxl.write.Label(3, 1,"Last",formato));
			sheet.addCell(new jxl.write.Label(4, 1,"",formato));
			sheet.addCell(new jxl.write.Label(5, 1, "Fst",formato));
			sheet.addCell(new jxl.write.Label(6, 1, "Lst",formato));
			sheet.addCell(new jxl.write.Label(7, 1, "First",formato));
			sheet.addCell(new jxl.write.Label(8, 1, "Last",formato));			
			sheet.addCell(new jxl.write.Label(9, 1, "Pri",formato));
			sheet.addCell(new jxl.write.Label(10, 1, "Sec",formato));
			sheet.addCell(new jxl.write.Label(11,1, "Type",formato));
			sheet.addCell(new jxl.write.Label(12, 1, "RA DEC",formato));
			sheet.addCell(new jxl.write.Label(13, 1, "RA DEC",formato));
			sheet.addCell(new jxl.write.Label(14,1, "",formato));
			sheet.addCell(new jxl.write.Label(15,1, "",formato));
			sheet.addCell(new jxl.write.Label(16,1, "",formato));
			sheet.addCell(new jxl.write.Label(17,1, "Coordinates",formato));	
			
			int car=0;
			int j= 2;
			while (car<line.length()){
				String id  = line.substring(car, car+13);
				String discovr = line.substring(car+13,car+21);	
				String EpochFirst= line.substring(car+23,car+28);
				String EpochLast= line.substring(car+28,car+32);
				String contador= line.substring(car+34,car+37);
				String thetaFirt= line.substring(car+38,car+41);
				String thetaLast= line.substring(car+42,car+45);
				String roFirt= line.substring(car+47,car+51);
				String roLast= line.substring(car+53,car+57);
				String magPri= line.substring(car+58,car+63);
				String magSec= line.substring(car+64,car+69);
				String type= line.substring(car+70,car+79);
				String PropRa= line.substring(car+80,car+88);
				String secRa= line.substring(car+89,car+97);
				String dm= line.substring(car+99,car+101);
				String desig= line.substring(car+104,car+106);
				String note= line.substring(car+107,car+111);
				String coord= line.substring(car+112,car+130);
				int i=0;
			          
				sheet.addCell(new jxl.write.Label(i, j, id, formato1));
				sheet.addCell(new jxl.write.Label(i + 1, j, discovr, formato1));
				sheet.addCell(new jxl.write.Label(i + 2, j, EpochFirst,formato1));
				sheet.addCell(new jxl.write.Label(i + 3, j, EpochLast, formato1));
				sheet.addCell(new jxl.write.Label(i + 4, j, contador, formato1));
				sheet.addCell(new jxl.write.Label(i + 5, j, thetaFirt, formato1));
				sheet.addCell(new jxl.write.Label(i + 6, j, thetaLast, formato1));
				sheet.addCell(new jxl.write.Label(i + 7, j, roFirt, formato1));
				sheet.addCell(new jxl.write.Label(i + 8, j, roLast, formato1));
				sheet.addCell(new jxl.write.Label(i + 9, j, magPri, formato1));
				sheet.addCell(new jxl.write.Label(i + 10, j, magSec, formato1));
				sheet.addCell(new jxl.write.Label(i + 11, j, type, formato1));
				sheet.addCell(new jxl.write.Label(i + 12, j, PropRa, formato1));
				sheet.addCell(new jxl.write.Label(i + 13, j, secRa, formato1));
				sheet.addCell(new jxl.write.Label(i + 14, j, dm, formato1));
				sheet.addCell(new jxl.write.Label(i + 15, j, desig, formato1));
				sheet.addCell(new jxl.write.Label(i + 16, j, note, formato1));
				sheet.addCell(new jxl.write.Label(i + 17, j, coord, formato1));					
					 
			    
				j++;
				car+=132;
			}
			
			workbook.write();
			workbook.close();
			
		}
		catch (IOException ex)
		{
			JOptionPane.showMessageDialog(null,"Failed to create file");
		}
		catch (WriteException ex)
		{
			JOptionPane.showMessageDialog(null,"Error writing file");
		}
	}
	
	public void sdss(String ras, String des) {
		try{
			Star s = new Star(ras,des);
			double ra = s.getRa();
			double dec = s.getDec();
			String coords = "ra="+ra + "&dec="+dec;
			String uris ="http://cas.sdss.org/astro/en/tools/chart/chart.asp?"+coords;
			openWebpage(new URL(uris));
//		     uris  = "http://surveys.roe.ac.uk/wsa/cgi-bin/getImage.cgi?file=/disk38/wsa/ingest/fits/20080217_v4/w20080217_01110_sf_st.fit&mfid=4634512&extNo=4&lx=285&hx=584&ly=1997&hy=2297&rf=0&flip=1&uniq=5824_251_52_2927_2&xpos=150.6&ypos=151&band=H&"+coords;
//			openWebpage(new URL(uris));
		} catch(Exception e) {
			
		}
	}
	
	private   void openWebpage(URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

	private   void openWebpage(URL url) {
	    try {
	        openWebpage(url.toURI());
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	    }
	}
	public void aladin(String path, String ra, String de) {
		String FileName = "Aladin.jar";
		boolean found = false;
		File f = new File(FileName);
		
		if (!f.exists()) {
			FileName = "aladin.jar";
			File f2 = new File(FileName);
			if (f2.exists()) found=true;
		} else  found=true; 
		
		if (!found) {
			JFileChooser selecFile= new JFileChooser(path);
			selecFile.setSelectedFile(new File("Aladin.jar"));
			
			int status=selecFile.showOpenDialog(Interface.this);
			if(status == JFileChooser.APPROVE_OPTION){
				FileName=selecFile.getSelectedFile().getAbsolutePath();	
				found=true;
			}

		}
		if (found)  Info.aladin(FileName, ra, de);				

	}

	private void searchAnyText() {
		if (open==false){
			JOptionPane.showMessageDialog(null,"must open a file");
		}
		else{
			String text = searchTextField.getText(); 
			combo.setSelectedIndex(0);				
			searchCoordinates = searchConst = false;
			searchText = true;
			resultTextPane.setText("");
			int i=0;
			int max=5;
			boolean found=false;
			for (i=0; i<max && !found; i++) {
			    Info.searchInFile(Catalog.SearchMode.TEXT,text);
			    if(resultTextPane.getText().length()==0){
			    	int pos = -1;
			    	boolean posfound=false;
			    	text = text.trim();
			    	for (int j=0; j<text.length()-1 && !posfound; j++) 
			    		if  (text.charAt(j)==' ' || 
			    		      (Character.isLetter(text.charAt(j)) &&  (Character.isDigit(text.charAt(j+1))) ) ) {
			    			posfound=true;
			    			pos = j+1;
			    		}
			    	
					if (pos !=-1)
					   text = text.substring(0, pos)+" "+text.substring(pos);
					else found=true;

			    } else found=true;
			}
			if(resultTextPane.getText().length()==0){
				JOptionPane.showMessageDialog(null,"'" + searchTextField.getText() +"' not found in file");
			}
		}

	}
	
		
}
