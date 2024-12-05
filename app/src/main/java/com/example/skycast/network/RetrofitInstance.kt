package com.example.skycast.network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object to provide a Retrofit instance and the WeatherService.
 */
object RetrofitInstance {
    private const val WEATHER_API_BASE_URL = "https://api.weatherapi.com"

    /**
     * Creates and returns a Retrofit instance configured with the base URL and Gson converter.
     *
     * @return A configured Retrofit instance.
     */
    private fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(WEATHER_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provides the WeatherService implementation.
     */
    val weatherService: WeatherService = getInstance().create(WeatherService::class.java)
}