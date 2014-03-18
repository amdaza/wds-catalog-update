package parser.semantic;

public class Expression {
	private String tipo;
	private String lugar;
	private String verdadera;
	private String falsa;

	public Expression() {
		this.tipo = "";
		this.lugar = "";
		this.verdadera = "";
		this.falsa = "";
	}

	public Expression(String tipo, String lugar, String verdadero, String falso) {
		this.tipo = tipo;
		this.lugar = lugar;
		this.verdadera = verdadero;
		this.falsa = falso;
	}

	public Expression(Expression e) {
		this.tipo = e.getTipo();
		this.lugar = e.getLugar();
		this.verdadera = e.getVerdadera();
		this.falsa = e.getFalsa();
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getVerdadera() {
		return verdadera;
	}

	public void setVerdadera(String verdadera) {
		this.verdadera = verdadera;
	}

	public String getFalsa() {
		return falsa;
	}

	public void setFalsa(String falsa) {
		this.falsa = falsa;
	}

	public boolean esTipoNumerico() {
		return (tipo.equals("enteros") || (tipo.equals("enteros positivos"))
				|| (tipo.equals("enteros infinitos"))
				|| (tipo.equals("naturales"))
				|| (tipo.equals("naturales positivos"))
				|| (tipo.equals("naturales infinitos"))
				|| (tipo.equals("reales")) || (tipo.equals("número"))
				|| (tipo.equals("reales positivos")) || (tipo
					.equals("reales infinitos")));

	}
}
