package parser.errors;

import java.awt.Color;

import javax.swing.JTextArea;

import parser.elements.Token;


public class Errors {
	// Atributos
		private String s;
		private Token t;

		// Constructoras
		public Errors(int id, Token t, boolean hayError, JTextArea consola) {
			this.t = new Token(t);
			String tError = "";
			if ((id > 0) && (id < 100))
				tError = "ERROR SINTACTICO:\n";
			else if (id >= 100)
				tError = "ERROR SEMANTICO:\n";

			if (id == 1)
				this.s = "Se esperaba la palabra Programa";
			else if (id == 2)
				this.s = "Se esperaba la palabra fPrograma";
			else if (id == 3)
				this.s = "Se esperaba un identificador";
			else if (id == 4)
				this.s = "Se esperaba la palabra ftipos";
			else if (id == 5)
				this.s = "Se esperaba un salto de línea ";
			else if (id == 6)
				this.s = "Se esperaba un tipo ";
			else if (id == 7)
				this.s = "Se esperaba 'de' ";
			else if (id == 8)
				this.s = "Se esperaba un ']' ";
			else if (id == 9)
				this.s = "Se esperaba un rango ";
			else if (id == 10)
				this.s = "Se esperaba un = ";
			else if (id == 11)
				this.s = "Se esperaba freg";
			else if (id == 12)
				this.s = "Puntero mal definido, se esperaba una a";
			else if (id == 13)
				this.s = "Valores mal definidos";
			else if (id == 14)
				this.s = "Se esperaba un )";
			else if (id == 15)
				this.s = "campos mal definidos";
			else if (id == 16)
				this.s = "Se esperaba un '[' o ':' ";
			else if (id == 17)
				this.s = "Se esperaba palabra dev";
			else if (id == 18)
				this.s = "Se esperaba ffun";
			else if (id == 19)
				this.s = "Especificar el tipo de retorno";
			else if (id == 20)
				this.s = "Se esperaba un '(' ";
			else if (id == 21)
				this.s = "Se esperaba fproc";
			else if (id == 22)
				this.s = "Se esperaba una entrada";
			else if (id == 23)
				this.s = "Se esperaba una declaración de tipo";
			else if (id == 24)
				this.s = "no se esperaba coma";
			else if (id == 25)
				this.s = "Se esperaba un '>' o una ','";
			else if (id == 26)
				this.s = "Tiene que haber una o varias salidas";
			else if (id == 27)
				this.s = "Tiene que haber el mismo número de expresiones";
			else if (id == 28)
				this.s = "Asignación mal construida";
			else if (id == 29)
				this.s = "Se esperaba una expresión";
			else if (id == 30)
				this.s = "Se esperaba un rango o una expresión";
			else if (id == 31)
				this.s = "Se esperaba fcasos";
			else if (id == 32)
				this.s = "Se esperaba una flecha derecha";
			else if (id == 33)
				this.s = "Se esperaba fsi";
			else if (id == 34)
				this.s = "Se esperaba 'no'";
			else if (id == 35)
				this.s = "Se esperaba 'entonces'";
			else if (id == 36)
				this.s = "expresión mal construída";
			else if (id == 37)
				this.s = "Se esperaba un fpara";
			else if (id == 38)
				this.s = "Se esperaba hacer o paso";
			else if (id == 39)
				this.s = "Se esperaba hasta";
			else if (id == 40)
				this.s = "Se esperaba fmientras";
			else if (id == 41)
				this.s = "Se esperaba un id o un vector o un puntero o un registro";
			else if (id == 42)
				this.s = "(opLog) Se esperaba Exp1";
			else if (id == 43)
				this.s = "(opRel | opIgual) Se esperaba Exp1";
			else if (id == 44)
				this.s = "(opAdit)Se esperaba Exp2";
			else if (id == 45)
				this.s = "(opMult) Se esperaba un Exp3";
			else if (id == 46)
				this.s = "(neg | res | sum) Se esperaba un Exp4";
			else if (id == 47)
				this.s = "Se esperaba un Exp0";
			else if (id == 48)
				this.s = "(opExp)Se esperaba un Exp4";
			else if (id == 49)
				this.s = "falta al menos un elemento en el vector";
			else if (id == 50)
				this.s = "Se esperaba '(' o '↑' o '['";
			else if (id == 51)
				this.s = "excepcion en objetos";
			else if (id == 52)
				this.s = "excepcion en campo";
			else if (id == 100)
				this.s = "error de tipo";
			else if (id == 102)
				this.s = "Rango negativo o nulo";
			else if (id == 103)
				this.s = "Uno de los limites es negativo";
			else if (id == 105)
				this.s = "el tipo no ha sido declarado previamente";
			else if (id == 106)
				this.s = "Variable ya declarada anteriormente";
			else if (id == 107)
				this.s = "Se esperaba una función";
			else if (id == 108)
				this.s = "Número de parámetros para asignación no coincide";
			else if (id == 109)
				this.s = "Los tipos de parámetros para asignación no coincide";
			else if (id == 110)
				this.s = "Los tipos en la op. aditiva o exponecial no son numéricos";
			else if (id == 111)
				this.s = "Los tipos en la op. aditiva o exponecial no son compatibles";
			else if (id == 112)
				this.s = "Los tipos en la resta no son numéricos";
			else if (id == 113)
				this.s = "Los tipos en la resta no son no son compatibles";
			else if (id == 114)
				this.s = "Error de tipo en la funcion si";
			else if (id == 115)
				this.s = "Error de tipo en el rango";

			if (!hayError) {
				consola.setForeground(Color.red);
				System.out.println(tError + s + " En la fila: " + t.leeFila()
						+ " En la columna: " + t.leeColumna());
				consola.append(tError + s + " En la fila: " + t.leeFila()
						+ " En la columna: " + t.leeColumna() + "\n");
			}

			hayError = true;
		}

		// Getters and Setters
		public String getS() {
			return s;
		}

		public void setS(String s) {
			this.s = s;
		}

		public Token getT() {
			return t;
		}

		public void setT(Token t) {
			this.t = t;
		}
}
