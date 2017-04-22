package mx.itesm.quesodesuaperro;

import java.util.ArrayList;

public class InterpolacionNewton {

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Double>> matriz = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> linea = new ArrayList<Double>(2);
		linea.add(1.0);
		linea.add(6.0);
		matriz.add(linea);
		linea = new ArrayList<Double>(2);
		linea.add(2.0);
		linea.add(9.0);
		matriz.add(linea);
		linea = new ArrayList<Double>(2);
		linea.add(3.0);
		linea.add(2.0);
		matriz.add(linea);
		linea = new ArrayList<Double>(2);
		linea.add(4.0);
		linea.add(5.0);
		matriz.add(linea);
		System.out.println(interpolacionNewton(matriz));
	}*/
	
	public static ArrayList<ArrayList<Double>> matrizConPuntos(ArrayList<ArrayList<Double>> puntos){
		ArrayList<ArrayList<Double>> matriz = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> linea;
		for(int i = 0; i < puntos.size(); i++){
			linea = new ArrayList<Double>(puntos.size()+1);
			for(int j = 0; j < puntos.size()+1; j++){
				if(j==0 || j == 1)
					linea.add(puntos.get(i).get(j));
				else
					linea.add(0.0);
			}
			matriz.add(linea);
		}
		return matriz;
	}
	
	public static ArrayList<Double> multiplos(ArrayList<ArrayList<Double>> matriz){
		
		double[][] arr = new double[matriz.size()][matriz.size()+1];
		
		for(int i = 0; i < matriz.size(); i++){
			for(int j = 0; j < matriz.size(); j++){
				arr[i][j] = matriz.get(i).get(j);
			}
		}
		
		for(int j = 2; j < matriz.size() + 1; j++){
			for(int i = j-1; i < matriz.size(); i++){
				arr[i][j] = (arr[i][j-1]-arr[i-1][j-1])/(arr[i][0]-arr[i-(j-1)][0]);
			}
		}
		/*
		ArrayList<ArrayList<Double>> temp = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> lista;
		for(int i = 0; i < matriz.size(); i++){
			lista = new ArrayList<Double>(matriz.size()+1); 
			for(int j = 0; j < matriz.size()+1; j++){
				lista.add(arr[i][j]);
			}
			temp.add(lista);
		}*/
		
		ArrayList<Double> multiplos = new ArrayList<Double>();
		
		for(int i = 0; i < matriz.size(); i++){
			multiplos.add(arr[i][i+1]);
		}
		
		return multiplos;
	}
	
	public static String polinomio(ArrayList<Double> multiplos, ArrayList<ArrayList<Double>> puntos){
		String polinomio = Double.toString(multiplos.get(0));
		for(int i = 1; i < multiplos.size(); i++){
			polinomio += "+(" + Double.toString(multiplos.get(i))+"*";
			for(int j = 0; j < i; j++){
				polinomio += "(x-("+puntos.get(j).get(0)+"))";
				if(j+1 < i)
					polinomio += "*";
			}
			polinomio += ")";
		}
		return polinomio;
	}
	
	public static String interpolacionNewton(ArrayList<ArrayList<Double>> puntos){
		ArrayList<ArrayList<Double>> matrizCeros = matrizConPuntos(puntos);
		ArrayList<Double> multiplos = multiplos(matrizCeros);
		return polinomio(multiplos, puntos);
	}
	
}
