package com.example.tugas14_anisahasna.rest;

import com.example.tugas14_anisahasna.model.EmployeeModel;
import com.example.tugas14_anisahasna.model.ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIHandler {

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseModel> login(@Field("username") String username,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseModel> register(@Field("username") String username,
                                 @Field("email") String email,
                                 @Field("password") String password);

    @GET("viewAllEmployee.php")
    Call<List<EmployeeModel>> getAllEmployee();

    @GET("viewDetailEmployee.php")
    Call<EmployeeModel> getDetailEmployee(@Query("id") int id);

    @FormUrlEncoded
    @POST("addEmployee.php")
    Call<ResponseModel> postAddEmployee(@Field("name") String name,
                                        @Field("sex") String sex,
                                        @Field("email") String email,
                                        @Field("phone") String phone,
                                        @Field("address") String address,
                                        @Field("division") String division);
    @FormUrlEncoded
    @POST("updateEmployee.php")
    Call<ResponseModel> postUpdateEmployee(@Field("id") int id,
                                           @Field("name") String name,
                                           @Field("sex") String sex,
                                           @Field("email") String email,
                                           @Field("phone") String phone,
                                           @Field("address") String address,
                                           @Field("division") String division);

    @DELETE("deleteEmployee.php")
    Call<ResponseModel> postDeleteEmployee(@Query("id") String id);
}
