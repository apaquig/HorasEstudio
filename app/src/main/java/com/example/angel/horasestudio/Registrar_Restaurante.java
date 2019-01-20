package com.example.angel.horasestudio;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Registrar_Restaurante extends Fragment{

    Button registarRestaurant;
    View vista;
    public Registrar_Restaurante() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista=inflater.inflate(R.layout.fragment_registrar__restaurante, container, false);
        registarRestaurant= (Button)vista.findViewById(R.id.btnRegistarProp);
        registarRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Registro de propietaro",Toast.LENGTH_LONG).show();
            }
        });


        return vista;

    }

}
