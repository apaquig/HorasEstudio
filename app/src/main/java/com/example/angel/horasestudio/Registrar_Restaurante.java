package com.example.angel.horasestudio;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Base64DataException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Registrar_Restaurante extends Fragment{

    private static  final String ARG_PARM1="param1";
    private static  final String ARG_PARM2="param2";
    File fileImagen;
    Bitmap bitmap;
    ProgressDialog progressDialog;

    private static final int IMAGE_REQUEST_CODE = 3;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private RequestQueue rq;
    private JsonRequest jrq;

    private EditText txtNombrePro,txtCedulaPro,txtRestaurante, txtTelefonoPro, txtCorreoPro,txtPassPro,txtDireccionPro;

    private Button btnRegistarProp;
    View vistaRest;
    private ImageView imagen;
    private int galeria = 1;
    Uri uri;

    StringRequest stringRequest;
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

        btnRegistarProp=(Button)vistaRest.findViewById(R.id.btnRegistarProp);
        btnRegistarProp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String url="http://jamsfood.atwebpages.com/registrarUsuario.php";
                cargarWebServices();
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
    public void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == galeria && resultCode == Activity.RESULT_OK && data != null && data.getData() != null){
            uri = data.getData();
            imagen.setImageURI(uri);

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                imagen.setImageBitmap(bitmap);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    private void cargarWebServices(){
         progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Registrando...");
        progressDialog.show();

        String url="https://d4ee9633.ngrok.io/login/registrarRest.php?";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                       progressDialog.hide();
                if (response.trim().equalsIgnoreCase("registra")){

                    limpiarCaja();
                    Toast.makeText(getContext(),"Se registro correctamente",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getContext(),"Error al registrar",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"NO se registro "+error.getStackTrace(),Toast.LENGTH_LONG).show();
                      progressDialog.hide();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String nombreRestaurante=txtRestaurante.getText().toString();
                String Usuario_IdUsuario=txtCorreoPro.getText().toString();
                String logo=combertirImgString(bitmap);

                Map<String,String>parametros=new HashMap<>();
                parametros.put("nombreRestaurante",nombreRestaurante);
                parametros.put("Usuario_IdUsuario",Usuario_IdUsuario);
                parametros.put("logo",logo);

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

    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    private void limpiarCaja(){
        txtNombrePro.setText("");
        txtCedulaPro.setText("");
        txtRestaurante.setText("");
        txtTelefonoPro.setText("");
        txtCorreoPro.setText("");
        txtPassPro.setText("");
        txtDireccionPro.setText("");


    }

}
