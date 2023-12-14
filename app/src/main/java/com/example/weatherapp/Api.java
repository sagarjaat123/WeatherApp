package com.example.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("weather")
Call<Example>Weather(@Query("q") String cityName,
                     @Query("appid") String key);



}
