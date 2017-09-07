package com.upwiththekite.weather

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by buy-more on 9/6/2017.
 */

interface WeatherAPI {
    //@GET("yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys")
    @GET("yql?format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys")

    fun getForecast(@Query("q") q: String): Call<Weather>
}

class Weather(val query: WeatherQuery)

class WeatherQuery(val results: WeatherResults)

class WeatherResults(val channel: WeatherChannel)

class WeatherChannel(val title: String, val item: WeatherItem)

class WeatherItem(val forecast: List<WeatherForecast>)

class WeatherForecast(val date: String, val day: String, val high: String, val low: String, text: String)

class WeatherRetriver {
    val service : WeatherAPI

    init {
        val retrofit = Retrofit.Builder().baseUrl("https://query.yahooapis.com/v1/public/").addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(WeatherAPI::class.java)
    }

    fun getForecast(callback: Callback<Weather>, searchCity: String) {

        var searchTerm = searchCity

        if (searchTerm == "") {
            searchTerm = "New York City"
        }

        val q = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"${searchTerm}\")"
        val call = service.getForecast(q)
        call.enqueue(callback)
    }
}

