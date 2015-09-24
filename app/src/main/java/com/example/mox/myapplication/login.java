package com.example.mox.myapplication;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mox.myapplication.Classes.User;

import org.json.JSONObject;

public class login extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    EditText txtEmail,txtPassword;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = (EditText) findViewById(R.id.txt_email);
        txtPassword = (EditText) findViewById(R.id.txt_password);
        btnLogin =  (Button) findViewById(R.id.btn_login);

        requestQueue = Volley.newRequestQueue(this);

        String url ="http://52.89.124.0/api/v1/users";
        User prueba = new User("mox","wanmarcos","mox@mox.com","mox");
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,url,prueba.creatingJSON(prueba),
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(stringRequest);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            switch(v.getId()){
                case (R.id.btn_login):
                    startActivity(new Intent(this, register.class));
                    break;
            }
        }else{

        }

    }
}
