package com.example.angel.horasestudio;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class Adaptador extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private Context contexto;
    private String[][] datos;
    private int[] datosImg;


    public Adaptador(Context contexto, String[][] datos, int[] datosImg) {
        this.contexto = contexto;
        this.datos = datos;
        this.datosImg = datosImg;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return datosImg.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.elemento_lista, null);

        ImageView imagen = (ImageView) vista.findViewById(R.id.tvImagen);
        TextView tvNomRest = (TextView) vista.findViewById(R.id.tvNomRest);
        TextView tvDireccion = (TextView) vista.findViewById(R.id.tvDireccion);

        tvNomRest.setText(datos[i][0]);
        tvDireccion.setText(datos[i][1]);
        imagen.setImageResource(datosImg[i]);

        imagen.setTag(i);


        return vista;
    }
}
