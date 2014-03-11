package main;

//Esta clase guardara la segunda y tercera columna de datos del catalogo
public class DataStructure {
	enum Type{A, I, F, E};
	
	private String mag;
	private Type type;
	private String description;
	private int lenght;
	private int decimals;
	
	
	public DataStructure(){
		description="";
	}

	public Type getType() {
		return type;
	}

	public void setType(char type) {
		switch (type) {
			case 'a':
			case 'A':
				this.type = Type.A;
			break;
			case 'F':
				this.type = Type.F;
			break;
			case 'I':
				this.type = Type.I;
			break;
			case 'E':
				this.type = Type.E;
			break;
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMag() {
		return mag;
	}

	public void setMag(String mag) {
		this.mag = mag;
	}
	
	public String toString() {
		return type + description ;
	}

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}

	public int getDecimals() {
		return decimals;
	}

	public void setDecimals(int decimals) {
		this.decimals = decimals;
	}

	public void setType(Type type) {
		this.type = type;
	}


}
