package com.example.angel.horasestudio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{
    private int valor1;
    RequestQueue rq;
    JsonRequest jrq;
    private EditText txtUser,txtPass;
    private Button btnCrearCuenta,btnIniciarSesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUser=(EditText)findViewById(R.id.txtUserLogin);
        txtPass=(EditText)findViewById(R.id.txtPassLogin);

        btnCrearCuenta=(Button)findViewById(R.id.btnCrearCuenta);
        btnIniciarSesion=(Button)findViewById(R.id.btnInisiarSesionR);
        rq=Volley.newRequestQueue(this);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarsesion();
            }
        });
        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               registrar();
            }
        });

    //prueba de subida
        //este es una nueva pruebA
    }

    private void iniciarsesion() {
        String url="http://jamsfood.atwebpages.com/loguin.php?cuentaUsuario="+txtUser.getText().toString()+
                "&contrasenia="+txtPass.getText().toString();
        jrq=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        rq.add(jrq);
    }

    public void registrar(){
        Intent login =new Intent(this, Registrar.class);
        startActivity(login);

    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this,"No se ha encontrado el usuario"+error.toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        //User user=new User();
        Toast.makeText(this,"Bienvenido"+txtUser.getText().toString(),Toast.LENGTH_LONG).show();

        //JSONArray jsonArray=response.optJSONArray("datos");
       // JSONObject jsonObject=null;

      /*  try {
            jsonObject= jsonArray.getJSONObject(0);
            user.setUser(jsonObject.optString("usr"));
            user.setPass(jsonObject.optString("pass"));
            user.setNombre(jsonObject.optString("nombre"));
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        //Intent intention =new Intent(this,Main2Activity.class );
        //intention.putExtra(Main2Activity.nombres, user.getNombre());
        //startActivity(intention);

    }
}
