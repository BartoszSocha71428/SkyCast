package com.example.skycast.network

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
    // TODO: Add support for multiple languages in the API response (at least polish)
    suspend fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") city: String,
        @Query("lang") lang: String = "en"
    ): Response<WeatherResponse>
}