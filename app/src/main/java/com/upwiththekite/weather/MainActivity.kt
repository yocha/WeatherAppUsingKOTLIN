package com.upwiththekite.weather

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var cityWeather = findViewById<Button>(R.id.getForecastButton)

        cityWeather.setOnClickListener {
            var moveToCityWeather = Intent(getApplicationContext(), CityWeather::class.java)
            startActivity(moveToCityWeather)
        }




    }
}


