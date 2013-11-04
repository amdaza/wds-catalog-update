package main;

public class LineCatalog {
	private String primRa;
	private String primDec;
	private String secRa;
	private String secDec;
	
	public LineCatalog(String line){
		this.primRa = line.substring(80, 84);
		this.primDec = line.substring(84, 88);
		this.secRa = line.substring(89, 93);
		this.secDec = line.substring(93, 97);
	}

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
