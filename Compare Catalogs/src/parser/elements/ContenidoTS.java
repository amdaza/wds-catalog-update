package parser.elements;

import java.util.LinkedList;

public class ContenidoTS {
	private String Tipo;
	private String TipoBase;
	private int posIni;
	private int posFin;
	private int NumArgs;
	private LinkedList<String> TipoArgs; // linkedlist por si se trata una
											// llamada a un metodo o funcion
	private int PasoArgs;
	private LinkedList<String> retorno; // linkedlist por si es una funcion que
										// devuelve mas de un argumento
	private int tamaño;
	private TablaSimbolo TablaHija;
	private LinkedList<ContenidoTS> puntero; // linkedlist por si es una puntero

	public ContenidoTS(String tipo, String tipoBase, int posIni, int posFin,
			int NumArgs, LinkedList<String> TipoArgs, int pasoArgs,
			LinkedList<String> retorno, int tamaño, TablaSimbolo th,
			LinkedList<ContenidoTS> puntero) {
		this.setTipo(tipo);
		this.setTipoBase(tipoBase);
		this.setPosIni(posIni);
		this.setPosFin(posFin);
		this.setPasoArgs(pasoArgs);
		this.setRetorno(retorno);
		this.setTamaño(tamaño);
		this.setTablaHija(th);
		this.setTipoArgs(TipoArgs);
		this.setNumArgs(NumArgs);
		this.setPuntero(puntero);
	}

	public int getTamaño() {
		return tamaño;
	}

	public void setTamaño(int tamaño) {
		this.tamaño = tamaño;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public String getTipoBase() {
		return TipoBase;
	}

	public void setTipoBase(String tipoBase) {
		TipoBase = tipoBase;
	}

	public int getPosIni() {
		return posIni;
	}

	public void setPosIni(int posIni) {
		this.posIni = posIni;
	}

	public int getPosFin() {
		return posFin;
	}

	public void setPosFin(int posFin) {
		this.posFin = posFin;
	}

	public int getNumArgs() {
		return NumArgs;
	}

	public void setNumArgs(int numArgs) {
		NumArgs = numArgs;
	}

	public LinkedList<String> getTipoArgs() {
		return TipoArgs;
	}

	public void setTipoArgs(LinkedList<String> tipoArgs) {
		TipoArgs = tipoArgs;
	}

	public int getPasoArgs() {
		return PasoArgs;
	}

	public void setPasoArgs(int pasoArgs) {
		PasoArgs = pasoArgs;
	}

	public LinkedList<String> getRetorno() {
		return retorno;
	}

	public void setRetorno(LinkedList<String> retorno) {
		this.retorno = retorno;
	}

	public TablaSimbolo getTablaHija() {
		return TablaHija;
	}

	public void setTablaHija(TablaSimbolo tablaHija) {
		TablaHija = tablaHija;
	}

	public void setPuntero(LinkedList<ContenidoTS> puntero) {
		this.puntero = puntero;
	}

	public LinkedList<ContenidoTS> getPuntero() {
		return puntero;
	}
}
