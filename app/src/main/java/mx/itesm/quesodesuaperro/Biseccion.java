package mx.itesm.quesodesuaperro;

import java.util.ArrayList;

public class Biseccion {

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(biseccionCompleta("x^2-3", -10, 10, 0.1));
	}*/
	
	public static double eval(final String str) {
	    return new Object() {
	        int pos = -1, ch;

	        void nextChar() {
	            ch = (++pos < str.length()) ? str.charAt(pos) : -1;
	        }

	        boolean eat(int charToEat) {
	            while (ch == ' ') nextChar();
	            if (ch == charToEat) {
	                nextChar();
	                return true;
	            }
	            return false;
	        }

	        double parse() {
	            nextChar();
	            double x = parseExpression();
	            if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
	            return x;
	        }

	        // Grammar:
	        // expression = term | expression `+` term | expression `-` term
	        // term = factor | term `*` factor | term `/` factor
	        // factor = `+` factor | `-` factor | `(` expression `)`
	        //        | number | functionName factor | factor `^` factor

	        double parseExpression() {
	            double x = parseTerm();
	            for (;;) {
	                if      (eat('+')) x += parseTerm(); // addition
	                else if (eat('-')) x -= parseTerm(); // subtraction
	                else return x;
	            }
	        }

	        double parseTerm() {
	            double x = parseFactor();
	            for (;;) {
	                if      (eat('*')) x *= parseFactor(); // multiplication
	                else if (eat('/')) x /= parseFactor(); // division
	                else return x;
	            }
	        }

	        double parseFactor() {
	            if (eat('+')) return parseFactor(); // unary plus
	            if (eat('-')) return -parseFactor(); // unary minus

	            double x;
	            int startPos = this.pos;
	            if (eat('(')) { // parentheses
	                x = parseExpression();
	                eat(')');
	            } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
	                while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
	                x = Double.parseDouble(str.substring(startPos, this.pos));
	            } else if (ch >= 'a' && ch <= 'z') { // functions
	                while (ch >= 'a' && ch <= 'z') nextChar();
	                String func = str.substring(startPos, this.pos);
	                x = parseFactor();
	                if (func.equals("sqrt")) x = Math.sqrt(x);
	                else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
	                else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
	                else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
	                else throw new RuntimeException("Unknown function: " + func);
	            } else {
	                throw new RuntimeException("Unexpected: " + (char)ch);
	            }

	            if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

	            return x;
	        }
	    }.parse();
	}
	
	public static double biseccion(String funcion, double puntoInicial, double puntoFinal, double error){
		String sustitucionMitad, sustitucionInicial; 
		double mitad = (puntoInicial + puntoFinal)/2;
		while((Math.abs((mitad-puntoInicial)/mitad)*100)>error){
			
			sustitucionMitad = funcion.replaceAll("x", "("+Double.toString(mitad)+")");
			
			if(eval(sustitucionMitad) == 0.0)
				break;
			
			sustitucionInicial = funcion.replaceAll("x", "("+Double.toString(puntoInicial)+")");
			if((eval(sustitucionMitad)*eval(sustitucionInicial))<0){
				puntoFinal = mitad;
			} else {
				puntoInicial = mitad;
			}
			
			mitad = (puntoInicial + puntoFinal)/2;
		}
		return mitad;
	}
	
	public static ArrayList<Double> intervalos(String funcion, double puntoInicial, double puntoFinal, double paso){
		ArrayList<Double> intervalos = new ArrayList<Double>();
		String sustitucionInicial, sustitucionFinal;
		for(double i = puntoInicial; i < puntoFinal; i+=paso){
			sustitucionInicial = funcion.replaceAll("x", "("+Double.toString(i)+")");
			sustitucionFinal = funcion.replaceAll("x", "("+Double.toString(i+paso)+")");
			if((eval(sustitucionInicial)*eval(sustitucionFinal)) < 0 || eval(sustitucionInicial) == 0.0){
				intervalos.add(i);
				intervalos.add(i+paso);
			}
		}
		return intervalos;
	}
	
	public static ArrayList<Double> biseccionCompleta(String funcion, double puntoInicial, double puntoFinal, double error){
		ArrayList<Double> raices = new ArrayList<Double>();
		ArrayList<Double> intervalos = intervalos(funcion, puntoInicial, puntoFinal, 0.3);
		for(int i = 0; i < intervalos.size(); i+=2){
			raices.add(biseccion(funcion, intervalos.get(i), intervalos.get(i+1), error));
		}
		return raices;
	}
	
}
