package main;

import java.awt.EventQueue;

/**
 * Launch the application.
 */
public class Main {
	@SuppressWarnings("unused")
	private static Catalog Info;
public static void main(String[] args) {
	
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				Interface frame = new Interface();
				frame.setVisible(true);
				
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}
}