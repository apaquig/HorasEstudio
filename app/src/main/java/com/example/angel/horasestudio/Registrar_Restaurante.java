package com.example.angel.horasestudio;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class Registrar_Restaurante extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{

    private RequestQueue rq;
    private JsonRequest jrq;

    private EditText txtNombrePro,txtCedulaPro,txtRestaurante, txtTelefonoPro, txtCorreoPro,txtPassPro,txtDireccionPro;
    private Button btnRegistarUser;

    Button btnRegistarProp;
    View vistaRest;
    private ImageView imagen;
    private int galeria = 1;
    public Registrar_Restaurante() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vistaRest=inflater.inflate(R.layout.fragment_registrar__restaurante, container, false);

        txtNombrePro=(EditText)vistaRest.findViewById(R.id.txtNombrePro);
        txtCedulaPro=(EditText)vistaRest.findViewById(R.id.txtCedulaPro);
        txtRestaurante=(EditText)vistaRest.findViewById(R.id.txtRestaurante);
        txtTelefonoPro=(EditText)vistaRest.findViewById(R.id.txtTelefonoPro);
        txtCorreoPro=(EditText)vistaRest.findViewById(R.id.txtCorreoPro);
        txtPassPro=(EditText)vistaRest.findViewById(R.id.txtPassPro);
        txtDireccionPro=(EditText)vistaRest.findViewById(R.id.txtDireccionPro);
        rq=Volley.newRequestQueue(getContext());

        btnRegistarUser=(Button)vistaRest.findViewById(R.id.btnRegistrarUser);
        btnRegistarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String url="http://jamsfood.atwebpages.com/registrarUsuario.php";
                registrarRestaurante();
            }
        });

        imagen = (ImageView) vistaRest.findViewById(R.id.imarest);
        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Seleccionar imagen"), galeria);
            }
        });

        return vistaRest;

    }

    private void registrarRestaurante() {
        String url="https://2cab987a.ngrok.io/login/registrar.php?cuentaUsuario=" + txtCorreoPro.getText().toString() +
                "&contrasenia=" + txtPassPro.getText().toString()+"&cedula=" + txtCedulaPro.getText().toString()+
                "&nombre=" + txtNombrePro.getText().toString()+"&telefono=" + txtTelefonoPro.getText().toString()+
                "&direccion=" + txtDireccionPro.getText().toString()+"&nombreRestaurante=" + txtRestaurante.getText().toString();
        // Toast.makeText(getContext(),"Name "+nombre.getText().toString()+" Usr "+txtUser.getText().toString()+" Paas "+txtPassword.getText().toString(),Toast.LENGTH_LONG).show();


        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);
    }

    public void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == galeria && resultCode == Activity.RESULT_OK && data != null && data.getData() != null){
            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                imagen.setImageBitmap(bitmap);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"Error al registrar",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        //User user=new User();
        Toast.makeText(getContext(),"Registro correcto",Toast.LENGTH_LONG).show();
        limpiarCaja();

    }

    private void limpiarCaja(){
        txtNombrePro.setText("");
        txtCedulaPro.setText("");
        txtRestaurante.setText("");
        txtTelefonoPro.setText("");
        txtCedulaPro.setText("");
        txtPassPro.setText("");
        txtDireccionPro.setText("");

    }
}