package com.example.skycast.network

data class WeatherResponse(
    val name: String,
    val current: Current,
    val weather: List<Weather>
)
//można jeszcze coś dodać https://openweathermap.org/api/one-call-3#start
data class Current(
    val dt: Double,
    val temp: Double,
    val feels_like: Double,
    val pressure: Int
)

data class Weather(
    val main: String,
    val description: String,
    val icon: String
)