package com.example.mox.myapplication.Services;

import android.content.pm.LauncherApps;

import com.example.mox.myapplication.Classes.User;

import java.util.List;


import retrofit.Call;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by mox on 24/09/15.
 */
public interface UserService {

    @POST("/api/v1/")
    Call<User> createUser(@Body User user);
}
