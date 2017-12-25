package com.example.cia.crud_retrofit.rest;

import com.example.cia.crud_retrofit.model.DataModel;
import com.example.cia.crud_retrofit.model.ResponseLogin;
import com.example.cia.crud_retrofit.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by cia on 12/11/2017.
 */

public interface ApiRequestBiodata {
    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseModel> sendBiodata(@Field("nama") String nama,
                                    @Field("usia") String usia,
                                    @Field("tempat") String tempat);

    @GET("read.php")
    Call<ResponseModel> getBiodata();

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseModel> updateBiodata(@Field("id") String id,
                                      @Field("nama") String nama,
                                      @Field("usia") String usia,
                                      @Field("tempat") String tempat);
    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseModel> deleteBiodata(@Field("id") String id);

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseLogin> postLogin(@Field("email") String email,
                              @Field("password") String password,
                              @Field("rule") String rule);

    @FormUrlEncoded
    @POST("userlogin.php")
    Call<ResponseModel> Login(@Field("email") String email,
                                  @Field("password") String password);
}
