package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import static javax.swing.GroupLayout.Alignment.*;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;




public class Interface extends JFrame {
	/**
	 * 
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
	private JTextPane resultTextPane;
	private JScrollPane scrollPanel; 
	private static StatusTextArea statusTextArea;
	//////////////////////////////////////
	private String statusMessage;
	private ImageIcon iconExit ;
	
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

	public Interface(){
		statusMessage = "No selected file. Please, select one from your computer or download it.";
		searchText = false;
		searchCoordinates = false;
		initialize();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
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
	
	private JPanel globalPanel() {
		labelCoord = new JLabel("Coordinates:");
		labelAnyText = new JLabel("Any text:");
		labelRA = new JLabel("Right Ascension (e.g. 192141.60)");
		labelDec = new JLabel("Declination (e.g. -222820.4)");
		labelRadius = new JLabel("Radius (seconds)");
		raTextField = new JTextField();
		decTextField = new JTextField();
		radiusTextField = new JTextField();
		searchTextField = new JTextField();

		labelWDShead1 = new JLabel("    WDS   Discovr Comp  EPOCH      #  THETA       RHO     Magnitudes Spectral  Prop Mot  2nd PM   DM Desig Note     Precise");
		labelWDShead2 = new JLabel("Identifier             Frst Last      Fst Lst First  Last  Pri   Sec  Type      RA\" DEC\" RA\" DEC\"                 Coordinate");
		ButtonListener o = new ButtonListener();
		searchButton1 = new JButton("Search");
		searchButton1.addActionListener(o);
		
		searchButton2 = new JButton("Search");
		searchButton2.addActionListener(o);

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
		
		searchTextField.setMaximumSize(new Dimension(1500,20));
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		menuBar=new JMenuBar();
		menuBar.add(getFileMenu());
		
		fieldsPanel = new JPanel();
		fieldsPanel.setEnabled(true);
		fieldsPanel.setVisible(true);
		//fieldsPanel.setBackground(Color.BLUE);
		
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
					.addComponent(searchButton2))
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
			            .addGroup(fieldsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
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
					.addComponent(scrollPanel)
				)	.addComponent(statusTextArea)
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
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.add(getOpenFileItem());
		fileMenu.add(getDownloadCatalogItem());
		fileMenu.addSeparator();
		fileMenu.add(getExitFileItem());
	   
		
		return fileMenu;
	}
	
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
				//	JOptionPane.showMessageDialog(null,"The file has been opened successfully \n" + file);
						
				}
				/*else if (status == JFileChooser) {
					//JOptionPane.showMessageDialog(null,"File not found \n" );
				}*/
			}

			
			});
		return openFileItem;
	}
	private String extraer_valor(String s) {
		String blanco = " ";
		int i = 0;
		while(s.charAt(i) != blanco.charAt(0)){
			i++;
		}
		int total = s.length();
		return s.substring(i+1,total);
	}

	private String extraer_nombre(String s) {
		String blanco = " ";
		int i = s.length()-1;
		while(s.charAt(i) != blanco.charAt(0)){
			i--;
		}
		return s.substring(0,i);
	}
	
	private JMenuItem getExitFileItem() {
		//iconExit = new ImageIcon(getClass().getResource("./Icons/exit.jpg"));
		//InputStream is= this.getClass().getResourceAsStream("/exit.jpg");
		exitFileItem=new JMenuItem("Exit");	
		//exitFileItem.setIcon(new ImageIcon(getClass().getResource("exit.jpg")));
		//exitFileItem.setIcon(new ImageIcon(getClass().getResource("/Icons/exit.jpg")));
		
		exitFileItem.setMnemonic(KeyEvent.VK_E);
		exitFileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
	           ActionEvent.CTRL_MASK));		
		exitFileItem.setEnabled(true);		
		exitFileItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JDialog.setDefaultLookAndFeelDecorated(true);
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

	private JMenuItem getDownloadCatalogItem() {
		downCatalFileItem=new JMenuItem("Download WDS Catalog");
		downCatalFileItem.setMnemonic(KeyEvent.VK_D);
		downCatalFileItem.setEnabled(true);
		downCatalFileItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFileChooser selecFile=new JFileChooser(path);
				@SuppressWarnings("unused")
				int i=selecFile.showSaveDialog(Interface.this);
				try{
					String fileName=selecFile.getSelectedFile().getAbsolutePath();					
					Info.saveCatalog(fileName);
					Info.setCatalogPath(fileName);
				/*	JOptionPane.showMessageDialog(null, "Download finished: \n"+ ">> URL: " + Info.getUrl() +  "\n"+
							">> Path: " + fileName+ "\n"+ 
							">> Size: " + Info.getConn().getContentLength() + " bytes");*/
									    
				}
				catch(Exception e2){};
			}
				

			});
		return downCatalFileItem;
	}

	
	/**********************************AUXILIAR CLASSES************************************************/
	
	public class StatusTextArea extends JTextArea implements Observer{
		

		public StatusTextArea(String text){
			this.setText(text);
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			//this.setText(Info.getMessage());
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
				int index1 = 80;
				int index2 = 97;
				int end = ((String) arg1).length();
				
				StyledDocument doc = resultTextPane.getStyledDocument();

		        Style style = resultTextPane.addStyle("I'm a Style", null);
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
	
	
	public class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if (o==searchButton1){//Search from coordinates and radius
				//System.out.println("Pulsado 1");
				searchCoordinates = true;
				searchText = false;
				resultTextPane.setText("");
				String ra = raTextField.getText();
				String dec = decTextField.getText();
				String radius = radiusTextField.getText();
				Info.searchInFile(ra, dec, radius);
			}
			else if (o==searchButton2){//Search any text
				searchCoordinates = false;
				searchText = true;
				resultTextPane.setText("");
				Info.searchInFile(searchTextField.getText());
			}
		}
	}
	
	
	
}
