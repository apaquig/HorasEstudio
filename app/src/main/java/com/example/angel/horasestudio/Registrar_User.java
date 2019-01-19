package com.example.angel.horasestudio;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Registrar_User extends Fragment {

    Button btnRegistarUser;

    public Registrar_User() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment hola
       View  vistaUser=inflater.inflate(R.layout.fragment_registrar__user, container, false);

        btnRegistarUser=(Button)vistaUser.findViewById(R.id.btnRegistrarUser);
        btnRegistarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Registrar User",Toast.LENGTH_LONG).show();
            }
        });
        return vistaUser;
    }
private void mostraMensaje(View view){
        Toast.makeText(getContext(),"Hola",Toast.LENGTH_LONG).show();
}
}
