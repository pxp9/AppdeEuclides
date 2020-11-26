package com.example.appdeeuclides;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    Button Volver;
    TextView  CajaResultado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        //caja del resultado
        CajaResultado= (TextView) findViewById(R.id.CajaResultado);

        CajaResultado.setText(getIntent().getStringExtra("resultado"));
        //boton para volver
        Volver = (Button)findViewById(R.id.btnVolver);



        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){

               finish();
            }
        });

        //caja del resultado





    }

}
