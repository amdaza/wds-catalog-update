package parser.syntactic;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import parser.elements.ContenidoTS;
import parser.elements.TablaSimbolo;
import parser.elements.Token;
import parser.errors.Errors;
import parser.lexical.Lexical;
import parser.semantic.Expression;
import parser.semantic.Operand;



public class AnalizadorSintactico {
/*
	private Token token_act;
	private AnalizadorLexico analizadorLexico;
	private GeneracionCodigoFinal generador;
	boolean hayTipo = false;
	boolean hayComa = false;
	boolean hayError = false;
	boolean hayAsigMult = false;
	boolean AsigMult = false;
	boolean error_tipo = false;
	boolean transita = false;
	private TablaSimbolo Tabla_Actual;
	TablaSimbolo auxiliar = null;
	private JTextArea consola = new JTextArea();
	private Token auxToken, auxToken2, posini, posfin = null;
	private Stack<TablaSimbolo> pilaTabla = new Stack<TablaSimbolo>();
	private Stack<Integer> pilaDesplazamiento = new Stack<Integer>();
	private Error error;
	private boolean leyendoRegistro = false;
	private boolean vector = false;
	private LinkedList<Object> listaRangoIni = new LinkedList<Object>();
	private LinkedList<Object> listaRangoFin = new LinkedList<Object>();;
	private LinkedList<String> listaVariables = new LinkedList<String>();
	private LinkedList<ContenidoTS> listaPuntero;
	private boolean esTipoRango, esPuntero = false;
	private boolean añadirRango = false;
	private boolean estamosEnTipos;
	private boolean expid = false;
	private boolean leyendoRegistro2;
	private String codigo = "";
	private int numeroEtq, numeroTem = -1;

	private boolean tipoBasico, tipoPuntero, tipoRegistro, tipoEnum,
			tipoRango = false; // Para diferenciar los tipos en tipo()
	private TablaSimbolo tablaAuxGeneral;
	private ContenidoTS contenidoAuxGeneral;
	private boolean tipoTipo;
	private boolean estamosDeclaracion = false;
	private int cont = 0;

	*//**
	 * Constructora de la clase.
	 * 
	 * @param analizadorLexico
	 *            nos propociona los Tokens del lenguaje a procesar.
	 *//*
	public AnalizadorSintactico(AnalizadorLexico analizadorLexico) {
		this.analizadorLexico = analizadorLexico;
		token_act = analizadorLexico.sigToken();
	}

	*//**
	 * Método que nos devuelve el contenido de la consola
	 * 
	 * @return devuelve el contenido de la consola
	 *//*
	public JTextArea getConsola() {
		return consola;
	}

	*//**
	 * Método que consume los saltos de linea entre instrucciones.
	 *//*
	private void compruebaSaltoLinea() {
		try {
			while (token_act.leeAtributo().equals("\\n"))
				token_act = analizadorLexico.sigToken();
		} catch (NullPointerException e) {
		}
	}

	*//**
	 * Método que comprueba si el atributo del Token es nulo.
	 * 
	 * @return devuelve verdadero si el atributo del Token es nulo, en caso
	 *         contrario devuelve falso.
	 *//*
	private boolean atributoNull() {
		return (token_act.leeAtributo() == null);
	}

	*//**
	 * Método que comprueba el tipo de Token.
	 * 
	 * @param s
	 *            parametro de entrada, el cual nos da el tipo que deseamos.
	 * 
	 * @return devuelve verdadero y avanza Token si este es del mismo tipo al
	 *         esperado, en caso contrario devuelve falso.
	 *//*
	private boolean compruebaTipo(String s) {
		boolean comprueba = false;
		if (token_act.leeTipo().compareTo(s) == 0) {
			System.out.println(token_act.toString() + " ");
			token_act = analizadorLexico.sigToken();
			comprueba = true;
		}
		return comprueba;
	}

	*//**
	 * Método que comprueba el tipo de Token.
	 * 
	 * @param s
	 *            parametro de entrada, el cual nos da el tipo que deseamos.
	 * 
	 * @return devuelve verdadero si este es del mismo tipo al esperado, en caso
	 *         contrario devuelve falso. (No avanza el token)
	 *//*
	private boolean compruebaTipoNoAvanzado(String s) {
		boolean comprueba = false;
		if (token_act.leeTipo().compareTo(s) == 0) {
			System.out.println(token_act.toString() + " ");
			comprueba = true;
		}
		return comprueba;
	}

	*//**
	 * Método que comprueba el atributo de Token.
	 * 
	 * @param s
	 *            parametro de entrada, el cual nos da el atributo que deseamos.
	 * 
	 * @return devuelve verdadero y avanza Token si este tiene el mismo atributo
	 *         al esperado, en caso contrario devuelve falso.
	 *//*
	private boolean compruebaAtributo(Object s) {
		if (!esTipoTipo(token_act)) {
			if (!atributoNull() && token_act.leeAtributo().equals(s)) {
				System.out.println(token_act.toString() + " ");
				token_act = analizadorLexico.sigToken();
				return true;
			}
		}
		return false;
	}

	*//**
	 * Método que comprueba el atributo de Token.
	 * 
	 * @param s
	 *            parametro de entrada, el cual nos da el atributo que deseamos.
	 * 
	 * @return devuelve verdadero si el Token tiene el mismo atributo al
	 *         esperado, en caso contrario devuelve falso.
	 *//*
	private boolean compruebaAtributoNoAvanzado(Object s) {
		if (!esTipoTipo(token_act)) {
			if (!atributoNull() && token_act.leeAtributo().equals(s)) {
				System.out.println(token_act.toString() + " ");
				return true;
			}
		}
		return false;
	}

	private String temporalNueva() {
		numeroEtq++;
		return "temporal" + numeroEtq;
	}

	private String etiquetaNueva() {
		numeroTem++;
		return "etiqueta" + numeroTem;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R1: principal → Programa id cuerpo fPrograma
	 * 
	 *//*
	public void principal() {
		compruebaSaltoLinea();
		System.out.println("***Principal***");
		if (compruebaAtributo(40)) {
			codigo += token_act.leeAtributo() + " :" + "\n";

			if (compruebaTipo("identificador")) {
				// ////////////TABLA GLOBAL////////////
				Tabla_Actual = new TablaSimbolo();
				// ////////////////////////////////////
				pilaTabla.add(Tabla_Actual);
				pilaDesplazamiento.add(0);
				cuerpo();
				codigo += "return";
				if (transita) {
					transita = false;
					String s[] = codigo.split("\n");
					s = s[1].split(" ");
					String c = s[1] + " :";
					codigo = codigo.replaceAll(c + "\n", "");
					codigo = codigo.replaceAll("goto " + s[1] + "\n", "goto "
							+ c + "\n" + c + "\n");
				}
				
				if (compruebaAtributo(22))
					if (!hayError) {
						pilaDesplazamiento.pop();
						pilaTabla.pop();
						consola.setForeground(Color.blue);
						consola.append("Análisis Léxico y Sintáctico correcto.");
						//generador = new GeneracionCodigoFinal(codigo, Tabla_Actual);
						//generador.genera();
					} else if (!hayError) {
						error = new Error(2, token_act, hayError, consola);
						hayError = true;
					}
			} else if (!hayError) {
				error = new Error(3, token_act, hayError, consola);
				hayError = true;
			}
		} else if (!hayError) {
			error = new Error(1, token_act, hayError, consola);
			hayError = true;
		}
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R2: cuerpo→ [declaraciones][Insts]
	 * 
	 *//*
	private void cuerpo() {
		String declaraciones = etiquetaNueva();
		codigo += "goto " + declaraciones + "\n";
		if (!hayError) {
			compruebaSaltoLinea();
			System.out.println("***Cuerpo***");
			declaraciones();
			codigo += declaraciones + " :" + "\n";
			compruebaSaltoLinea();
			instrucciones();
		}
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R3: declaraciones → [decTipo] [var declaración] [bloque]
	 * 
	 *//*
	private void declaraciones() {

		decTipo();
		compruebaSaltoLinea();
		if (compruebaAtributo(49)) {// Leemos var
			declaracion(new LinkedList<String>(), new LinkedList<String>());
		}
		bloque();
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R4: decTipo → tipos objetos ftipos
	 * 
	 *//*
	private void decTipo() {
		if (compruebaAtributo(48)) {// Leemos la palabra tipos
			estamosEnTipos = true;
			objetos();
			compruebaSaltoLinea();
			if (compruebaAtributo(25)) {// Leemos ftipos
				estamosEnTipos = false;
				System.out.println("Hemos leído la palabra ftipos.");
			} else if (!hayError) {
				error = new Error(4, token_act, hayError, consola);
				hayError = true;
			}

		}
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R5: objetos → id = tipo '\n' [objetos] - R6: objetos → id = id [rangos]
	 * de tipo '\n [objetos]
	 * 
	 *//*
	// //////////////////
	private void objetos() {
		try {
			if (!hayError) {
				compruebaSaltoLinea();
				System.out.println("***Objetos***");
				auxToken2 = token_act;
				if (compruebaTipo("identificador")) {
					System.out.println("Hemos leído identificador.");
					if (compruebaAtributo("IGUA")) {
						Token idAux = token_act;
						if (compruebaTipo("identificador")) {
							if (compruebaAtributo('[')) {// Para comprobar si es
															// vector
								esTipoRango = true;
								if (rangos(true)) {
									if (compruebaAtributo(']')) {
										if (compruebaAtributo(6)) {
											System.out
													.println("Hemos leído de.");
											Token tipo = token_act;
											// boolean
											// esId=compruebaTipoNoAvanzado("identificador");
											esTipoRango = false;
											vector = true;
											Expresion e = new Expresion();
											if (tipo(e)) {
												int cont = 0;
												int posVector = 0;
												TablaSimbolo tablaAux = Tabla_Actual; // creamos
																						// la
																						// nueva
																						// tabla
																						// con
																						// la
																						// superior
																						// como
																						// padre
												Tabla_Actual = new TablaSimbolo(
														tablaAux);
												while ((cont < listaRangoIni
														.size())
														&& (!listaRangoIni.get(
																cont).equals(
																"/"))) {
													int tam = dameTamañoRango(
															(Integer) listaRangoFin
																	.get(cont),
															(Integer) listaRangoIni
																	.get(cont));
													ContenidoTS conte = new ContenidoTS(
															"rango",
															(String) idAux
																	.leeAtributo(),
															(Integer) listaRangoIni
																	.get(cont),
															(Integer) listaRangoFin
																	.get(cont),
															0, null, 10, null,
															tam, null, null);
													Tabla_Actual
															.anadeId1(
																	String.valueOf(posVector),
																	conte);
													cont++;
													posVector++;
												}
												int tamVector = dameTamañoVector();

												if (tipoTipo) {
													int tam = dameTamId(tipo);
													if (tamVector > 0) {
														ContenidoTS c = new ContenidoTS(
																"vector",
																(String) tipo
																		.leeAtributo(),
																-1,
																-1,
																tamVector,
																null,
																10,
																null,
																tamVector * tam,
																Tabla_Actual,
																null);
														insertaEnTablaSiNoExisteId(
																tablaAux,
																(String) idAux
																		.leeAtributo(),
																c);
														ContenidoTS c1 = new ContenidoTS(
																(String) idAux
																		.leeAtributo(),
																"vector",
																-1,
																-1,
																tamVector,
																null,
																10,
																null,
																tamVector * tam,
																Tabla_Actual,
																null);
														insertaEnTablaSiNoExisteId(
																tablaAux,
																(String) auxToken2
																		.leeAtributo(),
																c1);
													}
													tipoTipo = false;

												} else if (tipoRango) {
													ContenidoTS c = new ContenidoTS(
															"vector",
															"rango",
															(Integer) posini
																	.leeAtributo(),
															(Integer) posfin
																	.leeAtributo(),
															tamVector, null,
															10, null,
															tamVector * 4,
															Tabla_Actual, null);
													insertaEnTablaSiNoExisteId(
															tablaAux,
															(String) idAux
																	.leeAtributo(),
															c);
													ContenidoTS c1 = new ContenidoTS(
															(String) idAux
																	.leeAtributo(),
															"vector", -1, -1,
															tamVector, null,
															10, null,
															tamVector * 4,
															Tabla_Actual, null);
													insertaEnTablaSiNoExisteId(
															tablaAux,
															(String) auxToken2
																	.leeAtributo(),
															c1);
													tipoRango = false;
												}

												else if (tipoBasico) {// Tipo
																		// basico
													String nombre = dameNombreTipo((Integer) tipo
															.leeAtributo());
													int tam = dameTamTipoBasico((Integer) tipo
															.leeAtributo());
													ContenidoTS c = new ContenidoTS(
															"vector", nombre,
															-1, -1, tamVector,
															null, 10, null,
															tam * tamVector,
															Tabla_Actual, null);
													insertaEnTablaSiNoExisteId(
															tablaAux,
															(String) idAux
																	.leeAtributo(),
															c);
													ContenidoTS c1 = new ContenidoTS(
															(String) idAux
																	.leeAtributo(),
															"vector", -1, -1,
															tamVector, null,
															10, null,
															tam * tamVector,
															Tabla_Actual, null);
													insertaEnTablaSiNoExisteId(
															tablaAux,
															(String) auxToken2
																	.leeAtributo(),
															c1);
													tipoBasico = false;
												}

												listaRangoIni.clear();
												listaRangoFin.clear();
												Tabla_Actual = Tabla_Actual
														.getSuperior();
												vector = false;
												System.out
														.println("Declaración correcta.");
												hayTipo = true;
												if (compruebaAtributo("\\n")) {
													compruebaSaltoLinea();
													objetos();
												} else if (!hayError) {
													error = new Error(5,
															token_act,
															hayError, consola);
													hayError = true;
												}

											} else if (!hayError) {
												error = new Error(6, token_act,
														hayError, consola);
												hayError = true;
											}
										} else if (!hayError) {
											error = new Error(7, token_act,
													hayError, consola);
											hayError = true;
										}
									} else if (!hayError) {
										error = new Error(8, token_act,
												hayError, consola);
										hayError = true;
									}
								} else if (!hayError) {
									error = new Error(9, token_act, hayError,
											consola);
									hayError = true;
								}
							} else {
								if (esTipoTipo(idAux)) {
									// if (estamosEnTipos){//Si declaramos tal
									// cual. Ej: ali = dani, donde dani es un
									// entero.
									ContenidoTS c = Tabla_Actual
											.getContenido((String) idAux
													.leeAtributo());
									ContenidoTS c1 = new ContenidoTS(
											(String) idAux.leeAtributo(),
											c.getTipo(), c.getPosIni(),
											c.getPosFin(), c.getNumArgs(),
											c.getTipoArgs(), c.getPasoArgs(),
											c.getRetorno(), c.getTamaño(), c
													.getTablaHija().Copia(),
											c.getPuntero());
									try {
										insertaEnTablaSiNoExisteId(
												Tabla_Actual,
												(String) auxToken2
														.leeAtributo(), c1);
										if (compruebaAtributo("\\n")) {
											compruebaSaltoLinea();
											objetos();
										} else if (!hayError) {
											error = new Error(5, token_act,
													hayError, consola);
											hayError = true;
										}
									} catch (Exception e3) {
									}
									// }
								} else if (!hayError) {
									error = new Error(105, idAux, hayError,
											consola);
									hayError = true;
								}
							}
						} else {
							Expresion e = new Expresion();
							if (tipo(e)) {
								System.out.println("Declaración correcta.");
								hayTipo = true;
								objetos();
							}
						}
					} else if (!hayError) {
						error = new Error(10, token_act, hayError, consola);
						hayError = true;
					}
				}
				if (!hayTipo && !hayError) {
					error = new Error(6, token_act, hayError, consola);
					hayError = true;
				}
			}
		} catch (Exception e1) {
			if (!hayError) {
				error = new Error(51, token_act, hayError, consola);
				hayError = true;
			}
			System.out.println("captura de excepciones en objetos");
		}
	}

	private void insertaEnTablaSiNoExisteId(TablaSimbolo t, String key,
			ContenidoTS c) {
		if (!t.existeId(key))
			t.anadeId1(key, c);
		else if (!hayError) {
			error = new Error(106, token_act, hayError, consola);
			hayError = true;
		}
	}

	private int dameTamañoVector() {
		int tamVector = 1;
		Iterator<String> it = Tabla_Actual.getTs().keySet().iterator();
		while (it.hasNext()) {
			tamVector = tamVector
					* Tabla_Actual.getTs().get(it.next()).getTamaño();
		}
		return tamVector;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R7: tipo → reg L campos freg - R8: tipo → rango - R9: tipo→ puntero a
	 * tipo - R10: tipo → nat - R11: tipo → ent - R12: tipo → real - R13: tipo →
	 * nat+ - R14: tipo → ent+ - R15: tipo → real+ - R16: tipo → nat∞ - R17:
	 * tipo → ent∞ - R18: tipo → real∞ - R19: tipo → booleano - R20: tipo → ( L
	 * valores ) - R21: tipo → num - R22: tipo → elemento - R23: tipo → car
	 * 
	 * @return devuelve verdadero si reconoce uno de los tipos anteriores, en
	 *         caso contrario falso.
	 *//*
	// ///////////////
	private boolean tipo(Expresion e) {
		boolean correcto = false;
		try {
			if (!hayError) {
				compruebaSaltoLinea();
				System.out.println("***Tipo***");
				ContenidoTS cTS;
				Token token_variable;
				Token tokenAux = token_act;
				if (leyendoRegistro)
					token_variable = auxToken;
				else
					token_variable = auxToken2;
				Token t_aux = token_act;

				if (esTipoTipo(token_act)) {// Si es de tipo declarado por el
											// usuario y esta en la tabla de
											// simbolos
					// Si declaramos tal cual. Ej: ali = dani, donde dani es un
					// entero. Si no NO funciona.

					if (!vector) {// Si no vengo de vector
						ContenidoTS c = Tabla_Actual
								.getContenido((String) token_act.leeAtributo());
						ContenidoTS c1 = new ContenidoTS(
								(String) token_act.leeAtributo(), c.getTipo(),
								c.getPosIni(), c.getPosFin(), c.getNumArgs(),
								c.getTipoArgs(), c.getPasoArgs(),
								c.getRetorno(), c.getTamaño(),
								c.getTablaHija(), c.getPuntero());
						try {
							if (esPuntero) {
								ContenidoTS contenido = new ContenidoTS(
										(String) token_act.leeAtributo(), null,
										-1, -1, 0, null, 10, null, 8, null,
										null);// Puntero
								listaPuntero.add(contenido);
								ContenidoTS cp = new ContenidoTS("puntero",
										(String) token_act.leeAtributo(), -1,
										-1, 0, null, 10, null, 8, null,
										listaPuntero);
								if (estamosEnTipos || leyendoRegistro)
									insertaEnTablaSiNoExisteId(Tabla_Actual,
											(String) token_variable
													.leeAtributo(), cp);
								else {
									tipoPuntero = true;
									contenidoAuxGeneral = c;
								}
								esPuntero = false;
								token_act = analizadorLexico.sigToken();
							} else if ((estamosEnTipos) || (leyendoRegistro)) {// Para
																				// tipos
																				// ftipos
								insertaEnTablaSiNoExisteId(Tabla_Actual,
										(String) token_variable.leeAtributo(),
										c1);
								token_act = analizadorLexico.sigToken();

								while (leyendoRegistro
										&& !listaVariables.isEmpty()) {// Registro
									// cTS= new
									// ContenidoTS(nombre,null,-1,-1,0,null,0,null,tam,null,null);
									insertaEnTablaSiNoExisteId(Tabla_Actual,
											listaVariables.poll(), c1);
								}
								// listaVariables.clear();
							} else {
								contenidoAuxGeneral = c1;
								token_act = analizadorLexico.sigToken();
								tipoTipo = true;
							}
						} catch (Exception e3) {
						}
						correcto = true;
					} else {
						correcto = true;// Si vengo de vector, ya lo añadire en
										// vector
						token_act = analizadorLexico.sigToken();
						tipoTipo = true;
					}

				}

				else if (compruebaTipo("identificador")) {
					System.out.println("Hemos leído identificador.");
					correcto = true;
					// buscar el identificador en la tabla de simbolos
					ContenidoTS c = Tabla_Actual.getContenido((String) t_aux
							.leeAtributo());
					// si esta e.tipo=identificador
					if (c != null)
						e.setTipo((String) t_aux.leeAtributo());
					// sino error
					else {
						e.setTipo("error_tipo");
						System.out
								.println("error de tipo metodo tipo (tipo no declarado)");
						if (e.getTipo().equals("error_tipo") && (!hayError)) {
							error = new Error(100, token_act, hayError, consola);
							hayError = true;
						}
					}
				} else if (esTipoBasico()) {// Los tipos basicos
					e.setTipo(dameNombreTipo((Integer) t_aux.leeAtributo()));
					if (!vector) {
						if (esPuntero) {
							String nombre = dameNombreTipo((Integer) tokenAux
									.leeAtributo());
							ContenidoTS contenido = new ContenidoTS(nombre,
									null, -1, -1, 0, null, 10, null, 8, null,
									null);// Puntero
							listaPuntero.add(contenido);
							ContenidoTS c = new ContenidoTS("puntero", nombre,
									-1, -1, 0, null, 10, null, 8, null,
									listaPuntero);
							if (estamosEnTipos || leyendoRegistro)
								insertaEnTablaSiNoExisteId(Tabla_Actual,
										(String) auxToken2.leeAtributo(), c);
							else {
								tipoPuntero = true;
								contenidoAuxGeneral = c;
							}
							esPuntero = false;
						} else if ((estamosEnTipos) || (leyendoRegistro)) { // Funciona
																			// si
																			// estamos
																			// en
																			// tipos
																			// ftipos
																			// o
																			// leyendo
																			// de
																			// registros
							String nombre = dameNombreTipo((Integer) tokenAux
									.leeAtributo());
							int tam = dameTamTipoBasico((Integer) tokenAux
									.leeAtributo());
							cTS = new ContenidoTS(nombre, null, -1, -1, 0,
									null, 10, null, tam, null, null);
							insertaEnTablaSiNoExisteId(Tabla_Actual,
									token_variable.leeAtributo().toString(),
									cTS);

							while (leyendoRegistro && !listaVariables.isEmpty()) {// Registro
								cTS = new ContenidoTS(nombre, null, -1, -1, 0,
										null, 10, null, tam, null, null);
								insertaEnTablaSiNoExisteId(Tabla_Actual,
										listaVariables.poll(), cTS);
							}
							// listaVariables.clear();
						} else {
							correcto = true;
							if (esPuntero) {
								tipoPuntero = true;
							} else
								tipoBasico = true;
						}
						System.out.println("Declaración correcta.");
						correcto = true;
					} else {
						correcto = true;
						if (esPuntero) {
							String nombre = dameNombreTipo((Integer) tokenAux
									.leeAtributo());
							ContenidoTS contenido = new ContenidoTS(nombre,
									null, -1, -1, 0, null, 10, null, 8, null,
									null);// Puntero
							listaPuntero.add(contenido);
							ContenidoTS c = new ContenidoTS("puntero", nombre,
									-1, -1, 0, null, 10, null, 8, null,
									listaPuntero);
							contenidoAuxGeneral = c;
							tipoPuntero = true;
						} else
							tipoBasico = true;
					}
				} else if (compruebaAtributo(45)) {// Rama de si es registro
					TablaSimbolo aux = Tabla_Actual;
					Tabla_Actual = new TablaSimbolo(aux); // creamos la nueva
															// tabla con la
															// superior como
															// padre
					cont++;
					System.out.println("Hemos leído reg.");
					leyendoRegistro = true;
					leyendoRegistro2 = true;
					compruebaSaltoLinea();
					tipoRegistro = true;
					Object lista = listaVariables.clone();
					listaVariables.clear();
					campos();
					compruebaSaltoLinea();
					if (compruebaAtributo(23)) {// Leemos freg
						int numCampos = Tabla_Actual.getTs().size();
						int tamSumaCampos = dameTamañoTabla(Tabla_Actual);
						if ((estamosEnTipos) || leyendoRegistro) {
							ContenidoTS contenido = new ContenidoTS("registro",
									null, -1, -1, numCampos, null, 10, null,
									tamSumaCampos, Tabla_Actual, null); // creamos
																		// el
																		// contenido
																		// con
																		// la TS
																		// hija
							// aux.anadeId1((String)token_variable.leeAtributo(),
							// contenido);
							insertaEnTablaSiNoExisteId(aux,
									(String) token_variable.leeAtributo(),
									contenido);// se lo aÃ±adimos a la superior

						}
						while (leyendoRegistro
								&& !((LinkedList<Object>) lista).isEmpty()) {// Registro
							ContenidoTS contenido = new ContenidoTS("registro",
									null, -1, -1, numCampos, null, 10, null,
									tamSumaCampos, Tabla_Actual, null); // creamos
																		// el
																		// contenido
																		// con
																		// la TS
																		// hija
							aux.anadeId1((String) ((LinkedList<Object>) lista)
									.poll(), contenido);// se lo aÃ±adimos a la
														// superior
						}

						Tabla_Actual = Tabla_Actual.getSuperior();
						System.out.println("Hemos leído freg.");
						e.setTipo("registro");
						cont--;
						if (cont == 0)
							leyendoRegistro = false;

					} else if (!hayError) {
						error = new Error(11, token_act, hayError, consola);
						hayError = true;
					}

					correcto = true;
				} else if (compruebaAtributo(41)) { // Rama de si es puntero
					System.out.println("Hemos leído puntero.");
					if (compruebaAtributo(1)) {
						System.out.println("Hemos leído a.");
						if (!esPuntero)
							listaPuntero = new LinkedList<ContenidoTS>();
						esPuntero = true;
						ContenidoTS contenido = new ContenidoTS("puntero a",
								null, -1, -1, 0, null, 10, null, 0, null, null);
						listaPuntero.add(contenido);
						Expresion e1 = new Expresion();
						tipo(e1);
						esPuntero = false;
						e.setTipo("puntero a " + e1.getTipo());
						System.out.println("Declaración correcta.");
						correcto = true;
					} else if (!hayError) {
						error = new Error(12, token_act, hayError, consola);
						hayError = true;
					}

				} else if (compruebaAtributo('(')) { // Rama de si es enumerado
					System.out.println("Es de tipo enumerado.");

					TablaSimbolo aux = Tabla_Actual;
					Tabla_Actual = new TablaSimbolo(aux);
					if (valores()) {
						if (compruebaAtributo(')')) {
							if ((estamosEnTipos) || (leyendoRegistro)) {
								int tamEnum = Tabla_Actual.getTs().size() * 8;
								cTS = new ContenidoTS("enumerado", null, -1,
										-1, Tabla_Actual.getTs().size(), null,
										10, null, tamEnum, Tabla_Actual, null);
								// aux.anadeId1(token_variable.leeAtributo().toString(),
								// cTS);
								insertaEnTablaSiNoExisteId(aux, token_variable
										.leeAtributo().toString(), cTS);
							} else {
								int tamEnum = Tabla_Actual.getTs().size() * 8;
								cTS = new ContenidoTS("enumerado", null, -1,
										-1, Tabla_Actual.getTs().size(), null,
										10, null, tamEnum, Tabla_Actual, null);
								contenidoAuxGeneral = cTS;
								tipoEnum = true;
							}
							System.out.println("Declaración correcta.");
							e.setTipo("enumerado");
							Tabla_Actual = Tabla_Actual.getSuperior();

							correcto = true;
						} else if (!hayError) {
							error = new Error(14, token_act, hayError, consola);
							hayError = true;
						}

					} else if (!hayError) {
						error = new Error(13, token_act, hayError, consola);
						hayError = true;
					}
				} else if (rango(false)) { // Rama de si es rango

					if (!vector) {// Funciona para tipos ftipos
						int tam = dameTamañoRango(
								(Integer) posfin.leeAtributo(),
								(Integer) posini.leeAtributo());
						// Pongo 4 porque quiero guardar solo posIni y posFin en
						// memoria y ambos son nat
						ContenidoTS cont = new ContenidoTS("rango", null,
								(Integer) posini.leeAtributo(),
								(Integer) posfin.leeAtributo(), tam, null, 10,
								null, 4, null, null);
						if (estamosEnTipos)// Tabla_Actual.anadeId1((String)auxToken2.leeAtributo(),
											// cont);
							insertaEnTablaSiNoExisteId(Tabla_Actual,
									(String) auxToken2.leeAtributo(), cont);
						else {
							contenidoAuxGeneral = cont;
							tipoRango = true;
						}
					} else
						tipoRango = true;
					System.out.println("Es de tipo rango");
					e.setTipo("rango");
					correcto = true;
				} else if (!hayError) {
					error = new Error(6, token_act, hayError, consola);
					hayError = true;
				}
			}
		} catch (Exception e1) {
			if (!hayError) {
				error = new Error(100, token_act, hayError, consola);
				hayError = true;
			}
			e.setTipo("error_tipo");
			System.out.println("captura de excepciones en tipo");
		}
		return correcto;
	}

	private int dameTamañoTabla(TablaSimbolo t) {
		int tamSumaCampos = 0;
		Iterator<String> it = t.getTs().keySet().iterator();
		while (it.hasNext()) {
			tamSumaCampos += t.getTs().get(it.next()).getTamaño();
		}
		return tamSumaCampos;
	}

	private boolean esTipoTipo(Token t) {
		try {
			if (Tabla_Actual.getContenido((String) t.leeAtributo()) != null) {
				return true;
			} else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R24: campos → campo [ , campos]
	 * 
	 * @param l
	 * 
	 *//*
	private void campos() {
		if (!hayError) {
			compruebaSaltoLinea();
			System.out.println("***Campos***");
			auxToken = token_act;
			if (!token_act.leeAtributo().equals(23)) {
				if (campo(new LinkedList<String>())) {
					if (compruebaAtributo(',') || compruebaAtributo("\\n")) {
						compruebaSaltoLinea();
						campos();
					}
				} else if (!hayError) {
					error = new Error(15, token_act, hayError, consola);
					hayError = true;
				}
			}
		}
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R25: campo → listaIdentificarores : tipo - R26: campo → (id [rangos])*
	 * de tipo
	 * 
	 * @param l
	 * 
	 * @return devuelve verdadero si evalua correctamente la regla anterior, y
	 *         falso en caso contrario.
	 *//*
	private boolean campo(LinkedList<String> l) {
		boolean correcto = false;
		try {
			if (!hayError) {
				System.out.println("***Campo***");
				Token id = token_act;
				if (compruebaTipo("identificador")) {
					l.add((String) id.leeAtributo());
					if (compruebaAtributo(':')) {
						Expresion e = new Expresion();
						if (tipo(e))
							correcto = true;
					} else if (compruebaAtributo(',')) {
						// Expresion e1= new Expresion();
						if (listaIdentificadores()) {
							if (compruebaAtributo(':')) {
								Expresion e = new Expresion();
								if (tipo(e))
									correcto = true;
							}
						}
					} else if (compruebaAtributo('[')) {
						esTipoRango = true;
						if (rangos(true)) {
							if (compruebaAtributo(']')) {
								if (compruebaAtributo(6)) {
									System.out.println("Hemos leído de.");
									Token tipo = token_act;
									esTipoRango = false;
									vector = true;
									Expresion e = new Expresion();
									if (tipo(e)) {
										int i = 0;
										int cont = 0;
										while (i < l.size()) {
											int posVector = 0;
											TablaSimbolo tablaAux = Tabla_Actual; // creamos
																					// la
																					// nueva
																					// tabla
																					// con
																					// la
																					// superior
																					// como
																					// padre
											Tabla_Actual = new TablaSimbolo(
													tablaAux);
											while ((cont < listaRangoIni.size())
													&& (!listaRangoIni
															.get(cont).equals(
																	"/"))) {
												int tam = dameTamañoRango(
														(Integer) listaRangoFin
																.get(cont),
														(Integer) listaRangoIni
																.get(cont));
												if (!hayError) {
													ContenidoTS conte = new ContenidoTS(
															"rango",
															l.get(i),
															(Integer) listaRangoIni
																	.get(cont),
															(Integer) listaRangoFin
																	.get(cont),
															0, null, 10, null,
															tam, null, null);
													Tabla_Actual
															.anadeId1(
																	String.valueOf(posVector),
																	conte);
													cont++;
													posVector++;

												}
											}

											int tamVector = dameTamañoVector();

											if (tipoTipo) {
												int tam = dameTamId(tipo);
												if (tam > 0) {
													ContenidoTS c = new ContenidoTS(
															"vector",
															(String) tipo
																	.leeAtributo(),
															-1, -1, tamVector,
															null, 10, null,
															tamVector * tam,
															Tabla_Actual, null);
													// tablaAux.anadeId1(l.get(i),
													// c);
													insertaEnTablaSiNoExisteId(
															tablaAux, l.get(i),
															c);
												}
											} else if (tipoPuntero) {
												ContenidoTS c = new ContenidoTS(
														"vector", "puntero",
														-1, -1, tamVector,
														null, 10, null,
														tamVector * 8,
														Tabla_Actual,
														listaPuntero);
												// tablaAux.anadeId1(l.get(i),
												// c);
												insertaEnTablaSiNoExisteId(
														tablaAux, l.get(i), c);
											} else if (tipoRango) {
												ContenidoTS c = new ContenidoTS(
														"vector", "rango",
														(Integer) posini
																.leeAtributo(),
														(Integer) posfin
																.leeAtributo(),
														tamVector, null, 10,
														null, tamVector * 4,
														Tabla_Actual, null);
												// tablaAux.anadeId1(l.get(i),
												// c);
												insertaEnTablaSiNoExisteId(
														tablaAux, l.get(i), c);
											} else if (tipoBasico) {
												String nombre = dameNombreTipo((Integer) tipo
														.leeAtributo());
												int tam = dameTamTipoBasico((Integer) tipo
														.leeAtributo());
												ContenidoTS c = new ContenidoTS(
														"vector", nombre, -1,
														-1, tamVector, null,
														10, null, tamVector
																* tam,
														Tabla_Actual, null);
												// tablaAux.anadeId1(l.get(i),
												// con);
												insertaEnTablaSiNoExisteId(
														tablaAux, l.get(i), c);
											}
											i++;

											if ((cont < listaRangoIni.size())
													&& (listaRangoIni.get(cont)
															.equals("/")))
												cont++;
											Tabla_Actual = Tabla_Actual
													.getSuperior();
										}

										listaRangoIni.clear();
										listaRangoFin.clear();
										tipoTipo = false;
										tipoRango = false;
										tipoBasico = false;
										tipoPuntero = false;
										vector = false;
										System.out
												.println("Declaración correcta.");
										hayTipo = true;
										correcto = true;
									}
								} else {
									while (compruebaAtributo(',')) {
										Token idAux = token_act;
										if (compruebaTipo("identificador"))
											if (compruebaAtributo('[')) {
												listaRangoIni.add("/");
												listaRangoFin.add("/");
												if (rangos(true)) {
													if (!compruebaAtributo(']')
															&& !hayError) {
														error = new Error(8,
																token_act,
																hayError,
																consola);
														hayError = true;
													} else
														l.add((String) idAux
																.leeAtributo());
												} else if (!hayError) {
													error = new Error(9,
															token_act,
															hayError, consola);
													hayError = true;
												}
											} else if (!hayError) {
												error = new Error(16,
														token_act, hayError,
														consola);
												hayError = true;
											} else if (!hayError) {
												error = new Error(3, token_act,
														hayError, consola);
												hayError = true;
											}

									}// fin while
									if (compruebaAtributo(6)) {
										System.out.println("Hemos leído de.");
										Token tipo = token_act;
										esTipoRango = false;
										vector = true;
										Expresion e = new Expresion();
										if (tipo(e)) {
											int i = 0;
											int cont = 0;
											while (i < l.size()) {
												int posVector = 0;
												TablaSimbolo tablaAux = Tabla_Actual; // creamos
																						// la
																						// nueva
																						// tabla
																						// con
																						// la
																						// superior
																						// como
																						// padre
												Tabla_Actual = new TablaSimbolo(
														tablaAux);

												while ((cont < listaRangoIni
														.size())
														&& (!listaRangoIni.get(
																cont).equals(
																"/"))) {
													int tam = dameTamañoRango(
															(Integer) listaRangoFin
																	.get(cont),
															(Integer) listaRangoIni
																	.get(cont));
													if (!hayError) {
														ContenidoTS conte = new ContenidoTS(
																"rango",
																l.get(i),
																(Integer) listaRangoIni
																		.get(cont),
																(Integer) listaRangoFin
																		.get(cont),
																0, null, 10,
																null, tam,
																null, null);
														Tabla_Actual
																.anadeId1(
																		String.valueOf(posVector),
																		conte);
														cont++;
														posVector++;
													}
												}

												int tamVector = dameTamañoVector();

												if (tipoTipo) {
													int tam = dameTamId(tipo);
													if (tam > 0) {
														ContenidoTS c = new ContenidoTS(
																"vector",
																(String) tipo
																		.leeAtributo(),
																-1,
																-1,
																tamVector,
																null,
																10,
																null,
																tamVector * tam,
																Tabla_Actual,
																null);
														// tablaAux.anadeId1(l.get(i),
														// c);
														insertaEnTablaSiNoExisteId(
																tablaAux,
																l.get(i), c);
													}
												} else if (tipoRango) {
													ContenidoTS c = new ContenidoTS(
															"vector",
															"rango",
															(Integer) posini
																	.leeAtributo(),
															(Integer) posfin
																	.leeAtributo(),
															tamVector, null,
															10, null,
															tamVector * 4,
															Tabla_Actual, null);
													// tablaAux.anadeId1(l.get(i),
													// c);
													insertaEnTablaSiNoExisteId(
															tablaAux, l.get(i),
															c);
													añadirRango = false;
												} else if (tipoPuntero) {
													ContenidoTS c = new ContenidoTS(
															"vector",
															"puntero", -1, -1,
															tamVector, null,
															10, null,
															tamVector * 8,
															Tabla_Actual,
															listaPuntero);
													// tablaAux.anadeId1(l.get(i),
													// c);
													insertaEnTablaSiNoExisteId(
															tablaAux, l.get(i),
															c);
												} else if (tipoBasico) {
													String nombre = dameNombreTipo((Integer) tipo
															.leeAtributo());
													int tam = dameTamTipoBasico((Integer) tipo
															.leeAtributo());
													ContenidoTS c = new ContenidoTS(
															"vector", nombre,
															-1, -1, tamVector,
															null, 10, null,
															tamVector * tam,
															Tabla_Actual, null);
													// tablaAux.anadeId1(l.get(i),
													// con);
													insertaEnTablaSiNoExisteId(
															tablaAux, l.get(i),
															c);
												}
												i++;

												if ((cont < listaRangoIni
														.size())
														&& (listaRangoIni
																.get(cont)
																.equals("/")))
													cont++;
												Tabla_Actual = Tabla_Actual
														.getSuperior();
											}

											listaRangoIni.clear();
											listaRangoFin.clear();
											tipoTipo = false;
											tipoRango = false;
											tipoPuntero = false;
											tipoBasico = false;
											vector = false;
											System.out
													.println("Declaración correcta.");
											hayTipo = true;
											correcto = true;
										}
									}
								}// fin else
							} else if (!hayError) {
								error = new Error(8, token_act, hayError,
										consola);
								hayError = true;
							}
						}
					} else if (!hayError) {
						error = new Error(16, token_act, hayError, consola);
						hayError = true;
					}
				}
			}
		} catch (Exception e1) {
			if (!hayError) {
				error = new Error(52, token_act, hayError, consola);
				hayError = true;
			}
			System.out.println("captura de excepcion en campos");
		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R27 : listaIdentificadores → id (, id)
	 * 
	 * @param e1
	 * @param l
	 *            *
	 * 
	 * @return devuelve verdadero si la lectura de tokens reconoce la estructura
	 *         de lista de identificadores, en caso contrario devuelve falso.
	 *//*
	private boolean listaIdentificadores() {
		boolean correcto = false;
		if (!hayError) {
			System.out.println("***Lista Identificadores***");
			while (!correcto) {
				listaVariables.add((String) token_act.leeAtributo());
				if (compruebaTipo("identificador")) {
					System.out.println("Hemos leído identificador.");
					if (!compruebaAtributo(','))
						correcto = true;
				}
			}
		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R28: rangos → rango [, rangos]
	 * 
	 * @return devuelve verdadero si la lectura de tokens reconoce la estructura
	 *         de rango o rangos, en caso contrario devuelve falso.
	 *//*
	// Añadido boolean lanzaError para distinguir si debe o no lanzar error
	private boolean rangos(boolean lanzaError) {
		boolean correcto = false;
		if (!hayError) {
			System.out.println("***Rangos***");
			if (rango(lanzaError)) {

				correcto = true;
				while (compruebaAtributo(','))
					correcto = rango(lanzaError);
			} else {
				correcto = false;
				// if(lanzaError)
				// error=new Error(9,token_act,hayError,consola);
			}

		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática (enumerados):
	 * 
	 * - R29: valores → id ( , id )*
	 * 
	 * @return
	 *//*
	private boolean valores() {
		int cont = 0;
		boolean correcto = false;
		if (!hayError) {
			System.out.println("***Valores***");
			while (!correcto) {
				Token tokenAux = token_act;
				if (compruebaTipo("identificador")) {
					ContenidoTS c = new ContenidoTS(
							(String) auxToken2.leeAtributo(), "enumerado",
							cont, -1, 0, null, 10, null, 8, null, null);
					// Tabla_Actual.anadeId1((String)tokenAux.leeAtributo(),c);
					insertaEnTablaSiNoExisteId(Tabla_Actual,
							(String) tokenAux.leeAtributo(), c);
					cont++;
					System.out.println("Hemos leído identificador.");
					if (!compruebaAtributo(','))
						correcto = true;
				}
			}
		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R30: declaracion → listaIdentificadores : tipo [, declaracion] - R31:
	 * declaracion → (id [ rangos ])* de tipo [, declaracion] las expresiones
	 * ctes o bien tipos=>listas
	 *//*
	private void declaracion(LinkedList<String> l, LinkedList<String> lis) {
		try {
			if (!hayError) {
				compruebaSaltoLinea();
				System.out.println("***Declaración***");
				Token id = token_act;
				auxToken2 = token_act;
				if (compruebaTipo("identificador")) {
					Expresion e5 = new Expresion("", (String) id.leeAtributo(),
							"", "");
					l.add((String) id.leeAtributo());
					System.out.println("Hemos leído identificador.");
					while (compruebaAtributo(',')) {
						l.add((String) token_act.leeAtributo());
						if (!compruebaTipo("identificador") && !hayError) {
							error = new Error(3, token_act, hayError, consola);
							hayError = true;
						}
						System.out.println("Hemos leído identificador.");
					}
					if (compruebaAtributo(':')) {
						Token tipo = token_act;
						// boolean
						// esId=(compruebaTipoNoAvanzado("identificador"));
						Expresion e = new Expresion();
						estamosDeclaracion = true;

						listaVariables = (LinkedList<String>) l.clone();
						listaVariables.removeFirst();

						if (tipo(e)) {
							int i = 0;
							while (i < l.size()) {
								if (tipoTipo) {
									// Tabla_Actual.anadeId1(l.get(i),
									// contenidoAuxGeneral);
									insertaEnTablaSiNoExisteId(Tabla_Actual,
											l.get(i), contenidoAuxGeneral);
									lis.add((String) tipo.leeAtributo());
								} else if (tipoRegistro) {
									
									 * l.removeFirst();
									 * listaVariables=(LinkedList<String>)
									 * l.clone(); /*int tamSumaCampos =
									 * dameTamañoTabla(tablaAuxGeneral); int
									 * numCampos =
									 * tablaAuxGeneral.getTs().size();
									 * ContenidoTS c= new
									 * ContenidoTS("registro",
									 * null,-1,-1,numCampos
									 * ,null,10,null,tamSumaCampos
									 * ,tablaAuxGeneral,null);
									 * //Tabla_Actual.anadeId1(l.get(i), c);
									 * insertaEnTablaSiNoExisteId
									 * (Tabla_Actual,l.get(i), c);
									 * lis.add("registro");
									 * estamosDeclaracion=false;
									 
								} else if (tipoRango) {
									// Tabla_Actual.anadeId1(l.get(i),
									// contenidoAuxGeneral);
									insertaEnTablaSiNoExisteId(Tabla_Actual,
											l.get(i), contenidoAuxGeneral);
									lis.add("rango");
								} else if (tipoPuntero) {
									// Tabla_Actual.anadeId1(l.get(i),
									// contenidoAuxGeneral);
									insertaEnTablaSiNoExisteId(Tabla_Actual,
											l.get(i), contenidoAuxGeneral);
									lis.add("puntero");
								} else if (tipoEnum) {
									// Tabla_Actual.anadeId1(l.get(i),
									// contenidoAuxGeneral);
									insertaEnTablaSiNoExisteId(Tabla_Actual,
											l.get(i), contenidoAuxGeneral);
									lis.add("enumerado");
								} else if (tipoBasico) {// TipoBasico
									String nombre = dameNombreTipo((Integer) tipo
											.leeAtributo());
									int tam = dameTamTipoBasico((Integer) tipo
											.leeAtributo());
									ContenidoTS c = new ContenidoTS(nombre,
											null, -1, -1, 0, null, 10, null,
											tam, null, null);
									// Tabla_Actual.anadeId1(l.get(i), c);
									insertaEnTablaSiNoExisteId(Tabla_Actual,
											l.get(i), c);
									lis.add(nombre);
								}
								i++;
							}
							tipoTipo = false;
							tipoRegistro = false;
							tipoBasico = false;
							tipoRango = false;
							tipoPuntero = false;
							tipoEnum = false;

							while (!l.isEmpty())
								l.removeFirst();
							System.out.println("Declaración correcta.");
							hayTipo = true;
							hayComa = false;
							// if(esId)token_act = analizadorLexico.sigToken();
							if (compruebaAtributo(',')) {
								hayTipo = false;
								hayComa = true;
								declaracion(l, lis);
							} else if (compruebaAtributo("\\n")) {
								compruebaSaltoLinea();
								declaracion(l, lis);
							}
						}
					} else if (compruebaAtributo('[')) {
						esTipoRango = true;
						if (rangos(true)) {
							if (compruebaAtributo(']')) {
								while (compruebaAtributo(',')) {
									Token idAux = token_act;
									if (compruebaTipo("identificador"))
										if (compruebaAtributo('[')) {
											listaRangoIni.add("/");
											listaRangoFin.add("/");
											if (rangos(true)) {
												if (!compruebaAtributo(']')
														&& !hayError) {
													error = new Error(8,
															token_act,
															hayError, consola);
													hayError = true;
												} else
													l.add((String) idAux
															.leeAtributo());
											} else if (!hayError) {
												error = new Error(9, token_act,
														hayError, consola);
												hayError = true;
											}
										} else if (!hayError) {
											error = new Error(16, token_act,
													hayError, consola);
											hayError = true;
										} else if (!hayError) {
											error = new Error(3, token_act,
													hayError, consola);
											hayError = true;
										}
								}
								if (compruebaAtributo(6)) {
									System.out.println("Hemos leído de.");
									Token tipo = token_act;
									// boolean
									// esId=compruebaTipoNoAvanzado("identificador");
									esTipoRango = false;
									vector = true;
									Expresion e = new Expresion();
									if (tipo(e)) {
										int i = 0;
										int cont = 0;
										while (i < l.size()) {
											int posVector = 0;
											TablaSimbolo tablaAux = Tabla_Actual; // creamos
																					// la
																					// nueva
																					// tabla
																					// con
																					// la
																					// superior
																					// como
																					// padre
											Tabla_Actual = new TablaSimbolo(
													tablaAux);
											while ((cont < listaRangoIni.size())
													&& (!listaRangoIni
															.get(cont).equals(
																	"/"))) {
												int tam = dameTamañoRango(
														(Integer) listaRangoFin
																.get(cont),
														(Integer) listaRangoIni
																.get(cont));
												ContenidoTS conte = new ContenidoTS(
														"rango", l.get(i),
														(Integer) listaRangoIni
																.get(cont),
														(Integer) listaRangoFin
																.get(cont), 0,
														null, 10, null, tam,
														null, null);
												Tabla_Actual.anadeId1(String
														.valueOf(posVector),
														conte);
												cont++;
												posVector++;
											}
											int tamVector = dameTamañoVector();
											if (tipoTipo) {
												int tam = dameTamId(tipo);
												ContenidoTS c = new ContenidoTS(
														"vector",
														(String) tipo
																.leeAtributo(),
														-1, -1, tamVector,
														null, 10, null,
														tamVector * tam,
														Tabla_Actual, null);
												// tablaAux.anadeId1(l.get(i),
												// c);
												insertaEnTablaSiNoExisteId(
														tablaAux, l.get(i), c);
												lis.add((String) tipo
														.leeAtributo());
											} else if (tipoRango) {
												ContenidoTS c = new ContenidoTS(
														"vector", "rango",
														(Integer) posini
																.leeAtributo(),
														(Integer) posfin
																.leeAtributo(),
														tamVector, null, 10,
														null, tamVector * 4,
														Tabla_Actual, null);
												// tablaAux.anadeId1(l.get(i),
												// c);
												insertaEnTablaSiNoExisteId(
														tablaAux, l.get(i), c);
												lis.add("rango");
											} else if (tipoPuntero) {
												ContenidoTS c = new ContenidoTS(
														"vector", "puntero",
														-1, -1, tamVector,
														null, 10, null,
														tamVector * 8,
														Tabla_Actual,
														listaPuntero);
												// tablaAux.anadeId1(l.get(i),
												// c);
												insertaEnTablaSiNoExisteId(
														tablaAux, l.get(i), c);
												lis.add("puntero");
											} else if (tipoEnum) {
												// ¿Como le paso la lista del
												// enumerado?
												ContenidoTS c = new ContenidoTS(
														"vector", "enumerado",
														-1, -1, tamVector,
														null, 10, null,
														tamVector * 8,
														Tabla_Actual, null);
												// tablaAux.anadeId1(l.get(i),
												// c);
												insertaEnTablaSiNoExisteId(
														tablaAux, l.get(i), c);
												lis.add("puntero");
											} else if (tipoBasico) {
												String nombre = dameNombreTipo((Integer) tipo
														.leeAtributo());
												int tam = dameTamTipoBasico((Integer) tipo
														.leeAtributo());
												ContenidoTS c = new ContenidoTS(
														"vector", nombre, -1,
														-1, tamVector, null,
														10, null, tamVector
																* tam,
														Tabla_Actual, null);
												// tablaAux.anadeId1(l.get(i),
												// c);
												insertaEnTablaSiNoExisteId(
														tablaAux, l.get(i), c);
												lis.add(nombre);
											}
											i++;

											if ((cont < listaRangoIni.size())
													&& (listaRangoIni.get(cont)
															.equals("/")))
												cont++;
											Tabla_Actual = Tabla_Actual
													.getSuperior();
										}
										tipoTipo = false;
										tipoRango = false;
										tipoBasico = false;
										tipoPuntero = false;
										tipoEnum = false;

										listaRangoIni.clear();
										listaRangoFin.clear();
										vector = false;
										while (!l.isEmpty())
											l.removeFirst();

										System.out
												.println("Declaración correcta.");
										hayTipo = true;
										hayComa = false;
										if (compruebaAtributo(',')) {
											hayTipo = false;
											hayComa = true;
											declaracion(l, lis);
										} else if (compruebaAtributo("\\n")) {
											compruebaSaltoLinea();
											declaracion(l, lis);
										}
									}
								} else if (compruebaAtributo(',')) {
									hayComa = true;
									declaracion(l, lis);
								}
							} else if (!hayError) {
								error = new Error(8, token_act, hayError,
										consola);
								hayError = true;
							}
						} else if (compruebaAtributoNoAvanzado(']'))
							transicionDecInst(e5);
						*//**
						 * } else if(!hayError) error=new
						 * Error(16,token_act,hayError,consola); } else if
						 * (compruebaAtributoNoAvanzado(']'))
						 * transicionDecInst();
						 *//*
					} else if (compruebaAtributo(',')) {
						hayComa = true;
						declaracion(l, lis);
					} else {
						hayTipo = true;
						transicionDecInst(e5);
					}
				}
				if (hayComa) {
					hayComa = false;
					if (!hayError) {
						error = new Error(16, token_act, hayError, consola);
						hayError = true;
					}
				}
				if (!hayTipo) {
					hayTipo = false;
					if (!hayError) {
						error = new Error(16, token_act, hayError, consola);
						hayError = true;
					}
				}
			}
		} catch (Exception e1) {
			if (!hayError) {
				error = new Error(100, token_act, hayError, consola);
				hayError = true;
			}
			System.out.println("captura de excepciones en declaracion");
		}
	}

	*//**
	 * Comprobamos la tansición de Declaraciones a Instrucciones
	 *//*
	private void transicionDecInst(Expresion e) {
		try {
			transita = true;
			if (!hayError) {
				System.out.println("***transicionDecInst***");
				if (!hayComa) {
					if ((token_act.leeAtributo() != null)
							&& compruebaAtributo('(')) {
						System.out.println("=>=>=>Transitamos a Metodo***");
						Expresion e1 = new Expresion();
						e1.setLugar(e.getLugar());
						metodo(e1, null, null);
						if (e1.getTipo().equals("error_tipo") && !hayError) {
							error = new Error(100, token_act, hayError, consola);
							hayError = true;
						}
					} else {
						if (token_act.leeTipo().compareTo("OP_ASIG") == 0
								|| compruebaAtributo(']')) {
							System.out
									.println("=>=>=>Transitamos a Asignación<=<=<=");
							Expresion e1 = new Expresion();
							e1.setLugar(e.getLugar());
							// /expresion de tipos de la variable de la izq
							ContenidoTS c = Tabla_Actual
									.getContenido((String) auxToken2
											.leeAtributo());
							if (c != null) {
								if (c.getTipo().equals("vector"))
									e1.setTipo(c.getTipoBase());
								else
									e1.setTipo(c.getTipo());

							}

							else {
								e1.setTipo("error_tipo");
							}
							asignacion(e1);

						} else {

							System.out
									.println("=>=>=>Transitamos a expIdConIdLeido<=<=<=");
							Expresion e1 = new Expresion();
							e1.setLugar(e.getLugar());

							// /expresion de tipos de la variable de la izq
							ContenidoTS c = Tabla_Actual
									.getContenido((String) auxToken2
											.leeAtributo());
							if (c != null) {
								if (c.getTipo().equals("vector"))
									e1.setTipo(c.getTipoBase());
								else
									e1.setTipo(c.getTipo());
							} else {
								e1.setTipo("error_tipo");
							}

							expIdConIdLeido(e1, hayError);
							System.out
									.println("=>=>=>Transitamos a Asignación<=<=<=");
							asignacion(e1);
						}
					}
				} else if (!hayError)
					error = new Error(16, token_act, hayError, consola);
			}
		} catch (Exception e2) {
			if (!hayError) {
				error = new Error(100, token_act, hayError, consola);
				hayError = true;
			}
			System.out.println("captura de excepcion en metodo transdecinst");
		}
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R32: bloque → función [bloque] - R33: bloque → procedimiento [bloque]
	 * 
	 *//*
	private void bloque() {
		if (!hayError) {
			compruebaSaltoLinea();
			System.out.println("***Bloque***");
			if (compruebaAtributo(26)) {
				if (funcion())
					bloque();
			} else if (compruebaAtributo(39))
				if (procedimiento())
					bloque();
		}
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R34: función → fun id N ( entradas ) dev salida cuerpo ffun
	 * 
	 * @return devuelve verdadero si tiene una declaración de función valida, en
	 *         caso contrario devuelve falso.
	 *//*
	private boolean funcion() {
		boolean correcto = false;
		String siguiente = etiquetaNueva();
		if (!hayError) {
			System.out.println("***Función***");
			Token id = token_act;
			codigo += id.leeAtributo() + " :" + "\n";
			if (compruebaTipo("identificador")) {
				System.out.println("Hemos leído identificador.");
				if (compruebaAtributo('(')) {
					TablaSimbolo aux = Tabla_Actual;
					Tabla_Actual = new TablaSimbolo(aux); // creamos la nueva
															// tabla con la
															// superior como
															// padre
					LinkedList<String> l = new LinkedList<String>();
					entradas(l);
					if (compruebaAtributo(')')) {
						if (compruebaAtributo(7)) {
							System.out.println("Hemos leído dev.");
							LinkedList<String> lis = new LinkedList<String>();
							if (salida(lis)) {
								int tam = dameTamañoTabla(Tabla_Actual);
								ContenidoTS contenido = new ContenidoTS(
										"funcion", null, -1, -1, l.size(), l,
										10, lis, tam, Tabla_Actual, null); // creamos
																			// el
																			// contenido
																			// con
																			// la
																			// TS
																			// hija
								
								insertaEnTablaSiNoExisteId(aux,
										(String) id.leeAtributo(), contenido);
								cuerpo();
								codigo += "return" + "\n";
							} else if (!hayError) {
								error = new Error(19, token_act, hayError,
										consola);
								hayError = true;
							}

						} else if (!hayError) {
							error = new Error(17, token_act, hayError, consola);
							hayError = true;
						}

						if (compruebaAtributo(18)) {// Hemos leido ffun
							correcto = true;
							int nuevoTamArg = aux.getTs()
									.get((String) id.leeAtributo())
									.getTablaHija().getTs().size();
							aux.getTs().get((String) id.leeAtributo())
									.setNumArgs(nuevoTamArg);
							int nuevotamTabla = dameTamañoTabla(aux.getTs()
									.get((String) id.leeAtributo())
									.getTablaHija());
							aux.getTs().get((String) id.leeAtributo())
									.setTamaño(nuevotamTabla);
							Tabla_Actual = Tabla_Actual.getSuperior();
							System.out.println("Hemos leído ffun.");
						} else {
							if (!hayError) {
								error = new Error(18, token_act, hayError,
										consola);
								hayError = true;
							}
						}
					} else if (!hayError) {
						error = new Error(14, token_act, hayError, consola);
						hayError = true;
					}
				} else if (!hayError) {
					error = new Error(20, token_act, hayError, consola);
					hayError = true;
				}
			} else if (!hayError) {
				error = new Error(3, token_act, hayError, consola);
				hayError = true;
			}
		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R35: procedimiento → proc id N ( entradas ) cuerpo fproc
	 * 
	 * @return devuelve verdadero si tiene una declaración de procedimiento
	 *         valida, en caso contrario devuelve falso.
	 *//*
	private boolean procedimiento() {
		boolean correcto = false;
		String siguiente = etiquetaNueva();
		if (!hayError) {
			System.out.println("***Procedimiento***");
			Token auxiliar = token_act; // guardamos el token antes de que
										// avance
			codigo += auxiliar.leeAtributo() + " :" + "\n";
			if (compruebaTipo("identificador")) {
				TablaSimbolo aux = Tabla_Actual;
				Tabla_Actual = new TablaSimbolo(aux); // creamos la nueva tabla
														// con la superior como
														// padre
				LinkedList<String> l = new LinkedList<String>();
				System.out.println("Hemos leído identificador");
				if (compruebaAtributo('(')) {
					auxToken2 = token_act;
					entradas(l);
					if (compruebaAtributo(')')) {
						int tam = dameTamañoTabla(Tabla_Actual);
						ContenidoTS contenido = new ContenidoTS(
								"procedimiento", null, -1, -1, Tabla_Actual
										.getTs().size(), l, 10, null, tam,
								Tabla_Actual, null); // creamos el contenido con
														// la TS hija
						// aux.anadeId1((String)auxiliar.leeAtributo(),
						// contenido);
						insertaEnTablaSiNoExisteId(aux,
								(String) auxiliar.leeAtributo(), contenido);// se
																			// lo
																			// aÃ±adimos
																			// a
																			// la
																			// superior
						cuerpo();
						codigo += "return" + "\n";
						if (compruebaAtributo(21)) {// Hemos leido fproc
							correcto = true;
							System.out.println("Hemos leído fproc");
							// ContenidoTS c=
							// Tabla_Actual.getContenido("diametro");
							int nuevoTamArg = aux.getTs()
									.get((String) auxiliar.leeAtributo())
									.getTablaHija().getTs().size();
							aux.getTs().get((String) auxiliar.leeAtributo())
									.setNumArgs(nuevoTamArg);
							int nuevotamTabla = dameTamañoTabla(aux.getTs()
									.get((String) auxiliar.leeAtributo())
									.getTablaHija());
							aux.getTs().get((String) auxiliar.leeAtributo())
									.setTamaño(nuevotamTabla);
							Tabla_Actual = Tabla_Actual.getSuperior();
						} else {
							if (!hayError) {
								error = new Error(21, token_act, hayError,
										consola);
								hayError = true;
							}
						}
					} else if (!hayError) {
						error = new Error(14, token_act, hayError, consola);
						hayError = true;
					}
				} else if (!hayError) {
					error = new Error(20, token_act, hayError, consola);
					hayError = true;
				}
			} else if (!hayError) {
				error = new Error(3, token_act, hayError, consola);
				hayError = true;
			}
		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R36: entradas → entrada [, entradas ]
	 * 
	 *//*
	private void entradas(LinkedList<String> l) {
		if (!hayError) {
			boolean e;
			System.out.println("***Entradas***");
			e = entrada(l);
			if (e) {
				while (compruebaAtributo(',')) {
					hayComa = true;
					entradas(l);
				}
			}
			if (hayComa && !e && !hayError) {
				error = new Error(22, token_act, hayError, consola);
				hayComa = false;
				hayError = true;
			}
		}
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R37: entrada → [e | e/s | s] listaIdentificadores : tipo - R38: entrada
	 * → (id [rangos])* de tipo ¿ [e | e/s | s] VARIABLES POR VALOR /REFERENCIA?
	 * 
	 * @return devuelve verdadero si comprueba la que hay una declaración
	 *         correcta de etrada, en caso contrario devuelve falso.
	 *//*
	private boolean entrada(LinkedList<String> l) {
		boolean correcto = false;
		boolean tipoArgs = false;
		try {
			LinkedList<String> identificadores = new LinkedList<String>();
			if (!hayError) {
				System.out.println("***Entrada***");
				// // guardamos el token auxiliar para despues
				Token argumento = token_act;
				if (compruebaAtributo(9) || (compruebaAtributo(10))
						|| (compruebaAtributo(46))) {
					tipoArgs = true;
					System.out.println("Hemos leído e, e/s o s.");
				}
				System.out.println(token_act.toString());
				Token id = token_act;
				if (compruebaTipo("identificador")) {
					identificadores.add((String) id.leeAtributo());
					System.out.println("Hemos leído identificador.");
					while (compruebaAtributo(',')) {
						identificadores.add((String) token_act.leeAtributo());
						if (!compruebaTipo("identificador") && !hayError) {
							error = new Error(3, token_act, hayError, consola);
							hayError = true;
						}
						System.out.println("Hemos leído identificador.");
					}
					if (compruebaAtributo(':')) {
						Token tipo = token_act;
						Expresion e = new Expresion();
						if (tipo(e)) {

							if (tipoTipo)
								l.add((String) tipo.leeAtributo());
							else if (tipoRango)
								l.add("rango");
							else if (tipoPuntero)
								l.add("puntero");
							else if (tipoEnum)
								l.add("enumerado");
							else if (tipoBasico) {
								String nombre = dameNombreTipo((Integer) tipo
										.leeAtributo());
								l.add(nombre);
							}

							if (tipoArgs) {// ¿?¿?¿
								int i = 0;
								while (i < identificadores.size()) {
									if (tipoTipo) {
										ContenidoTS c = Tabla_Actual
												.getContenido((String) token_act
														.leeAtributo());
										ContenidoTS c1 = new ContenidoTS(
												(String) token_act
														.leeAtributo(),
												c.getTipoBase(), c.getPosIni(),
												c.getPosFin(), c.getNumArgs(),
												c.getTipoArgs(),
												(Integer) argumento
														.leeAtributo(), c
														.getRetorno(), c
														.getTamaño(), c
														.getTablaHija(), c
														.getPuntero());
										// Tabla_Actual.anadeId1(identificadores.get(i),
										// c1);
										insertaEnTablaSiNoExisteId(
												Tabla_Actual,
												identificadores.get(i), c1);
										if (i > 0)
											l.add((String) tipo.leeAtributo());
									} else if (tipoRango) {
										contenidoAuxGeneral
												.setPasoArgs((Integer) argumento
														.leeAtributo());
										// Tabla_Actual.anadeId1(identificadores.get(i),
										// contenidoAuxGeneral);
										insertaEnTablaSiNoExisteId(
												Tabla_Actual,
												identificadores.get(i),
												contenidoAuxGeneral);
										if (i > 0)
											l.add("rango");
									} else if (tipoPuntero) {
										contenidoAuxGeneral
												.setPasoArgs((Integer) argumento
														.leeAtributo());
										// Tabla_Actual.anadeId1(identificadores.get(i),
										// contenidoAuxGeneral);
										insertaEnTablaSiNoExisteId(
												Tabla_Actual,
												identificadores.get(i),
												contenidoAuxGeneral);
										if (i > 0)
											l.add("puntero");

									} else if (tipoEnum) {
										contenidoAuxGeneral
												.setPasoArgs((Integer) argumento
														.leeAtributo());
										// Tabla_Actual.anadeId1(identificadores.get(i),
										// contenidoAuxGeneral);
										insertaEnTablaSiNoExisteId(
												Tabla_Actual,
												identificadores.get(i),
												contenidoAuxGeneral);
										if (i > 0)
											l.add("enumerado");

									} else if (tipoBasico) {// TipoBasico
										String nombre = dameNombreTipo((Integer) tipo
												.leeAtributo());
										int tam = dameTamTipoBasico((Integer) tipo
												.leeAtributo());
										ContenidoTS c = new ContenidoTS(nombre,
												null, -1, -1, 0, null,
												(Integer) argumento
														.leeAtributo(), null,
												tam, null, null);
										// Tabla_Actual.anadeId1(identificadores.get(i),
										// c);
										insertaEnTablaSiNoExisteId(
												Tabla_Actual,
												identificadores.get(i), c);
										if (i > 0)
											l.add(nombre);
									}
									// if(i>0)l.add((Integer)tipo.leeAtributo());
									i++;
								}
								tipoTipo = false;
								tipoBasico = false;
								tipoRango = false;
								tipoPuntero = false;
								tipoEnum = false;
							} else {
								int i = 0;
								while (i < identificadores.size()) {
									if (tipoTipo) {
										// Tabla_Actual.anadeId1(identificadores.get(i),
										// contenidoAuxGeneral);
										insertaEnTablaSiNoExisteId(
												Tabla_Actual,
												identificadores.get(i),
												contenidoAuxGeneral);
										if (i > 0)
											l.add((String) tipo.leeAtributo());
									} else if (tipoRango) {
										// Tabla_Actual.anadeId1(identificadores.get(i),
										// contenidoAuxGeneral);
										insertaEnTablaSiNoExisteId(
												Tabla_Actual,
												identificadores.get(i),
												contenidoAuxGeneral);
										if (i > 0)
											l.add("rango");
									} else if (tipoPuntero) {
										// Tabla_Actual.anadeId1(identificadores.get(i),
										// contenidoAuxGeneral);
										insertaEnTablaSiNoExisteId(
												Tabla_Actual,
												identificadores.get(i),
												contenidoAuxGeneral);
										if (i > 0)
											l.add("puntero");
									} else if (tipoEnum) {
										// Tabla_Actual.anadeId1(identificadores.get(i),
										// contenidoAuxGeneral);
										insertaEnTablaSiNoExisteId(
												Tabla_Actual,
												identificadores.get(i),
												contenidoAuxGeneral);
										if (i > 0)
											l.add("enumerado");
									} else if (tipoBasico) {// TipoBasico
										String nombre = dameNombreTipo((Integer) tipo
												.leeAtributo());
										int tam = dameTamTipoBasico((Integer) tipo
												.leeAtributo());
										ContenidoTS c = new ContenidoTS(nombre,
												null, -1, -1, 0, null, 10,
												null, tam, null, null);
										// Tabla_Actual.anadeId1(identificadores.get(i),
										// c);
										insertaEnTablaSiNoExisteId(
												Tabla_Actual,
												identificadores.get(i), c);
										if (i > 0)
											l.add(nombre);
									}

									i++;
								}

								tipoTipo = false;
								tipoBasico = false;
								tipoRango = false;
								tipoPuntero = false;
								tipoEnum = false;
							}
							correcto = true;
							// if(esId)token_act = analizadorLexico.sigToken();
							System.out.println("Declaración correcta.");
						} else if (!hayError) {
							error = new Error(6, token_act, hayError, consola);
							hayError = true;
						}
					} else if (compruebaAtributo('[')) {
						esTipoRango = true;
						if (rangos(true)) {
							if (compruebaAtributo(']')) {
								while (compruebaAtributo(',')) {
									Token idAux = token_act;
									if (compruebaTipo("identificador"))
										if (compruebaAtributo('[')) {
											listaRangoIni.add("/");
											listaRangoFin.add("/");
											if (rangos(true)) {
												if (!compruebaAtributo(']')
														&& !hayError) {
													error = new Error(8,
															token_act,
															hayError, consola);
													hayError = true;
												} else
													identificadores
															.add((String) idAux
																	.leeAtributo());
											} else if (!hayError) {
												error = new Error(9, token_act,
														hayError, consola);
												hayError = true;
											}
										} else if (!hayError) {
											error = new Error(16, token_act,
													hayError, consola);
											hayError = true;
										} else if (!hayError) {
											error = new Error(3, token_act,
													hayError, consola);
											hayError = true;
										}
								}
								if (compruebaAtributo(6)) {
									System.out.println("Hemos leído de.");
									Token tipo = token_act;
									vector = true;
									Expresion e = new Expresion();
									if (tipo(e)) {
										if (tipoArgs) {// ¿?¿
											int i = 0;
											int cont = 0;
											while (i < identificadores.size()) {
												int posVector = 0;
												TablaSimbolo tablaAux = Tabla_Actual; // creamos
																						// la
																						// nueva
																						// tabla
																						// con
																						// la
																						// superior
																						// como
																						// padre
												Tabla_Actual = new TablaSimbolo(
														tablaAux);

												while ((cont < listaRangoIni
														.size())
														&& (!listaRangoIni.get(
																cont).equals(
																"/"))) {
													int tam = dameTamañoRango(
															(Integer) listaRangoFin
																	.get(cont),
															(Integer) listaRangoIni
																	.get(cont));
													ContenidoTS conte = new ContenidoTS(
															"rango",
															identificadores
																	.get(i),
															(Integer) listaRangoIni
																	.get(cont),
															(Integer) listaRangoFin
																	.get(cont),
															0, null, 10, null,
															tam, null, null);
													Tabla_Actual
															.anadeId1(
																	String.valueOf(posVector),
																	conte);
													cont++;
													posVector++;
												}

												int tamVector = dameTamañoVector();

												if (tipoTipo) {
													int tam = dameTamId(tipo);
													if (tamVector > 0) {
														ContenidoTS c = new ContenidoTS(
																"vector",
																(String) tipo
																		.leeAtributo(),
																-1,
																-1,
																tamVector,
																null,
																(Integer) argumento
																		.leeAtributo(),
																null, tamVector
																		* tam,
																Tabla_Actual,
																null);
														// tablaAux.anadeId1(identificadores.get(i),
														// c);
														insertaEnTablaSiNoExisteId(
																tablaAux,
																identificadores
																		.get(i),
																c);
														if (i > 0)
															l.add((String) tipo
																	.leeAtributo());
													}
												} else if (tipoRango) {
													ContenidoTS c = new ContenidoTS(
															"vector",
															"rango",
															(Integer) posini
																	.leeAtributo(),
															(Integer) posfin
																	.leeAtributo(),
															tamVector,
															null,
															(Integer) argumento
																	.leeAtributo(),
															null,
															tamVector * 4,
															Tabla_Actual, null);
													// tablaAux.anadeId1(identificadores.get(i),
													// c);
													insertaEnTablaSiNoExisteId(
															tablaAux,
															identificadores
																	.get(i), c);
													if (i > 0)
														l.add("rango");
												}

												else if (tipoPuntero) {
													ContenidoTS c = new ContenidoTS(
															"vector",
															"puntero",
															-1,
															-1,
															tamVector,
															null,
															(Integer) argumento
																	.leeAtributo(),
															null,
															tamVector * 8,
															Tabla_Actual,
															listaPuntero);
													// tablaAux.anadeId1(identificadores.get(i),
													// c);
													insertaEnTablaSiNoExisteId(
															tablaAux,
															identificadores
																	.get(i), c);
													if (i > 0)
														l.add("puntero");
												} else if (tipoBasico) {
													String nombre = dameNombreTipo((Integer) tipo
															.leeAtributo());
													int tam = dameTamTipoBasico((Integer) tipo
															.leeAtributo());
													ContenidoTS c = new ContenidoTS(
															"vector",
															nombre,
															-1,
															-1,
															tamVector,
															null,
															(Integer) argumento
																	.leeAtributo(),
															null, tamVector
																	* tam,
															Tabla_Actual, null);
													// tablaAux.anadeId1(identificadores.get(i),
													// c);
													insertaEnTablaSiNoExisteId(
															tablaAux,
															identificadores
																	.get(i), c);
													if (i > 0)
														l.add(nombre);
												}
												i++;
												if ((cont < listaRangoIni
														.size())
														&& (listaRangoIni
																.get(cont)
																.equals("/")))
													cont++;
												Tabla_Actual = Tabla_Actual
														.getSuperior();
											}
											listaRangoIni.clear();
											listaRangoFin.clear();
											tipoTipo = false;
											tipoRango = false;
											tipoPuntero = false;
											tipoBasico = false;

										} else {
											int i = 0;
											int cont = 0;
											while (i < identificadores.size()) {
												int posVector = 0;
												TablaSimbolo tablaAux = Tabla_Actual; // creamos
																						// la
																						// nueva
																						// tabla
																						// con
																						// la
																						// superior
																						// como
																						// padre
												Tabla_Actual = new TablaSimbolo(
														tablaAux);

												while ((cont < listaRangoIni
														.size())
														&& (!listaRangoIni.get(
																cont).equals(
																"/"))) {
													int tam = dameTamañoRango(
															(Integer) listaRangoFin
																	.get(cont),
															(Integer) listaRangoIni
																	.get(cont));
													ContenidoTS conte = new ContenidoTS(
															"rango",
															identificadores
																	.get(i),
															(Integer) listaRangoIni
																	.get(cont),
															(Integer) listaRangoFin
																	.get(cont),
															0, null, 10, null,
															tam, null, null);
													Tabla_Actual
															.anadeId1(
																	String.valueOf(posVector),
																	conte);
													cont++;
													posVector++;
												}

												int tamVector = dameTamañoVector();

												if (tipoTipo) {
													int tam = dameTamId(tipo);
													if (tamVector > 0) {
														ContenidoTS c = new ContenidoTS(
																"vector",
																(String) tipo
																		.leeAtributo(),
																-1,
																-1,
																tamVector,
																null,
																10,
																null,
																tamVector * tam,
																Tabla_Actual,
																null);
														// tablaAux.anadeId1(identificadores.get(i),
														// c);
														insertaEnTablaSiNoExisteId(
																tablaAux,
																identificadores
																		.get(i),
																c);
														if (i > 0)
															l.add((String) tipo
																	.leeAtributo());
													}
												} else if (tipoRango) {
													ContenidoTS c = new ContenidoTS(
															"vector",
															"rango",
															(Integer) posini
																	.leeAtributo(),
															(Integer) posfin
																	.leeAtributo(),
															tamVector, null,
															10, null,
															tamVector * 4,
															Tabla_Actual, null);
													// tablaAux.anadeId1(identificadores.get(i),
													// c);
													insertaEnTablaSiNoExisteId(
															tablaAux,
															identificadores
																	.get(i), c);
													if (i > 0)
														l.add("rango");
												}

												else if (tipoPuntero) {
													ContenidoTS c = new ContenidoTS(
															"vector",
															"puntero", -1, -1,
															tamVector, null,
															10, null,
															tamVector * 8,
															Tabla_Actual,
															listaPuntero);
													// tablaAux.anadeId1(identificadores.get(i),
													// c);
													insertaEnTablaSiNoExisteId(
															tablaAux,
															identificadores
																	.get(i), c);
													if (i > 0)
														l.add("puntero");
												} else if (tipoBasico) {
													String nombre = dameNombreTipo((Integer) tipo
															.leeAtributo());
													int tam = dameTamTipoBasico((Integer) tipo
															.leeAtributo());
													ContenidoTS c = new ContenidoTS(
															"vector", nombre,
															-1, -1, tamVector,
															null, 10, null,
															tamVector * tam,
															Tabla_Actual, null);
													// tablaAux.anadeId1(identificadores.get(i),
													// c);
													insertaEnTablaSiNoExisteId(
															tablaAux,
															identificadores
																	.get(i), c);
													if (i > 0)
														l.add(nombre);
												}
												i++;
												if ((cont < listaRangoIni
														.size())
														&& (listaRangoIni
																.get(cont)
																.equals("/")))
													cont++;
												Tabla_Actual = Tabla_Actual
														.getSuperior();
											}
											listaRangoIni.clear();
											listaRangoFin.clear();
											tipoTipo = false;
											tipoRango = false;
											tipoPuntero = false;
											tipoBasico = false;
										}
										// //////////////////
										vector = false;
										correcto = true;
										System.out
												.println("Declaración correcta.");
									} else if (!hayError) {
										error = new Error(23, token_act,
												hayError, consola);
										hayError = true;
									}
								} else if (!hayError) {
									error = new Error(7, token_act, hayError,
											consola);
									hayError = true;
								}

							} else if (!hayError) {
								error = new Error(8, token_act, hayError,
										consola);
								hayError = true;
							}
						}
					}
				} else if (!compruebaAtributoNoAvanzado(')') && !hayError) {
					error = new Error(3, token_act, hayError, consola);
					hayError = true;
				} else
					correcto = true;
			}
		} catch (Exception e1) {
			if (!hayError) {
				error = new Error(100, token_act, hayError, consola);
				hayError = true;
			}
			System.out.println("excepcion capturada en entrada");
		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R39: salida → id : tipo | id [rangos] de tipo - R40: salida → <
	 * (listaIdentificadores : tipo | (id [rangos])* de tipo) , declaracion >
	 * 
	 * @return devuelve verdadero si a identificado una de las reglas
	 *         anteriores.
	 *//*
	private boolean salida(LinkedList<String> lis) {

		boolean correcto = false;
		try {
			LinkedList<String> l = new LinkedList<String>();
			int NumArgs = 0;
			if (!hayError) {
				System.out.println("***salida***");
				Token id = token_act;
				if (compruebaTipo("identificador")) {// Salida única
					System.out.println("Hemos leído identificador.");
					if (compruebaAtributo(':')) {
						Token tip = token_act;
						Expresion e = new Expresion();
						if (tipo(e)) {
							if (tipoTipo) {
								// Tabla_Actual.anadeId1((String)id.leeAtributo(),
								// contenidoAuxGeneral);
								insertaEnTablaSiNoExisteId(Tabla_Actual,
										(String) id.leeAtributo(),
										contenidoAuxGeneral);
								lis.add((String) tip.leeAtributo());
								tipoTipo = false;
							} else if (tipoRango) {
								// Tabla_Actual.anadeId1((String)id.leeAtributo(),
								// contenidoAuxGeneral);
								insertaEnTablaSiNoExisteId(Tabla_Actual,
										(String) id.leeAtributo(),
										contenidoAuxGeneral);
								lis.add("rango");
								tipoRango = false;
							} else if (tipoPuntero) {
								// Tabla_Actual.anadeId1((String)id.leeAtributo(),
								// contenidoAuxGeneral);
								insertaEnTablaSiNoExisteId(Tabla_Actual,
										(String) id.leeAtributo(),
										contenidoAuxGeneral);
								lis.add("puntero");
								tipoPuntero = false;
							} else if (tipoEnum) {
								// Tabla_Actual.anadeId1((String)id.leeAtributo(),
								// contenidoAuxGeneral);
								insertaEnTablaSiNoExisteId(Tabla_Actual,
										(String) id.leeAtributo(),
										contenidoAuxGeneral);
								lis.add("enumerado");
								tipoEnum = false;
							} else if (tipoBasico) {
								String nombre = dameNombreTipo((Integer) tip
										.leeAtributo());
								int tam = dameTamId(tip);
								ContenidoTS c = new ContenidoTS(nombre, null,
										-1, -1, 0, null, 10, null, tam, null,
										null);
								// Tabla_Actual.anadeId1((String)id.leeAtributo(),
								// c);
								insertaEnTablaSiNoExisteId(Tabla_Actual,
										(String) id.leeAtributo(), c);
								lis.add(nombre);
								tipoBasico = false;
							}
							System.out.println("Declaración correcta.");
							hayTipo = true;
							if (compruebaAtributo(',') && !hayError) {// No
																		// tendria
																		// que
																		// haber
																		// ','
								error = new Error(24, token_act, hayError,
										consola);
								hayError = true;
							} else
								correcto = true;
						}
					} else if (compruebaAtributo('[')) {
						esTipoRango = true;
						if (rangos(true)) {
							if (compruebaAtributo(']')) {
								if (compruebaAtributo(6)) {
									System.out.println("Hemos leído de.");
									Token tipo = token_act;
									// boolean esId =
									// compruebaTipoNoAvanzado("identificador");
									esTipoRango = false;
									vector = true;
									Expresion e = new Expresion();
									if (tipo(e)) {
										int cont = 0;
										int posVector = 0;
										TablaSimbolo tablaAux = Tabla_Actual; // creamos
																				// la
																				// nueva
																				// tabla
																				// con
																				// la
																				// superior
																				// como
																				// padre
										Tabla_Actual = new TablaSimbolo(
												tablaAux);
										while ((cont < listaRangoIni.size())
												&& (!listaRangoIni.get(cont)
														.equals("/"))) {
											int tam = dameTamañoRango(
													(Integer) listaRangoFin
															.get(cont),
													(Integer) listaRangoIni
															.get(cont));
											ContenidoTS conte = new ContenidoTS(
													"rango",
													(String) id.leeAtributo(),
													(Integer) listaRangoIni
															.get(cont),
													(Integer) listaRangoFin
															.get(cont), 0,
													null, 10, null, tam, null,
													null);
											Tabla_Actual.anadeId1(
													String.valueOf(posVector),
													conte);
											cont++;
											posVector++;
										}

										int tamVector = dameTamañoVector();

										if (tipoTipo) {
											int tam = dameTamId(tipo);
											if (tam > 0) {
												ContenidoTS c = new ContenidoTS(
														"vector",
														(String) tipo
																.leeAtributo(),
														-1, -1, tamVector,
														null, 10, null,
														tamVector * tam,
														Tabla_Actual, null);
												// tablaAux.anadeId1((String)id.leeAtributo(),
												// c);
												insertaEnTablaSiNoExisteId(
														tablaAux,
														(String) id
																.leeAtributo(),
														c);
												lis.add((String) tipo
														.leeAtributo());
											}
											tipoTipo = false;
										} else if (tipoRango) {
											ContenidoTS c = new ContenidoTS(
													"vector", "rango",
													(Integer) posini
															.leeAtributo(),
													(Integer) posfin
															.leeAtributo(),
													tamVector, null, 10, null,
													tamVector * 4,
													Tabla_Actual, null);
											// tablaAux.anadeId1((String)id.leeAtributo(),
											// c);
											insertaEnTablaSiNoExisteId(
													tablaAux,
													(String) id.leeAtributo(),
													c);
											lis.add("rango");
											tipoRango = false;
										} else if (tipoPuntero) {
											ContenidoTS c = new ContenidoTS(
													"vector", "puntero", -1,
													-1, tamVector, null, 10,
													null, tamVector * 8,
													Tabla_Actual, listaPuntero);
											// tablaAux.anadeId1((String)id.leeAtributo(),
											// c);
											insertaEnTablaSiNoExisteId(
													tablaAux,
													(String) id.leeAtributo(),
													c);
											lis.add("rango");
											tipoPuntero = false;
										} else if (tipoEnum) {
											ContenidoTS c = new ContenidoTS(
													"vector", "enumerado", -1,
													-1, tamVector, null, 10,
													null, tamVector * 8,
													Tabla_Actual, null);
											// tablaAux.anadeId1((String)id.leeAtributo(),
											// c);
											insertaEnTablaSiNoExisteId(
													tablaAux,
													(String) id.leeAtributo(),
													c);
											lis.add("rango");
											tipoEnum = false;
										} else if (tipoBasico) {
											String nombre = dameNombreTipo((Integer) tipo
													.leeAtributo());
											int tam = dameTamTipoBasico((Integer) tipo
													.leeAtributo());
											ContenidoTS c = new ContenidoTS(
													"vector", nombre, -1, -1,
													tamVector, null, 10, null,
													tamVector * tam,
													Tabla_Actual, null);
											// tablaAux.anadeId1((String)id.leeAtributo(),
											// c);
											insertaEnTablaSiNoExisteId(
													tablaAux,
													(String) id.leeAtributo(),
													c);
											lis.add(nombre);
											tipoBasico = false;
										}

										Tabla_Actual = Tabla_Actual
												.getSuperior();
										vector = false;
										listaRangoIni.clear();
										listaRangoFin.clear();
										System.out
												.println("Declaración correcta.");
										hayTipo = true;
										if (compruebaAtributo(',') && !hayError) { // No
																					// tendria
																					// que
																					// haber
																					// ','
											error = new Error(24, token_act,
													hayError, consola);
											hayError = true;
										} else
											correcto = true;
									}
								}
							}
						}
					}
				} // ///////////////////////////////
				else if (compruebaAtributo("MENOR")) { // Salida multiple
					Token iden = token_act;
					if (compruebaTipo("identificador")) {
						l.add((String) iden.leeAtributo());
						System.out.println("Hemos leído identificador.");
						while (compruebaAtributo(',')) {
							Token id1 = token_act;
							if (!compruebaTipo("identificador") && !hayError) {
								error = new Error(3, token_act, hayError,
										consola);
								hayError = true;
							}
							l.add((String) id1.leeAtributo());
							NumArgs++;
							System.out.println("Hemos leído identificador.");
						}
						if (compruebaAtributo(':')) {
							Token tipo = token_act;
							// boolean
							// esId=compruebaTipoNoAvanzado("identificador");
							Expresion e = new Expresion();
							if (tipo(e)) {
								int i = 0;
								while (i < NumArgs + 1) {
									if (tipoTipo) { // Tabla_Actual.anadeId1(l.get(i),
													// contenidoAuxGeneral);
										insertaEnTablaSiNoExisteId(
												Tabla_Actual, l.get(i),
												contenidoAuxGeneral);
										lis.add((String) tipo.leeAtributo());
									} else if (tipoRango) {
										// Tabla_Actual.anadeId1(l.get(i),
										// contenidoAuxGeneral);
										insertaEnTablaSiNoExisteId(
												Tabla_Actual, l.get(i),
												contenidoAuxGeneral);
										lis.add("rango");
									} else if (tipoPuntero) {
										// Tabla_Actual.anadeId1(l.get(i),
										// contenidoAuxGeneral);
										insertaEnTablaSiNoExisteId(
												Tabla_Actual, l.get(i),
												contenidoAuxGeneral);
										lis.add("puntero");
									} else if (tipoEnum) {
										// Tabla_Actual.anadeId1(l.get(i),
										// contenidoAuxGeneral);
										insertaEnTablaSiNoExisteId(
												Tabla_Actual, l.get(i),
												contenidoAuxGeneral);
										lis.add("enumerado");
									} else if (tipoBasico) {// TipoBasico
										String nombre = dameNombreTipo((Integer) tipo
												.leeAtributo());
										int tam = dameTamTipoBasico((Integer) tipo
												.leeAtributo());
										ContenidoTS c = new ContenidoTS(nombre,
												null, -1, -1, 0, null, 10,
												null, tam, null, null);
										// Tabla_Actual.anadeId1(l.get(i), c);
										insertaEnTablaSiNoExisteId(
												Tabla_Actual, l.get(i), c);
										lis.add(nombre);
									}
									i++;
								}
								tipoTipo = false;
								tipoRango = false;
								tipoPuntero = false;
								tipoEnum = false;
								tipoBasico = false;
								System.out.println("Declaración correcta.");
								hayTipo = true;
								if (compruebaAtributo(',')) {
									hayComa = true;
									declaracion(new LinkedList<String>(), lis);// hay
																				// coma
									if (compruebaAtributo("MAYOR"))
										correcto = true;
									else if (!hayError) {
										error = new Error(25, token_act,
												hayError, consola);
										hayError = true;
									}
								} else if (compruebaAtributo("MAYOR"))
									correcto = true;
								else if (!hayError) {
									error = new Error(25, token_act, hayError,
											consola);
									hayError = true;
								}
							}
						} else if (compruebaAtributo('[')) {
							esTipoRango = true;
							if (rangos(true)) {
								if (compruebaAtributo(']')) {
									while (compruebaAtributo(',')) {
										Token in = token_act;
										if (compruebaTipo("identificador")) {
											if (compruebaAtributo('[')) {
												listaRangoIni.add("/");
												listaRangoFin.add("/");
												if (rangos(true)) {
													if (!compruebaAtributo(']')
															&& !hayError) {
														error = new Error(8,
																token_act,
																hayError,
																consola);
														hayError = true;
													} else
														l.add((String) in
																.leeAtributo());
												} else if (!hayError) {
													error = new Error(9,
															token_act,
															hayError, consola);
													hayError = true;
												}
											} else if (!hayError) {
												error = new Error(16,
														token_act, hayError,
														consola);
												hayError = true;
											}
										} else if (!hayError) {
											error = new Error(3, token_act,
													hayError, consola);
											hayError = true;
										}
									}
									if (compruebaAtributo(6)) {
										System.out.println("Hemos leído de.");
										Token tipo = token_act;
										esTipoRango = false;
										vector = true;
										Expresion e = new Expresion();
										if (tipo(e)) {
											int i = 0;
											int cont = 0;
											while (i < l.size()) {
												int posVector = 0;
												TablaSimbolo tablaAux = Tabla_Actual; // creamos
																						// la
																						// nueva
																						// tabla
																						// con
																						// la
																						// superior
																						// como
																						// padre
												Tabla_Actual = new TablaSimbolo(
														tablaAux);
												while ((cont < listaRangoIni
														.size())
														&& (!listaRangoIni.get(
																cont).equals(
																"/"))) {
													int tam = dameTamañoRango(
															(Integer) listaRangoFin
																	.get(cont),
															(Integer) listaRangoIni
																	.get(cont));
													ContenidoTS conte = new ContenidoTS(
															"rango",
															l.get(i),
															(Integer) listaRangoIni
																	.get(cont),
															(Integer) listaRangoFin
																	.get(cont),
															0, null, 10, null,
															tam, null, null);
													Tabla_Actual
															.anadeId1(
																	String.valueOf(posVector),
																	conte);
													cont++;
													posVector++;
												}

												int tamVector = dameTamañoVector();

												if (tipoTipo) {
													int tam = dameTamId(tipo);
													if (tam > 0) {
														ContenidoTS c = new ContenidoTS(
																"vector",
																(String) tipo
																		.leeAtributo(),
																-1,
																-1,
																tamVector,
																null,
																10,
																null,
																tamVector * tam,
																Tabla_Actual,
																null);
														// tablaAux.anadeId1(l.get(i),
														// c);
														insertaEnTablaSiNoExisteId(
																tablaAux,
																l.get(i), c);
														lis.add((String) tipo
																.leeAtributo());
													}
												} else if (tipoRango) {
													ContenidoTS c = new ContenidoTS(
															"vector",
															"rango",
															(Integer) posini
																	.leeAtributo(),
															(Integer) posfin
																	.leeAtributo(),
															tamVector, null,
															10, null,
															tamVector * 4,
															Tabla_Actual, null);
													// tablaAux.anadeId1(l.get(i),
													// c);
													insertaEnTablaSiNoExisteId(
															tablaAux, l.get(i),
															c);
													lis.add("rango");
												} else if (tipoPuntero) {
													ContenidoTS c = new ContenidoTS(
															"vector",
															"puntero", -1, -1,
															tamVector, null,
															10, null,
															tamVector * 8,
															Tabla_Actual,
															listaPuntero);
													// tablaAux.anadeId1(l.get(i),
													// c);
													insertaEnTablaSiNoExisteId(
															tablaAux, l.get(i),
															c);
													lis.add("puntero");
												} else if (tipoEnum) {
													// ¿Como le paso la lista
													// del enumerado?
													ContenidoTS c = new ContenidoTS(
															"vector",
															"enumerado", -1,
															-1, tamVector,
															null, 10, null,
															tamVector * 8,
															Tabla_Actual, null);
													// tablaAux.anadeId1(l.get(i),
													// c);
													insertaEnTablaSiNoExisteId(
															tablaAux, l.get(i),
															c);
													lis.add("puntero");
												} else if (tipoBasico) {
													String nombre = dameNombreTipo((Integer) tipo
															.leeAtributo());
													int tam = dameTamTipoBasico((Integer) tipo
															.leeAtributo());
													ContenidoTS c = new ContenidoTS(
															"vector", nombre,
															-1, -1, tamVector,
															null, 10, null,
															tamVector * tam,
															Tabla_Actual, null);
													// tablaAux.anadeId1(l.get(i),
													// c);
													insertaEnTablaSiNoExisteId(
															tablaAux, l.get(i),
															c);
													lis.add(nombre);
												}
												i++;
												if ((cont < listaRangoIni
														.size())
														&& (listaRangoIni
																.get(cont)
																.equals("/")))
													cont++;
												Tabla_Actual = Tabla_Actual
														.getSuperior();
											}
											listaRangoIni.clear();
											listaRangoFin.clear();
											tipoTipo = false;
											tipoRango = false;
											tipoPuntero = false;
											tipoEnum = false;
											tipoBasico = false;
											vector = false;
											System.out
													.println("Declaración correcta.");
											hayTipo = true;

											if (compruebaAtributo(',')) {
												hayComa = true;
												declaracion(
														new LinkedList<String>(),
														lis);// hay coma
												if (compruebaAtributo("MAYOR"))
													correcto = true;
												else if (!hayError) {
													error = new Error(25,
															token_act,
															hayError, consola);
													hayError = true;
												}
											} else if (compruebaAtributo("MAYOR"))
												correcto = true;
											else if (!hayError) {
												error = new Error(25,
														token_act, hayError,
														consola);
												hayError = true;
											}
										}
									}
								}
							}
						}
					}
				}// ////////////////////////
				else if (!hayError) {
					error = new Error(26, token_act, hayError, consola);
					hayError = true;
				}
			}
		} catch (Exception e1) {
			if (!hayError) {
				error = new Error(100, token_act, hayError, consola);
				hayError = true;
			}
			System.out.println("excepcion capturada en salida");
		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R41: Insts → Inst (; | ‘/n’) [Insts1] - R42: Insts → Inst
	 * 
	 *//*
	private void instrucciones() {
		if (!hayError) {
			compruebaSaltoLinea();
			System.out.println("***Insts***");
			if (!atributoNull()) {
				if (!token_act.leeAtributo().equals(17)
						&& !token_act.leeAtributo().equals(18)
						&& !token_act.leeAtributo().equals(19)
						&& !token_act.leeAtributo().equals(20)
						&& !token_act.leeAtributo().equals(21)
						&& !token_act.leeAtributo().equals(22)
						&& !token_act.leeAtributo().equals(23)
						&& !token_act.leeAtributo().equals(24)
						&& !token_act.leeAtributo().equals(25)) {
					String instruccion = etiquetaNueva();
					if (instruccion(instruccion)) {
						// codigo+=instruccion +" :"+"\n";
						System.out
								.println("Hay instrucciones declaradas correctamente");
						if (compruebaAtributo(';') || compruebaAtributo("\\n"))
							compruebaSaltoLinea();
						instrucciones();
					} else if (compruebaAtributo(';')) {
						System.out.println("Venimos de declaraciones");
						instrucciones();
					}
				}
			}
		}
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R43: Inst → asig - R44: Inst → casos0 - R45: Inst → instSi - R46: Inst
	 * → instPara - R47: Inst → instMientras - R48: Inst → metodo - R49: Inst →
	 * nada
	 * 
	 * @return
	 *//*
	private boolean instruccion(String siguiente) {
		boolean correcto = false;
		try {
			if (!hayError) {
				compruebaSaltoLinea();
				System.out.println("***Inst***");
				Token id = token_act;
				auxToken2 = token_act;
				if (compruebaTipo("identificador")) {
					System.out.println("Hemos leído identificador");
					if (!atributoNull() && compruebaAtributo('(')) {
						Expresion e = new Expresion();
						ContenidoTS c = Tabla_Actual.getContenido((String) id
								.leeAtributo());
						if (c == null) {
							e.setTipo("error_tipo");

							System.out
									.println("error tipo identificador del metodo no valido");
						} else {
							e.setTipo(c.getTipo());
							e.setLugar((String) id.leeAtributo());
						}
						LinkedList<String> lista_retorno_funcion = null;
						LinkedList<String> size = null;
						if (c != null) {
							lista_retorno_funcion = c.getRetorno();
							size = (LinkedList<String>) c.getTipoArgs().clone();
						}
						if (e.getTipo().equals("error_tipo") && !hayError) {
							error = new Error(100, token_act, hayError, consola);
							hayError = true;
						}
						metodo(e, lista_retorno_funcion, size);
						if (e.getTipo().equals("error_tipo") && !hayError) {
							error = new Error(100, token_act, hayError, consola);
							hayError = true;
						}

						correcto = true;
					} else {
						if (compruebaTipoNoAvanzado("OP_ASIG")) {
							Expresion e = new Expresion();

							// /expresion de tipos de la variable de la izq
							ContenidoTS c = Tabla_Actual
									.getContenido((String) auxToken2
											.leeAtributo());

							if (c != null) {
								e.setLugar((String) auxToken2.leeAtributo());
								if (c.getTipo().equals("vector"))
									e.setTipo(c.getTipo());
								else
									e.setTipo(c.getTipo());
							}

							else {
								e.setTipo("error_tipo");
							}

							asignacion(e);
							// if(e.getTipo().equals("error_tipo"))error=new
							// Error(100,token_act,hayError,consola);
							correcto = true;
						} else {
							Expresion e = new Expresion();

							// /expresion de tipos de la variable de la izq
							ContenidoTS c = Tabla_Actual
									.getContenido((String) auxToken2
											.leeAtributo());
							if (c != null) {
								if (c.getTipo().equals("vector"))
									e.setTipo(c.getTipo());
								else
									e.setTipo(c.getTipo());
							}

							else {
								e.setTipo("error_tipo");
							}

							expIdConIdLeido(e, hayError);

							asignacion(e);
							// if(e.getTipo().equals("error_tipo"))error=new
							// Error(100,token_act,hayError,consola);
							correcto = true;
						}
					}
				} else if (compruebaAtributo(4)) {
					System.out.println("Hemos leído casos");
					Expresion e = new Expresion();
					casos0(e, siguiente);
					// if(e.getTipo().equals("error_tipo"))error=new
					// Error(100,token_act,hayError,consola);
					correcto = true;
				} else if (compruebaAtributo(47)) {
					System.out.println("Hemos leído si");
					if (!token_act.leeAtributo().equals(35)) {
						Expresion e = new Expresion();
						instSi(e, siguiente);
						// if(e.getTipo().equals("error_tipo"))error=new
						// Error(100,token_act,hayError,consola);
					}
					correcto = true;
				} else if (compruebaAtributo(37)) {
					System.out.println("Hemos leído para");
					Expresion e = new Expresion();
					instPara(e, siguiente);
					correcto = true;
					// if(e.getTipo().equals("error_tipo"))error=new
					// Error(100,token_act,hayError,consola);
				} else if (compruebaAtributo(29)) {
					System.out.println("Hemos leído mientras");
					Expresion e = new Expresion();
					instMientras(e, siguiente);
					correcto = true;
					// if(e.getTipo().equals("error_tipo"))error=new
					// Error(100,token_act,hayError,consola);
				} else if (compruebaAtributo(31)) {
					System.out.println("Hemos leído nada");
					correcto = true;
				} else if (compruebaAtributo("MENOR")) {
					Expresion e = new Expresion();
					asignacion(e);
					// if(e.getTipo().equals("error_tipo"))error=new
					// Error(100,token_act,hayError,consola);
					correcto = true;
				}

			}
		} catch (Exception e1) {
			if (!hayError) {
				error = new Error(100, token_act, hayError, consola);
				hayError = true;
			}
			System.out.println("captura de excepciones en instruccion");
		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R50: asig → Expid := Exp0 |[ id |rango | Exp0’] - R51: asig →
	 * <Expid(,Expid)+> := <Exp0(,Exp0)+>
	 * 
	 *//*
	// /////////////////
	private boolean asignacion(Expresion e) {
		boolean correcto = false;
		auxiliar = Tabla_Actual.Copia();
		boolean EDT = true;
		boolean DDS = false;

		try {
			if (!hayError) {
				int cont = 0;
				System.out.println("***Asig***");
				if (compruebaTipo("OP_ASIG")) {
					Expresion e1 = new Expresion();

					correcto = exp0(e1, hayError, EDT, DDS);

					// comprobacion de tipos con e y e1
					try {
						if (tiposAsignables(e, e1,
								(String) auxToken2.leeAtributo(),
								(String) auxToken.leeAtributo())
								&& !e.getTipo().equals("error_tipo"))
							e.setTipo("");
						else {
							e.setTipo("error_tipo");
							System.out.println("error tipo en la asignacion");
							if (!hayError) {
								error = new Error(100, token_act, hayError,
										consola);
								hayError = true;
							}
						}

					} catch (Exception e5) {
						if (tiposAsignables(e, e1, null, null)
								&& !e.getTipo().equals("error_tipo"))
							e.setTipo("");
						else {
							e.setTipo("error_tipo");
							System.out.println("error tipo en la asignacion");
							if (!hayError) {
								error = new Error(100, token_act, hayError,
										consola);
								hayError = true;
							}
						}
					}
					codigo += e.getLugar() + " := " + e1.getLugar() + "\n";

				} else {
					AsigMult = true;
					hayAsigMult = true;
					LinkedList<Expresion> listaExpresiones = new LinkedList<Expresion>();
					LinkedList<String> listaId = new LinkedList<String>();
					while (!compruebaAtributoNoAvanzado("MAYOR") && !hayError) {
						String iden = (String) token_act.leeAtributo();
						Expresion e1 = new Expresion();
						expId(e1, hayError);
						Tabla_Actual = auxiliar.Copia();
						listaExpresiones.add(e1);
						listaId.add(iden);
						compruebaAtributo(',');
						cont++;
					}
					if (compruebaAtributo("MAYOR")) {
						if (compruebaTipo("OP_ASIG")) {
							Token idAux = token_act;
							if (compruebaAtributo("MENOR")) {
								int i = 0;
								while (hayAsigMult && !hayError) {
									System.out.println(token_act.toString());
									Expresion e2 = new Expresion();
									String nombrePosibleId = "";
									try {
										nombrePosibleId = (String) token_act
												.leeAtributo();
									} catch (Exception e4) {
									}
									exp0(e2, hayError, EDT, DDS);

									if (!(e2.getTipo().equals("error_tipo"))) {
										// comparar con los e.tipos guardados
										if (i < listaId.size()) {
											if (!tiposAsignables(
													listaExpresiones.get(i),
													e2, listaId.get(i),
													nombrePosibleId)) {
												e.setTipo("error_tipo");
												System.out
														.println("error tipo en asignacion multiple");
												if (!hayError) {
													error = new Error(100,
															token_act,
															hayError, consola);
													hayError = true;
												}
											}
											codigo += listaExpresiones.get(i)
													.getLugar()
													+ " := "
													+ e2.getLugar() + "\n";
											
											 * if(!(e1.getTipo().equals(lista.get
											 * (i)))){
											 * if(!e1.esTipoNumerico()||!
											 * estiponumerico(lista.get(i)))
											 * {e.setTipo("error_tipo");
											 * System.out.println(
											 * "error tipo en asignacion multiple"
											 * ); error=new
											 * Error(100,token_act,hayError
											 * ,consola); hayError=true; } }else
											 * { e.setTipo("error_tipo");
											 * System.out.println(
											 * "error tipo en asignacion multiple"
											 * );
											 * 
											 * }
											 
										} else
											e.setTipo("error_tipo");
									}

									compruebaAtributo(',');
									cont--;
									System.out.println(cont);
									i++;
								}
								if (!(e.getTipo().equals("error_tipo")))
									e.setTipo("");
								AsigMult = false;
								if ((cont != 0) && !hayError) {
									error = new Error(29, token_act, hayError,
											consola);
									hayError = true;
								}
								if (!hayAsigMult) {
									correcto = true;
									hayAsigMult = false;
								} else if (!hayError) {
									error = new Error(29, token_act, hayError,
											consola);
									hayError = true;
								}
							} else {
								Expresion e5 = new Expresion();
								idAux = token_act;
								if (exp5(e5, false, EDT, DDS)) {
									if (e5.getTipo() == "funcion") {
										ContenidoTS con = Tabla_Actual
												.getContenido((String) idAux
														.leeAtributo());
										LinkedList<String> listaTipos = con
												.getRetorno();
										int tamListaTipos = listaTipos.size();

										if (listaId.size() == tamListaTipos) {

											int i = 0;
											boolean bien = true;
											while (i < tamListaTipos && bien) {
												// para reutilizar la función
												// tiposAsignables, necesito
												// expresiones
												Expresion ex = new Expresion();
												ex.setTipo(listaTipos.get(i));
												if (!tiposAsignables(
														listaExpresiones.get(i),
														ex, listaId.get(i), ""))
													bien = false;
												
												 * //Cojo el tipo de cada
												 * parametro de salida de la
												 * funcion String t1 =
												 * Tabla_Actual
												 * .getContenido((String
												 * )idAux.leeAtributo
												 * ()).getTablaHija
												 * ().getTs().get
												 * (con.getRetorno(
												 * ).get(i)).getTipo(); //Cojo
												 * el tipo de cada parametro de
												 * la parte izquiera de la
												 * asignacion String t2 =
												 * Tabla_Actual
												 * .getContenido(lista
												 * .get(i)).getTipo(); if
												 * (!t1.equals(t2)) bien=false;
												 
												i++;
											}

											if (!bien && !hayError) {
												error = new Error(109,
														token_act, hayError,
														consola); // Tipos de
																	// parametros
																	// no
																	// coinciden
												hayError = true;
											}
										} else if (!hayError) {
											error = new Error(108, token_act,
													hayError, consola); // Num
																		// de
																		// parametros
																		// no
																		// coincide
											hayError = true;
										}
									} else if (!hayError) {
										error = new Error(107, token_act,
												hayError, consola); // Asignacion
																	// a algo
																	// que no es
																	// una
																	// funcion
										hayError = true;
									}
								} else if (!hayError) {
									error = new Error(29, token_act, hayError,
											consola);
									hayError = true;
								}
							}

						} else if (!hayError) {
							error = new Error(29, token_act, hayError, consola);
							hayError = true;
						}
					} else if (!hayError) {
						error = new Error(29, token_act, hayError, consola);
						hayError = true;
					}
				}
			}
		} catch (Exception e1) {
			if (!hayError) {
				error = new Error(100, token_act, hayError, consola);
				hayError = true;
			}
			e.setTipo("error_tipo");

			System.out.println("captura de excepciones en asignacion");
		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R52: casos0 → casos Exp0 → Insts [casos1] fcasos
	 * 
	 * @return
	 *//*
	private boolean casos0(Expresion e, String siguiente) {
		boolean correcto = false;
		compruebaSaltoLinea();
		boolean EDT = false;
		boolean DDS = true;
		if (!hayError) {
			System.out.println("***Casos0***");
			Expresion e1 = new Expresion();
			e1.setVerdadera(etiquetaNueva());
			e1.setFalsa(etiquetaNueva());
			exp0(e1, true, EDT, DDS);
			if (e1.getTipo().equals("booleano")) {
				e.setTipo("");
				codigo += e1.getVerdadera() + " :" + "\n";
			} else {
				e.setTipo("error_tipo");
				System.out.println(" error de tipo en casos 0");
			}
			if (compruebaTipo("FLEC")) {
				instrucciones();
				codigo += "goto " + siguiente + "\n" + e1.getFalsa() + " :"
						+ "\n";
				Expresion e2 = new Expresion();
				casos1(e2, siguiente);
				codigo += siguiente + " :" + "\n";
				if (compruebaAtributo(17))
					correcto = true;
				else if (!hayError) {
					error = new Error(31, token_act, hayError, consola);
					hayError = true;
				}
			}
		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R53: casos1 → [] Exp0 → Insts [casos1]
	 * 
	 * @param e
	 *//*
	private void casos1(Expresion e, String siguiente) {
		compruebaSaltoLinea();
		boolean EDT = false;
		boolean DDS = true;
		if (!hayError) {
			System.out.println("***Casos1***");
			if (compruebaAtributo('[')) {
				if (compruebaAtributo(']')) {
					Expresion e1 = new Expresion();
					e1.setVerdadera(etiquetaNueva());
					e1.setFalsa(etiquetaNueva());
					exp0(e1, true, EDT, DDS);
					codigo += e1.getVerdadera() + " :" + "\n";
					if (e1.getTipo().equals("booleano")
							&& !e.getTipo().equals("error_tipo"))
						e.setTipo("");
					else {
						e.setTipo("error_tipo");
						System.out.println(" error de tipo en casos 0");
					}

					if (compruebaTipo("FLEC")) {
						instrucciones();
						codigo += "goto " + siguiente + "\n" + e1.getFalsa()
								+ " :" + "\n";
						casos1(e, siguiente);
					} else if (!hayError) {
						error = new Error(32, token_act, hayError, consola);
						hayError = true;
					}

				} else if (!hayError) {
					error = new Error(8, token_act, hayError, consola);
					hayError = true;
				}

			} else if (!token_act.leeAtributo().equals(17) && !hayError) {
				error = new Error(16, token_act, hayError, consola);
				hayError = true;
			}
			 ¿No habria que esperar que fuera vacio? 
		}
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R54: instSi → si Exp0 entonces Insts fsi - R55: instSi → si Exp0
	 * entonces Insts si no Insts fsi
	 * 
	 * @return
	 *//*
	private boolean instSi(Expresion e, String siguiente) {
		boolean correcto = false;
		if (!hayError) {
			boolean EDT = false;
			boolean DDS = true;
			System.out.println("***IntSi***");
			Expresion e1 = new Expresion();
			e1.setVerdadera(etiquetaNueva());
			e1.setFalsa(etiquetaNueva());
			if (exp0(e1, true, EDT, DDS)) {
				if (e1.getTipo().equals("booleano"))
					e.setTipo("");
				else if (!hayError) {
					error = new Error(114, token_act, hayError, consola);
					hayError = true;
					e.setTipo("error_tipo");
					System.out.println("error de tipo en la instruccion si");
				}
				if (compruebaAtributo(15)) {
					System.out.println("Hemos leído entonces.");
					codigo += e1.getVerdadera() + " :" + "\n";
					instrucciones();
					if (compruebaAtributo(24)) {
						codigo += siguiente + " :" + "\n";
						System.out.println("Hemos leído fsi.");
						correcto = true;
						codigo += "goto " + siguiente + "\n" + e1.getFalsa()
								+ " :" + "\n";
					} else {  ¡¡¡HOUSTON!!! 
						if (compruebaAtributo(35)) {
							System.out.println("Hemos leído no.");
							codigo += "goto " + siguiente + "\n"
									+ e1.getFalsa() + " :" + "\n";
							instrucciones();
							if (compruebaAtributo(24)) {
								codigo += siguiente + " :" + "\n";
								System.out.println("Hemos leído fsi.");
								correcto = true;
							} else if (!hayError) {
								error = new Error(33, token_act, hayError,
										consola);
								hayError = true;
							}
						} else if (!hayError) {
							error = new Error(34, token_act, hayError, consola);
							hayError = true;
						}
					}
				} else if (!hayError) {
					error = new Error(35, token_act, hayError, consola);
					hayError = true;
				}
			} else if (!hayError) {
				error = new Error(36, token_act, hayError, consola);
				hayError = true;
			}

		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R56: instPara → para asig-para hasta Exp0 hacer Insts fpara - R57:
	 * instPara → para asig-para hasta Exp0 paso Exp0 hacer insts fpara
	 * 
	 * @return
	 *//*
	private boolean instPara(Expresion e, String siguiente) {
		boolean correcto = false;
		if (!hayError) {
			boolean EDT = false;
			boolean DDS = true;
			String comienzo = etiquetaNueva();
			String instruccion = etiquetaNueva();
			System.out.println("***IntPara***");
			Expresion e2 = new Expresion();
			if (asigPara(e2)) {
				if (compruebaAtributo(28)) {
					System.out.println("Hemos leído hasta");
					Expresion e1 = new Expresion();
					if (exp0(e1, true, EDT, DDS)) {
						if ((e1.getTipo().equals("enteros") || e1.getTipo()
								.equals("naturales"))
								&& !e2.getTipo().equals("error_tipo"))
							e.setTipo("");
						else {
							e.setTipo("error_tipo");
							System.out
									.println("error de tipos en el metodo para");
						}
						if (compruebaAtributo(27)) {
							System.out.println("Hemos leído hacer");
							codigo += comienzo + " :" + "\n" + "if "
									+ e2.getLugar() + " = " + e1.getLugar()
									+ " goto " + siguiente + "\n";
							instrucciones();
							codigo += e2.getLugar() + " := " + e2.getLugar()
									+ " + " + 1 + "\n" + "goto " + comienzo
									+ "\n" + siguiente + " :" + "\n";

							if (compruebaAtributo(20)) {
								System.out.println("Hemos leído fpara");
								correcto = true;
							} else if (!hayError) {
								error = new Error(37, token_act, hayError,
										consola);
								hayError = true;
							}

						} else if (compruebaAtributo(38)) {
							System.out.println("Hemos leído paso");
							Expresion e3 = new Expresion();
							if (exp0(e3, true, EDT, DDS)) {
								if (e3.getTipo().equals("enteros")
										&& !e.getTipo().equals("error_tipo"))
									e.setTipo("");
								else {
									e.setTipo("error_tipo");
									System.out
											.println("error de tipos en el metodo para en hasta");
								}
								if (compruebaAtributo(27)) {
									System.out.println("Hemos leído hacer");
									codigo += comienzo + " :" + "\n" + "if "
											+ e2.getLugar() + " = "
											+ e1.getLugar() + " goto "
											+ siguiente + "\n";
									instrucciones();
									codigo += e2.getLugar() + " := "
											+ e2.getLugar() + " + "
											+ e3.getLugar() + "\n" + "goto "
											+ comienzo + "\n" + siguiente
											+ " :" + "\n";

									if (compruebaAtributo(20)) {
										System.out.println("Hemos leído fpara");
										correcto = true;
									} else if (!hayError) {
										error = new Error(37, token_act,
												hayError, consola);
										hayError = true;
									}
								} else if (!hayError) {
									error = new Error(38, token_act, hayError,
											consola);
									hayError = true;
								}
							} else if (!hayError) {
								error = new Error(36, token_act, hayError,
										consola);
								hayError = true;
							}
						} else if (!hayError) {
							error = new Error(38, token_act, hayError, consola);
							hayError = true;
						}
					} else if (!hayError) {
						error = new Error(36, token_act, hayError, consola);
						hayError = true;
					}
				} else if (!hayError) {
					error = new Error(39, token_act, hayError, consola);
					hayError = true;
				}
			} else if (!hayError) {
				error = new Error(36, token_act, hayError, consola);
				hayError = true;
			}
		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R58: asig-para → id = Exp0
	 * 
	 * @return
	 *//*
	private boolean asigPara(Expresion e) {
		boolean correcto = false;
		if (!hayError) {
			boolean EDT = true;
			boolean DDS = false;
			System.out.println("***AsigPara***");
			Token id = token_act;
			if (compruebaTipo("identificador")) {
				System.out.println("Hemos leído identificador.");
				if (compruebaAtributo("IGUA")) {
					Expresion e1 = new Expresion();
					if (exp0(e1, true, EDT, DDS)) {
						correcto = true;
						
						 * ContenidoTS c =
						 * Tabla_Actual.getContenido((String)id.leeAtributo());
						 * if(c==null){e.setTipo("error_tipo");
						 * System.out.println
						 * ("error de tipo variable no esta en TS"); } else
						 * if(c.getTipo().equals(e1.getTipo())) e.setTipo("");
						 * else { e.setTipo("error_tipo");
						 * System.out.println(" error de tipo en asig-para"); }
						 

						int tam = dameTamTipoBasico(12);// enteros
						ContenidoTS cTS = new ContenidoTS("enteros", null, -1,
								-1, 0, null, 10, null, tam, null, null);
						if (!Tabla_Actual.existeId(id.leeAtributo().toString()))
							Tabla_Actual.anadeId1(id.leeAtributo().toString(),
									cTS);
						else if (!hayError) {
							error = new Error(106, id, hayError, consola);
							hayError = true;
						}
						e.setTipo("enteros");
						if (e1.getTipo().equals("enteros")) {
							e.setTipo("");
							e.setLugar((String) id.leeAtributo());
							codigo += e.getLugar() + " := " + e1.getLugar()
									+ "\n";
						} else {
							e.setTipo("error_tipo");
							System.out.println(" error de tipo en asig-para");
						}
						// sobra
						e.setLugar((String) id.leeAtributo());
						codigo += e.getLugar() + " := " + e1.getLugar() + "\n";
						//
					}
				} else if (!hayError) {
					error = new Error(10, token_act, hayError, consola);
					hayError = true;
				}
			} else if (!hayError) {
				error = new Error(3, token_act, hayError, consola);
				hayError = true;
			}
		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R59: instMientras → mientras Exp0 hacer Insts fmientras
	 * 
	 * @return
	 *//*
	private boolean instMientras(Expresion e, String siguiente) {
		boolean correcto = false;

		if (!hayError) {
			boolean EDT = false;
			boolean DDS = true;
			System.out.println("***InstMientras***");
			Expresion e1 = new Expresion();
			String comienzo = etiquetaNueva();
			e1.setVerdadera(etiquetaNueva());
			e1.setFalsa(siguiente);
			String intrucciones = etiquetaNueva();
			codigo += "goto" + comienzo + "\n" + comienzo + " :" + "\n";
			if (exp0(e1, true, EDT, DDS)) {
				if (e1.getTipo().equals("booleano"))
					e.setTipo("");
				else {
					e.setTipo("error_tipo");
					System.out.println("error de tipo en mientras");
				}
				if (compruebaAtributo(27)) {
					System.out.println("Hemos leído hacer");
					codigo += e1.getVerdadera() + " :" + "\n";
					instrucciones();
					codigo += "goto " + comienzo + "\n" + siguiente + " :"
							+ "\n";
					if (compruebaAtributo(19)) {
						System.out.println("Hemos leído fmientras");
						correcto = true;
					} else if (!hayError) {
						error = new Error(40, token_act, hayError, consola);
						hayError = true;
					}

				} else if (!hayError) {
					error = new Error(38, token_act, hayError, consola);
					hayError = true;
				}
			} else if (!hayError) {
				error = new Error(36, token_act, hayError, consola);
				hayError = true;
			}
		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R60: metodo → id ( parametros )
	 * 
	 * @param lista_retorno_funcion
	 * @param size
	 * @param size
	 * 
	 * @return
	 *//*
	private boolean metodo(Expresion e,
			LinkedList<String> lista_retorno_funcion, LinkedList<String> size) {

		boolean correcto = false;
		try {
			if (!hayError) {
				System.out.println("***Metodo***");
				if (e.getTipo().equals("procedimiento")) {

					e.setTipo(""); // le tengo que pasar tambien el numero de
									// args y hacer sus comprobaciones

				} else {

					if (e.getTipo().equals("funcion")) {

						if (lista_retorno_funcion.size() == 1)
							e.setTipo(lista_retorno_funcion.getFirst());
						else {
							// / que hago con la lista de tipos de retorno
							// cuando hay mas de un tipo??????

						}

					} else {
						e.setTipo("error_tipo");
						System.out.println("error de tipo en metodo");
					}
				}

				parametros(size);
				if (error_tipo || size.size() > 0)
					e.setTipo("error_tipo");

				if (compruebaAtributo(')')) {
					codigo += "call "
							+ e.getLugar()
							+ ", "
							+ Tabla_Actual.getContenido(e.getLugar())
									.getNumArgs() + "\n";
					correcto = true;
				} else if (!hayError) {
					error = new Error(14, token_act, hayError, consola);
					hayError = true;
				}

			}
		} catch (Exception e1) {
			if (!hayError) {
				error = new Error(100, token_act, hayError, consola);
				hayError = true;
			}
			e.setTipo("error_tipo");
			System.out.println("captura de excepciones en metodo");
		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R61: parametros → [Exp0 [ , parametros ] ]
	 * 
	 * @param size
	 * 
	 *//*
	private void parametros(LinkedList<String> size) {
		try {
			if (!hayError) {
				boolean EDT = true;
				boolean DDS = false;
				if (!compruebaAtributoNoAvanzado(')')) {
					System.out.println("***Parametros***");
					Expresion e = new Expresion();
					if (exp0(e, false, EDT, DDS)) {
						if (size.isEmpty())
							error_tipo = true;
						else if (e.getTipo().equals(size.getFirst())
								|| (e.esTipoNumerico() && estiponumerico(size
										.getFirst())))
							size.removeFirst();
						else {
							error_tipo = true;
						}
						if (compruebaAtributo(',')) {
							hayComa = true;
							parametros(size);
						} else
							hayComa = false;
						codigo += "param " + e.getLugar() + "\n";
					}
				} else if (hayComa && !hayError) {
					error = new Error(41, token_act, hayError, consola);
					hayError = true;
				}
			}
		} catch (Exception e1) {
			if (!hayError) {
				error = new Error(100, token_act, hayError, consola);
				hayError = true;
			}
			error_tipo = true;
			System.out.println("captura de excepciones en parametros");
		}
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R62: Exp0 → Exp1 - R63: Exp0 → Exp1 op_log Exp1
	 * 
	 * @return
	 *//*
	private boolean exp0(Expresion e, boolean lanzaError, boolean EDT,
			boolean DDS) {
		boolean correcto = false;
		if (!hayError) {
			System.out.println("***Exp0***");
			Expresion e1 = new Expresion(e);
			if (exp1(e1, lanzaError, EDT, DDS)) {
				e.setTipo(e1.getTipo());
				e.setLugar(e1.getLugar());
				correcto = true;
				Operando op = new Operando();
				if (opLog(op)) {
					Expresion e2 = new Expresion();

					if (!exp1(e2, lanzaError, EDT, DDS) && !hayError) {
						error = new Error(42, token_act, hayError, consola);
						hayError = true;
					}

					if (e1.getTipo().equals(e2.getTipo())
							&& e1.getTipo().equals("booleano")) {
						e.setTipo("booleano");
						e.setTipo(temporalNueva());
						if ((op.getOp() == " ∧ ") || (op.getOp() == " ∧c ")) {
							e1.setVerdadera(etiquetaNueva());
							e1.setFalsa(e.getFalsa());
							e2.setVerdadera(e.getVerdadera());
							e2.setFalsa(e.getFalsa());
							codigo += e1.getLugar() + e1.getVerdadera() + " :"
									+ e2.getLugar();
						} else if ((op.getOp() == " V  ")
								|| (op.getOp() == " Vc ")) {
							e1.setVerdadera(e.getVerdadera());
							e1.setFalsa(etiquetaNueva());
							e2.setVerdadera(e.getVerdadera());
							e2.setFalsa(e.getFalsa());
							codigo += e1.getLugar() + e1.getFalsa() + " :"
									+ e2.getLugar();

						}
						codigo += e.getLugar() + " := " + e1.getLugar()
								+ op.getOp() + e2.getLugar() + "\n";
					} else {
						e.setTipo("error_tipo");
						System.out.println("error de tipos en exp0");
					}
				}
			}
		}
		if (e.getTipo().equals("error_tipo")) {
			if (!hayError) {
				error = new Error(100, token_act, hayError, consola);
				hayError = true;
			}
		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R64: Exp1→ Exp2 - R65: Exp1 → Exp2 op_relacional Exp1 - R66: Exp1 →
	 * Exp2 op_igual Exp1
	 * 
	 * @param e
	 * 
	 * @return
	 *//*
	private boolean exp1(Expresion e, boolean lanzaError, boolean EDT,
			boolean DDS) {
		boolean correcto = false;
		if (!hayError) {
			System.out.println("***Exp1***");
			Expresion e1 = new Expresion(e);
			if (exp2(e1, lanzaError, EDT, DDS)) {
				e.setTipo(e1.getTipo());
				e.setLugar(e1.getLugar());
				correcto = true;
				Operando op = new Operando();

				if (opRelacional(op)) {
					if (!AsigMult) {
						Expresion e2 = new Expresion();
						if (!exp2(e2, lanzaError, EDT, DDS) && !hayError) {
							error = new Error(43, token_act, hayError, consola);
							hayError = true;
						} else {
							if (((e.esTipoNumerico()) && (e2.esTipoNumerico()))
									|| (e.getTipo().equals("caracteres"))
									&& (e2.getTipo().equals("caracteres"))) {
								e.setTipo("booleano");
								codigo += "if " + e1.getLugar() + op.getOp()
										+ e2.getLugar() + " goto "
										+ e.getVerdadera() + "\n" + "goto "
										+ e.getFalsa() + "\n";
							} else {
								e.setTipo("error_tipo");
								System.out.print("Error de tipo Exp1");
							}
						}
					}
				} else if (opIgual(op)) {
					Expresion e2 = new Expresion();
					if ((!exp2(e2, lanzaError, EDT, DDS)) && (!hayError)) {
						error = new Error(43, token_act, hayError, consola);
						hayError = true;
					} else {
						if (((e.esTipoNumerico()) && (e2.esTipoNumerico()))
								|| (e.getTipo().equals(e2.getTipo()))) {
							e.setTipo("booleano");
							codigo += "if " + e1.getLugar() + op.getOp()
									+ e2.getLugar() + " goto "
									+ e.getVerdadera() + "\n" + "goto "
									+ e.getFalsa() + "\n";
						} else {
							e.setTipo("error_tipo");
							System.out.print("Error de tipo Exp1");
						}
					}
				}
			}
		}

		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R67: Exp2 → Exp3 - R68: Exp2 → Exp3 op_adit Exp2
	 * 
	 * @param e
	 * 
	 * @return
	 *//*
	private boolean exp2(Expresion e, boolean lanzaError, boolean EDT,
			boolean DDS) {
		boolean correcto = false;
		if (!hayError) {
			System.out.println("***Exp2***");
			Expresion e1 = new Expresion(e);
			if (exp3(e1, lanzaError, EDT, DDS)) {
				e.setTipo(e1.getTipo());
				e.setLugar(e1.getLugar());
				correcto = true;
				Operando op = new Operando();
				String b = opAdit(op);
				if (b.equals("SUMA")) {
					Expresion e2 = new Expresion();
					if ((!exp2(e2, lanzaError, EDT, DDS)) && (!hayError)) {
						error = new Error(44, token_act, hayError, consola);
						hayError = true;
					} else {
						compruebaTiposExpSuma(e, e1, e2);
						e.setLugar(temporalNueva());
						codigo += e.getLugar() + " := " + e1.getLugar()
								+ op.getOp() + e2.getLugar() + "\n";
					}
				} else if (b.equals("RESTA")) {
					Expresion e2 = new Expresion();
					if ((!exp2(e2, lanzaError, EDT, DDS)) && (!hayError)) {
						error = new Error(44, token_act, hayError, consola);
						hayError = true;
					} else {
						compruebaTiposResta(e, e1, e2);
						e.setLugar(temporalNueva());
						codigo += e.getLugar() + " := " + e1.getLugar()
								+ op.getOp() + e2.getLugar() + "\n";
					}
				}
			}
		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R69: Exp3 → Exp4 - R70: Exp3 → Exp4 op_mult Exp3
	 * 
	 * @param e
	 * 
	 * @return
	 *//*
	private boolean exp3(Expresion e, boolean lanzaError, boolean EDT,
			boolean DDS) {
		boolean correcto = false;
		if (!hayError) {
			System.out.println("***Exp3***");
			Expresion e1 = new Expresion(e);
			if (exp4(e1, lanzaError, EDT, DDS)) {
				correcto = true;
				e.setTipo(e1.getTipo());
				e.setLugar(e1.getLugar());
				Token tokenAux = token_act;
				Operando op = new Operando();
				if (opMult(op)) {
					Expresion e2 = new Expresion();
					if ((!exp3(e2, lanzaError, EDT, DDS)) && (!hayError)) {
						error = new Error(45, token_act, hayError, consola);
						hayError = true;
					} else {
						e.setTipo(dameTipoExpFinal(tokenAux, e1, e2));
						e.setLugar(temporalNueva());
						codigo += e.getLugar() + " := " + e1.getLugar()
								+ op.getOp() + e2.getLugar() + "\n";
					}
				}
			}
		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R71: Exp4 → Exp5 - R72: Exp4 → Exp5 ^ Exp4
	 * 
	 * @param e
	 * 
	 * @return
	 *//*
	private boolean exp4(Expresion e, boolean lanzaError, boolean EDT,
			boolean DDS) {
		boolean correcto = false;

		if (!hayError) {
			System.out.println("***Exp4***");
			Expresion e1 = new Expresion(e);
			if (exp5(e1, lanzaError, EDT, DDS)) {
				e.setTipo(e1.getTipo());
				e.setLugar(e1.getLugar());
				correcto = true;
				if (compruebaTipo("OP_EXP")) {
					Operando op = new Operando();
					op.setOp(" ^ ");
					Expresion e2 = new Expresion();
					if ((!exp4(e2, lanzaError, EDT, DDS)) && (!hayError)) {
						error = new Error(48, token_act, hayError, consola);
						hayError = true;
					} else {
						compruebaTiposExpSuma(e, e1, e2);
						e.setLugar(temporalNueva());
						codigo += e.getLugar() + " := " + e1.getLugar()
								+ op.getOp() + e2.getLugar() + "\n";
					}
				}
			}
		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R73: Exp5 → neg Exp5 - R74: Exp5 → men Exp5 - R75: Exp5 → naturales -
	 * R76: Exp5 → enteros - R77: Exp5 → reales - R78: Exp5 → naturales+ - R79:
	 * Exp5 → enteros+ - R80: Exp5 → reales+ - R81: Exp5 → naturales∞ - R82:
	 * Exp5 → enteros∞ - R83: Exp5 → reales ∞ - R84: Exp5 → booleanos - R85:
	 * Exp5 → caracteres - R86: Exp5 → Expid - R87: Exp5 → ( Exp0 ) - R88: Exp5
	 * → [elem_vector | rango | Expid]
	 * 
	 * @param e
	 * 
	 * @return
	 *//*
	private boolean exp5(Expresion e, boolean lanzaError, boolean EDT,
			boolean DDS) {
		boolean correcto = false;
		boolean b = false;
		try {
			auxiliar = Tabla_Actual.Copia();
			Expresion e1 = new Expresion(e);
			if (!hayError) {
				Token numer = token_act;
				System.out.println("***Exp5***");
				if (compruebaTipo("Natural")) {
					e.setTipo("naturales");
					e1.setLugar(temporalNueva());
					e.setLugar(e1.getLugar());
					codigo += e1.getLugar() + " := "
							+ Integer.toString((Integer) numer.leeAtributo())
							+ "\n";
					correcto = true;
				} else if (compruebaTipo("Real")) {
					e.setTipo("reales");
					e1.setLugar(temporalNueva());
					e.setLugar(e1.getLugar());
					codigo += e1.getLugar() + " := "
							+ Float.toString((Float) numer.leeAtributo())
							+ "\n";

					correcto = true;
				} else if (compruebaTipo("NaturalInf")) {
					e.setTipo("naturales infinitos");
					e1.setLugar(temporalNueva());
					e.setLugar(e1.getLugar());
					codigo += e.getLugar() + " := "
							+ numer.leeAtributo().toString() + "\n";
					correcto = true;
				} else if (compruebaTipo("Caracter")) {
					e.setTipo("caracteres");
					e1.setLugar(temporalNueva());
					e.setLugar(e1.getLugar());
					codigo += e.getLugar()
							+ " := "
							+ Character.toString((Character) numer
									.leeAtributo()) + "\n";
					correcto = true;
				}

				else if (compruebaAtributo(5) || compruebaAtributo(16)) {
					e.setTipo("booleano");
					if (numer.leeAtributo().equals(5)) {
						if ((EDT == true) && (DDS == false)) {
							e1.setLugar(temporalNueva());
							e.setLugar(e1.getLugar());
							codigo += e1.getLugar() + " := " + 1 + "\n";
						} else if ((EDT == false) && (DDS == true)) {
							codigo += "goto " + e.getVerdadera() + "\n";
						}
					} else if (numer.leeAtributo().equals(16)) {
						if ((EDT == true) && (DDS == false)) {
							e1.setLugar(temporalNueva());
							e.setLugar(e1.getLugar());
							codigo += e1.getLugar() + " := " + 0 + "\n";
						} else if ((EDT == false) && (DDS == true)) {
							codigo += "goto " + e.getFalsa() + "\n";
						}
					}
					correcto = true;
				}

				else if (compruebaAtributo("NEG")) {
					if (exp5(e, lanzaError, EDT, DDS)) {
						if (e.getTipo() == "booleano") {
							e.setTipo("booleano");
							e1.setLugar(temporalNueva());
							e.setLugar(e1.getLugar());
							codigo += e1.getLugar() + " := NOT " + e.getLugar()
									+ "\n";
						} else
							e.setTipo("error_tipo");
						correcto = true;
					} else {
						correcto = false;
						if (!hayError) {
							error = new Error(46, token_act, hayError, consola);
							hayError = true;
						}
					}
				} else if (compruebaAtributo("RESTA")) {
					if (exp5(e, lanzaError, EDT, DDS)) {
						if (e.esTipoNumerico()) {
							if (e.getTipo() == "natural")
								e.setTipo("enteros");
							else if (e.getTipo() == "reales")
								e.setTipo("reales");
							else if (e.getTipo() == "naturales infinitos")
								e.setTipo("enteros infinitos");
						} else
							e.setTipo("error_tipo");
						e1.setLugar(temporalNueva());
						e.setLugar(e1.getLugar());
						codigo += e1.getLugar() + " :=  -" + e.getLugar()
								+ "\n";
						correcto = true;
					} else {
						correcto = false;
						if (!hayError) {
							error = new Error(46, token_act, hayError, consola);
							hayError = true;
						}
					}
				} else if (compruebaAtributo("SUMA")) {
					if (exp5(e, lanzaError, EDT, DDS)) {

						if (!e.esTipoNumerico())
							e.setTipo("error_tipo");
						correcto = true;
					} else {
						correcto = false;
						if (!hayError) {
							error = new Error(46, token_act, hayError, consola);
							hayError = true;
						}
					}
				} else if (expId(e, false)) {
					correcto = true;
					Tabla_Actual = auxiliar.Copia();

					if (compruebaAtributo('(')) {
						if (e.getTipo().equals("error_tipo"))
							b = true;
						ContenidoTS c = Tabla_Actual
								.getContenido((String) auxToken.leeAtributo());
						LinkedList<String> retorno = null;
						LinkedList<String> size = null;
						if (c != null) {
							size = c.getTipoArgs();
							retorno = c.getRetorno();
							if (c.getTipo().equals("procedimiento"))
								e.setTipo("error_tipo");
							// else e.setTipo(listToString(retorno)); //e será
							// de tipo funcion
						} else {
							e.setTipo("error_tipo");
						}
						if (size != null)
							parametros(size);
						if (error_tipo)
							e.setTipo("error_tipo");
						
						 * else { if(c!=null){
						 * if(retorno.size()>1)e.setTipo("error_tipo"); else {
						 * if (!retorno.isEmpty())
						 * e.setTipo(retorno.getFirst()); } } }
						 
						if (!compruebaAtributo(')'))
							if (lanzaError && !hayError) {
								error = new Error(14, token_act, hayError,
										consola);
								hayError = true;
							}
					}
				} else if (compruebaAtributo('(')) {
					if (exp0(e, lanzaError, EDT, DDS)) {
						if (compruebaAtributo(')'))
							correcto = true;
						else if (!hayError) {
							error = new Error(14, token_act, hayError, consola);
							hayError = true;
						}
					} else if (!hayError) {
						error = new Error(47, token_act, hayError, consola);
						hayError = true;
					}
				} else if (compruebaAtributo('[')) {
					Expresion e3 = new Expresion();
					Expresion e2 = new Expresion();
					if (exp0(e3, false, EDT, DDS)) {
						if (compruebaAtributo(',')) {
							if (!elemVector(e, 0))// faltaria mirar el rango
								if (lanzaError && !hayError) {
									error = new Error(49, token_act, hayError,
											consola);
									hayError = true;
								}
						} else if (compruebaTipo("RANGO")) {

							if (exp0(e2, false, EDT, DDS)) {
								String tipo1 = e3.getTipo();
								String tipo2 = e2.getTipo();
								if (!tipoValidoRango(tipo1)
										|| !tipoValidoRango(tipo2)) {
									if (lanzaError && !hayError) {
										error = new Error(115, token_act,
												hayError, consola);// error de
																	// tipos
										hayError = true;
									}
								} else
									e.setTipo("rango");
								if (compruebaAtributo(','))
									rangos(false);
							}

						}
					} else if (!expId(e, lanzaError))
						if (lanzaError && !hayError) {
							error = new Error(49, token_act, hayError, consola);
							hayError = true;
						}
					Tabla_Actual = auxiliar.Copia();
					if (compruebaAtributo(']'))
						if (lanzaError && !hayError) {
							error = new Error(8, token_act, hayError, consola);
							hayError = true;
						}
				}

			}
		} catch (Exception e1) {
			if (!hayError) {
				error = new Error(100, token_act, hayError, consola);
				hayError = true;
			}
			e.setTipo("error_tipo");
			System.out.println("captura de excepciones en exp5");
		}

		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * -R89: Expid → id -R90: Expid → id [elem_vector | rango | Expid] -R91:
	 * Expid → id ‘.’ Expid -R92: Expid → id ↑ Expid -R93: Expid → id (
	 * parámetros )
	 * 
	 * @return
	 *//*
	private boolean expId(Expresion e, boolean lanzaError) {
		boolean correcto = false;
		int i = 0;
		boolean b = false;
		boolean EDT = true;
		boolean DDS = false;

		try {
			// if(expid==false) auxiliar=Tabla_Actual.Copia();
			if (!hayError) {
				System.out.println("***Expid***");
				auxToken = token_act;
				if (compruebaTipo("identificador")) {
					System.out.println("Hemos leído identificador.");
					// // comprobacion de tipos//////
					// llamo a dame numero tipo para saber si es un tipo basico
					// sin que me consuma token
					if (!(e.getTipo().equals("error_tipo"))) {
						ContenidoTS c = Tabla_Actual
								.getContenido((String) auxToken.leeAtributo());
						if (c != null) {
							int j = dameNumeroTipo(c.getTipo());
							if (j != 0) {
								e.setTipo(c.getTipo());
								e.setLugar((String) auxToken.leeAtributo());
								// if(expid==true){Tabla_Actual=auxiliar.Copia();expid=false;}
							} else {
								if (c.getTipo().equals("vector")) { // si es
																	// vector
									e.setTipo(c.getTipo());
								} else if (c.getTipo().equals("puntero")) {
									e.setTipo("puntero");
								} else {// si es registro
									ContenidoTS c1 = Tabla_Actual
											.getContenido(c.getTipo());
									if (c1 != null) {
										if (c1.getTipo().equals("registro"))
											Tabla_Actual = c1.getTablaHija();
										e.setTipo(c.getTipo());
										// expid=true;
									} else {
										// e.setTipo("error_tipo");System.out.println("error de tipo en expid");
										e.setTipo(c.getTipo());
									}
								}
							}
						} else { // / el caso en el que tengas a1.pos y vengas
									// con token pos
							ContenidoTS c1 = Tabla_Actual
									.getContenido((String) auxToken2
											.leeAtributo());
							if (c1 != null) { // accedo al contenido de a1
								ContenidoTS c2 = Tabla_Actual.getContenido(c1
										.getTipo());
								if (c2 != null) {// accedo a la tabla de arista
									if (c2.getTipo().equals("registro"))// si el
																		// tipo
																		// es
																		// registro
										Tabla_Actual = c2.getTablaHija();// me
																			// meto
																			// en
																			// su
																			// tabla
									// expid=true;
									ContenidoTS c3 = Tabla_Actual
											.getContenido((String) auxToken
													.leeAtributo());
									if (c3 != null) {
										int j = dameNumeroTipo(c3.getTipo());
										if (j != 0) {
											e.setTipo(c3.getTipo());
											// Tabla_Actual=auxiliar.Copia();
										} else {
											ContenidoTS c4 = Tabla_Actual
													.getContenido((String) auxToken
															.leeAtributo());
											if (c4 != null) {
												Tabla_Actual = c4
														.getTablaHija();
												e.setTipo(c4.getTipo());
											}
											// expid=true;
										}
									} else {
										e.setTipo("error_tipo");
										System.out
												.println("error de tipo en expid");
									}
								} else {
									e.setTipo("error_tipo");
									System.out
											.println("error de tipo en expid");
								}
							} else {
								e.setTipo("error_tipo");
								System.out.println("error de tipo en expid");
							}
							// {e.setTipo("error_tipo");System.out.println("error de tipo en expid");}
						}
					}
					// /////////////////////////////
					if (compruebaAtributo('.')) {
						if (!expId(e, lanzaError)) {
							if (lanzaError && !hayError) {
								error = new Error(41, token_act, hayError,
										consola);
								hayError = true;
							}
						}
					} else if (compruebaTipo("OP_ACCESO")) {
						// if (!expId(e, lanzaError)){
						if (lanzaError && !hayError) {
							error = new Error(41, token_act, hayError, consola);
							hayError = true;
						} else {
							LinkedList<ContenidoTS> c = (LinkedList<ContenidoTS>) Tabla_Actual
									.getContenido(
											(String) auxToken.leeAtributo())
									.getPuntero().clone();
							c.removeFirst();
							e.setTipo(c.getFirst().getTipo());
						}
						// }
					} else if (compruebaAtributo('[')) {
						Expresion e1 = new Expresion();
						Token expresionVector = auxToken;
						if (exp0(e1, false, EDT, DDS)) {
							String tipoExp = e1.getTipo();
							if (!tipoValidoRango(tipoExp)) {
								e.setTipo("error_tipo");
								if (lanzaError && !hayError) {
									error = new Error(115, token_act, hayError,
											consola);
									hayError = true;
								}
							}
							// if(!(e1.getTipo().equals("naturales")||
							// e1.getTipo().equals("enteros"))){e.setTipo("error_tipo");
							// System.out.println("mal acceso al vector");}
							else {
								ContenidoTS c;
								try {
									c = Tabla_Actual
											.getContenido((String) expresionVector
													.leeAtributo());
									e.setTipo(c.getTipoBase());
								} catch (Exception e3) {
								}
							}

							if (compruebaAtributo(',')) {
								ContenidoTS c = Tabla_Actual
										.getContenido((String) auxToken
												.leeAtributo());

								if (c != null && c.getTipo().equals("vector"))
									i = c.getTablaHija().getTs().size() - 1;
								b = elemVector(e, i);
								if (!b)
									if (lanzaError && !hayError) {
										error = new Error(49, token_act,
												hayError, consola);
										hayError = true;
									}

							} else if (compruebaTipo("RANGO")) {
								Expresion e2 = new Expresion();
								if (exp0(e2, false, EDT, DDS)) {
									String tipo1 = e1.getTipo();
									String tipo2 = e2.getTipo();
									if (!tipoValidoRango(tipo1)
											|| !tipoValidoRango(tipo2)) {
										if (lanzaError && !hayError) {
											error = new Error(115, token_act,
													hayError, consola);// error
																		// de
																		// tipos
											hayError = true;
										}
									}
									if (compruebaAtributo(','))
										rangos(false);
								}

							}
						} else if (!expId(e, lanzaError))
							if (lanzaError && !hayError) {
								error = new Error(49, token_act, hayError,
										consola);
								hayError = true;
							}
						if (!compruebaAtributo(']'))
							if (lanzaError && !hayError) {
								error = new Error(8, token_act, hayError,
										consola);
								hayError = true;
							}
					} else if (compruebaTipo("identificador"))
						if (lanzaError && !hayError) {
							error = new Error(50, token_act, hayError, consola);
							hayError = true;
						}

					correcto = true;
				} else if (compruebaAtributoNoAvanzado("\\n")
						|| compruebaAtributoNoAvanzado(":=")) {
					compruebaSaltoLinea();
					correcto = true;
				}

				else {
					if (lanzaError && !hayError) {
						error = new Error(41, token_act, hayError, consola);
						hayError = true;
					}

				}
			}
			if (!b && i != 0)
				e.setTipo("error_tipo");
		} catch (Exception e1) {
			e.setTipo("error_tipo");
			if (!hayError) {
				error = new Error(100, token_act, hayError, consola);
				hayError = true;
			}
			System.out.println("captura de excepciones en expid");
		}

		return correcto;
	}

	*//**
	 * 
	 * @param lanzaError
	 * @return
	 *//*
	private boolean expIdConIdLeido(Expresion e, boolean lanzaError) {
		auxiliar = Tabla_Actual.Copia();
		boolean correcto = false;
		int i = 0;
		boolean b = false;

		boolean EDT = true;
		boolean DDS = false;
		try {
			if (!hayError) {
				System.out.println("***ExpidConIdLeido***");
				// if (compruebaTipo("identificador")) {
				// System.out.println("Hemos leído identificador.");
				if (compruebaAtributo('(')) {
					parametros(null);
					if (!compruebaAtributo(')'))
						if (lanzaError && !hayError) {
							error = new Error(14, token_act, hayError, consola);
							hayError = true;
						}
				} else if (compruebaAtributo('.')) {
					if (!expId(e, lanzaError)) {
						if (lanzaError && !hayError) {
							error = new Error(41, token_act, hayError, consola);
							hayError = true;
						}
					}
					Tabla_Actual = auxiliar.Copia();
				} else if (compruebaTipo("OP_ACCESO")) {
					// if (!expId(e, lanzaError)){
					if (lanzaError && !hayError) {
						error = new Error(41, token_act, hayError, consola);
						hayError = true;
					} else {
						try {
							LinkedList<ContenidoTS> c = (LinkedList<ContenidoTS>) Tabla_Actual
									.getContenido(
											(String) auxToken2.leeAtributo())
									.getPuntero().clone();
							c.removeFirst();
							e.setTipo(c.getFirst().getTipo());
						} catch (Exception e3) {
						}
					}
					// }
				} else if (compruebaAtributo('[')) {
					Expresion e1 = new Expresion();
					if (exp0(e1, false, EDT, DDS)) {
						String tipoExp = e1.getTipo();
						if (!tipoValidoRango(tipoExp)) {
							e.setTipo("error_tipo");
							if (lanzaError && !hayError) {
								error = new Error(115, token_act, hayError,
										consola);
								hayError = true;
							}
						}
						// if(!(e1.getTipo().equals("naturales")||
						// e1.getTipo().equals("reales"))){e.setTipo("error_tipo");
						// System.out.println("mal acceso al vector");}
						else {
							ContenidoTS c;
							try {
								c = Tabla_Actual
										.getContenido((String) auxToken2
												.leeAtributo());
								e.setTipo(c.getTipoBase());
							} catch (Exception e3) {
							}
						}
						if (compruebaAtributo(',')) {
							ContenidoTS c = Tabla_Actual
									.getContenido((String) auxToken2
											.leeAtributo());

							if (c != null && c.getTipo().equals("vector"))
								i = c.getTablaHija().getTs().size() - 1;

							b = elemVector(e, i);
							if (!b)
								if (lanzaError && !hayError) {
									error = new Error(49, token_act, hayError,
											consola);
									hayError = true;
								}
						} else if (compruebaTipo("RANGO")) {
							Expresion e2 = new Expresion();
							if (exp0(e2, false, EDT, DDS)) {
								String tipo1 = e1.getTipo();
								String tipo2 = e2.getTipo();
								if (!tipoValidoRango(tipo1)
										|| !tipoValidoRango(tipo2)) {
									if (lanzaError && !hayError) {
										error = new Error(115, token_act,
												hayError, consola);// error de
																	// tipos
										hayError = true;
									}
								}
								if (compruebaAtributo(','))
									rangos(false);
							}

						}
					} else if (!expId(e, lanzaError))
						if (lanzaError && !hayError) {
							error = new Error(49, token_act, hayError, consola);
							hayError = true;
						}
					if (!compruebaAtributo(']'))
						if (lanzaError && !hayError) {
							error = new Error(8, token_act, hayError, consola);
							hayError = true;
						}
				} else if (compruebaTipo("identificador"))
					if (lanzaError && !hayError) {
						error = new Error(50, token_act, hayError, consola);
						hayError = true;
					}

				correcto = true;
			}
			if (!b && i != 0)
				e.setTipo("error_tipo");
		} catch (Exception e1) {
			if (!hayError) {
				error = new Error(100, token_act, hayError, consola);
				hayError = true;
			}
			e.setTipo("error_tipo");
			System.out.println("captura de excepciones en expid con id leido");
		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R94: elem_vector → Exp0 [, elem_vector]
	 * 
	 * @param e
	 * @param i
	 * 
	 * @return
	 *//*
	private boolean elemVector(Expresion e, int i) {
		boolean correcto = false;
		try {
			if (!hayError) {
				boolean EDT = true;
				boolean DDS = false;
				System.out.println("***ElemVector***");
				Expresion e1 = new Expresion();
				if (exp0(e1, false, EDT, DDS)) {
					if (!(e1.getTipo().equals("naturales") || e1.getTipo()
							.equals("enteros"))) {
						e.setTipo("error_tipo");
						System.out.println("error tipo en ElemVector");
					}
					correcto = true;
					i--;
					if (compruebaAtributo(',')) {
						if (elemVector(e1, i))
							correcto = true;
						else
							correcto = false;
						if (e1.getTipo().equals("error_tipo"))
							e.setTipo("error_tipo");
					}
				} else if (!hayError) {
					error = new Error(29, token_act, hayError, consola);
					hayError = true;
					correcto = false;
				}
			}
			if (i != 0) {
				e.setTipo("error_tipo");
				System.out.println("error tipo en ElemVector");
			}
		} catch (Exception e1) {
			if (!hayError) {
				error = new Error(100, token_act, hayError, consola);
				hayError = true;
			}
			e.setTipo("error_tipo");
			System.out.println("captura de excepciones en ElemVector");
		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R95: rango → Exp0 ‘.’‘.’ Exp0
	 * 
	 * @return
	 *//*
	private boolean rango(boolean lanzaError) {
		boolean correcto = false;
		boolean EDT = true;
		boolean DDS = false;
		try {
			if (!hayError) {
				System.out.println("***Rango***");
				posini = token_act;
				Expresion e1 = new Expresion();
				Expresion e2 = new Expresion();
				if (exp0(e1, lanzaError, EDT, DDS)) {
					if (compruebaTipo("RANGO")) {
						posfin = token_act;
						if (esTipoRango) {
							listaRangoIni.add(posini.leeAtributo()); // no le
																		// hago
																		// casting
																		// a
																		// integer
							listaRangoFin.add(posfin.leeAtributo()); // pq puede
																		// q sea
																		// n y
																		// entonces
																		// lanza
																		// excepcion
						}
						// contRangos++;

						if (exp0(e2, lanzaError, EDT, DDS)) {
							String tipo1 = e1.getTipo();
							String tipo2 = e2.getTipo();
							if (!tipoValidoRango(tipo1)
									|| !tipoValidoRango(tipo2)) {
								if (lanzaError && !hayError) {
									error = new Error(115, token_act, hayError,
											consola);// error de tipos
									hayError = true;
								}
							}
							correcto = true;
						}
					}
				}
			}
		} catch (Exception e1) {
			if (!hayError) {
				error = new Error(30, token_act, hayError, consola);
				hayError = true;
			}
			lanzaError = true;
			System.out.println("captura de excepciones en rango");
		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R97: op_adit → + | -
	 * 
	 * @return
	 *//*
	private String opAdit(Operando op) {
		String tipoOp = "";
		if (!hayError) {
			System.out.println("***OpAdit***");
			if (compruebaAtributo("SUMA")) {
				op.setOp(" + ");
				tipoOp = "SUMA";
			} else if (compruebaAtributo("RESTA")) {
				op.setOp(" - ");
				tipoOp = "RESTA";
			}
		}
		return tipoOp;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R98: op_mult → * | / | div | mod
	 * 
	 * @return
	 *//*
	private boolean opMult(Operando op) {
		boolean correcto = false;
		if (!hayError) {
			System.out.println("***OpMult***");
			if (compruebaAtributo(8)) {
				op.setOp(" div ");
				correcto = true;
			} else if (compruebaAtributo(30)) {
				op.setOp(" mod ");
				correcto = true;
			} else if (compruebaAtributo("MULT")) {
				op.setOp(" * ");
				correcto = true;
			} else if (compruebaAtributo("DIV")) {
				op.setOp(" / ");
				correcto = true;
			}
		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R99: op_relacional → ≤ |≥ |> |<
	 * 
	 * @return
	 *//*
	private boolean opRelacional(Operando op) {
		boolean correcto = false;
		if (!hayError) {

			System.out.println("***OpRelacional***");

			if (compruebaAtributo("MENIGU")) {
				op.setOp(" ≤ ");
				correcto = true;
			} else if (compruebaAtributo("MAYIGU")) {
				op.setOp(" ≥ ");
				correcto = true;
			} else if (compruebaAtributo("MENOR")) {
				op.setOp(" < ");
				correcto = true;
			} else if (compruebaAtributo("MAYOR")) {
				op.setOp(" > ");
				correcto = true;
				hayAsigMult = false;
			}

		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R100: op_igual → ≠ | =
	 * 
	 * @return
	 *//*
	private boolean opIgual(Operando op) {
		boolean correcto = false;
		if (!hayError) {
			System.out.println("***OpIgual***");
			if (compruebaAtributo("DIST")) {
				op.setOp(" ≠ ");
				correcto = true;
			} else if (compruebaAtributo("IGUA")) {
				op.setOp(" = ");
				correcto = true;
			}
		}
		return correcto;
	}

	*//**
	 * Método que evalua las siguientes reglas de la gramática:
	 * 
	 * - R101: op_log → V |∧ |Vc |∧c
	 * 
	 * @return
	 *//*
	private boolean opLog(Operando op) {
		boolean correcto = false;
		if (!hayError) {
			System.out.println("***OpLog***");
			if (compruebaAtributo("CONJ")) {
				op.setOp(" ∧ ");
				correcto = true;
			} else if (compruebaAtributo("CONJCORT")) {
				op.setOp(" ∧c ");
				correcto = true;
			} else if (compruebaAtributo("DISY")) {
				op.setOp(" V  ");
				correcto = true;
			} else if (compruebaAtributo("DISYCORT")) {
				op.setOp(" Vc ");
				correcto = true;
			}
		}
		return correcto;
	}

	*//**
	 * Método que a partir del atributo de la palabra reservada te devuelve el
	 * tipo
	 * 
	 * @return
	 *//*
	private String dameNombreTipo(int num) {
		String tipo;
		switch (num) {
		case 2:
			tipo = "booleano";
			break;
		case 3:
			tipo = "caracteres";
			break;
		case 11:
			tipo = "elemento";
			break;
		case 12:
			tipo = "enteros";
			break;
		case 13:
			tipo = "enteros positivos";
			break;
		case 14:
			tipo = "enteros infinitos";
			break;
		case 32:
			tipo = "naturales";
			break;
		case 33:
			tipo = "naturales positivos";
			break;
		case 34:
			tipo = "naturales infinitos";
			break;
		case 36:
			tipo = "números";
			break;
		case 41:
			tipo = "puntero";
			break;
		case 42:
			tipo = "reales";
			break;
		case 43:
			tipo = "reales positivos";
			break;
		case 44:
			tipo = "reales infinitos";
			break;
		case 45:
			tipo = "registro";
			break;
		default:
			tipo = "";
			break;
		}
		return tipo;
	}

	private int dameNumeroTipo(String tipo) {
		int num;
		if (tipo.equals("booleano"))
			num = 2;
		else if (tipo.equals("caracteres"))
			num = 3;
		else if (tipo.equals("elemento"))
			num = 11;
		else if (tipo.equals("enteros"))
			num = 12;
		else if (tipo.equals("enteros positivos"))
			num = 13;
		else if (tipo.equals("enteros infinitos"))
			num = 14;
		else if (tipo.equals("naturales"))
			num = 32;
		else if (tipo.equals("naturales positivos"))
			num = 33;
		else if (tipo.equals("naturales infinitos"))
			num = 34;
		else if (tipo.equals("números"))
			num = 36;
		else if (tipo.equals("puntero"))
			num = 41;
		else if (tipo.equals("reales"))
			num = 42;
		else if (tipo.equals("reales positivos"))
			num = 43;
		else if (tipo.equals("reales infinitos"))
			num = 44;
		else if (tipo.equals("registro"))
			num = 45;
		else
			num = 0;
		return num;
	}

	*//**
	 * Método auxiliar que, a partir de dos expresiones de entrada, en funcion
	 * de sus tipos, y en operaciones de resultado positivo, devuelve cierto si
	 * el tipo de la expresion final es real.
	 * 
	 * @return
	 *//*
	private boolean expReal(Expresion e1, Expresion e2) {
		boolean esReal = false;
		if (((e1.getTipo().equals("reales")) && ((e2.getTipo()
				.equals("naturales positivos"))
				|| (e2.getTipo().equals("enteros"))
				|| (e2.getTipo().equals("enteros positivos"))
				|| (e2.getTipo().equals("reales positivos")) || (e2.getTipo()
				.equals("naturales"))))
				|| ((e2.getTipo().equals("reales")) && ((e1.getTipo()
						.equals("naturales positivos"))
						|| (e1.getTipo().equals("enteros"))
						|| (e1.getTipo().equals("enteros positivos"))
						|| (e1.getTipo().equals("reales positivos")) || (e1
							.getTipo().equals("naturales")))))
			esReal = true;

		else
			esReal = false;
		return esReal;
	}

	*//**
	 * Método auxiliar que, a partir de dos expresiones de entrada, en funcion
	 * de sus tipos, y en operaciones de resultado positivo, devuelve cierto si
	 * el tipo de la expresion final es real Inf.
	 * 
	 * @return
	 *//*
	private boolean expRealInf(Expresion e1, Expresion e2) {
		boolean esRealInf = false;
		if (((e1.getTipo().equals("reales infinitos")) && ((e2.getTipo()
				.equals("naturales positivos"))
				|| (e2.getTipo().equals("naturales infinitos"))
				|| (e2.getTipo().equals("enteros"))
				|| (e2.getTipo().equals("enteros positivos"))
				|| (e2.getTipo().equals("enteros infinitos"))
				|| (e2.getTipo().equals("reales positivos"))
				|| (e2.getTipo().equals("naturales")) || (e2.getTipo()
				.equals("reales"))))
				|| ((e2.getTipo().equals("reales infinitos")) && ((e1.getTipo()
						.equals("naturales positivos"))
						|| (e1.getTipo().equals("naturales infinitos"))
						|| (e1.getTipo().equals("enteros"))
						|| (e1.getTipo().equals("enteros positivos"))
						|| (e1.getTipo().equals("enteros infinitos"))
						|| (e1.getTipo().equals("reales positivos"))
						|| (e1.getTipo().equals("naturales")) || (e1.getTipo()
						.equals("reales"))))
				|| ((e1.getTipo().equals("reales")) && ((e2.getTipo()
						.equals("naturales infinitos")) || (e2.getTipo()
						.equals("enteros infinitos"))))
				|| ((e2.getTipo().equals("reales")) && ((e1.getTipo()
						.equals("naturales infinitos")) || (e1.getTipo()
						.equals("enteros infinitos"))))
				|| ((e1.getTipo().equals("reales positivos")) && ((e2.getTipo()
						.equals("naturales infinitos")) || (e2.getTipo()
						.equals("enteros infinitos"))))
				|| ((e2.getTipo().equals("reales positivos")) && ((e1.getTipo()
						.equals("naturales infinitos")) || (e1.getTipo()
						.equals("enteros infinitos")))))
			esRealInf = true;

		else
			esRealInf = false;
		return esRealInf;
	}

	*//**
	 * Método auxiliar que, a partir de dos expresiones de entrada, en funcion
	 * de sus tipos, y en operaciones de resultado positivo, devuelve cierto si
	 * el tipo de la expresion final es real Pos.
	 * 
	 * @return
	 *//*
	private boolean expRealPos(Expresion e1, Expresion e2) {
		boolean esRealPos = false;
		if (((e1.getTipo().equals("reales positivos")) && ((e2.getTipo()
				.equals("naturales positivos"))
				|| (e2.getTipo().equals("enteros"))
				|| (e2.getTipo().equals("enteros positivos")) || (e2.getTipo()
				.equals("naturales"))))
				|| ((e2.getTipo().equals("reales positivos")) && ((e1.getTipo()
						.equals("naturales positivos"))
						|| (e1.getTipo().equals("enteros"))
						|| (e1.getTipo().equals("enteros positivos")) || (e1
							.getTipo().equals("naturales")))))
			esRealPos = true;

		else
			esRealPos = false;
		return esRealPos;
	}

	*//**
	 * Método auxiliar que, a partir de dos expresiones de entrada, en funcion
	 * de sus tipos, y en operaciones de resultado positivo, devuelve cierto si
	 * el tipo de la expresion final es Entero.
	 * 
	 * @return
	 *//*
	private boolean expEntero(Expresion e1, Expresion e2) {
		boolean esEntero = false;
		if (((e1.getTipo().equals("enteros")) && ((e2.getTipo()
				.equals("naturales positivos"))
				|| (e2.getTipo().equals("enteros positivos")) || (e2.getTipo()
				.equals("naturales"))))
				|| ((e2.getTipo().equals("enteros")) && ((e1.getTipo()
						.equals("naturales positivos"))
						|| (e1.getTipo().equals("enteros positivos")) || (e1
							.getTipo().equals("naturales")))))
			esEntero = true;

		else
			esEntero = false;
		return esEntero;
	}

	*//**
	 * Método auxiliar que, a partir de dos expresiones de entrada, en funcion
	 * de sus tipos, y en operaciones de resultado positivo, devuelve cierto si
	 * el tipo de la expresion final es Entero Pos.
	 * 
	 * @return
	 *//*
	private boolean expEnteroPos(Expresion e1, Expresion e2) {
		boolean esEnteroPos = false;
		if (((e1.getTipo().equals("enteros positivos")) && ((e2.getTipo()
				.equals("naturales positivos")) || (e2.getTipo()
				.equals("naturales"))))
				|| ((e2.getTipo().equals("enteros positivos")) && ((e1
						.getTipo().equals("naturales positivos")) || (e1
						.getTipo().equals("naturales")))))
			esEnteroPos = true;

		else
			esEnteroPos = false;
		return esEnteroPos;
	}

	*//**
	 * Método auxiliar que, a partir de dos expresiones de entrada, en funcion
	 * de sus tipos, y en operaciones de resultado positivo, devuelve cierto si
	 * el tipo de la expresion final es Entero Inf.
	 * 
	 * @return
	 *//*
	private boolean expEnteroInf(Expresion e1, Expresion e2) {
		boolean esEnteroInf = false;
		if (((e1.getTipo().equals("enteros infinitos")) && ((e2.getTipo()
				.equals("naturales positivos"))
				|| (e2.getTipo().equals("naturales infinitos"))
				|| (e2.getTipo().equals("enteros"))
				|| (e2.getTipo().equals("enteros positivos")) || (e2.getTipo()
				.equals("naturales"))))
				|| ((e2.getTipo().equals("enteros infinitos")) && ((e1
						.getTipo().equals("naturales positivos"))
						|| (e1.getTipo().equals("naturales infinitos"))
						|| (e1.getTipo().equals("enteros"))
						|| (e1.getTipo().equals("enteros positivos")) || (e1
							.getTipo().equals("naturales"))))
				|| ((e1.getTipo().equals("enteros")) && (e2.getTipo()
						.equals("naturales infinitos")))
				|| ((e2.getTipo().equals("enteros")) && (e1.getTipo()
						.equals("naturales infinitos")))
				|| ((e1.getTipo().equals("enteros positivos")) && (e2.getTipo()
						.equals("naturales infinitos")))
				|| ((e2.getTipo().equals("enteros positivos")) && (e1.getTipo()
						.equals("naturales infinitos"))))
			esEnteroInf = true;

		else
			esEnteroInf = false;
		return esEnteroInf;
	}

	*//**
	 * Método auxiliar que, a partir de dos expresiones de entrada, en funcion
	 * de sus tipos, y en operaciones de resultado positivo, devuelve cierto si
	 * el tipo de la expresion final es Natural Pos.
	 * 
	 * @return
	 *//*
	private boolean expNatPos(Expresion e1, Expresion e2) {
		boolean esNatPos = false;
		if (((e1.getTipo().equals("naturales positivos")) && (e2.getTipo()
				.equals("naturales")))
				|| ((e1.getTipo().equals("naturales")) && (e2.getTipo()
						.equals("naturales positivos"))))
			esNatPos = true;

		else
			esNatPos = false;
		return esNatPos;
	}

	*//**
	 * Método auxiliar que, a partir de dos expresiones de entrada, en funcion
	 * de sus tipos, y en operaciones de resultado positivo, devuelve cierto si
	 * el tipo de la expresion final es Natural Inf.
	 * 
	 * @return
	 *//*
	private boolean expNatInf(Expresion e1, Expresion e2) {
		boolean esNatInf = false;
		if (((e1.getTipo().equals("naturales infinitos")) && ((e2.getTipo()
				.equals("naturales positivos")) || (e2.getTipo()
				.equals("naturales"))))
				|| ((e2.getTipo().equals("naturales infinitos")) && ((e1
						.getTipo().equals("naturales positivos")) || (e1
						.getTipo().equals("naturales")))))
			esNatInf = true;

		else
			esNatInf = false;
		return esNatInf;
	}

	*//**
	 * Método auxiliar que, a partir de dos expresiones de entrada, en funcion
	 * de sus tipos, y en operaciones de resultado positivo, devuelve cierto si
	 * el tipo de la expresion final es Numero.
	 * 
	 * @return
	 *//*
	private boolean expNumero(Expresion e1, Expresion e2) {
		boolean esNumero = false;
		if (((e1.getTipo().equals("números")) && ((e2.getTipo()
				.equals("naturales positivos"))
				|| (e2.getTipo().equals("naturales infinitos"))
				|| (e2.getTipo().equals("enteros"))
				|| (e2.getTipo().equals("enteros positivos"))
				|| (e2.getTipo().equals("enteros infinitos"))
				|| (e2.getTipo().equals("reales positivos"))
				|| (e2.getTipo().equals("naturales"))
				|| (e2.getTipo().equals("reales")) || (e2.getTipo()
				.equals("reales infinitos"))))
				|| ((e2.getTipo().equals("números")) && ((e1.getTipo()
						.equals("naturales positivos"))
						|| (e1.getTipo().equals("naturales infinitos"))
						|| (e1.getTipo().equals("enteros"))
						|| (e1.getTipo().equals("enteros positivos"))
						|| (e1.getTipo().equals("enteros infinitos"))
						|| (e1.getTipo().equals("reales positivos"))
						|| (e1.getTipo().equals("naturales"))
						|| (e1.getTipo().equals("reales")) || (e1.getTipo()
						.equals("reales infinitos")))))
			esNumero = true;

		else
			esNumero = false;
		return esNumero;
	}

	*//**
	 * Método que a partir del atributo de la palabra reservada te comprueba si
	 * es un tipo basico
	 * 
	 * @return
	 *//*
	private boolean esTipoBasico() {
		if (compruebaAtributo(2) || compruebaAtributo(3)
				|| compruebaAtributo(11) || compruebaAtributo(12)
				|| compruebaAtributo(13) || compruebaAtributo(14)
				|| compruebaAtributo(32) || compruebaAtributo(33)
				|| compruebaAtributo(34) || compruebaAtributo(36)
				|| compruebaAtributo(42) || compruebaAtributo(43)
				|| compruebaAtributo(44))
			return true;
		else
			return false;
	}

	*//**
	 * Método que a partir de las expresiones e1 y e1, decide el valor de la
	 * expresion conjunta en e, como salida, para exponeciales y suma
	 * 
	 * @return
	 *//*
	private void compruebaTiposExpSuma(Expresion e, Expresion e1, Expresion e2) {
		if (e1.esTipoNumerico() && e2.esTipoNumerico()) {
			if (e1.getTipo().equals(e2.getTipo()))
				e.setTipo(e1.getTipo());

			else if (expReal(e1, e2))
				e.setTipo("reales");

			else if (expRealPos(e1, e2))
				e.setTipo("reales positivos");

			else if (expRealInf(e1, e2))
				e.setTipo("reales infinitos");

			else if (expEntero(e1, e2))
				e.setTipo("enteros");

			else if (expEnteroPos(e1, e2))
				e.setTipo("enteros positivos");

			else if (expEnteroInf(e1, e2))
				e.setTipo("enteros infinitos");

			else if (expNatPos(e1, e2))
				e.setTipo("naturales positivos");

			else if (expNatInf(e1, e2))
				e.setTipo("naturales infinitos");

			else if (expNumero(e1, e2))
				e.setTipo("números");

			else if (!hayError) {
				e.setTipo("error_tipo");
				error = new Error(111, token_act, hayError, consola);
				hayError = true;
			}

		} else if (!hayError) {
			e.setTipo("error_tipo");
			error = new Error(110, token_act, hayError, consola);
			hayError = true;
		}

	}

	*//**
	 * Método que a partir de las expresiones e1 y e1, decide el valor de la
	 * expresion conjunta en e, como salida, para restas
	 * 
	 * @return
	 *//*
	private void compruebaTiposResta(Expresion e, Expresion e1, Expresion e2) {
		if (e1.esTipoNumerico() && e2.esTipoNumerico()) {
			if (e1.getTipo().equals(e2.getTipo()))
				e.setTipo(e1.getTipo());

			else if (expReal(e1, e2) || expRealPos(e1, e2))
				e.setTipo("reales");

			else if (expRealInf(e1, e2))
				e.setTipo("reales infinitos");

			else if (expEntero(e1, e2) || expEnteroPos(e1, e2)
					|| expNatPos(e1, e2))
				e.setTipo("enteros");

			else if (expEnteroInf(e1, e2) || expNatInf(e1, e2))
				e.setTipo("enteros infinitos");

			else if (expNumero(e1, e2))
				e.setTipo("números");

			else if (!hayError) {
				e.setTipo("error_tipo");
				error = new Error(113, token_act, hayError, consola);
				hayError = true;
			}

		} else if (!hayError) {
			e.setTipo("error_tipo");
			error = new Error(112, token_act, hayError, consola);
			hayError = true;
		}
	}

	private String dameTipoExpFinal(Token tokenAux, Expresion e1, Expresion e2) {
		String resultado = "error_tipo";
		String s = tokenAux.leeAtributo().getClass().getName();

		if (!s.equals("java.lang.String")) {
			if ((Integer) tokenAux.leeAtributo() == 8) {// div
				if ((e1.getTipo().equals("reales infinitos")
						|| e1.getTipo().equals("reales")
						|| e1.getTipo().equals("reales positivos")
						|| e1.getTipo().equals("enteros infinitos")
						|| e1.getTipo().equals("enteros")
						|| e1.getTipo().equals("enteros positivoss")
						|| e1.getTipo().equals("naturales infinitos")
						|| e1.getTipo().equals("naturales positivos")
						|| e1.getTipo().equals("naturales") || e1.getTipo()
						.equals("números"))
						&& (e2.getTipo().equals("reales infinitos")
								|| e2.getTipo().equals("reales")
								|| e2.getTipo().equals("reales positivos")
								|| e2.getTipo().equals("enteros infinitos")
								|| e2.getTipo().equals("enteros")
								|| e2.getTipo().equals("enteros positivos")
								|| e2.getTipo().equals("naturales infinitos")
								|| e2.getTipo().equals("naturales positivos")
								|| e2.getTipo().equals("naturales") || e2
								.getTipo().equals("números")))

					resultado = "enteros";
			}

			else if ((Integer) tokenAux.leeAtributo() == 30) {// mod
				if ((e1.getTipo().equals("reales infinitos")
						|| e1.getTipo().equals("reales")
						|| e1.getTipo().equals("reales positivos")
						|| e1.getTipo().equals("enteros infinitos")
						|| e1.getTipo().equals("enteros")
						|| e1.getTipo().equals("enteros positivoss")
						|| e1.getTipo().equals("naturales infinitos")
						|| e1.getTipo().equals("naturales positivos")
						|| e1.getTipo().equals("naturales") || e1.getTipo()
						.equals("números"))
						&& (e2.getTipo().equals("reales infinitos")
								|| e2.getTipo().equals("reales")
								|| e2.getTipo().equals("reales positivos")
								|| e2.getTipo().equals("enteros infinitos")
								|| e2.getTipo().equals("enteros")
								|| e2.getTipo().equals("enteros positivos")
								|| e2.getTipo().equals("naturales infinitos")
								|| e2.getTipo().equals("naturales positivos")
								|| e2.getTipo().equals("naturales") || e2
								.getTipo().equals("números")))

					resultado = "enteros positivos";

			}
		}

		else if (tokenAux.leeAtributo().equals("MULT")) {// '*'
			if (e1.getTipo().equals(e2.getTipo()))
				resultado = e1.getTipo();

			else if (expReal(e1, e2))
				resultado = "reales";

			else if (expRealInf(e1, e2))
				resultado = "reales infinitos";

			else if (expRealPos(e1, e2))
				resultado = "reales positivos";

			else if (expEnteroInf(e1, e2))
				resultado = "enteros infinitos";

			else if (expEntero(e1, e2))
				resultado = "enteros";

			else if (expEntero(e1, e2))
				resultado = "enteros positivos";

			else if (expNatInf(e1, e2))
				resultado = "naturales infinitos";

			else if (expNatPos(e1, e2))
				resultado = "naturales positivos";

			else if (expNumero(e1, e2))
				resultado = "números";
		}

		else if (tokenAux.leeAtributo().equals("DIV")) {// '/'
			if ((e1.getTipo().equals("reales infinitos")
					|| e1.getTipo().equals("reales")
					|| e1.getTipo().equals("reales positivos")
					|| e1.getTipo().equals("enteros infinitos")
					|| e1.getTipo().equals("enteros")
					|| e1.getTipo().equals("enteros positivoss")
					|| e1.getTipo().equals("naturales infinitos")
					|| e1.getTipo().equals("naturales positivos")
					|| e1.getTipo().equals("naturales") || e1.getTipo().equals(
					"números"))
					&& (e2.getTipo().equals("reales infinitos")
							|| e2.getTipo().equals("reales")
							|| e2.getTipo().equals("reales positivos")
							|| e2.getTipo().equals("enteros infinitos")
							|| e2.getTipo().equals("enteros")
							|| e2.getTipo().equals("enteros positivos")
							|| e2.getTipo().equals("naturales infinitos")
							|| e2.getTipo().equals("naturales positivos")
							|| e2.getTipo().equals("naturales") || e2.getTipo()
							.equals("números")))

				resultado = "reales infinitos";
		}

		return resultado;
	}

	private int dameTamañoRango(Integer r1, Integer r2) {
		if ((r1 >= 0) && (r2 >= 0)) {
			int aux = r1 - r2 + 1;
			if (aux > 0) {
				return aux;
			} else if (!hayError) {
				error = new Error(102, token_act, hayError, consola);// Rango
																		// negativo
																		// o
																		// nulo
				hayError = true;
			}
		} else if (!hayError) {
			error = new Error(103, token_act, hayError, consola);// Uno de los
																	// limites
																	// es
																	// negativo
			hayError = true;
		}
		return -1;
	}

	private int dameTamTipoBasico(Integer num) {
		int tam;
		switch (num) {
		case 2: // booleano
			tam = 8;
			break;
		case 3: // caracteres
			tam = 8;
			break;
		case 11:// elemento
			tam = 8;
			break;
		case 12:// enteros
			tam = 4;
			break;
		case 13:// enteros positivos
			tam = 4;
			break;
		case 14:// enteros infinitos
			tam = 4;
			break;
		case 32:// naturales
			tam = 4;
			break;
		case 33:// naturales positivos
			tam = 4;
			break;
		case 34:// naturales infinitos
			tam = 8;
			break;
		case 36:// números
			tam = 8;
			break;
		case 41:// puntero
			tam = 8;
			break;
		case 42:// reales
			tam = 8;
			break;
		case 43:// reales positivos
			tam = 8;
			break;
		case 44:// reales infinitos
			tam = 8;
			break;
		default:
			tam = 0;
			break;
		}
		return tam;
	}

	private int dameTamId(Token idAux) {
		if (esTipoTipo(idAux)) {
			return Tabla_Actual.getContenido((String) idAux.leeAtributo())
					.getTamaño();
		} else
			return -1;
	}

	private boolean estiponumerico(String first) {

		return first.equals("naturales") || first.equals("enteros")
				|| first.equals("naturales infinitos")
				|| first.equals("enteros infinitos");
	}

	private String listToString(LinkedList<String> l) {
		String resultado = "";
		LinkedList<String> list = (LinkedList<String>) l.clone();
		while (!list.isEmpty()) {
			resultado += list.poll() + "";
		}
		return codigo;
	}

	*//**
	 * Método auxiliar que, a partir de dos expresiones de entrada y de los ids,
	 * en funcion de sus tipos, dice si se le puede asignar e1 tipo e2 al tipo
	 * e1 En caso afirmativo, en e1 se queda el tipo final
	 *//*
	private boolean tiposAsignables(Expresion e1, Expresion e2, String id1,
			String id2) {
		boolean compatibles = false;
		// los enumerados y vectores necesitan tratamiento especial

		if (e1.getTipo().equals("error_tipo")
				|| e2.getTipo().equals("error_tipo"))
			return false;

		if ((e1.getTipo().equals(e2.getTipo())
				&& !e1.getTipo().equals("enumerado") && !e1.getTipo().equals(
				"vector"))
				||
				// casos numéricos:
				// si son números o reales infinitos, cualquier tipo numérico se
				// les puede asignar
				((e1.getTipo().equals("números") || e1.getTipo().equals(
						"reales infinitos")) && e2.esTipoNumerico())
				|| (e1.getTipo().equals("reales") && (e2.getTipo().equals(
						"reales")
						|| e2.getTipo().equals("enteros")
						|| e2.getTipo().equals("naturales")
						|| e2.getTipo().equals("naturales positivos") || e2
						.getTipo().equals("enteros positivos")))
				|| (e1.getTipo().equals("enteros infinitos") && (e2.getTipo()
						.equals("enteros infinitos")
						|| e2.getTipo().equals("enteros")
						|| e2.getTipo().equals("naturales")
						|| e2.getTipo().equals("naturales positivos") || e2
						.getTipo().equals("enteros positivos")))
				|| (e1.getTipo().equals("enteros") && (e2.getTipo().equals(
						"enteros")
						|| e2.getTipo().equals("naturales")
						|| e2.getTipo().equals("naturales positivos") || e2
						.getTipo().equals("enteros positivos")))
				|| (e1.getTipo().equals("naturales") && (e2.getTipo().equals(
						"naturales")
						|| e2.getTipo().equals("naturales positivos") || e2
						.getTipo().equals("enteros positivos"))) ||
				// naturales positivos y enteros positivos son equivalentes
				((e1.getTipo().equals("naturales positivos") || e2.getTipo()
						.equals("enteros positivos")) && (e2.getTipo().equals(
						"naturales positivos") || e2.getTipo().equals(
						"enteros positivos"))))
			compatibles = true;
		else if (e1.getTipo().equals("vector") && e2.getTipo().equals("vector")) {
			// Comprobar que tienen el mismo tipo base
			ContenidoTS contV1 = Tabla_Actual.getContenido(id1);
			ContenidoTS contV2 = Tabla_Actual.getContenido(id2);
			if (contV1.getTipoBase().equals(contV2.getTipoBase())) {// Vectores
																	// con el
																	// mismo
																	// tipo base
				compatibles = true;
			}

		} else {// Comprobar si es una asignación a un id declarado como un tipo
				// enumerado
			
			 * ej b:dia, siendo dia:(lunes, martes)) comprobaríamos aquí que la
			 * asignación b:=lunes fuera correcta
			 
			String nombreEnum = e1.getTipo();// nos daría dia, que es el tipo de
												// b
			ContenidoTS contAux = Tabla_Actual.getContenido(id1);// contenido de
																	// b
			ContenidoTS c = null;
			try {
				c = contAux.getTablaHija().getContenido(nombreEnum);// contenido
																	// de dia
			} catch (Exception e) {
			}
			if (c != null) {// significa que lunes estaba guardado como parte
							// del enumerado
				compatibles = true;
			}
		}
		return compatibles;
	}

	public boolean tipoValidoRango(String s) {
		return (s.equals("naturales positivos")
				|| s.equals("enteros positivos") || s.equals("naturales")
				|| s.equals("enteros") || s.equals("rango"));
	}

	public void setHayError(boolean estado) {
		this.hayError = estado;
	}
*/
}
