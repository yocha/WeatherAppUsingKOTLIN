package com.upwiththekite.weather

import android.content.Intent
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

        val searchCity = intent.extras.getString("searchCity")

        println(searchCity)

        var listView = findViewById<ListView>(R.id.forecastListView)

        // var myList = listOf("hello","how are you?")

        // var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,myList)

        // listView.adapter = adapter

        var retriver = WeatherRetriver()

        val callback: Callback<Weather> = object : Callback<Weather> {
            override fun onResponse(call: Call<Weather>?, response: Response<Weather>?) {
                println("It's working!")
                title = response?.body()?.query?.results?.channel?.title
                var forecasts = response?.body()?.query?.results?.channel?.item?.forecast

                var forecastStrings = mutableListOf<String>()

                if (forecasts != null) {
                    for (forecast in forecasts) {
                        var forecastString = "${forecast.date} - High: ${forecast.high} Low: ${forecast.low}"
                        forecastStrings.add(forecastString)
                    }
                }

                var adapter = ArrayAdapter(this@CityWeather, android.R.layout.simple_list_item_1,forecastStrings)
                listView.adapter = adapter
            }

            override fun onFailure(call: Call<Weather>?, t: Throwable?) {
                println("Not working! :(")
            }

        }

        retriver.getForecast(callback, searchCity)
    }
}
