package main;

public class Star {
    public  double ra;
    public  double dec;
    public  double pmra;
    public  double pmdec;
    public  double r1;
    public  double r2;
    
    public  String line;

    private  String raS;
    private  String decS;
    private  String pmraS;
    private  String pmdecS;
    private  String r1S;
    private  String r2S;
    
    
    public Star(String raS, String decS, String pmraS, String pmdecS,
    			String r1S, String r2S){
    	this.raS = raS;
    	this.ra = Double.parseDouble(raS);
    	this.decS = decS;
    	this.dec = Double.parseDouble(decS);
    	this.pmraS = pmraS;
    	this.pmra = Double.parseDouble(pmraS);
    	this.pmdecS = pmdecS;
    	this.pmdec = Double.parseDouble(pmdecS);
    	this.r1S = r1S;
    	this.r1 = Double.parseDouble(r1S);
    	this.r2S = r2S;
    	this.r2 = Double.parseDouble(r2S);
    }
    
    public Star(String raS, String decS) throws NumberFormatException{
    	try{
			this.raS = raS;
			this.ra = Double.parseDouble(raS);
			this.decS = decS;
			this.dec = Double.parseDouble(decS);
    	}catch (NumberFormatException ex){
    		throw ex;
    	}
	}
    
    
	public double getRa() {
		return ra;
	}


	public void setRa(double ra) {
		this.ra = ra;
	}


	public double getDec() {
		return dec;
	}


	public void setDec(double dec) {
		this.dec = dec;
	}


	public double getPmra() {
		return pmra;
	}


	public void setPmra(double pmra) {
		this.pmra = pmra;
	}


	public double getPmdec() {
		return pmdec;
	}


	public void setPmdec(double pmdec) {
		this.pmdec = pmdec;
	}


	public double getR1() {
		return r1;
	}


	public void setR1(double r1) {
		this.r1 = r1;
	}


	public double getR2() {
		return r2;
	}


	public void setR2(double r2) {
		this.r2 = r2;
	}


	public String getLine() {
		return line;
	}


	public void setLine(String line) {
		this.line = line;
	}


	public String getR1S() {
		return r1S;
	}


	public void setR1S(String r1s) {
		r1S = r1s;
		this.r1 = Double.parseDouble(r1s);
	}


	public String getR2S() {
		return r2S;
	}


	public void setR2S(String r2s) {
		r2S = r2s;
		this.r2 = Double.parseDouble(r2s);
	}


	public String getPmraS() {
		return pmraS;
	}


	public void setPmraS(String pmraS) {
		this.pmraS = pmraS;
		this.pmra = Double.parseDouble(pmraS);
	}


	public String getPmdecS() {
		return pmdecS;
	}


	public void setPmdecS(String pmdecS) {
		this.pmdecS = pmdecS;
		this.pmdec = Double.parseDouble(pmdecS);
	}


	public String getRaS() {
		return raS;
	}


	public void setRaS(String raS) {
		this.raS = raS;
		this.ra = Double.parseDouble(raS);
	}


	public String getDecS() {
		return decS;
	}


	public void setDecS(String decS) {
		this.decS = decS;
		this.dec = Double.parseDouble(decS);
	}
    

    // distance in seconds
    public double distance(Star s2) {
        double a = Math.pow(Math.sin(Math.toRadians((s2.dec-this.dec)/2)),2) + 
                   Math.cos(Math.toRadians(this.dec))*Math.cos(Math.toRadians(s2.dec))*Math.pow(Math.sin(Math.toRadians((s2.ra-this.ra)/2)), 2);
        double c = 2*Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
        //double ap1 = Math.toDegrees(Math.asin(Math.cos(Math.toRadians(s2.dec)))*Math.sin(Math.toRadians(s2.ra-this.ra))/Math.sin(c));
        double d = Math.toDegrees(c)*3600;
        
        return d;

    }


}
