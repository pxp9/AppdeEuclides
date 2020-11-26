package com.example.appdeeuclides;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView miTextView ;
   private  Button Resolver;
   private TextView CajaResultado;
  private   EditText TermA, TermB, TermTI;
  private PopupWindow popupWindow;
  private LayoutInflater layoutInflater;


    int a, b, ti;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CajaResultado= findViewById(R.id.CajaResultado);

        miTextView = (TextView) findViewById(R.id.textView2);

        TermA = (EditText) findViewById(R.id.TermA);
        TermB = (EditText) findViewById(R.id.TermB);
        TermTI = (EditText) findViewById(R.id.TermTI);




        miTextView.setText("Algoritmo de Euclides ");

        //boton para cambiar de pantalla
        Resolver = (Button) findViewById(R.id.Resolver);

        Resolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TermA.getText().toString().equals("") || TermB.getText().toString().equals("") || TermTI.getText().toString().equals("")){
                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    ViewGroup container;
                }
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("resultado" , mostrar(v));
                startActivity(intent);

            }
        });
    }
    public String mostrar(View v){
        //variables para obtener el resultado

        a = Integer.parseInt(TermA.getText().toString());
        b = Integer.parseInt(TermB.getText().toString());
        ti = Integer.parseInt(TermTI.getText().toString());

      return  ec(a,b,ti);

    }
    private int mayor2(int x, int y) {
        return ((x >= y) ? x : y);
    }

    /*
     * FUNCION mcd (Entero x, y) ---> Entero PRE: cierto POST: mcd(a,b)
     *
     */
    protected int mcd(int x, int y) {
        int a = x;
        int b = y;
        int resto = 0;
        do {
            resto = a % b;
            a = b;
            b = resto;
        } while (resto != 0);
        return a;
    }

    /*
     * FUNCION divisible (Entero x, y) ---> Bool
     */
    protected boolean div(int x, int y) {
        return (x % y == 0);
    }

    /*
     * FUNCION bezoutAux(Array 2D) ---> Array2D PRE: cierto
     *
     * POST: Elemento array 2D
     *
     */
    protected int[][] bezAux(int[][] bezA) {
        bezA[2][0] = bezA[0][0] % bezA[1][0];
        bezA[2][1] = bezA[0][0] / bezA[1][0];
        bezA[2][2] = bezA[0][2] - bezA[2][1] * bezA[1][2];
        bezA[2][3] = bezA[0][3] - bezA[2][1] * bezA[1][3];

        return bezA;
    }

    /*
     * FUNCION subir fila (Array 2D) ---> Array2D PRE: cierto
     *
     * POST: Elemento array 2D
     *
     */
    protected int[][] subFila(int[][] subF) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (i < 2) {
                    subF[i][j] = subF[i + 1][j];
                }
            }
        }
        return subF;
    }

    /*
     * FUNCION fila de zeros (Array 2D, int f, int p) ---> Array2D PRE: cierto
     *
     * POST: Array con una fila a escoger rellena del parametro escogico
     *
     */
    protected int[][] blFila(int[][] bl, int f, int p) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (f == i) {
                    bl[i][j] = p;
                }
            }
        }
        return bl;
    }

    /*
     * FUNCION Euclides (Entero a, b,p) ---> Entero p1 o p2 PRE: cierto POST:x
     * particualar o y particular referente al la identidad de Bezout
     *
     */
    protected int Euclides(int x, int y, int p) {
        int m = x;
        int n = y;
        int sol = p;
        int[][] bezout = new int[3][4];
        bezout[0][0] = m;
        bezout[0][1] = 0;
        bezout[0][2] = 1;
        bezout[0][3] = 0;
        bezout[1][0] = n;
        bezout[1][1] = 0;
        bezout[1][2] = 0;
        bezout[1][3] = 1;
        bezout[2][0] = 0;
        bezout[2][1] = 0;
        bezout[2][2] = 0;
        bezout[2][3] = 0;

        do {

            bezAux(bezout);
            subFila(bezout);
            blFila(bezout, 2, 0);

        } while (bezout[1][0] != 0);
        return ((sol == 1) ? bezout[0][2] : bezout[0][3]);
    }

    /*
     * FUNCION CUERPO
     *
     */
    public String ec(int a, int b, int m) {
        int xh, yh, xp =1, yp=1 ;
        String resultado;
        int mcd = mcd(a, b);


        if (!div(m, mcd)) {
            resultado = "\n"+a + "x + " + b + "y = " + m + " no tiene soluciones enteras \n";
         return resultado;
        } else {

            if (mcd != 1) {
                xh = b / mcd;
                yh = -a / mcd;
                xp = Euclides(a / mcd, b / mcd, 1);
                yp = Euclides(a / mcd, b / mcd, 2);
            } else {
                xh = b;
                yh = -a;
                xp = Euclides(a, b, 1);
                yp = Euclides(a, b, 2);

            }

            xp = xp * m;
            yp = yp * m;
            resultado=""+a + "x + " + b + "y = " + m + " tiene soluciones enteras \n \n" +
                    "x = " + xp + " + " + xh + " * t \n" +
                    "y = " + yp + " + " +"("+ yh +")"+
                    " * t \n"+ " \n siendo t un entero \n";
           return resultado;


        }
    }

}
