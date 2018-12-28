package com.example.win10.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.HorizontalScrollView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

   // ArrayList personNames = new ArrayList<>(Arrays.asList("Rustom", "Raid", "Manikarnika", "Villain", "Sarkari Prauda Pra. Shalee Kasargod"));
  //  ArrayList personImages = new ArrayList<>(Arrays.asList("https://akshaydemo.000webhostapp.com/Trailers/Images/rus.png", "https://akshaydemo.000webhostapp.com/Trailers/Images/villn.png", "https://akshaydemo.000webhostapp.com/Trailers/Images/mani.png", "https://akshaydemo.000webhostapp.com/Trailers/Images/raid.png", "https://akshaydemo.000webhostapp.com/Trailers/Images/spsk.png"));
    String serverurl="https://akshaydemo.000webhostapp.com/Trailers/imglist.php";
    String res = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressDialog progDailog = ProgressDialog.show(this, "Please wait ...", "Loading ...", true);


        RequestQueue requestQueue= Volley.newRequestQueue(this);

        StringRequest stringRequest= new StringRequest(Request.Method.GET, serverurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


               progDailog.dismiss();
                res =response;

                String[] separated = res.split(":");

                ArrayList<String> files = new ArrayList<String>(separated.length);
                for(String file: separated) {
                    files.add(file);
                }

                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
// set a LinearLayoutManager with default vertical orientaion
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView

                // call the constructor of CustomAdapter to send the reference and data to Adapter
                CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, files, files);
                recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView

               // Toast.makeText(MainActivity.this,"resp-"+files,Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);





    }
}
