package com.example.angel.horasestudio;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegistrarPlato extends AppCompatActivity {
private EditText txtnombreplato,txtdescripcionplato,txtprecioplato,txtstockplato;
private ImageView imageView;
private Button btnguardarProducto;

    Bitmap bitmap;
    ProgressDialog progressDialog;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private RequestQueue rq;
    private JsonRequest jrq;

    private int galeria = 1;
    private Uri urifile;
    StringRequest stringRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_plato);

        txtnombreplato=(EditText)findViewById(R.id.txtnombreplato);
        txtdescripcionplato=(EditText)findViewById(R.id.txtdescripcionplato);
        txtprecioplato=(EditText)findViewById(R.id.txtprecioplato);
        txtstockplato=(EditText)findViewById(R.id.txtstockplato);
        rq=Volley.newRequestQueue(this);
        btnguardarProducto=(Button)findViewById(R.id.btnguardarProducto);
        imageView=(ImageView)findViewById(R.id.imageView2);

        btnguardarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarWebServices();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Seleccionar imagen"), galeria);
            }
        });
    }
    public void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == galeria && resultCode == Activity.RESULT_OK && data != null && data.getData() != null){
            urifile = data.getData();
            imageView.setImageURI(urifile);

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), urifile);
                imageView.setImageBitmap(bitmap);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    private void cargarWebServices(){
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Registrando plato...");
        progressDialog.show();

        String url=" https://98a2a71c.ngrok.io/login/plato.php?";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.hide();
             //  Toast.makeText(this,"REGISTRADO",Toast.LENGTH_LONG).show();
                limpiarCajas();
                if (response.trim().equalsIgnoreCase("registra")){

                    //limpiarCaja();
                   // Toast.makeText(this,"Se registro correctamente",Toast.LENGTH_LONG).show();
                }else{
                    //Toast.makeText(this,"Error al registrar",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(getContext(),"NO se registro "+error.getStackTrace(),Toast.LENGTH_LONG).show();
                progressDialog.hide();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String plato=txtnombreplato.getText().toString();
                String descripcion=txtdescripcionplato.getText().toString();
                String precio=txtprecioplato.getText().toString();
                String stock=txtstockplato.getText().toString();
                String imagen=combertirImgString(bitmap);

                Map<String,String>parametros=new HashMap<>();
                parametros.put("nombre",plato);
                parametros.put("stock",stock);
                parametros.put("descripcion",descripcion);
                parametros.put("precio",precio);

                parametros.put("imagen",imagen);

                return parametros;
            }
        };
        rq.add(stringRequest);
    }

    private String combertirImgString(Bitmap bitmap) {
        ByteArrayOutputStream array= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,array);
        byte[] imagenByte=array.toByteArray();
        String imagenString= Base64.encodeToString(imagenByte,Base64.DEFAULT);
        return imagenString;
    }
    private void limpiarCajas(){
        txtstockplato.setText("");
        txtprecioplato.setText("");
        txtdescripcionplato.setText("");
        txtnombreplato.setText("");

    }

}
