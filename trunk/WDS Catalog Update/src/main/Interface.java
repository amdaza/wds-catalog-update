package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
 * @since 1.0
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
	private JLabel labelWDShead1;
	private JLabel labelWDShead2;
	
	private JTextField raTextField;
	private JTextField decTextField;
	private JTextField radiusTextField;
	private JTextField searchTextField;	
	
	private JButton searchButton1;
	private JButton searchButton2;
	private JButton clearButton;
	
	private JTextPane resultTextPane;
	
	private JScrollPane scrollPanel; 
	
	private static StatusTextArea statusTextArea;
	
	private String statusMessage;
	
	private boolean open=false;	
	private boolean searchText;
	private boolean searchCoordinates;
	
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
		this.setSize(1140, 640);
		this.setJMenuBar(getMenBar());
		this.setContentPane(globalPanel());
		this.validate();
		path =  System.getProperty("user.dir"); //new File(".").getCanonicalPath();		
	}
	
	
	/** interface creation method */
	private JPanel globalPanel() {
		labelCoord = new JLabel("Coordinates:");
		labelAnyText = new JLabel("Any text:");
		labelRA = new JLabel("Right Ascension (e.g. 192141.60)");
		labelDec = new JLabel("Declination (e.g. -222820.4)");
		labelRadius = new JLabel("Radius (seconds)");
		
		raTextField = new JTextField();
		raTextField.setMaximumSize(new Dimension(70,20));
		decTextField = new JTextField();
		decTextField.setMaximumSize(new Dimension(70,20));
		radiusTextField = new JTextField();
		radiusTextField.setMaximumSize(new Dimension(50,20));
		
		searchTextField = new JTextField();		
		searchTextField.setMaximumSize(new Dimension(1500,20));

		labelWDShead1 = new JLabel("    WDS   Discovr Comp  EPOCH      #  THETA       RHO     Magnitudes Spectral  Prop Mot  2nd PM   DM Desig Note     Precise");
		labelWDShead2 = new JLabel("Identifier             Frst Last      Fst Lst First  Last  Pri   Sec  Type      RA\" DEC\" RA\" DEC\"                 Coordinate");
		
		ButtonListener o = new ButtonListener();
		searchButton1 = new JButton("Search");
		searchButton1.addActionListener(o);
		
		searchButton2 = new JButton("Search");
		searchButton2.addActionListener(o);
		
		clearButton = new JButton("Clear");
		clearButton.addActionListener(o);
		
		resultTextPane = new JTextPane();
		
		scrollPanel = new JScrollPane(resultTextPane); 
		scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		Font font = new Font( "Monospaced", Font.PLAIN, 12 );  
		resultTextPane.setFont( font );
		labelWDShead1.setFont(font);
		labelWDShead2.setFont(font);
		
		statusTextArea = new StatusTextArea(statusMessage);

		Info.addObserver(statusTextArea);
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
								.addComponent(radiusTextField)))
					.addComponent(searchTextField)
					.addComponent(labelWDShead1)
				    .addComponent(labelWDShead2)
					.addComponent(scrollPanel)
					.addComponent(statusTextArea))
				.addGroup(fieldsLayout.createParallelGroup(CENTER)
					.addComponent(searchButton1)
					.addComponent(searchButton2)
					.addComponent(clearButton))
		);
		fieldsLayout.linkSize(SwingConstants.HORIZONTAL, searchButton1);

		fieldsLayout.setVerticalGroup(fieldsLayout.createSequentialGroup()
				.addGroup(fieldsLayout.createParallelGroup(CENTER)
					.addComponent(labelCoord)
					.addGroup(fieldsLayout.createSequentialGroup()
			            .addGroup(fieldsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
			                .addComponent(labelRA)
			                .addComponent(labelDec)
			                .addComponent(labelRadius))
			            .addGroup(fieldsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
			                .addComponent(raTextField)
			                .addComponent(decTextField)
			                .addComponent(radiusTextField))) 
			            .addComponent(searchButton1))
				.addGroup(fieldsLayout.createParallelGroup(LEADING)
					.addComponent(labelAnyText)
					.addComponent(searchTextField)
					.addComponent(searchButton2))
				.addGroup(fieldsLayout.createParallelGroup(LEADING)					
    				    .addComponent(labelWDShead1))
                .addGroup(fieldsLayout.createParallelGroup(LEADING)					
    					.addComponent(labelWDShead2))
				.addGroup(fieldsLayout.createParallelGroup(LEADING)
					.addGroup(fieldsLayout.createParallelGroup(CENTER)
						.addComponent(scrollPanel)
						.addComponent(clearButton))
				)	.addComponent(statusTextArea)
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
												"date: 2014/11/01 \n"+
												"version 1.0");
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
					Info.saveCatalog(fileName);			    
				}
				catch(Exception e2){};
			}
			});
		return downCatalFileItem;
	}

	
	/**********************************AUXILIAR CLASSES************************************************/
	
	/**
	 * class StatusTextArea
	 * 
	 */
	public class StatusTextArea extends JTextArea implements Observer{
		

		public StatusTextArea(String text){
			this.setText(text);
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			if (arg1.equals("append")){
				this.append(Info.getMessage());
			}else if (arg1.equals("clear")){
				this.setText(Info.getMessage());
			}else if (searchText){// arg1 is the line to show
				this.setText("Search any text from Catalog");
				String searchText = searchTextField.getText();
				
				int index1 = ((String) arg1).indexOf(searchText);
				int index2 = index1 + searchText.length();
				int end = ((String) arg1).length();
				
				StyledDocument doc = resultTextPane.getStyledDocument();

		        Style style = resultTextPane.addStyle("I'm a Style", null);
		        StyleConstants.setFontFamily(style, "Lucida Console");
		        StyleConstants.setForeground(style, Color.black);

		        try { doc.insertString(doc.getLength(), ((String) arg1).substring(0,index1),style); }
		        catch (BadLocationException e){}

		        StyleConstants.setForeground(style, Color.red);

		        try { doc.insertString(doc.getLength(), ((String) arg1).substring(index1,index2),style); }
		        catch (BadLocationException e){}
		        
		        StyleConstants.setForeground(style, Color.black);

		        try { doc.insertString(doc.getLength(), ((String) arg1).substring(index2,end),style); }
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
	 * 
	 * class ButtonListener
	 *
	 */
	public class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if (o==searchButton1){//Search from coordinates and radius			
				if (open==false){
					JOptionPane.showMessageDialog(null,"must open a file");
				}
				else{
					searchCoordinates = true;
					searchText = false;
					resultTextPane.setText("");
					String ra = raTextField.getText();
					String dec = decTextField.getText();
					String radius = radiusTextField.getText();	
					//try{
						Info.searchInFile(ra, dec, radius);
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
					if (open==false){
						JOptionPane.showMessageDialog(null,"must open a file");
					}
					else{
				
						searchCoordinates = false;
						searchText = true;
						resultTextPane.setText("");
						Info.searchInFile(searchTextField.getText());
						if(resultTextPane.getText().length()==0){
							JOptionPane.showMessageDialog(null,"'" + searchTextField.getText() +"' not found in file");
						}
					}
				}else if(o== clearButton){
							resultTextPane.setText("");
							resultTextPane.revalidate();
					        resultTextPane.update(resultTextPane.getGraphics());
				}
			}
		}
	
	
	
	
}
