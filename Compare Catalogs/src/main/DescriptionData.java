package main;

import java.io.*;
import java.util.LinkedHashMap;

import javax.swing.JOptionPane;

public class DescriptionData{
	private LinkedHashMap<String, DataStructure> dt;
	private String path;
	private final String init = "#---Details of Columns:";
	
	public DescriptionData(){

		this.setDt(new LinkedHashMap<String, DataStructure>());
	}
	
	public DescriptionData(String path){
		this.path = path;
		this.setDt(new LinkedHashMap<String, DataStructure>());
	}

	public LinkedHashMap<String, DataStructure> getDt() {
		return dt;
	}

	public void setDt(LinkedHashMap<String, DataStructure> dt) {
		this.dt = dt;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public LinkedHashMap<String, DataStructure> parser(){
		
		 File archive = null;
	      FileReader fr = null;
	      BufferedReader br = null;
		try {
	         // Open file and create BufferedReader
	         // for reading (using readLine()).
	         archive = new File (path);
	         fr = new FileReader (archive);
	         br = new BufferedReader(fr);
	         String line;
	         //RAJ2000 (deg) (F10.6) (ra) Right ascension (J2000) [ucd=pos.eq.ra;meta.main]
	         try{
	        	 System.out.println("Antes init");
		         while(!(line=br.readLine()).equals(init));

		         System.out.println("Despues init");
		         while(!(line=br.readLine()).substring(0, 1).equals("-")){
		        	 String key;
		     		 DataStructure value = new DataStructure();
		        	 int i=4;
		        	 while(line.charAt(i)!= ' '){//R
		        		 i++;
		        	 }
		        	 key = line.substring(4, i);//RAJ2000
		        	 System.out.println("key: "+key);
		        	 
		        	 while(line.charAt(i)== ' '){
		        		 i++;
		        	 }
		        	 if(!Character.isDigit(line.charAt(i+2))){
		        		 int j=i;
		        		 while(line.charAt(j)!= ' '){
			        		 j++;
			        	 }
		        		 value.setMag(line.substring(i, j));//(deg)
		        		 System.out.println("mag: "+line.substring(i, j));
		        		 i=j;
		        		 while(line.charAt(i)== ' '){
			        		 i++;
			        	 }
		        	 }
		        	 char type = line.charAt(i+1);
		        	 value.setType(type);
		        	 System.out.println("type: "+type);
		        	 
		        	 if (type == 'F'){
		        		 if(line.charAt(i+3) == '.'){
		        			 int lenght = Integer.parseInt(Character.toString(line.charAt(i+2)));
		        			 value.setLenght(lenght);
		        			 System.out.println("lenght: "+lenght);
		        			 int dec = Integer.parseInt(Character.toString(line.charAt(i+4)));
		        			 value.setDecimals(dec);
		        			 System.out.println("dec: "+dec);
		        			 i= i+5;
		        		 }else{
		        			 int lenght = Integer.parseInt(line.substring(i+2, i+4));
		        			 value.setLenght(lenght);
		        			 System.out.println("lenght: "+lenght);
		        			 int dec = Integer.parseInt(Character.toString(line.charAt(i+5)));
		        			 value.setDecimals(dec);
		        			 System.out.println("dec: "+dec);
		        			 i= i+6;
		        		 }
		        	 }else if(line.charAt(i+3) == ')'){
				        		 int lenght = Integer.parseInt(Character.toString(line.charAt(i+2)));
			        			 value.setLenght(lenght);
			        			 System.out.println("lenght: "+lenght);
			        			 i= i+3;
		        	 		}else{
				        		 int lenght = Integer.parseInt(line.substring(i+2, i+4));
			        			 value.setLenght(lenght);
			        			 System.out.println("lenght: "+lenght);
			        			 i= i+4;
		        	 		}
		        	 String description = line.substring(i+1, line.length());
		        	 value.setDescription(description);

        			 System.out.println("description: "+description);
        			 
        			 //Add new element into LinkedHashMap
        			 dt.put(key, value);
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
		 
		return dt;
		 
	}
	
}

