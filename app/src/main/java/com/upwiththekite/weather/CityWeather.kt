package com.upwiththekite.weather

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CityWeather : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_weather)

        var listView = findViewById<ListView>(R.id.forecastListView)

        var myList = listOf("hello","how are you?")

        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,myList)

        listView.adapter = adapter

        var retriver = WeatherRetriver()

        val callback = object : Callback<List<Forecast>> {
            override fun onResponse(call: Call<List<Forecast>>?, response: Response<List<Forecast>>?) {
                println("We got a response")
                println(response?.body())
                for (forecastDay in response!!.body()!!) {
                    println("High:${forecastDay.high} Low:${forecastDay.low}")
                }
            }

            override fun onFailure(call: Call<List<Forecast>>?, t: Throwable?) {
                println("We got a fail")
            }
        }
        retriver.getForecast(callback)
    }
}
