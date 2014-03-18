package parser.lexical;

import parser.elements.TablaSimbolo;
import parser.elements.Token;

public class Lexical {
	enum EstadoLexico {
		INICIAL, ES_INICIO_ASIGNACION, ES_NUMERO, ES_IDENTIFICADOR,
		ES_MAS, ES_MENOS, ES_POR, ES_ENTRE, ES_EXPONENCIAL, ES_MENOR, ES_MAYOR, 
		ES_MENOR_IGUAL, ES_MAYOR_IGUAL, ES_DISTINTO, ES_IGUAL, ES_NEGACION,
		ES_CONJUNCION, ES_DISYUNCION, 
		ES_CORCHETE_INICIAL, ES_CORCHETE_FINAL, ES_PARENT_INICIAL, ES_PARENT_FINAL,
		ES_PUNTOYCOMA, ES_COMA, ES_PUNTO, ES_SALTOLINEA, ES_EOF, 
		ES_IDENTIFICADOR_INTER, ES_IDENTIFICADOR_INF, ES_ASIGNACION, ES_DISYUNCION_CORT,
		ES_CONJUNCION_CORT, PARTE_DECIMAL, PARTE_DECIMALDOS, ES_CARACTER, ES_CARACTER_FIN, 
		ES_CARACTER_FIN2, ES_INTERROGACION
		
	};
	private EstadoLexico estado;
	private String codigo;
	private String lexema;
	private int fila;
	private int columna;
	private int caracter;
	private int indice;
	private int filaInicio;
	private int columnaInicio;
	private TablaSimbolo ts;
	public Lexical(String input, TablaSimbolo ts){
		this.codigo = input + ' ';
		fila = 0;
		columna = -1;
		indice = 0;
		//caracter = sigCar();
		this.ts = ts;
		
	}
	private int sigCar() {
		try {
			caracter = (int) codigo.charAt(indice);
			indice++;
			if (caracter == '\n') {
				columna = -1;
				fila++;
			} else {
				columna++;
			}
			return caracter;
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("Error lÃ©xico: ");
			return '}';
		}
	}
	public Token sigToken() {

		estado = EstadoLexico.INICIAL;
		filaInicio = fila;
		columnaInicio = columna;
		lexema = "";
		while (true) {
			switch (estado) {
			case INICIAL:
				if (indice == codigo.length()) {
					transita(EstadoLexico.ES_EOF);
				} else if (esLetra(caracter)) {
					transita(EstadoLexico.ES_IDENTIFICADOR);
				} else if (esDigito(caracter)) {
					transita(EstadoLexico.ES_NUMERO);
				} else if (caracter == ':') {
					transita(EstadoLexico.ES_INICIO_ASIGNACION);
				} else if (caracter == '+') {
					transita(EstadoLexico.ES_MAS);
				} else if (caracter == '-') {
					transita(EstadoLexico.ES_MENOS);
				} else if (caracter == '*') {
					transita(EstadoLexico.ES_POR);
				} else if (caracter == '/') {
					transita(EstadoLexico.ES_ENTRE);
				} else if (caracter == '^') {
					transita(EstadoLexico.ES_EXPONENCIAL);
				} else if (caracter == '<') {
					transita(EstadoLexico.ES_MENOR);
				} else if (caracter == '>') {
					transita(EstadoLexico.ES_MAYOR);
				} else if (caracter == '=') {
					transita(EstadoLexico.ES_IGUAL);
				} else if (caracter == '¬') {
					transita(EstadoLexico.ES_NEGACION);
				} else if (caracter == '[') {
					transita(EstadoLexico.ES_CORCHETE_INICIAL);
				} else if (caracter == ']') {
					transita(EstadoLexico.ES_CORCHETE_FINAL);
				} else if (caracter == '(') {
					transita(EstadoLexico.ES_PARENT_INICIAL);
				} else if (caracter == ')') {
					transita(EstadoLexico.ES_PARENT_FINAL);
				} else if (caracter == ';') {
					transita(EstadoLexico.ES_PUNTOYCOMA);
				} else if (caracter == ',') {
					transita(EstadoLexico.ES_COMA);
				} else if (caracter == '.') {
					transita(EstadoLexico.ES_PUNTO);
				} else if (caracter == '\n') {
					transita(EstadoLexico.ES_SALTOLINEA);
				} else if (caracter == '\'') {
					transita(EstadoLexico.ES_CARACTER);
				} else if (esSep(caracter)) {
					transitaIgnorando(EstadoLexico.INICIAL);
				} else {
					return errorLexico();
				}
				break;

			case ES_IDENTIFICADOR:
				if (esLetra(caracter) || esDigito(caracter) || caracter == '_'
						|| caracter == '/') {
					transita(EstadoLexico.ES_IDENTIFICADOR);
				} else if (caracter == '?') {
					transita(EstadoLexico.ES_IDENTIFICADOR_INTER);
				
				} else if ((lexema.compareToIgnoreCase("nat") == 0
						|| lexema.compareToIgnoreCase("ent") == 0 || lexema
						.compareToIgnoreCase("real") == 0) && (caracter == '+')) {
					transita(EstadoLexico.ES_IDENTIFICADOR_INF);
				} /*else {
					return tokenPalabraReservada();
				}*/
				break;

			case ES_IDENTIFICADOR_INTER:
				Token t = new Token(filaInicio, columnaInicio, "identificador",
						lexema);
				ts.anadeId(t.leeAtributo().toString(), null);
				return t;

			
			case ES_NUMERO:
				if (esDigito(caracter)) {
					transita(EstadoLexico.ES_NUMERO);
				} else if (caracter == '.') {
					transita(EstadoLexico.PARTE_DECIMAL);
				} else {
					return new Token(filaInicio, columnaInicio, "Natural",
							Integer.valueOf(lexema));
				}
				break;

			case PARTE_DECIMAL:
				if (esDigito(caracter)) {
					transita(EstadoLexico.PARTE_DECIMALDOS);
				} else {
					antCar();
					return new Token(filaInicio, columnaInicio, "Natural",
							Integer.valueOf(lexema.substring(0,
									lexema.length() - 1)));

				}
				break;

			case PARTE_DECIMALDOS:
				if (esDigito(caracter)) {
					transita(EstadoLexico.PARTE_DECIMALDOS);
				} else {
					return new Token(filaInicio, columnaInicio, "Real",
							Float.valueOf(lexema));
				}
				break;

			case ES_INICIO_ASIGNACION:
				if (caracter == '=') {
					transita(EstadoLexico.ES_ASIGNACION);
				} else {
					return new Token(filaInicio, columnaInicio, "Separador",
							':');
				}
				break;

			case ES_ASIGNACION:
				return new Token(filaInicio, columnaInicio, "OP_ASIG", null);

			case ES_MAS:
				return new Token(filaInicio, columnaInicio, "OP_ADIT", "SUMA");

			case ES_MENOS:
				return new Token(filaInicio, columnaInicio, "OP_ADIT", "RESTA");

			case ES_POR:
				return new Token(filaInicio, columnaInicio, "OP_MULT", "MULT");

			case ES_ENTRE:
				return new Token(filaInicio, columnaInicio, "OP_MULT", "DIV");

			case ES_EXPONENCIAL:
				return new Token(filaInicio, columnaInicio, "OP_EXP", null);

			case ES_MENOR:
				return new Token(filaInicio, columnaInicio, "OP_COMP", "MENOR");

			case ES_MAYOR:
				return new Token(filaInicio, columnaInicio, "OP_COMP", "MAYOR");

			case ES_MENOR_IGUAL:
				return new Token(filaInicio, columnaInicio, "OP_COMP", "MENIGU");

			case ES_MAYOR_IGUAL:
				return new Token(filaInicio, columnaInicio, "OP_COMP", "MAYIGU");

			case ES_DISTINTO:
				return new Token(filaInicio, columnaInicio, "OP_IGU", "DIST");

			case ES_IGUAL:
				return new Token(filaInicio, columnaInicio, "OP_IGU", "IGUA");

			case ES_NEGACION:
				return new Token(filaInicio, columnaInicio, "OP_LOG", "NEG");

			case ES_CONJUNCION:
				if (caracter == 'c') {
					transita(EstadoLexico.ES_CONJUNCION_CORT);
				} else {
					return new Token(filaInicio, columnaInicio, "OP_LOG",
							"CONJ");
				}
				break;

			case ES_CONJUNCION_CORT:
				return new Token(filaInicio, columnaInicio, "OP_LOG",
						"CONJCORT");

			case ES_DISYUNCION:
				if (caracter == 'c') {
					transita(EstadoLexico.ES_DISYUNCION_CORT);
				} else {
					return new Token(filaInicio, columnaInicio, "OP_LOG",
							"DISY");
				}
				break;

			case ES_DISYUNCION_CORT:
				return new Token(filaInicio, columnaInicio, "OP_LOG",
						"DISYCORT");
			
			case ES_CORCHETE_INICIAL:
				return new Token(filaInicio, columnaInicio, "SEPAR", '[');

			case ES_CORCHETE_FINAL:
				return new Token(filaInicio, columnaInicio, "SEPAR", ']');

			case ES_PARENT_INICIAL:
				return new Token(filaInicio, columnaInicio, "SEPAR", '(');

			case ES_PARENT_FINAL:
				return new Token(filaInicio, columnaInicio, "SEPAR", ')');

			case ES_PUNTOYCOMA:
				return new Token(filaInicio, columnaInicio, "SEPAR", ';');

			case ES_COMA:
				return new Token(filaInicio, columnaInicio, "SEPAR", ',');

			case ES_PUNTO:
				return new Token(filaInicio, columnaInicio, "SEPAR", '.');
				

			case ES_SALTOLINEA:
				return new Token(filaInicio, columnaInicio, "SEPAR", "\\n");			

			case ES_CARACTER:
				if (esLetra(caracter)) {
					transita(EstadoLexico.ES_CARACTER_FIN);
				}
				break;

			case ES_CARACTER_FIN:
				if (caracter == '\'') {
					transita(EstadoLexico.ES_CARACTER_FIN2);
				}
				break;

			case ES_CARACTER_FIN2:
				return new Token(filaInicio, columnaInicio, "Caracter", lexema);

			case ES_EOF:
				return new Token(filaInicio, columnaInicio, "EOF", null);
			default:
				break;
			}
		}
	}	
	
	
	private Token errorLexico() {
		String error = "ERROR LEXICO:" + "\n" + "Caracter desconocido: '"
				+ (char) caracter + "' en la linea " + fila + ", columna "
				+ columna + ".";
		transita(EstadoLexico.INICIAL);
		return new Token(filaInicio, columnaInicio, "Error", error);
	}
	
	private boolean esSep(int car) {
		return car == ' ' || car == '\t' || car == '\r' || car == '\b';
	}
	
	private void transita(EstadoLexico aEstado) {
		lexema += (char) caracter;
		transitaIgnorando(aEstado);
	}
	
	private void transitaIgnorando(EstadoLexico aEstado) {
		estado = aEstado;
		filaInicio = fila;
		columnaInicio = columna;
		if (estado != EstadoLexico.ES_EOF)
			caracter = sigCar();
	}
	
	private boolean esLetra(int car) {
		return (car >= 'a' && car <= 'z') || (car >= 'A' && car <= 'Z')
			/*	|| (car == 'Ã¡') || (car == 'Ã�') || (car == 'Ã©') || (car == 'Ã‰')
				|| (car == 'Ã­') || (car == 'Ã�') || (car == 'Ã³') || (car == 'Ã“')
				|| (car == 'Ãº') || (car == 'Ãš') || (car == 'Ã¼') || (car == 'Ãœ')
				|| (car == 'Ã±') || (car == 'Ã‘')*/;
	}
	/**
	 * 
	 * @param car
	 * @return
	 */
	private boolean esDigito(int car) {
		return (car >= '0' && car <= '9');
	}
	
	private int antCar() {
		indice--;
		caracter = (int) codigo.charAt(indice - 1);
		if (caracter == '\n') {
			columna = -1;
			fila--;
		} else {
			columna--;
		}
		return caracter;
	}
}
