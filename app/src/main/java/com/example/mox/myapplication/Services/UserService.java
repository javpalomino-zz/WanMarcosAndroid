package com.example.mox.myapplication.Services;

import com.example.mox.myapplication.Models.User;


import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by mox on 24/09/15.
 */
public interface UserService {

    @POST("/api/v1/users")
    Call<User> createUser(@Body User user);
}
