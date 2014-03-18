package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Observable;

import javax.swing.JOptionPane;

//import elements.ContenidoTS;
//import elements.TablaSimbolo;

public class Catalog{
	/**
	 * This class download a catalog
	�* Performs search on any text or by coordinates (right ascension, declination, radius)
	 * 
	 * @author Alicia Mireya Daza castillo
	 * @author Jorge Gonz�lez L�pez
	 * @author Rosa Rodr�guez Navarro
	 * @since 1.1.1
	 */
	
	/** attributes */
	private String catalogPath;
	private String message;
	private String urlCatalogVizier;
	private String urlCatalogWds;
	
	
	/** construction */
	public Catalog(){
		catalogPath = "";
		
	}
	
	
	/** construction */
	public Catalog(String catalogPath){
		this.catalogPath = catalogPath;
	}
	
	
	/** Getters and setters */
	public String getCatalogPath() {
		return catalogPath;
	}

	
	public void setCatalogPath(String catalogPath) {
		this.catalogPath = catalogPath;
	/*	message= "Actual file path: " + catalogPath + "\n";
		setChanged();
		notifyObservers("clear");
*/
	}
	
	/**
	 * OLD
	 * Downloads WDS Catalog and saves it in /downloads/catalog.txt
	 * 
	 * @param filePath
	 */
	/*public void saveCatalog(String filePath, String fileNotesPath) {
		try {
			
			// Url with WDS Catalog
			URL url = new URL(
					"http://ad.usno.navy.mil/wds/Webtextfiles/wdsweb_summ.txt");

			download(url,filePath);
			
			// Url with WDS Notes
			URL url2 = new URL(
					"http://ad.usno.navy.mil/wds/Webtextfiles/wdsnewnotes_main.txt");

			download(url2,filePath+".notes");
			
			JOptionPane.showMessageDialog(null,"download","Info",JOptionPane.INFORMATION_MESSAGE);
				 
				} catch (MalformedURLException e) {
				  System.out.println("url not valid!");
				  
				} catch (IOException e) {					
				  e.printStackTrace();
				}
		
	}*/
	public void saveCatalogFile(String filePath, String source, String coord, String rad) {
		try {
			
			coord = coord.replaceAll(" ", "%20");
			
			urlCatalogVizier = "http://vizier.u-strasbg.fr/viz-bin/asu-txt?-source="+source+"&-c="+coord+"&-c.rs="+rad;
			//urlCatalog = "http://vizier.u-strasbg.fr/viz-bin/asu-txt?-source=II/246/out&-c=15%2000%2000%20+12%2012%2034&-c.rs=200";
			// Url with WDS Catalog
			URL url = new URL(urlCatalogVizier);
			download(url,filePath);			
			catalogPath = filePath;
				 
		} catch (MalformedURLException e) {
		  System.out.println("url not valid!");
		  System.out.println(urlCatalogVizier);
		  	 
		  
		} catch (IOException e) {					
		  e.printStackTrace();
		}
		
	}
	
	
	
	private void download(URL url, String filePath) throws IOException {
		try {
			File file = new File(filePath);

			URLConnection conn = url.openConnection();
			conn.connect();		
	
			InputStream in = conn.getInputStream();
			OutputStream out = new FileOutputStream(file);		
		
			// Read file from web and write in local file
			byte[] array = new byte[1000]; // temporal read buffer
			int tamRead = in.read(array);
			while (tamRead > 0) {
				out.write(array, 0, tamRead);
				tamRead = in.read(array);			
			}
		
			out.close();
			in.close();		
		}
		catch (MalformedURLException e){
			System.out.println("url not valid!");
			
		}
	}
	

}
