package parser.endCode;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Stack;

import parser.elements.ContenidoTS;
import parser.elements.TablaSimbolo;


public class FinalCode {
	private String codigo;
	private TablaSimbolo tabla;
	private Stack<TablaSimbolo> pilaTabla = new Stack<TablaSimbolo>();
	private boolean primerparam = true;
	private int mem = 0;

	enum Registro {
		r0, r1, r2, r3, r4, r5, r6, r7, r8, r9
	};

	private Registro[] regs; // Vector de registros del 0 al 9
	private Boolean[] registroAsignado; // Si el registro i ha sido asignado
	private HashMap<String, Registro> tablaRegistros;
	private Registro registroActual;
	private String codigoActual;

	public FinalCode(String codigo, TablaSimbolo tabla_Actual) {
		this.codigo = codigo;
		this.tabla = tabla_Actual;
		pilaTabla.push(tabla);
		tablaRegistros = new HashMap<String, Registro>();
		regs = new Registro[10];
		regs = Registro.values();
		registroAsignado = new Boolean[10];
		for (int i = 0; i < 10; i++) {
			registroAsignado[i] = false;
		}
	}

	public void genera() {
		codigo = limpiaEtiquetas(codigo);
		String c = generaCodigo(codigo);
		// //////////////////////////
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {

			fichero = new FileWriter(
					"C:/final.ens");

			pw = new PrintWriter(fichero);

			pw.print(c);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Nuevamente aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		// ///////////////////////////
	}

	private String limpiaEtiquetas(String codigo2) {
		String a = null;
		String[] cadena = codigo2.split("\n");
		for (int i = 0; i < cadena.length; i++) {
			if (!(cadena[i].length() < 8) && !(cadena[i + 1].length() < 8))
				if ((cadena[i].substring(0, 8).compareTo(("etiqueta")) == 0)
						&& (cadena[i].substring(0, 8).compareTo(
								cadena[i + 1].substring(0, 8)) == 0)) {
					a = codigo2.replaceAll(
							"goto "
									+ cadena[i + 1].substring(0,
											cadena[i + 1].length() - 2),
							"goto "
									+ cadena[i].substring(0,
											cadena[i].length() - 2));
					codigo2 = a.replaceAll(cadena[i + 1] + "\n", "");
				}
		}
		return codigo2;
	}

	private String generaCodigo(String codigoIntermedio) {
		pilaTabla.add(tabla);
		String cadena[] = codigoIntermedio.split("\n");
		String codigoFinal = null;
		codigoActual = codigoIntermedio;
		codigoFinal = "move #500, .ix\n";
		for (int i = 0; i < cadena.length; i++) {
			if (cadena[i].substring(0, 2).compareTo("if") == 0)
				codigoFinal += generaCodigoIf(cadena[i]);
			else if (cadena[i].substring(0, 4).compareTo("goto") == 0)
				codigoFinal += generaCodigoGoto(cadena[i]);
			else if (cadena[i].substring(0, 4).compareTo("call") == 0)
				codigoFinal += generaCodigoCall(cadena[i]);
			else if (cadena[i].substring(0, 5).compareTo("param") == 0)
				codigoFinal += generaCodigoParam(cadena[i]);
			else if (cadena[i].substring(0, 6).compareTo("return") == 0)
				codigoFinal += generaCodigoReturn(cadena[i]);
			else if (cadena[i].contains(":="))
				codigoFinal += generaCodigoAsignaciones(cadena[i]);
			else if (cadena[i].substring(cadena[i].length() - 2,
					cadena[i].length()).compareTo(" :") == 0)
				codigoFinal += generaCodigoEtiqueta(cadena[i]);
		}
		codigoActual = consumeCodigo();
		return codigoFinal;

	}

	private String consumeCodigo() {
		String cadena[] = codigoActual.split("\n");
		codigoActual="";
		for (int i = 1; i < cadena.length; i++)
			codigoActual += cadena[i] + "\n";
		return codigoActual;
	}

	private String generaCodigoReturn(String string) {

		return "move #9[.ix],.r1" + "move #10[.ix],.r2" + "move #11[.ix],.r3"
				+ "move #12[.ix],.r4" + "move #13[.ix],.r5"
				+ "move #14[.ix],.r6" + "move #15[.ix],.r7"
				+ "move #16[.ix],.r8" + "sub .ix, #100 " + "move .a,.ix"
				+ "ret";
	}

	private String generaCodigoParam(String string) {
		String salida = "";
		String subcadena = "";
		if (primerparam) {
			salida = "add .ix, #100" + "\n" + "move .a, .ix" + "\n";
			primerparam = false;
		}
		subcadena = string.substring(6, string.length());
		salida += "move " + obtenRegistro(subcadena, codigoActual) + ", #"
				+ mem;
		mem += tabla.getContenido(subcadena).getTama�o();
		return salida;
	}

	private String generaCodigoCall(String string) {
		//String salida = "";
		primerparam = true;
		mem = 0;
		return "move .r1, #9[.ix]" + "move .r2, #10[.ix]"
				+ "move .r3, #11[.ix]" + "move .r4, #12[.ix]"
				+ "move .r5, #13[.ix]" + "move .r6, #14[.ix]"
				+ "move .r7, #15[.ix]" + "move .r8, #16[.ix]"
				+ "call /POTENCIA" + "move #108[.ix], #8[.ix]";

	}

	private String generaCodigoIf(String string) {
		String[] cadena = string.split(" ");
		String salida = "move " + dameMemoria(cadena[1]) + ", "
				+ obtenRegistro(cadena[1], codigoActual) + "\n" + "sub "
				+ obtenRegistro(cadena[3], codigoActual) + ", "
				+ obtenRegistro(cadena[1], codigoActual) + "\n";
		if (cadena[2].equalsIgnoreCase("=")) {
			salida += "bz /" + cadena[5] + "\n";
		} else if (cadena[2].equalsIgnoreCase("≠")) {
			salida += "bnz /" + cadena[5] + "\n";
		} else if (cadena[2].equalsIgnoreCase("<")) {
			salida += "bp /" + cadena[5] + "\n";
		} else if (cadena[2].equalsIgnoreCase(">")) {
			salida += "bn /" + cadena[5] + "\n";
		} else if (cadena[2].equalsIgnoreCase("≤")) {

		} else if (cadena[2].equalsIgnoreCase("≥")) {

		}
		return salida;
	}

	private String generaCodigoEtiqueta(String string) {
		String cadena[] = string.split(" ");
		TablaSimbolo t = pilaTabla.firstElement();
		ContenidoTS c = t.getContenido(cadena[0]);
		if (c != null && c.getTipo().equals("procedimiento")) {
			t = c.getTablaHija();
			pilaTabla.add(t);
			tabla = pilaTabla.lastElement();
		}
		return string.toUpperCase()+"\n";
	}

	private String generaCodigoAsignaciones(String string) {
		String cadena[] = string.split(" := ");
		String s = "";
		//String elem[]=new String[2];
		if (cadena[0].contains("↑")) {
		}
		 else if (cadena[0].contains("[")) {

		} else if (cadena[0].length()>=8&&cadena[0].substring(0, 8).compareTo("temporal") == 0) {
			s = obtenRegistro(cadena[0], codigoActual);
			cadena = generaCodigoAsignacionesOp2(cadena[1]);
			return cadena[0] + "move " + cadena[1] + " " + s + "\n";
		} else {
			s = obtenRegistro(cadena[0], codigoActual);
			cadena = generaCodigoAsignacionesOp2(cadena[1]);
			return cadena[0] + "move " + cadena[1] + " " + s + "\n";
		}
		return null;
	}

	public String obtenRegistro(String temporal, String codigo) {

		String r = "";

		if (tablaRegistros.isEmpty()) { // Si la tabla está vacía se inserta el
										// registro0 con el valor de la teporal
			tablaRegistros.put(temporal, regs[0]);
			registroAsignado[0] = true;
			for (int i = 1; i < 10; i++) {
				registroAsignado[i] = false;
			}
			r = regs[0].toString();
		} else {
			if (tablaRegistros.size() < 10)

				r = noEstaTemporal(temporal, codigo);

		}
		return "." + r;
	}

	@SuppressWarnings("unused")
	private String noEstaTemporal(String temporalNueva, String codigo) {
		java.util.Iterator<Entry<String, Registro>> it = tablaRegistros
				.entrySet().iterator();
		int i = 1;
		boolean enc = false;

		while (it.hasNext() && !enc) {

			String temporal = it.next().getKey();
			if (!estaTemporal(temporal, codigo)) {
				Registro regAntiguo = tablaRegistros.get(temporal);
				tablaRegistros.put(temporalNueva, regAntiguo);
				switch (regAntiguo) {
				case r0:
					registroAsignado[0] = true;
					break;
				case r1:
					registroAsignado[1] = true;
					break;
				case r2:
					registroAsignado[2] = true;
					break;
				case r3:
					registroAsignado[3] = true;
					break;
				case r4:
					registroAsignado[4] = true;
					break;
				case r5:
					registroAsignado[5] = true;
					break;
				case r6:
					registroAsignado[6] = true;
					break;
				case r7:
					registroAsignado[7] = true;
					break;
				case r8:
					registroAsignado[8] = true;
					break;
				case r9:
					registroAsignado[9] = true;
					break;

				}
				registroActual = regAntiguo;
				enc = true;
			} else {
				if (!registrosOcupados()) {
					if (dameRegistroActual() == null)
						return dameMemoria(temporalNueva);
					else {
						registroActual = dameRegistroActual();
						tablaRegistros.put(temporalNueva, registroActual);
						return registroActual.toString();
					}
				}
			}
			i++;
		}
		return registroActual.toString();
	}

	private String dameMemoria(String temporalNueva) {

		return "memoria";
	}

	// Que haya algún registro libre
	private boolean registrosOcupados() {

		boolean estaOcupado = true;
		int i = 0;
		while (estaOcupado && i < 10) {
			estaOcupado = registroAsignado[i];
			i++;
		}
		return estaOcupado;
	}

	private Registro dameRegistroActual() {

		boolean estaOcupado = true;
		int i = 0;
		while (estaOcupado && i < 10) {
			estaOcupado = registroAsignado[i];
			if (estaOcupado)
				i++;
		}
		if (i == 10)
			return null;
		else
			return regs[i];
	}

	// Comprueba si se va a utilizar dicha temporal en el código posterior,
	// método Nacho
	private boolean estaTemporal(String temporal, String codigo) {
		boolean enc = codigo.contentEquals(temporal);
		return enc;
	}

	private String[] generaCodigoAsignacionesOp2(String string) {
		String[] s = new String[2];
		if (string.contains(" ")) {
			String[] cadena = string.split(" ");
			if (cadena[1].equalsIgnoreCase("mod")) {
				s[0] = "mod " + obtenRegistro(cadena[0], codigoActual) + ", "
						+ obtenRegistro(cadena[1], codigoActual);
			} else if (cadena[1].equalsIgnoreCase("-")) {
				s[0] = "sub " + obtenRegistro(cadena[0], codigoActual) + ", "
						+ obtenRegistro(cadena[1], codigoActual);
			} else if (cadena[1].equalsIgnoreCase("+")) {
				s[0] = "add " + obtenRegistro(cadena[0], codigoActual) + ", "
						+ obtenRegistro(cadena[1], codigoActual);
			} else if (cadena[1].equalsIgnoreCase("*")) {
				s[0] = "mul " + obtenRegistro(cadena[0], codigoActual) + ", "
						+ obtenRegistro(cadena[1], codigoActual);
			} else if (cadena[1].equalsIgnoreCase("/")) {
				s[0] = "";
			} else if (cadena[1].equalsIgnoreCase("div")) {
				s[0] = "div " + obtenRegistro(cadena[0], codigoActual) + ", "
						+ obtenRegistro(cadena[1], codigoActual);
			} else if (cadena[1].equalsIgnoreCase("ʌ")
					|| cadena[1].equalsIgnoreCase("ʌc")) {
				s[0] = "and " + obtenRegistro(cadena[0], codigoActual) + ", "
						+ obtenRegistro(cadena[1], codigoActual);
			} else if (cadena[1].equalsIgnoreCase("ν")
					|| cadena[1].equalsIgnoreCase("νc")) {
				s[0] = "or " + obtenRegistro(cadena[0], codigoActual) + ", "
						+ obtenRegistro(cadena[1], codigoActual);
			} else if (cadena[1].equalsIgnoreCase("^")) {
				s[0] = "";
			} else
				s[1] = ".a";

		} else {
			s[0] = "";
			s[1] = "#" + string;
		}
		return s;
	}

	private String generaCodigoGoto(String string) {
		String cadena[] = string.split(" ");
		return "br /" + cadena[1].toUpperCase()+"\n";
	}

	@SuppressWarnings("unused")
	private String getIniPalabra(String codigoIntermedio) {
		String ini = "";
		int longi = codigoIntermedio.length();
		int i = 0;
		char pos = codigoIntermedio.charAt(i);
		while (pos != ' ' && i < longi - 1) {
			ini += pos;
			i++;
			pos = codigoIntermedio.charAt(i);
		}
		return ini;

	}

	@SuppressWarnings("unused")
	private String getFinPalabra(String codigoIntermedio) {
		int longi = codigoIntermedio.length();
		int i = 0;
		char pos = codigoIntermedio.charAt(i);
		while (pos != ' ' && i < longi - 1) {
			i++;
			pos = codigoIntermedio.charAt(i);
		}

		return codigoIntermedio.substring(i, longi - 1);

	}
}
