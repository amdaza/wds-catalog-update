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
	
	String catalogPath;
	
	public Catalog(){
		catalogPath = "";
	}
	
	public Catalog(String catalogPath){
		this.catalogPath = catalogPath;
	}
	
	/**
	 * Downloads WDS Catalog and saves it in /downloads/catalog.txt
	 * 
	 * @param args
	 */
	public void saveCatalog(String filePath) {
		try {
			// Url with WDS Catalog
			URL url = new URL(
					"http://ad.usno.navy.mil/wds/Webtextfiles/wdsweb_summ.txt");

			File file = new File(filePath);
			
			URLConnection conn = url.openConnection();
			conn.connect();
			System.out.println("\nStarting download: \n");
			System.out.println(">> URL: " + url);
			System.out.println(">> Path: " + filePath);
			System.out.println(">> Size: " + conn.getContentLength() + " bytes");
			
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
			
			System.out.println("\nDownload finished");
				 
				} catch (MalformedURLException e) {
				  System.out.println("url not valid!");
				} catch (IOException e) {
				  e.printStackTrace();
				}
		
	}

}
