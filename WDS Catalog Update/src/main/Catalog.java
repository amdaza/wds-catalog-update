package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Observable;


public class Catalog extends Observable{
	
	private String catalogPath;
	//private URL url;
	//private URLConnection conn;
	private String message;


	public Catalog(){
		catalogPath = "";
	}
	
	public Catalog(String catalogPath){
		this.catalogPath = catalogPath;
	}
	
	public String getCatalogPath() {
		return catalogPath;
	}

	public void setCatalogPath(String catalogPath) {
		this.catalogPath = catalogPath;
		message= "Actual file path: " + catalogPath + "\n";
		setChanged();
		notifyObservers("clear");
	}
	
/*	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public URLConnection getConn() {
		return conn;
	}

	public void setConn(URLConnection conn) {
		this.conn = conn;
	}*/
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
			System.out.println("Starting download: \n");
			System.out.println(">> URL: " + url);
			System.out.println(">> Path: " + filePath);
			System.out.println(">> Size: " + conn.getContentLength() + " bytes");
			message = "\nStarting download: \n" +
					">> URL: " + url + "\n" +
					">> Path: " + filePath + "\n" +
					">> Size: " + conn.getContentLength() + " bytes\n";
			setChanged();
			notifyObservers("clear");
			
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
			message = "\nDownload finished. \n" +
					"Actual file path: " + filePath + "\n";
			setChanged();
			notifyObservers("append");
			catalogPath = filePath;
				 
				} catch (MalformedURLException e) {
				  System.out.println("url not valid!");
				  
				} catch (IOException e) {					
				  e.printStackTrace();
				}
		
	}
	
	public void searchInFile(String... strings){
		  File archive = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	 
	      try {
	         // Open file and create BufferedReader
	         // for reading (using readLine()).
	         archive = new File (catalogPath);
	         fr = new FileReader (archive);
	         br = new BufferedReader(fr);
	 
	         // Read file
	         String line;
	         while((line=br.readLine())!=null){
		            //System.out.println(line);
	        	 if (strings.length ==1){
	        		 searchAnyText(line, strings[0]);
	        	 }else{
	        		 seachCoordinates(line, strings[0], strings[1], strings[2]);
	        	 }
	         }
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	    	  // Here we close file, to make sure that
	    	  // it closes even if everything's ok
	    	  // or there is an exception
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	}
	
	private void seachCoordinates(String line, String ra, String dec, String radius) {
		
		
	}

	public void searchAnyText(String line, String text){
		int index = line.indexOf(text);
		if (index > 0){
			System.out.println(line);
			setChanged();
			notifyObservers(line);
		}//else System.out.println(text);
	}
	

}
