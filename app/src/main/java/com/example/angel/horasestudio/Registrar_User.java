package com.example.angel.horasestudio;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Registrar_User extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{

    private RequestQueue rq;
    private JsonRequest jrq;

    private EditText txtNombre,txtCedula,txtCorreo,txtPassword,txtDireccion,txtTelefono;
    private Button btnRegistarUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment hola
       View  vistaUser=inflater.inflate(R.layout.fragment_registrar__user, container, false);

       txtNombre=(EditText)vistaUser.findViewById(R.id.txtNombreUser);
        txtCedula=(EditText)vistaUser.findViewById(R.id.txtCedulaUser);
        txtCorreo=(EditText)vistaUser.findViewById(R.id.txtCorreoUser);
        txtDireccion=(EditText)vistaUser.findViewById(R.id.txtDireccionUser);
        txtTelefono=(EditText)vistaUser.findViewById(R.id.txtTelefonoUser);
        txtPassword=(EditText)vistaUser.findViewById(R.id.txtPassUser);
        rq=Volley.newRequestQueue(getContext());


        btnRegistarUser=(Button)vistaUser.findViewById(R.id.btnRegistrarUser);
        btnRegistarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //String url="http://jamsfood.atwebpages.com/registrarUsuario.php";
                registrarUsuario();
            }
        });
        return vistaUser;
    }
    private void registrarUsuario() {
    String url=" https://98a2a71c.ngrok.io/login/registrar.php?cuentaUsuario=" + txtCorreo.getText().toString() +
            "&contrasenia=" + txtPassword.getText().toString()+"&cedula=" + txtCedula.getText().toString()+
            "&nombre=" + txtNombre.getText().toString()+"&telefono=" + txtTelefono.getText().toString()+
            "&direccion=" + txtDireccion.getText().toString();
   // Toast.makeText(getContext(),"Name "+nombre.getText().toString()+" Usr "+txtUser.getText().toString()+" Paas "+txtPassword.getText().toString(),Toast.LENGTH_LONG).show();


       /* String url="http://jamsfood.atwebpages.com/registrarUsuario.php?cuentaUsuario=" + txtCorreo.getText().toString() +
                "&contrasenia=" + txtPassword.getText().toString()+"&cedula=" + txtCedula.getText().toString()+
                "&nombre=" + txtNombre.getText().toString()+"&telefono=" + txtTelefono.getText().toString()+
                "&direccion=" + txtDireccion.getText().toString();*/

        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);
    }
        @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"Error al registrar",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        //User user=new User();
        Toast.makeText(getContext(),"Registro correcto",Toast.LENGTH_LONG).show();
        limoiarcaja();
        //limpiarCajas();
    }
    private void limoiarcaja(){
        txtNombre.setText("");
        txtCedula.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtPassword.setText("");

    }

}
