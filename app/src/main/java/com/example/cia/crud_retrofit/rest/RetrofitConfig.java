package com.example.cia.crud_retrofit.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cia on 12/11/2017.
 */

public class RetrofitConfig {
    private static final String base_url= "http://seputarsemarang.000webhostapp.com/api/biodata/";

    public static Retrofit getRetrofit() {


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;

    }

//    public static ApiRequestBiodata getApiService(){
//        return getRetrofit().create(ApiRequestBiodata.class);
//    }
}
