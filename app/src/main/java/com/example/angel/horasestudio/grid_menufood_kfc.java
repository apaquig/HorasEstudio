package com.example.angel.horasestudio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class grid_menufood_kfc extends AppCompatActivity {

    TextView name;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_menukfc);

    name = findViewById(R.id.griddata);
    image = findViewById(R.id.imageView);

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
    image.setImageResource(intent.getIntExtra("image",0));
    }
}
