package com.example.skycast

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skycast.network.ApiIdentification
import com.example.skycast.network.ApiResponse
import com.example.skycast.network.RetrofitInstance
import com.example.skycast.network.WeatherResponse
import kotlinx.coroutines.launch

/**
 * ViewModel for managing weather data.
 * This ViewModel fetches weather data from the WeatherService and exposes it via LiveData.
 */
class WeatherViewModel : ViewModel() {

    private val weatherService = RetrofitInstance.weatherService
    private val _weatherResult = MutableLiveData<ApiResponse<WeatherResponse>>()
    val weatherResult: MutableLiveData<ApiResponse<WeatherResponse>> = _weatherResult

    /**
     * Fetches weather data for the specified city.
     *
     * @param city The name of the city to fetch the weather for.
     */
    fun getData(city: String) {
        _weatherResult.value = ApiResponse.Loading
        viewModelScope.launch {
            try {
                val response = weatherService.getForecastWeather(ApiIdentification.API_KEY, city)

                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherResult.value = ApiResponse.Success(it)
                    }
                } else {
                    _weatherResult.value =
                        ApiResponse.Error("Failed to load weather information :(")
                }
            }
            // Errors can be added to dictionary only in @Composable
            catch (e: Exception) {
                // TODO: Handle specific exceptions such as network errors, API limit exceeded, etc.
                Log.e("WeatherViewModel", "getData: ${e.message}")
                _weatherResult.value = ApiResponse.Error("Failed to load weather information :(")
            }
        }
    }
}