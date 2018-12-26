package com.example.win10.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.HorizontalScrollView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ArrayList personNames = new ArrayList<>(Arrays.asList("Rustom", "Raid", "Manikarnika", "Villain", "Sarkari Prauda Pra. Shalee Kasargod"));
    ArrayList personImages = new ArrayList<>(Arrays.asList(R.drawable.rus, R.drawable.raid, R.drawable.mani, R.drawable.villn, R.drawable.spsk));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
// set a LinearLayoutManager with default vertical orientaion
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView

        // call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, personNames, personImages);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView


    }
}
