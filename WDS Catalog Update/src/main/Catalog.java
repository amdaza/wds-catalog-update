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

import javax.swing.JOptionPane;

/**
 * This class download the file. Txt WDS
 * Performs search on any text or by coordinates (right ascension, declination, radius)
 * 
 * @author Alicia Mireya Daza castillo
 * @author Jorge González López
 * @author Rosa Rodríguez Navarro
 * @since 1.1.1
 */

public class Catalog extends Observable{
	
	/** search form */
	public enum SearchMode {
		   COORDS, TEXT, CONST
		 };
	/** attributes */
	private String catalogPath;
	private String message;

	
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
		message= "Actual file path: " + catalogPath + "\n";
		setChanged();
		notifyObservers("clear");
	}
	
	
	public String getMessage() {
		return message;
	}
	

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Downloads WDS Catalog and saves it in /downloads/catalog.txt
	 * 
	 * @param filePath
	 */
	public void saveCatalog(String filePath, String fileNotesPath) {
		try {
			message = "Dowloading...";
			setChanged();
			notifyObservers("clear");

			// Url with WDS Catalog
			URL url = new URL(
					"http://ad.usno.navy.mil/wds/Webtextfiles/wdsweb_summ.txt");

			download(url,filePath);
			
			// Url with WDS Notes
			URL url2 = new URL(
					"http://ad.usno.navy.mil/wds/Webtextfiles/wdsnewnotes_main.txt");

			download(url2,filePath+".notes");
			System.out.println("\nDownload finished");
			message = "\nDownload finished. \n" +
					"Actual file path: " + filePath + "\n";
			setChanged();
			notifyObservers("append");

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
	

	
	private void download(URL url, String filePath) throws IOException {
		File file = new File(filePath);

		URLConnection conn = url.openConnection();
		conn.connect();			
		/*System.out.println("Starting download: \n");
		System.out.println(">> URL: " + url);
		System.out.println(">> Path: " + filePath);
		System.out.println(">> Size: " + conn.getContentLength() + " bytes");*/
		message = "\nStarting download: \n" +
				">> URL: " + url + "\n" +
				">> Path: " + filePath + "\n" +
				">> Size: " + conn.getContentLength() + " bytes\n";
		setChanged();
		notifyObservers("append");
		
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
		message = "\nDownload finished. \n" +
				"Actual file path: " + filePath + "\n";
		setChanged();
		notifyObservers("append");
		
	
	}
	/**
     *  search method in the file
     *
	 * @param mode
	 * @param strings
	 */
	public void searchInFile(SearchMode mode, String... strings)/*throws java.lang.StringIndexOutOfBoundsException*/{
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
	         try{
		         while((line=br.readLine())!=null){
		        	 switch(mode) { 
		        	 case TEXT:
		        		 searchAnyText(line, strings[0]);
		        	    break;
		        	 case COORDS:   
		        		 seachCoordinates(line, strings[0], strings[1], strings[2]);
		        		 break;
		        	 case CONST: 
	        		    searchConst(line, strings[0]);
                        break;
		        	 }
		         }
	         }catch (java.lang.StringIndexOutOfBoundsException ex){
	   			 fr.close();
	   			 br.close();
	   			 /*Exception e;
	   			 throw e= new Exception();*/	   			
	   			 JOptionPane.showMessageDialog(null,ex.getMessage());
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
	      message= "Search finished \n";
		  setChanged();
		  notifyObservers("clear");
	}
	
	
	/**
     *  coordinate search method
     *  @param line, right ascension, declination, radius
     * 
     */
	private void seachCoordinates(String line, String ra, String dec, String radius) throws java.lang.StringIndexOutOfBoundsException {
		try{
			Star aux = new Star(ra, dec);
			Double rad = Double.parseDouble(radius);
			LineCatalog lineCat = new LineCatalog(line);
			
				Star prim = new Star(lineCat.getPrimRa(), lineCat.getPrimDec());
				if (aux.distance(prim)<= rad){
						setChanged();
						notifyObservers(line);					
				}
		}catch (NumberFormatException ex){
			//Do nothing with this star
		}catch (java.lang.StringIndexOutOfBoundsException e){
			throw e;
			//System.out.println("Incorrect coordinate format ");
    	}			
	}
	
	
	private void searchConst(String line,String constellation) throws java.lang.StringIndexOutOfBoundsException {
		try{
			LineCatalog lineCat = new LineCatalog(line);
			
				Star prim = new Star(lineCat.getPrimRa(), lineCat.getPrimDec());
				if (inConstellation(constellation,prim.getRa(), prim.getDec())) {
						setChanged();
						notifyObservers(line);				
				}
		}catch (NumberFormatException ex){
			//Do nothing with this star
		}catch (java.lang.StringIndexOutOfBoundsException e){
			throw e;
			//System.out.println("Incorrect coordinate format ");
    	}			
	}
	
	/**
     *  constellation filter method
     *  @param right ascension, declination
     *  ftp://cdsarc.u-strasbg.fr/pub/cats/VI/42/program.c
     */
	private boolean inConstellation(String constellation, double RA, double DEC)  {
		   double CONVH = 0.2617993878;
		   double CONVD = 0.1745329251994e-01;
		   double PI4 = 6.28318530717948;
		   double E75 = 1875.;
		   double ARAD,DRAD,A,D,RAH,RAL,RAU,DECL,DECD;
		   String CON="";
		   
		   boolean result= false;
		   

		   RAH = RA/15.0;
		   DECD=DEC;
		   
		    /* PRECESS POSITION TO 1875.0 EQUINOX */
		     ARAD = CONVH * RAH;
		     DRAD = CONVD * DECD ;
		     
		   CoordsE c = new CoordsE();
		   c.RA = ARAD;
		   c.DEC = DRAD;
		   c.EPOCH = 2000.;
		   CoordsE c2 = new CoordsE();
		   c2.EPOCH = E75;
		   hgtprc(c,c2) ;
		    A = c2.RA;
		    D = c2.DEC;
		   
		   if(A <  0.)A=A+PI4 ;
		   if(A >= PI4)A=A-PI4 ;
		   RA= A/CONVH ;
		   DEC=D/CONVD ;
		   
		   int n = Constellations.boundary.length;
		   boolean found = false;
		   for (int i=0; i<n && !found; i++) {
			   
				RAL = Constellations.boundary[i][0];
				RAU = Constellations.boundary[i][1];
				DECL= Constellations.boundary[i][2];
				CON = Constellations.constellation[i];
			      	if (RA >= RAL &&  RA <  RAU &&  DECL <= DEC) { 
//			          System.out.println("RA"+RA+" DEC: "+DEC+" in const. "+CON);
			          found = true;
			      }
			    }
	
		   if (found && CON.equals(constellation)) {
			   result = true;
		      //    System.out.println("RA"+RA+" DEC: "+DEC+" in const. "+CON);
		   }
		return result;
	}
	
	// obtained from ftp://cdsarc.u-strasbg.fr/pub/cats/VI/42/program.c
	private void hgtprc(CoordsE c1, CoordsE c2) {
		   double CDR = 0.17453292519943e-01 ;
		   double EP1=0, EP2=0 ;
		   double X1[] = {0,0,0,0},X2[] = {0,0,0,0} ,R[][] = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}} , T,ST,A,B,C,CSR,SINA,SINB,SINC,COSA,COSB,COSC; 
		   int i,j;
		   double RA1, DEC1, EPOCH1, EPOCH2, RA2, DEC2;
		   RA1 = c1.RA;
		   DEC1 = c1.DEC;
		   EPOCH1 = c1.EPOCH;
		   EPOCH2 = c2.EPOCH;
		   RA2 = c2.RA;
		   DEC2 = c2.DEC;
		   		   
		   /* Compute input direction cosines */
		    A=Math.cos(DEC1) ;
		    X1[1]=A*Math.cos(RA1) ;
		    X1[2]=A*Math.sin(RA1) ;
		    X1[3]=Math.sin(DEC1) ;
		   
		    /* Set up rotation matrix (R) */
		    if(EP1 == EPOCH1 &&  EP2 == EPOCH2) ;
		    else {
		    	 EP1 = EPOCH1 ; EP2 = EPOCH2 ;
		         CSR=CDR/3600. ;
		         T=0.001*(EP2-EP1) ;
		         ST=0.001*(EP1-1900.) ;
		         A=CSR*T*(23042.53+ST*(139.75+0.06*ST)+T*(30.23-0.27*ST+18.0*T)) ;
		         B=CSR*T*T*(79.27+0.66*ST+0.32*T)+A ;
		         C=CSR*T*(20046.85-ST*(85.33+0.37*ST)+T*(-42.67-0.37*ST-41.8*T)) ;
		         SINA=Math.sin(A) ;
		         SINB=Math.sin(B) ;
		         SINC=Math.sin(C) ;
		         COSA=Math.cos(A) ;
		         COSB=Math.cos(B) ;
		         COSC=Math.cos(C) ;
		         R[1][1]=COSA*COSB*COSC-SINA*SINB ;
		         R[1][2]=-COSA*SINB-SINA*COSB*COSC ;
		         R[1][3]=-COSB*SINC ;
		         R[2][1]=SINA*COSB+COSA*SINB*COSC ;
		         R[2][2]=COSA*COSB-SINA*SINB*COSC ;
		         R[2][3]=-SINB*SINC ;
		         R[3][1]=COSA*SINC ;
		         R[3][2]=-SINA*SINC ;
		         R[3][3]=COSC ;
		    }
		    /* Perform the rotation to get the direction cosines at epoch2 */
		    for (i=1; i<=3; i++) {
		      X2[i]=0. ;
		      for (j=1; j<=3; j++) 
		    	  X2[i]+=R[i][j]*X1[j] ;
		    }
		    RA2=Math.atan2(X2[2],X2[1]) ;
		    if(RA2 <  0) RA2 = 6.28318530717948 + RA2 ;
		    DEC2=Math.asin(X2[3]) ;
		   
	      // return data
		   c2.RA = RA2;
		   c2.DEC = DEC2;
	}
	/**
     *  search method for any string, notice the line where there is agreement
     *  @param line, text
     * 
     */
	public void searchAnyText(String line, String text){
		int index = line.indexOf(text);
		if (index > 0){
			setChanged();
			notifyObservers(line);
		} 	
	}	
		/**
	     *  show notes for a given WDS id
	     *  @param id, notes
	     * 
	     */
	 public void showNotes(String id, String notes) {
     	  boolean nFound = false;
	      message= id;
	      String tnotes =notes.trim(); 
	      int n=tnotes.length();
	      if (n==0) {
	    	  message += ": No notes for this entry\n";
	      } else {
	    	  message += " \nNotes: \n";
	    	  for (int i=0; i<n;i++){
	    		 String N = tnotes.substring(i,i+1);
	    		 for (int j=0; j<Notes.notes.length;j++) {
	    			 if (Notes.notes[j][0].equals(N))
	    				 message += N+": "+Notes.notes[j][1]+"\n";
	    		 }
	    		 if (N.equals("N")) nFound=true;
	    	  }
	       		  
	      }
		  if (nFound) {
			 searchNotes(id.substring(0, 7)  , catalogPath+".notes");
		  }
		  setChanged();
		  notifyObservers("clear");

	 }

		public void searchNotes(String id,String path) {
			 File archive = null;
		      FileReader fr = null;
		      BufferedReader br = null;
		 
		      try {
		         // Open file and create BufferedReader
		         // for reading (using readLine()).
		         archive = new File (path);
		         fr = new FileReader (archive);
		         br = new BufferedReader(fr);
		 
		         // Read file
		         String line;
		         int mode = 0;
		         try{
			         while((line=br.readLine())!=null){
			          if (mode==0) {
			        	  if (line.contains(id)) {
			    	    	  message += line+"\n";
			    			  mode=1;			        		  
			        	  }
			          } else {
			        	  if (line.substring(1, 2) != null && line.substring(1, 2).equals(" ")){
			    	    	  message += line+"\n";
			        	  }
			        	  else
			        	  mode = 0;
			          }
			         } // while			         		         
		      } // try
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
		      } // finally-try
		      } catch(Exception e){
			         e.printStackTrace();
		      }
		  }
	

/**
* Auxiliar class
*/
public  class CoordsE {
	public double  RA;
	public double  DEC;
	public double EPOCH;
}	

}
