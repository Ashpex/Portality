package com.ashpex.portality.api;

import com.ashpex.portality.model.Course;
import com.ashpex.portality.model.LoginRequest;
import com.ashpex.portality.model.LoginStatus;
import com.ashpex.portality.model.UserCourseForum;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    //URL: http://intro2se-api.herokuapp.com
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://intro2se-api.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @POST("/user/login")
    Call<LoginStatus> loginAction(@Body LoginRequest loginRequest);
    @GET("/user/{userId}/allCourses")
    Call<List<Course>> getAllUserCourse(@Path("userId") Integer userId, @Header("auth") String token);
    @GET("/user/{userId}/courses")
    Call<List<UserCourseForum>> getUserCourse(@Path("userId") Integer userId, @Header("auth") String token);

    @GET("/course/all")
    Call<List<Course>> getAllCourse(@Query("page") int page);
}
