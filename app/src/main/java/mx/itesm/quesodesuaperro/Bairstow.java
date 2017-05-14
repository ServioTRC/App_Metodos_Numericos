package mx.itesm.quesodesuaperro;

import android.os.Bundle;
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

public class Bairstow extends android.support.v4.app.Fragment implements View.OnClickListener {

    private Button calcular;
    private EditText valores;
    private TextView resultados;
    private ArrayList<Double> coeficientes;
    private TextView titulo;
    private TextView instrucciones;
    private TextView resTitle;
    private Toast toast;



    private double[] a = new double[20];
    private double[] b = new double[20];
    private double[] c = new double[20];
    private ArrayList<Double> raices;
    private int n;




    public Bairstow(){
        //Constructor vacio requerido
    }

    public void fuckYou(){
        int i;
        double r1, r2, du, dv, u, v, r, dr;
        double sq, det, nu, nv, error;
        double epsilon = 1e-6;

        System.out.println("The polynomial is:");
        for (i = n; i >= 0; i--) {
            if (i == 0)
                System.out.print(a[i]);
            else
                System.out.print(a[i] + "*x^" + i + "  ");
        }

        System.out.print("\n\n");
        while (n > 3) {
            u = 0;
            v = 0;
            error = 1;
            c[n] = b[n] = a[n];

            while (error > epsilon) {
                b[n-1] = a[n-1] + u * b[n];
                c[n-1] = b[n-1] + u * c[n];

                for (i = n - 2; i > 0; i--) {
                    b[i] = a[i] + u * b[i+1] + v * b[i+2];
                    c[i] = b[i] + u * c[i+1] + v * c[i+2];
                }

                b[0] = a[0] + u * b[1] + v * b[2];

                det = (c[2] * c[2]) - c[1] * c[3];

                nu = b[0] * c[3] - b[1] * c[2];
                nv = b[1] * c[1] - b[0] * c[2];

                if (det == 0) {
                    du = dv = 1;
                } else {
                    du = nu / det;
                    dv = nv / det;
                }

                u += du;
                v += dv;

                error = Math.sqrt(du * du + dv * dv);
            }


            sq = u * u + 4 * v;

            if (sq < 0) {
                r1 = u/2;
                r2 = Math.sqrt(-sq)/2;

                System.out.println(r1 + " + " + r2 + "i");
                System.out.println(r1 + " - " + r2 + "i");
            } else {
                r1 = u/2 + Math.sqrt(sq)/2;
                r2 = u/2 - Math.sqrt(sq)/2;

                System.out.println(r1);
                System.out.println(r2);
            }

            n -= 2;

            for (i = 0; i < n + 1; i++)
                a[i] = b[i+2];
        }




        if (n == 3) {
            r = 0;
            error = 1;
            b[n] = a[n];

            while (error > epsilon) {
                b[2] = a[2] + r * b[3];
                b[1] = a[1] + r * b[2];
                b[0] = a[0] + r * b[1];

                double d = a[1] + (2 * a[2] * r) + (3 * a[3] * r * r);

                if (d == 0)
                    dr = 1;
                else
                    dr = -b[0] / d; // b[0] = f(x)

                r -= dr;
                error = Math.abs(dr);
            }

            System.out.println(r);
            n--;

            for (i = 0; i < n + 1; i++)
                a[i] = b[i + 1];
        }


        if (n == 2) {
            u = -a[1] / a[2];
            v = -a[0] / a[2];
            sq = u * u + 4 * v;

            if (sq < 0) {
                r1 = u/2;
                r2 = Math.sqrt(-sq)/2;

                System.out.println(r1 + " + " + r2 + "i");
                System.out.println(r1 + " - " + r2 + "i");
            } else {
                r1 = u/2 + Math.sqrt(sq)/2;
                r2 = u/2 - Math.sqrt(sq)/2;

                System.out.println(r1);
                System.out.println(r2);
            }
        } else if (n == 1) {
            System.out.println(-a[0] / a[1]);
        }

        System.out.println("\nRoot finding process has finished.");
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
            j++;
            System.out.println("coef " + coef.toString() +" en corrida " +j);

            System.out.println("Condicion " + Math.sqrt(Math.abs(Math.pow(b.get(b.size()-1),2)+Math.pow(b.get(b.size()-2),2))) + "fue mayor que " +prec);
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


    public ArrayList<Double> evaluarFuncion(ArrayList<Double> arreglo){
        ArrayList<Double> raices = new ArrayList<Double>();
        double a,b,c,d;
        if(arreglo.size()==3){
            a= arreglo.get(0);
            b = arreglo.get(1);
            c = arreglo.get(2);
            d = b * b - 4 * a * c;

            if(d > 0)

            {

                System.out.println("Roots are real and unequal");

                raices.add(( - b + Math.sqrt(d))/(2*a));

                raices.add((-b - Math.sqrt(d))/(2*a));

                System.out.println("First root is:"+raices.get(0));

                System.out.println("Second root is:"+raices.get(1));

            }

            else if(d == 0)

            {

                System.out.println("Roots are real and equal");

                raices.add((-b+Math.sqrt(d))/(2*a));

                System.out.println("Root:"+raices.get(0));

            }

            else

            {

                System.out.println("Roots are imaginary");

            }
        } else if(arreglo.size() == 2){
            raices.add(-1*(arreglo.get(1)));
        }
        return raices;
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
                try {
                    leerEntrada(valores.getText().toString());
                    System.out.println(a.length+ " es el tamano de a ");
                    ArrayList<Double> prueba = new ArrayList<Double>();
                    for(int i = 0; i<n; i++){
                        System.out.print(a[i]+ " ");
                        prueba.add(a[i]);
                    }
                    System.out.println("");
                    prueba = bairSimple(prueba, 0.00001);
                    raices = evaluarFuncion(prueba);
                    System.out.println("Las raices son: " + raices.toString());

                    //fuckYou();

                } catch(Exception e){
                    toast = Toast.makeText(getActivity(), "Error en los valores ingresados" , Toast.LENGTH_LONG);
                    toast.show();
                }
        }
    }

    private void leerEntrada(String s) {
        String[] valoresSimples = s.split(",");
        n = valoresSimples.length;
        for(int i = 0; i < valoresSimples.length; i++){
            a[i] = Double.parseDouble(valoresSimples[i]);
        }
    }

    private void imprimirValor() {
        String res = "";
        ArrayList<Double> prueba = new ArrayList<Double>();
        prueba.add(1.25);
        prueba.add(-3.875);
        prueba.add(2.125);
        prueba.add(2.75);
        prueba.add(-3.5);
        prueba.add(1.0);
        //prueba.add(-13.0);
        //prueba.add(-1.0);
        prueba = bairSimple(prueba, 0.00000001);
        System.out.println("Lo logré con " + prueba.size());
        for(int i =0; i<prueba.size(); i++){
            res+=prueba.get(i) + ", ";
        }
        resultados.setText(res);
    }

}
