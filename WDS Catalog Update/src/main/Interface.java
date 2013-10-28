package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import static javax.swing.GroupLayout.Alignment.*;

import javax.swing.*;




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
	private JTextField raTextField;
	private JTextField decTextField;
	private JTextField radiusTextField;
	private JTextField searchTextField;	
	private JButton findButton1;
	private JButton findButton2;
	private JTextArea resultTextArea;
	private JScrollPane scrollPanel; 
	private static StatusTextArea statusTextArea;
	//////////////////////////////////////
	private String statusMessage;
	private ImageIcon iconExit ;
	
	
	private static Catalog Info;

	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Info = new Catalog();
		Interface mainClass = new Interface();
		

	}

	public Interface(){
		statusMessage = "No selected file. Please, select one from your computer or download it.";
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
		labelCoord = new JLabel("Coordinates:");
		labelAnyText = new JLabel("Any text:");
		labelRA = new JLabel("Right Ascension");
		labelDec = new JLabel("Declination");
		labelRadius = new JLabel("Radius");
		raTextField = new JTextField();
		decTextField = new JTextField();
		radiusTextField = new JTextField();
		searchTextField = new JTextField();
		findButton1 = new JButton("Search");
		findButton2 = new JButton("Search");

		resultTextArea = new JTextArea();
		resultTextArea.setWrapStyleWord(true);
		resultTextArea.setLineWrap(true);
		
		scrollPanel = new JScrollPane(resultTextArea); 
		scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		statusTextArea = new StatusTextArea(statusMessage);

		Info.addObserver(statusTextArea);
		statusTextArea.setVisible(true);
		statusTextArea.setEditable(false);
		
		searchTextField.setMaximumSize(new Dimension(1000,20));
		
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
					.addComponent(scrollPanel)
					.addComponent(statusTextArea))
				.addGroup(fieldsLayout.createParallelGroup(CENTER)
					.addComponent(findButton1)
					.addComponent(findButton2))
		);
		fieldsLayout.linkSize(SwingConstants.HORIZONTAL, findButton1);

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
			            .addComponent(findButton1))
				.addGroup(fieldsLayout.createParallelGroup(LEADING)
					.addComponent(labelAnyText)
					.addComponent(searchTextField)
					.addComponent(findButton2))
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
				
				JFileChooser selecFile= new JFileChooser();
				int status=selecFile.showOpenDialog(Interface.this);
				if(status == JFileChooser.APPROVE_OPTION){
					String file=selecFile.getSelectedFile().getName();
					Info.setCatalogPath(file);
					JOptionPane.showMessageDialog(null,"The file has been opened successfully \n" + file);
						
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
				JFileChooser selecFile=new JFileChooser();
				@SuppressWarnings("unused")
				int i=selecFile.showSaveDialog(Interface.this);
				try{
					String fileName=selecFile.getSelectedFile().getAbsolutePath();					
					Info.saveCatalog(fileName);
				/*	JOptionPane.showMessageDialog(null, "Download finished: \n"+ ">> URL: " + Info.getUrl() +  "\n"+
							">> Path: " + fileName+ "\n"+ 
							">> Size: " + Info.getConn().getContentLength() + " bytes");*/
									    
				}
				catch(Exception e2){};
			}
				

			});
		return downCatalFileItem;
	}

	
	public class StatusTextArea extends JTextArea implements Observer{
		
		public StatusTextArea(String text){
			this.setText(text);
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			System.out.println("\nupdate: \n");
			//this.setText(Info.getMessage());
			this.append(Info.getMessage());
			this.revalidate();
			this.update(this.getGraphics());
		}

	}
	
	
}
