package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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
	
	private JTextField catalogPsel;
	private JTextField catalogSsel;
	private JTextField coordinates;
	private JTextField radius1;
	private JTextField radius2;
	private JTextField catalogPfil;
	private JTextField catalogSfil;
	private JTextField crit1text;
	private JTextField crit2text;
	
	private JRadioButton oneToOneRB;
	private JRadioButton showClosestRB;
	
	
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
		//this.setSize(1120, 640);
		//this.setJMenuBar(getMenBar());
		this.setContentPane(globalPanel());
		this.validate();
	}
	
	private JPanel globalPanel(){
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
	    
	    mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		return mainPanel;
	}
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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

}
