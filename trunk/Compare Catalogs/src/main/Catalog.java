package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Catalog {
	/**
	 * This class download the file. Txt WDS
	 * Performs search on any text or by coordinates (right ascension, declination, radius)
	 * 
	 * @author Alicia Mireya Daza castillo
	 * @author Jorge González López
	 * @author Rosa Rodríguez Navarro
	 * @since 1.1.1
	 */
	
	/** attributes */
	private String catalogPath;
	private String message;
	private String urlCatalog;

	
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
	 * Downloads WDS Catalog and saves it in /downloads/catalog.txt
	 * 
	 * @param filePath
	 */
	public void saveCatalogFile(String filePath, String fileNotesPath, String source, String coord, String rad) {
		try {
			/*message = "Dowloading...";
			setChanged();
			notifyObservers("clear");*/
			coord = coord.replaceAll(" ", "%20");
			
			urlCatalog = "http://vizier.u-strasbg.fr/viz-bin/asu-txt?-source="+source+"&-c="+coord+"&-c.rs="+rad;
			//urlCatalog = "http://vizier.u-strasbg.fr/viz-bin/asu-txt?-source=II/246/out&-c=15%2000%2000%20+12%2012%2034&-c.rs=200";
			// Url with WDS Catalog
			URL url = new URL(urlCatalog);

			download(url,filePath);

		/*	download(url2,filePath+".notes");
			System.out.println("\nDownload finished");
			message = "\nDownload finished. \n" +
					"Actual file path: " + filePath + "\n";
			setChanged();
			notifyObservers("append");

			System.out.println("\nDownload finished");
			message = "\nDownload finished. \n" +
					"Actual file path: " + filePath + "\n";
			setChanged();
			notifyObservers("append");*/
			
			catalogPath = filePath;
				 
		} catch (MalformedURLException e) {
		  System.out.println("url not valid!");
		  System.out.println(urlCatalog);
		  
		} catch (IOException e) {					
		  e.printStackTrace();
		}
		
	}
	

	
	private void download(URL url, String filePath) throws IOException {
		File file = new File(filePath);

		URLConnection conn = url.openConnection();
		conn.connect();			
	/*	message = "\nStarting download: \n" +
				">> URL: " + url + "\n" +
				">> Path: " + filePath + "\n" +
				">> Size: " + conn.getContentLength() + " bytes\n";
		setChanged();
		notifyObservers("append");*/
		
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
		
//		System.out.println("\nDownload finished");
	/*	message = "\nDownload finished. \n" +
				"Actual file path: " + filePath + "\n";
		setChanged();
		notifyObservers("append");*/
		
	
	}
}
