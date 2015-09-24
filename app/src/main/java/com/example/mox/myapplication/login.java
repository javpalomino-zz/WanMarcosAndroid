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
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.mox.myapplication.Classes.User;
import com.example.mox.myapplication.Services.UserService;

import java.util.List;

import retrofit.*;





public class login extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    EditText txtEmail,txtPassword;
    RequestQueue requestQueue;
    TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = (EditText) findViewById(R.id.txt_email);
        txtPassword = (EditText) findViewById(R.id.txt_password);
        btnLogin =  (Button) findViewById(R.id.btn_login);
        mTextView = (TextView) findViewById(R.id.mTextView);

        User prueba = new User("mox","wanmarcos","mox@prueba.com","mox");
        Retrofit response = new Retrofit.Builder().baseUrl("http://52.89.124.0").addConverterFactory(GsonConverterFactory.create()).build();

        UserService service = response.create(UserService.class);

        final Call<User> user = service.createUser(prueba);
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
