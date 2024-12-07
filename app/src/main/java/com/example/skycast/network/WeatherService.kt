package com.example.skycast.network

import androidx.compose.ui.text.intl.Locale
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Interface defining the WeatherService API endpoints.
 */
interface WeatherService {

    /**
     * Fetches the current weather for a specified city.
     *
     * @param apiKey The API key for authentication.
     * @param city The name of the city to fetch the weather for.
     * @param lang The language code for the response (default is "en").
     * @return A [Response] containing the [WeatherResponse].
     */
    @GET("/v1/current.json")
    suspend fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") city: String,
        @Query("lang") lang: String = Locale.current.language
    ): Response<WeatherResponse>

    /**
     * Fetches the weather forecast for a specified city including current weather.
     *
     * @param apiKey The API key for authentication.
     * @param city The name of the city to fetch the weather for.
     * @param lang The language code for the response (default is "en").
     * @param days The number of days to fetch the forecast for (default is 5).
     * @return A [Response] containing the [WeatherResponse].
     */
    @GET("/v1/forecast.json")
    suspend fun getForecastWeather(
        @Query("key") apiKey: String,
        @Query("q") city: String,
        @Query("lang") lang: String = Locale.current.language,
        @Query("days") days: String = "5"
    ): Response<WeatherResponse>
}
