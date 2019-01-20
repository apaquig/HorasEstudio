package com.example.angel.horasestudio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.angel.horasestudio.grid_menufood_kfc;

public class comida_kfc extends AppCompatActivity {
    GridView gridView;
    String[] fruitNames = {"Combo 1","Hmaburguesa","Pollo 5 presas "};
    int[] menuFood_kfc_Images = {R.drawable.kfc_cbx1,R.drawable.kfc_hamburguesa,R.drawable.kfc_pollo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comida_kfc);

        gridView = findViewById(R.id.gridview);
        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(getApplicationContext(),grid_menufood_kfc.class);
                intent.putExtra("name",fruitNames[i]);
                intent.putExtra("image",menuFood_kfc_Images[i]);
                startActivity(intent);
            }
        });
    }

    private class CustomAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return menuFood_kfc_Images.length;
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

            View view1 = getLayoutInflater().inflate(R.layout.menufood_kfc,null);
            TextView name =view1.findViewById(R.id.fruits);
            ImageView image = view1.findViewById(R.id.images);

            name.setText(fruitNames[i]);
            image.setImageResource(menuFood_kfc_Images[i]);
            return view1;
        }
    }
}
