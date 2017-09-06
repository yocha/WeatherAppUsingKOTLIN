package com.upwiththekite.weather

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by buy-more on 9/6/2017.
 */

interface WeatherAPI {
    @GET("bins/1do0et")
    fun getForecast(): Call<List<Forecast>>
}

class Forecast(val high: String, val low: String)

class WeatherRetriver {
    val service : WeatherAPI

    init {
        val retrofit = Retrofit.Builder().baseUrl("https://api.myjson.com/").addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(WeatherAPI::class.java)
    }

    fun getForecast(callback: Callback<List<Forecast>>) {
        val call = service.getForecast()
        call.enqueue(callback)
    }
}

