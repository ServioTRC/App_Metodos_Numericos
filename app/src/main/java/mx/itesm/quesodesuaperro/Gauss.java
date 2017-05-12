package mx.itesm.quesodesuaperro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Julio on 24/04/2017.
 */

public class Gauss extends Fragment implements View.OnClickListener {


    private EditText linea;
    private Button calcular;
    private Button agregar;
    private Button borrar;
    private TextView valores;
    private TextView resultados;
    private Toast toast;
    private int lineasFaltantes = -1;
    private ArrayList<ArrayList<Double>> matriz;
    private int longitud =0;
    private double[][] matrizFinal;

    public Gauss(){

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.agregar:
                try {
                    leerEntrada(linea.getText().toString());

                } catch(Exception e){
                    toast = Toast.makeText(getActivity(), "Error en los valores ingresados" , Toast.LENGTH_LONG);
                    toast.show();
                }
                imprimirValores();
                break;
            case R.id.calcular:
                if(lineasFaltantes>0){
                    toast = Toast.makeText(getActivity(), "Faltan  agregar más líneas", Toast.LENGTH_LONG);
                    toast.show();
                } else if(lineasFaltantes== -1){
                    toast = Toast.makeText(getActivity(), "Agrega líneas", Toast.LENGTH_LONG);
                    toast.show();
                 } else{
                    double[][] matrizUltimate =traducir(matriz);
                    System.out.println();
                    imprimirResultados(matrizUltimate);
                }

                break;
            case R.id.borrar:
                eliminarValores();
                imprimirValores();
                break;
        }
    }

    private void imprimirResultados(double[][] matrizFinal) {
        String res = "";
        for(int i = 0; i<matrizFinal.length; i++){
            for(int j =0; j<matrizFinal[0].length; j++){
                res+=matrizFinal[i][j] + ",";
            }
            res+="\n";
        }
        resultados.setText(res);
    }

    private double[][] traducir(ArrayList<ArrayList<Double>> matriz) {
        matrizFinal = new double[matriz.size()][matriz.get(0).size()];
        for(int i =0; i<matriz.size();i++){
            for(int j =0; j<matriz.get(0).size(); j++){
                matrizFinal[i][j] = matriz.get(i).get(j);
                System.out.println(matrizFinal[i][j]);
            }
        }

        matrizFinal = gauss(matrizFinal);
        System.out.println("El resultado final es");
        for(int i =0; i<matriz.size();i++){
            for(int j =0; j<matriz.get(0).size(); j++){
                System.out.println(matrizFinal[i][j]);
            }
        }
        return matrizFinal;
    }

    private void eliminarValores() {
        if(matriz.size()>1){
            matriz.remove(matriz.size()-1);
            toast = Toast.makeText(getActivity(), "Valores eliminados", Toast.LENGTH_LONG);
            toast.show();
            lineasFaltantes--;
        } else if(matriz.size()==1){
            matriz.remove(matriz.size()-1);
            toast = Toast.makeText(getActivity(), "Valores eliminados", Toast.LENGTH_LONG);
            toast.show();
            lineasFaltantes = -1;
        }
    }

    private void leerEntrada(String linea) throws Exception{
        String[] valoresSimples = linea.split(",");
        ArrayList<Double> coef = new ArrayList<Double>(valoresSimples.length);
        for(int i = 0; i < valoresSimples.length; i++){
            coef.add(Double.parseDouble(valoresSimples[i]));
        }

        if(lineasFaltantes == -1){
            lineasFaltantes = coef.size()-2;
            toast = Toast.makeText(getActivity(), "Líneas faltantes " + lineasFaltantes , Toast.LENGTH_LONG);
            toast.show();
            matriz.add(coef);
            longitud = matriz.get(0).size();
        } else if(coef.size() != longitud){
            toast = Toast.makeText(getActivity(), "Longitud errónea", Toast.LENGTH_LONG);
            toast.show();
        }else if(lineasFaltantes<=0){
            toast = Toast.makeText(getActivity(), "Número de lineas máximo alcanzado", Toast.LENGTH_LONG);
            toast.show();
        }else{
            matriz.add(coef);
            lineasFaltantes--;
        }

    }
    private void imprimirValores(){
        String res = "";
        for(ArrayList<Double> lista: matriz){
            for(Double val: lista){
                res+= val + ", ";
            }
            res+= "\n";
        }
        valores.setText(res);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gauss, container, false);
        matriz = new ArrayList<ArrayList<Double>>();
        agregar = (Button) view.findViewById(R.id.agregar);
        calcular = (Button) view.findViewById(R.id.calcular);
        valores = (TextView) view.findViewById(R.id.valores);
        resultados = (TextView) view.findViewById(R.id.Resultados);
        linea = (EditText) view.findViewById(R.id.matriz);
        agregar.setOnClickListener(this);
        calcular.setOnClickListener(this);
        borrar = (Button) view.findViewById(R.id.borrar);
        borrar.setOnClickListener(this);

        return view;
    }


    public double[][] gauss(double[][] matrix){
        double pivote=1;
        double numBajoPivote;
        for(int fila=0;fila<matrix.length-1;fila++){
            System.out.println("Me meti a gauss");
            for(int filaSig=fila+1;filaSig<matrix.length;filaSig++){
                if(matrix[fila][fila]==0){
                    matrix=cambioDe0(matrix,fila);
                }

                for(int num=0;num<matrix[fila].length;num++){
                    if(num==fila){
                        pivote=matrix[fila][fila];
                    }

                    matrix[fila][num]=matrix[fila][num]/pivote;
                }
                numBajoPivote= matrix[filaSig][fila];
                for(int num=0;num<matrix[filaSig].length;num++){
                    matrix[filaSig][num]=(matrix[fila][num]*(-numBajoPivote))+matrix[filaSig][num];
                }
                pivote= matrix[filaSig][filaSig];
                for(int k=0;k<matrix[filaSig].length;k++){
                    matrix[filaSig][k]=(matrix[filaSig][k])/pivote;
                }
            }
        }
        return matrix;
    }

    private double[][] cambioDe0(double[][]matrix, int fila){
        double[] temp;
        while(matrix[fila][fila]==0){
            for(int cambio=fila+1;cambio<matrix.length;cambio++){
                if(matrix[cambio][fila]!=0){
                    temp=matrix[fila];
                    matrix[fila]=matrix[cambio];
                    matrix[cambio]=temp;
                }
            }
        }
        return matrix;
    }

}
