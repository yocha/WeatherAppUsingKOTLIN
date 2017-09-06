package com.upwiththekite.weather

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class CityWeather : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_weather)

        var listView = findViewById<ListView>(R.id.forecastListView)

        var myList = listOf("hello","how are you?")

        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,myList)

        listView.adapter = adapter
    }
}
