package com.example.angel.horasestudio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Ventana_Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana__principal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemRestaurantes:
                Intent i = new Intent(this,Restaurantes.class);
                startActivity(i);
                Toast.makeText(this, "Restaurantes", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.itemVerCarrito:
                Toast.makeText(this, "Rsstuarante", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.itemSalir:
                Toast.makeText(this, "Rsstuarante", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
