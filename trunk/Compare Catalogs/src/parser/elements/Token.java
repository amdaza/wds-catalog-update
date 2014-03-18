package parser.elements;

public class Token {
	private String tipo;
	private Object atributo;
	int fila;
	int columna;

	/**
	 * 
	 * @param fila
	 * @param col
	 * @param tipo
	 * @param atributo
	 */
	public Token(int fila, int col, String tipo, Object atributo) {
		this.fila = fila;
		this.columna = col;
		this.tipo = tipo;
		this.atributo = atributo;

	}

	/**
	 * 
	 * @param fila
	 * @param col
	 * @param tipo
	 */
	public Token(int fila, int col, String tipo) {
		this(fila, col, tipo, null);
	}

	/**
	 * 
	 * @param t
	 */
	public Token(Token t) {
		this.fila = t.fila;
		this.columna = t.columna;
		this.tipo = t.tipo;
		this.atributo = t.atributo;
	}

	/**
	 * 
	 * @return
	 */
	public String leeTipo() {
		return tipo;
	}

	/**
	 * 
	 * @return
	 */
	public int leeFila() {
		return fila;
	}

	/**
	 * 
	 * @return
	 */
	public int leeColumna() {
		return columna;
	}

	/**
	 * 
	 * @return
	 */
	public Object leeAtributo() {
		return atributo;
	}

	/**
	 * 
	 */
	public String toString() {
		if (atributo == null)
			return "<tipo: " + tipo.toString() + ",atributo: null >";
		else
			return "<tipo: " + tipo.toString() + ",atributo: "
					+ atributo.toString() + ">";
	}
}
