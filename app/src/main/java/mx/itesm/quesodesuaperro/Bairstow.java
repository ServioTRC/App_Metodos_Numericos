package mx.itesm.quesodesuaperro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Julio on 24/04/2017.
 */

public class Bairstow extends android.support.v4.app.Fragment implements View.OnClickListener {

    private Button calcular;
    private EditText valores;
    private TextView resultados;
    private ArrayList<Double> coeficientes;
    private TextView titulo;
    private TextView instrucciones;
    private TextView resTitle;




    public Bairstow(){
        //Constructor vacio requerido
    }

    public ArrayList<Double> divSinDoble(ArrayList<Double> coef, double r, double s){
        ArrayList<Double> copia = new ArrayList<Double>(coef.size());
        for(Double dab : coef){
            copia.add(dab);
        }
        for(int i = 1; i < copia.size(); i++){
            if(i<2) {
                System.out.print("De " + copia.get(i));
                copia.set(i, copia.get(i) + copia.get(i - 1) * r);
                System.out.println(" A " + copia.get(i));
            } else {
                System.out.print("De " + copia.get(i));
                copia.set(i,copia.get(i) + copia.get(i - 1) * r + copia.get(i - 2) * s);
                System.out.println(" A " + copia.get(i));
            }
        }
        return copia;
    }

    public ArrayList<Double> bairSimple(ArrayList<Double> coef, double prec){
        ArrayList<Double> b = coef, c, fin = new ArrayList<Double>(),copia = coef;
        int j = 0;
        double delta, r=-1, s=-1, deltaR, deltaS;
        int lengthB = 0,lengthC = 0;
        while(Math.sqrt(Math.abs(Math.pow(b.get(b.size()-1),2)+Math.pow(b.get(b.size()-2),2)))>prec){
            System.out.println("coef " + coef.toString());
            j++;
            System.out.println("Condicion " + Math.abs(b.get(b.size()-1)*b.get(b.size()-2)));
            b = divSinDoble(coef, r, s);
            System.out.println("Hice b " + b.toString() +" con div y ahora c");
            c = divSinDoble(b,r,s);
            System.out.println("Hice c " + c.toString());
            lengthC = c.size();
            lengthB = b.size();
            delta = Math.pow(c.get(lengthC-3),2)-c.get(lengthC-4)*c.get(lengthC-2);


            deltaR = ((c.get(lengthC-4)*b.get(lengthB-1))-(c.get(lengthC-3)*b.get(lengthB-2)))/delta;

            deltaS = ((b.get(lengthB-2)*c.get(lengthC-2))-(b.get(lengthB-1)*c.get(lengthC-3)))/delta;

            r+=deltaR;
            s+=deltaS;
            System.out.println("delta " + delta + " r "+ r + " s " + s);
        }
        System.out.println("Condicion " + Math.sqrt(Math.abs(Math.pow(b.get(b.size()-1),2)+Math.pow(b.get(b.size()-2),2))));
        for(int i = 0; i < lengthB-2; i++){
            fin.add(b.get(i));
        }

        return fin;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_bairstow, container, false);
        calcular = (Button) view.findViewById(R.id.calcular);
        valores = (EditText) view.findViewById(R.id.coeficientes);
        resultados = (TextView) view.findViewById(R.id.resultados);
        resTitle = (TextView) view.findViewById(R.id.resTitle);
        titulo = (TextView) view.findViewById(R.id.titulo);
        instrucciones = (TextView) view.findViewById(R.id.instrucciones);
        calcular.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view){
        switch((view.getId())){
            case R.id.calcular:
                imprimirValor();
                break;
        }
    }

    private void imprimirValor() {
        String res = "";
        ArrayList<Double> prueba = new ArrayList<Double>();
        prueba.add(6.0);
        prueba.add(31.0);
        prueba.add(63.0);
        prueba.add(60.0);
        prueba.add(44.0);
        prueba.add(19.0);
        prueba.add(-13.0);
        prueba.add(-1.0);
        prueba = bairSimple(prueba, 0.00000001);
        System.out.println("Lo logré con " + prueba.size());
        for(int i =0; i<prueba.size(); i++){
            res+=prueba.get(i) + ", ";
        }
        resultados.setText(res);
    }

}
