package mx.itesm.quesodesuaperro;

import java.util.ArrayList;

public class Cramer {

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Double>> ejemplo = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> otro = new ArrayList<Double>(4);
		otro.add(1.0);
		otro.add(5.0);
		otro.add(6.0);
		otro.add(2.0);
		ejemplo.add(otro);
		otro = new ArrayList<Double>(4);
		otro.add(-1.0);
		otro.add(3.0);
		otro.add(2.0);
		otro.add(0.0);
		ejemplo.add(otro);
		otro = new ArrayList<Double>(4);
		otro.add(2.0);
		otro.add(4.0);
		otro.add(5.0);
		otro.add(-2.0);
		ejemplo.add(otro);
		System.out.println(metodoDeCrammer(ejemplo));
	}*/
	
	public static ArrayList<ArrayList<Double>> matrizSencilla(ArrayList<ArrayList<Double>> matrizCompleta){
		ArrayList<ArrayList<Double>> matriz = new ArrayList<ArrayList<Double>>();
		
		for(ArrayList<Double> arreglo: matrizCompleta){
			ArrayList<Double> res = new ArrayList<Double>();
			for(int i = 0; i < arreglo.size()-1; i++){
				res.add(arreglo.get(i));
			}
			matriz.add(res);
		}
		
		return matriz;
	}
	
	public static double determinante(ArrayList<ArrayList<Double>> matriz) {
		
		double[][] arr = new double[matriz.size()][matriz.size()];
		
		for(int i = 0; i < matriz.size(); i++){
			System.out.println("i= "+i);
			for(int j = 0; j < matriz.size(); j++){
				System.out.println("j= "+j);
				arr[i][j] = matriz.get(i).get(j);
			}
		}
		
		return determinanteUtil(arr);
		
	}
	
	public static double determinanteUtil(double[][] arr) {
		double result = 0.0;
		if (arr.length == 1) {
			result = arr[0][0];
			return result;
		}
		if (arr.length == 2) {
			result = arr[0][0] * arr[1][1] - arr[0][1] * arr[1][0];
			return result;
		}
		for (int i = 0; i < arr[0].length; i++) {
			double temp[][] = new double[arr.length - 1][arr[0].length - 1];

			for (int j = 1; j < arr.length; j++) {
				for (int k = 0; k < arr[0].length; k++) {

					if (k < i) {
						temp[j - 1][k] = arr[j][k];
					} else if (k > i) {
						temp[j - 1][k - 1] = arr[j][k];
					}
				}
			}
			result += arr[0][i] * Math.pow(-1, (int) i) * determinanteUtil(temp);
		}
		return result;
	}

	public static ArrayList<Double> metodoDeCrammer(ArrayList<ArrayList<Double>> matrizCompleta){
		ArrayList<Double> resultados = new ArrayList<Double>();
		ArrayList<ArrayList<Double>> matrizSencilla = matrizSencilla(matrizCompleta);
		System.out.println("Otro");
		Double determinante = determinante(matrizSencilla);
		if(determinante == 0.0)
			System.out.println("No se puede resolver");
		else{
			for(int i = 0; i < matrizCompleta.size(); i++){
				matrizSencilla = new ArrayList<ArrayList<Double>>();
				for(int j = 0; j < matrizCompleta.size(); j++){
					ArrayList<Double> temp = new ArrayList<Double>();
					for(int k = 0; k < matrizCompleta.size(); k++){
						if(k==i)
							temp.add(matrizCompleta.get(j).get(matrizCompleta.size()));
						else
							temp.add(matrizCompleta.get(j).get(k));
					}
					matrizSencilla.add(temp);
				}
				System.out.println("Extra");
				resultados.add(determinante(matrizSencilla)/determinante);
			}
		}
		return resultados;
	}
	
}
