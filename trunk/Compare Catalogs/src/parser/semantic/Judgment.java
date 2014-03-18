package parser.semantic;

public class Judgment {
	private String comienzo;
	private String siguiente;

	public Judgment() {
		this.comienzo = "";
		this.siguiente = "";

	}

	public Judgment(String comienzo, String siguiente) {
		this.comienzo = comienzo;
		this.siguiente = siguiente;

	}

	public String getComienzo() {
		return comienzo;
	}

	public void setComienzo(String comienzo) {
		this.comienzo = comienzo;
	}

	public String getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(String siguiente) {
		this.siguiente = siguiente;
	}
}
