package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	private JComboBox combo;
	private JButton selectButton;
	private JButton clearButton;
	private JButton notesButton;
	private JButton aladin;
	private JButton sdss;
	
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
									.addComponent(notesButton)))
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
					   	    .addComponent(notesButton))
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
			if (o==searchButton1){//Search from coordinates and radius			
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
			else if (o==searchButton2){//Search any text
				   searchAnyText();
				}else if(o== clearButton){
							resultTextPane.setText("");
							combo.setSelectedIndex(0);
							resultTextPane.setText("");
					       raTextField.setText("");
					       decTextField.setText("");
		                   radiusTextField.setText("");
		                   searchTextField.setText("");
							resultTextPane.revalidate();
					        resultTextPane.update(resultTextPane.getGraphics());
				}else if(o== selectButton){
					resultTextPane.requestFocusInWindow();
					resultTextPane.selectAll();
				}else if(o== notesButton){
					resultTextPane.requestFocusInWindow();
					String line = resultTextPane.getSelectedText();
					if (line.length()==130) {
  					   String id  = line.substring(10, 22);
					   String notes = line.substring(107, 111);
  					   String ras = line.substring(112, 121);
  					   String decs = line.substring(121, 130);
  					   raTextField.setText(ras);
  					   decTextField.setText(decs);
  					   Info.showNotes(id,notes);
				     }
			}
		}
	}
	
	public void sdss(String ras, String des) {
		try{
			Star s = new Star(ras,des);
			double ra = s.getRa();
			double dec = s.getDec();
			String uris ="http://cas.sdss.org/astro/en/tools/chart/chart.asp?ra="+ra + "&dec="+dec;
			openWebpage(new URL(uris));
		
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
