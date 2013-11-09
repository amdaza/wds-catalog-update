package main;

/**
 * 
 * @author Alicia Mireya Daza castillo
 * @author Jorge González López
 * @author Rosa Rodríguez Navarro
 * @since 1.0
 */

public class LineCatalog {
	
	/** attributes */
	private String primRa;
	private String primDec;
	private String secRa;
	private String secDec;
	
	/** construction */
	
	public LineCatalog(String line){
		this.primRa = line.substring(112, 121);
		this.primDec = line.substring(121, 130);
		// the secondary coordinates are not here!
		this.secRa = line.substring(89, 93);
		this.secDec = line.substring(93, 97);
	}
	
	/** Getters and setters */
	public String getPrimRa() {
		return primRa;
	}

	public void setPrimRa(String primRa) {
		this.primRa = primRa;
	}

	public String getPrimDec() {
		return primDec;
	}

	public void setPrimDec(String primDec) {
		this.primDec = primDec;
	}

	public String getSecRa() {
		return secRa;
	}

	public void setSecRa(String secRa) {
		this.secRa = secRa;
	}

	public String getSecDec() {
		return secDec;
	}

	public void setSecDec(String secDec) {
		this.secDec = secDec;
	}
	

}
