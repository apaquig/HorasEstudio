package com.example.andrew.horasestudio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText nombreUsuario;
    private EditText passsword;
    private Button login;
    private TextView info;
    private int contadorIntentos = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombreUsuario = (EditText)findViewById(R.id.txtUserName);
        passsword = (EditText)findViewById(R.id.txtPassword);
        login = (Button)findViewById(R.id.btnLogin);
        info = (TextView)findViewById(R.id.lblInfo);
        info.setText("Inteos de logueo disponibles: 5");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarIngreso(nombreUsuario.getText().toString(),passsword.getText().toString());
            }
        });
    }

    private void validarIngreso(String usrName, String usrPasswd){
        if((usrName.equals("Admin")) && (usrPasswd.equals("12345"))){
            Intent menuMain = new Intent(MainActivity.this,MenuPrincipal.class);
            startActivity(menuMain);
        }else{
            contadorIntentos--;
            info.setText(String.valueOf("Intentos de Logueo Disponibles: "+contadorIntentos));
            if(contadorIntentos == 0){
                login.setEnabled(false);
            }
        }
    }
}
