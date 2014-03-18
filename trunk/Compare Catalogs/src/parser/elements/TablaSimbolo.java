package parser.elements;

import java.util.HashMap;
import java.util.Map;

public class TablaSimbolo {
	
	private HashMap<String, ContenidoTS> ts;

	private TablaSimbolo superior;

	public TablaSimbolo(TablaSimbolo ts) {

		superior = ts;
		this.ts = new HashMap<String, ContenidoTS>();
	}

	public TablaSimbolo(TablaSimbolo ts, HashMap<String, ContenidoTS> mapa) {
		superior = ts;
		this.ts = mapa;
	}

	@SuppressWarnings("unchecked")
	public TablaSimbolo Copia() {
		TablaSimbolo nueva = new TablaSimbolo(superior,
				(HashMap<String, ContenidoTS>) ts.clone());
		return nueva;
	}

	public TablaSimbolo() {

		ts = new HashMap<String, ContenidoTS>();
		superior = null;
	}

	public Map<String, ContenidoTS> anadeId(String id, ContenidoTS reg) {
		if (existeId(id)) {
			return null;
		} else {
			ts.put(id, reg);
			return ts;
		}
	}

	public void anadeId1(String id, ContenidoTS reg) {
		if (!existeId(id)) {
			ts.put(id, reg);
		}
	}

	public Map<String, ContenidoTS> getTs() {
		return ts;
	}

	public void setTs(HashMap<String, ContenidoTS> ts) {
		this.ts = ts;
	}

	public boolean existeId(String id) {
		if (ts.containsKey(id))
			return true;
		else
			return false;
		
	}

	public TablaSimbolo getSuperior() {
		return superior;

	}

	public ContenidoTS getContenido(String id) {
		TablaSimbolo tsAux = this;
		while (tsAux.superior != null) { // || !tsAux.ts.containsKey(id)
			if (tsAux.ts.containsKey(id))
				return tsAux.ts.get(id);
			else
				tsAux = tsAux.superior;
		}
		if (tsAux.ts.containsKey(id))
			return tsAux.ts.get(id);
		else
			return null;
	}

	public void setSuperior(TablaSimbolo t) {
		superior = t;
	}
}
