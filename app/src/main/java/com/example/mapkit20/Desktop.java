package com.example.mapkit20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Desktop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desktop);
    }
    public void onClick1(View view) {
        Intent intent = new Intent(Desktop.this, MainActivity.class);
        startActivity(intent);
    }
    public void onClick2(View view) {
        Intent intent = new Intent(Desktop.this, Layers.class);
        startActivity(intent);
    }
    public void onClick3(View view) {
        Intent intent = new Intent(Desktop.this, CustomLayerActivity.class);
        startActivity(intent);
    }
}
