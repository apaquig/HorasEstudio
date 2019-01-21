package com.example.angel.horasestudio;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SesionFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{
    RequestQueue rqq;
    JsonRequest jrqq;

     EditText txtUser,txtPassword;
    Button btnInisiarSesion,btnRegistrarU;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vista=inflater.inflate(R.layout.fragment_sesion, container, false);
        txtUser = (EditText)vista.findViewById(R.id.txtLoginUserL);
        txtPassword=(EditText)vista.findViewById(R.id.txtLoginPassL);
        btnInisiarSesion=(Button)vista.findViewById(R.id.btnLoginL);
        btnRegistrarU=(Button)vista.findViewById(R.id.btnCrearCuentaL);

        rqq =Volley.newRequestQueue(getContext());

        btnInisiarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });
        btnRegistrarU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });

        return vista;
    }

    private void iniciarSesion() {
        String url="https://2cab987a.ngrok.io/login/login.php?cuentaUsuario="+txtUser.getText().toString()+
                "&contrasenia="+txtPassword.getText().toString();
        jrqq =new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        rqq.add(jrqq);
        Toast.makeText(getContext(),"User"+txtUser.getText().toString()+" Pass"+txtPassword.getText().toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"Usuario no existe"+error.toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        User user=new User();
        Toast.makeText(getContext(),"Se ha encontrado el usuario"+txtUser.getText().toString(),Toast.LENGTH_LONG).show();

        JSONArray jsonArray=response.optJSONArray("datos");
        JSONObject jsonObject=null;

        try {
            jsonObject= jsonArray.getJSONObject(0);
            user.setUser(jsonObject.optString("usr"));
            user.setPass(jsonObject.optString("pass"));
            user.setNombre(jsonObject.optString("nombre"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
      /*  Intent intention =new Intent(getContext(),Main2Activity.class );
        intention.putExtra(Main2Activity.nombres, user.getNombre());
        startActivity(intention);*/

    }
       public void registrar(){
        Intent login =new Intent(getContext(), Registrar.class);
        startActivity(login);

    }
}
