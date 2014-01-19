package main;

import static javax.swing.GroupLayout.Alignment.CENTER;
import static javax.swing.GroupLayout.Alignment.LEADING;
import static javax.swing.GroupLayout.Alignment.TRAILING;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
	private static Catalog Info;
	String path;
	private boolean open=false;	
	private boolean searchText;
	private boolean searchCoordinates;
	private boolean searchConst;
	private boolean loadWDS=false;
	
	private JPanel mainPanel;
	private JPanel fieldsPanel;
	
	private JLabel catalogSelection;
	private JLabel coordinates1;
	private JLabel coordinates2;
	private JLabel catalogP1;
	private JLabel catalogS1;
	private JLabel catalogP2;
	private JLabel catalogS2;
	private JLabel catalogP3;
	private JLabel catalogS3;
	private JLabel catalogP4;
	private JLabel catalogS4;
	private JLabel radius;
	private JLabel radiusAround;
	private JLabel filters;
	private JLabel criterium1;
	private JLabel criterium2;
	private JLabel result1;
	private JLabel result2;
	private JLabel oneToOne;
	private JLabel showClosest;
	private JLabel catalogDescription;
	
	private JTextField catPTextField;
	private JTextField catSTextField;
	private JTextField coordTextField;
	private JTextField r1TextField;
	private JTextField r2TextField;
	private JTextField catPfilTextField;
	private JTextField catSfilTextField;
	private JTextField crit1TextField;
	private JTextField crit2TextField;
	
	private JRadioButton oneToOneRB;
	private JRadioButton showClosestRB;
	
	private JButton load;
	private JButton start;
	
	
	/** construction */
	public Interface(){
		initialize();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	/** interface initialization method */
	private void initialize() {
		this.setTitle("Compare Catalogs");
		this.setEnabled(true);
		this.setVisible(true);
		this.setSize(500, 300);
		//this.setJMenuBar(getMenBar());
		this.setContentPane(globalPanel());
		this.validate();
		
		path =  System.getProperty("user.dir"); //new File(".").getCanonicalPath();
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
	
	private JPanel globalPanel(){
		catalogSelection = new JLabel("Catalog Selection");;
		coordinates1 = new JLabel("Coordinates:");
		coordinates2 = new JLabel("Coordinates:");
		catalogP1 = new JLabel("Catalog P");
		catalogS1 = new JLabel("Catalog S");
		catalogP2 = new JLabel("Catalog P");
		catalogS2 = new JLabel("Catalog S");
		catalogP3 = new JLabel("Catalog P");
		catalogS3 = new JLabel("Catalog S");
		catalogP4 = new JLabel("Catalog P");
		catalogS4 = new JLabel("Catalog S");
		radius = new JLabel("Radius (\")");
		radiusAround = new JLabel("Radius around each P star (\")");
		filters = new JLabel("Filters");
		criterium1 = new JLabel("Criterium");
		criterium2 = new JLabel("Criterium");
		result1 = new JLabel("Result");
		result2 = new JLabel("Result");
		oneToOne = new JLabel("One To One");
		showClosest = new JLabel("Show Closest Candidate");
		catalogDescription = new JLabel("Catalog Description");
		
		catPTextField = new JTextField();
		catPTextField.setMaximumSize(new Dimension(50,20));
		catSTextField = new JTextField();
		catSTextField.setMaximumSize(new Dimension(50,20));
		coordTextField = new JTextField();
		coordTextField.setMaximumSize(new Dimension(150,20));
		r1TextField = new JTextField();
		r1TextField.setMaximumSize(new Dimension(30,20));
		r2TextField = new JTextField();
		r2TextField.setMaximumSize(new Dimension(30,20));
		catPfilTextField = new JTextField();
		catPfilTextField.setMaximumSize(new Dimension(100,20));
		catSfilTextField = new JTextField();
		catSfilTextField.setMaximumSize(new Dimension(100,20));
		crit1TextField = new JTextField();
		crit1TextField.setMaximumSize(new Dimension(100,20));
		crit2TextField = new JTextField();
		crit2TextField.setMaximumSize(new Dimension(100,20));
		
		/*******************************************/
		//In initialization code:
	    //Create the radio buttons.
		oneToOneRB = new JRadioButton("One to One");
		oneToOneRB.setActionCommand("one");
		oneToOneRB.setSelected(true);
		
		showClosestRB = new JRadioButton("Show Closest Candidate");
		showClosestRB.setActionCommand("closest");
		showClosestRB.setSelected(true);

	    //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(oneToOneRB);
	    group.add(showClosestRB);
	    
	    //Register a listener for the radio buttons.
	    ButtonGroupListener bgl= new ButtonGroupListener();
	    oneToOneRB.addActionListener(bgl);
	    showClosestRB.addActionListener(bgl);
	    /*******************************************/
	    
	    ButtonListener o = new ButtonListener();
		load = new JButton("Load");
		load.addActionListener(o);
		
		start = new JButton("Start");
		start.addActionListener(o);
	    
	    mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		fieldsPanel = new JPanel();
		fieldsPanel.setEnabled(true);
		fieldsPanel.setVisible(true);
		
		GroupLayout fieldsLayout = new GroupLayout(fieldsPanel);
	
		fieldsPanel.setLayout(fieldsLayout);
		fieldsLayout.setAutoCreateGaps(true);
		fieldsLayout.setAutoCreateContainerGaps(true);
		fieldsLayout.setHorizontalGroup(
				fieldsLayout.createSequentialGroup()
			      .addGroup(fieldsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
			           .addComponent(catalogSelection)
			           .addComponent(catalogP1)
			           .addComponent(catPTextField))
			      .addGroup(fieldsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
			    	   .addComponent(coordinates1)
		    		   .addComponent(catalogP2)
			    	   .addGroup(fieldsLayout.createSequentialGroup()
			    			.addGroup(fieldsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
				    		    .addComponent(coordinates2)
				    		    .addComponent(coordTextField))
				    		.addGroup(fieldsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
				    		    .addComponent(radius)
				    		    .addComponent(r1TextField))))
			      .addComponent(load)
				);
		//layout.linkSize(SwingConstants.HORIZONTAL, findButton, cancelButton);

		fieldsLayout.setVerticalGroup(
				fieldsLayout.createSequentialGroup()
			      .addGroup(fieldsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
			           .addComponent(catalogSelection)
			           .addComponent(coordinates1))
			      .addGroup(fieldsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
			           .addComponent(catalogP1)
			           .addComponent(catalogP2))
			      .addGroup(fieldsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
			           .addComponent(catPTextField)
			           .addGroup(fieldsLayout.createSequentialGroup()
			        	   .addGroup(fieldsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
				    		    .addComponent(coordinates2)
				    		    .addComponent(radius))
				    	   .addGroup(fieldsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
				    		    .addComponent(coordTextField)
				    		    .addComponent(r1TextField)))
			           .addComponent(load))
			);
		//mainPanel.add("North",menuBar);
		mainPanel.add("Center",fieldsPanel);
		
		return mainPanel;
	}
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Info = new Catalog();
		Interface mainClass = new Interface();
	}
	
	/**********************************AUXILIAR CLASSES************************************************/
	
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
	 * class ButtonListener
	 *
	 */
	public class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if (o == load){
				//Load
				String source = catPTextField.getText();
				String coord = coordTextField.getText();
				String rad = r1TextField.getText();
				JFileChooser selecFile=new JFileChooser(path);
				@SuppressWarnings("unused")
				int i=selecFile.showSaveDialog(Interface.this);
				try{
					String fileName=selecFile.getSelectedFile().getAbsolutePath();
					String fileNotes = fileName+".notes";
					if (alreadyexists(fileName)/* && alreadyexists(fileNotes)*/){
					    Info.saveCatalogFile(fileName,fileNotes,source,coord,rad);	
					    Info.setCatalogPath(fileName);
					    open=true;
					}
				}
				catch(Exception e2){};
			}else if (o == start){
				//Start
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
		
}
