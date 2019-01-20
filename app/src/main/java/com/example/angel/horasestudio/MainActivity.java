package com.example.angel.horasestudio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private int valor1;
    private Button btnCrearCuenta;
    private Button btnIngreso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCrearCuenta=(Button)findViewById(R.id.btnCrearCuenta);
        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               registrar();
            }
        });


        btnIngreso=(Button)findViewById(R.id.btnIngresar);
        btnIngreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Login();
            }
        });

    //prueba de subida
        //este es una nueva pruebA
        //Proyecto Ing Software
        //Ely
    }
   public void registrar(){
        Intent login =new Intent(this, Registrar.class);
        startActivity(login);

    }

    public void Login(){
        Intent login2 =new Intent(this, Ventana_Principal.class);
        startActivity(login2);

    }
}
