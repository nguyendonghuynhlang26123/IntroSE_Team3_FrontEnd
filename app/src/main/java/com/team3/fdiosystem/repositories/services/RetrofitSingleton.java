package com.team3.fdiosystem.repositories.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.team3.fdiosystem.Constant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {
    private static OkHttpClient httpClient = new OkHttpClient();
    private static RetrofitSingleton instance = new RetrofitSingleton();
    private static Retrofit retrofit;

    private RetrofitSingleton() {
        retrofit = createAdapter().build();
    }

    public static Retrofit getInstance() {
        return retrofit;
    }

    private Retrofit.Builder createAdapter() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(Constant.BACK_END_API_PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient);
    }
}
