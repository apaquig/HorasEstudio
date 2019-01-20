package com.example.angel.horasestudio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class Restaurantes extends AppCompatActivity {

    private ListView listRest;
    private Adaptador adaptador;
    private ImageButton mybntimag;

    String [][] datosRest = {
            {"Patio de Comidas UPS", "Junto al Coliseo UPS"},
            {"El Vegetariano", "Calle Vieja y Elia Lut"},
            {"Chatos", "Dentro de Multiplaza - Miraflores"},
            {"KFC", "Dentro de Multiplaza - Miraflores"},
            {"Don Maño", "Calle Vieja y Elia Lut"},
            {"Oromar", ""},


            };
    int[] datosImg = {R.drawable.restaurant_ups, R.drawable.el_vegetariano, R.drawable.chatos,R.drawable.kfc,R.drawable.donmano,R.drawable.oromar};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantes);

        listRest = (ListView) findViewById(R.id.listaRestaurantes);

        listRest.setAdapter(new Adaptador(this,datosRest, datosImg));

        mybntimag = (ImageButton) findViewById(R.id.btnimage);

    }

    public void re(){
        Intent login2 =new Intent(this, comida_kfc.class);
        startActivity(login2);
    }

}
