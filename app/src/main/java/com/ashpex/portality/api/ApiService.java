package com.ashpex.portality.api;

import com.ashpex.portality.model.Course;
import com.ashpex.portality.model.CourseSigned;
import com.ashpex.portality.model.LoginRequest;
import com.ashpex.portality.model.LoginStatus;
import com.ashpex.portality.model.UserCourseOnStudying;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    //URL: https://intro2se-api.herokuapp.com
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
    Call<List<CourseSigned>> getAllUserCourseSigned(@Path("userId") Integer userId, @Header("auth") String token);
    @GET("/user/{userId}/courses")
    Call<List<UserCourseOnStudying>> getUserCourse(@Path("userId") Integer userId, @Header("auth") String token);
    @GET("/course/all")
    Call<List<CourseSigned>> getAllCourse(@Query("page") int page);

    @GET("/course/available")
    Call<List<Course>> getAvailableCourse(@Query("page") int page);

    @Headers({"Content-Type: application/json"})
    @POST("/course/{user_id}/sign")
    Call<RequestBody> signUpCourseRequestStudent(@Path("user_id") int user_id, @Body RequestBody body, @Header("auth") String token);

    @DELETE("/course/{user_id}/unsign/{course_id}")
    Call<ResponseBody> unSignCourse(@Path("user_id") int userId, @Path("course_id") int courseId ,@Header("auth") String token);
}