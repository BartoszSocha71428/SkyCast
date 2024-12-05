package com.example.skycast.network


import com.google.gson.annotations.SerializedName

/**
 * Data class representing the weather response from the API.
 *
 * @property current The current weather conditions.
 * @property forecast The weather forecast (optional).
 * @property location The location information.
 */
data class WeatherResponse(
    @SerializedName("current")
    val current: Current,
    @SerializedName("forecast")
    val forecast: Forecast?,
    @SerializedName("location")
    val location: Location
) {

    /**
     * Data class representing the current weather conditions.
     *
     * @property cloud The cloud coverage.
     * @property condition The weather condition.
     * @property dewpoStringC The dew point in Celsius.
     * @property dewpoStringF The dew point in Fahrenheit.
     * @property feelslikeC The feels-like temperature in Celsius.
     * @property feelslikeF The feels-like temperature in Fahrenheit.
     * @property gustKph The wind gust speed in kilometers per hour.
     * @property gustMph The wind gust speed in miles per hour.
     * @property heatindexC The heat index in Celsius.
     * @property heatindexF The heat index in Fahrenheit.
     * @property humidity The humidity percentage.
     * @property isDay Indicator if it is day time.
     * @property lastUpdated The last updated time.
     * @property lastUpdatedEpoch The last updated time in epoch format.
     * @property precipIn The precipitation in inches.
     * @property precipMm The precipitation in millimeters.
     * @property pressureIn The pressure in inches.
     * @property pressureMb The pressure in millibars.
     * @property tempC The temperature in Celsius.
     * @property tempF The temperature in Fahrenheit.
     * @property uv The UV index.
     * @property visKm The visibility in kilometers.
     * @property visMiles The visibility in miles.
     * @property windDegree The wind direction in degrees.
     * @property windDir The wind direction.
     * @property windKph The wind speed in kilometers per hour.
     * @property windMph The wind speed in miles per hour.
     * @property windchillC The wind chill in Celsius.
     * @property windchillF The wind chill in Fahrenheit.
     */
    data class Current(
        @SerializedName("cloud")
        val cloud: String?,
        @SerializedName("condition")
        val condition: Condition?,
        @SerializedName("dewpoString_c")
        val dewpoStringC: String?,
        @SerializedName("dewpoString_f")
        val dewpoStringF: String?,
        @SerializedName("feelslike_c")
        val feelslikeC: String?,
        @SerializedName("feelslike_f")
        val feelslikeF: String?,
        @SerializedName("gust_kph")
        val gustKph: String?,
        @SerializedName("gust_mph")
        val gustMph: String?,
        @SerializedName("heatindex_c")
        val heatindexC: String?,
        @SerializedName("heatindex_f")
        val heatindexF: String?,
        @SerializedName("humidity")
        val humidity: String?,
        @SerializedName("is_day")
        val isDay: String?,
        @SerializedName("last_updated")
        val lastUpdated: String?,
        @SerializedName("last_updated_epoch")
        val lastUpdatedEpoch: String?,
        @SerializedName("precip_in")
        val precipIn: String?,
        @SerializedName("precip_mm")
        val precipMm: String?,
        @SerializedName("pressure_in")
        val pressureIn: String?,
        @SerializedName("pressure_mb")
        val pressureMb: String?,
        @SerializedName("temp_c")
        val tempC: String?,
        @SerializedName("temp_f")
        val tempF: String?,
        @SerializedName("uv")
        val uv: String?,
        @SerializedName("vis_km")
        val visKm: String?,
        @SerializedName("vis_miles")
        val visMiles: String?,
        @SerializedName("wind_degree")
        val windDegree: String?,
        @SerializedName("wind_dir")
        val windDir: String?,
        @SerializedName("wind_kph")
        val windKph: String?,
        @SerializedName("wind_mph")
        val windMph: String?,
        @SerializedName("windchill_c")
        val windchillC: String?,
        @SerializedName("windchill_f")
        val windchillF: String?
    ) {

        /**
         * Data class representing the weather condition.
         *
         * @property code The condition code.
         * @property icon The condition icon URL.
         * @property text The condition description.
         */
        data class Condition(
            @SerializedName("code")
            val code: String?,
            @SerializedName("icon")
            val icon: String?,
            @SerializedName("text")
            val text: String?
        )
    }

    /**
     * Data class representing the weather forecast.
     *
     * @property forecastday The list of forecast days.
     */
    data class Forecast(
        @SerializedName("forecastday")
        val forecastday: List<Forecastday?>?
    ) {

        /**
         * Data class representing a forecast day.
         *
         * @property astro The astronomical data.
         * @property date The date of the forecast.
         * @property dateEpoch The date of the forecast in epoch format.
         * @property day The day weather data.
         * @property hour The hourly weather data.
         */
        data class Forecastday(
            @SerializedName("astro")
            val astro: Astro?,
            @SerializedName("date")
            val date: String?,
            @SerializedName("date_epoch")
            val dateEpoch: String?,
            @SerializedName("day")
            val day: Day?,
            @SerializedName("hour")
            val hour: List<Hour?>?
        ) {

            /**
             * Data class representing astronomical data.
             *
             * @property isMoonUp Indicator if the moon is up.
             * @property isSunUp Indicator if the sun is up.
             * @property moonIllumination The moon illumination percentage.
             * @property moonPhase The moon phase.
             * @property moonrise The moonrise time.
             * @property moonset The moonset time.
             * @property sunrise The sunrise time.
             * @property sunset The sunset time.
             */
            data class Astro(
                @SerializedName("is_moon_up")
                val isMoonUp: String?,
                @SerializedName("is_sun_up")
                val isSunUp: String?,
                @SerializedName("moon_illumination")
                val moonIllumination: String?,
                @SerializedName("moon_phase")
                val moonPhase: String?,
                @SerializedName("moonrise")
                val moonrise: String?,
                @SerializedName("moonset")
                val moonset: String?,
                @SerializedName("sunrise")
                val sunrise: String?,
                @SerializedName("sunset")
                val sunset: String?
            )

            /**
             * Data class representing the day weather data.
             *
             * @property avghumidity The average humidity.
             * @property avgtempC The average temperature in Celsius.
             * @property avgtempF The average temperature in Fahrenheit.
             * @property avgvisKm The average visibility in kilometers.
             * @property avgvisMiles The average visibility in miles.
             * @property condition The weather condition.
             * @property dailyChanceOfRain The daily chance of rain.
             * @property dailyChanceOfSnow The daily chance of snow.
             * @property dailyWillItRain Indicator if it will rain.
             * @property dailyWillItSnow Indicator if it will snow.
             * @property maxtempC The maximum temperature in Celsius.
             * @property maxtempF The maximum temperature in Fahrenheit.
             * @property maxwindKph The maximum wind speed in kilometers per hour.
             * @property maxwindMph The maximum wind speed in miles per hour.
             * @property mStringempC The minimum temperature in Celsius.
             * @property mStringempF The minimum temperature in Fahrenheit.
             * @property totalprecipIn The total precipitation in inches.
             * @property totalprecipMm The total precipitation in millimeters.
             * @property totalsnowCm The total snowfall in centimeters.
             * @property uv The UV index.
             */
            data class Day(
                @SerializedName("avghumidity")
                val avghumidity: String?,
                @SerializedName("avgtemp_c")
                val avgtempC: String?,
                @SerializedName("avgtemp_f")
                val avgtempF: String?,
                @SerializedName("avgvis_km")
                val avgvisKm: String?,
                @SerializedName("avgvis_miles")
                val avgvisMiles: String?,
                @SerializedName("condition")
                val condition: Condition?,
                @SerializedName("daily_chance_of_rain")
                val dailyChanceOfRain: String?,
                @SerializedName("daily_chance_of_snow")
                val dailyChanceOfSnow: String?,
                @SerializedName("daily_will_it_rain")
                val dailyWillItRain: String?,
                @SerializedName("daily_will_it_snow")
                val dailyWillItSnow: String?,
                @SerializedName("maxtemp_c")
                val maxtempC: String?,
                @SerializedName("maxtemp_f")
                val maxtempF: String?,
                @SerializedName("maxwind_kph")
                val maxwindKph: String?,
                @SerializedName("maxwind_mph")
                val maxwindMph: String?,
                @SerializedName("mStringemp_c")
                val mStringempC: String?,
                @SerializedName("mStringemp_f")
                val mStringempF: String?,
                @SerializedName("totalprecip_in")
                val totalprecipIn: String?,
                @SerializedName("totalprecip_mm")
                val totalprecipMm: String?,
                @SerializedName("totalsnow_cm")
                val totalsnowCm: String?,
                @SerializedName("uv")
                val uv: String?
            ) {

                /**
                 * Data class representing the weather condition.
                 *
                 * @property code The condition code.
                 * @property icon The condition icon URL.
                 * @property text The condition description.
                 */
                data class Condition(
                    @SerializedName("code")
                    val code: String?,
                    @SerializedName("icon")
                    val icon: String?,
                    @SerializedName("text")
                    val text: String?
                )
            }

            /**
             * Data class representing the hourly weather data.
             *
             * @property chanceOfRain The chance of rain.
             * @property chanceOfSnow The chance of snow.
             * @property cloud The cloud coverage.
             * @property condition The weather condition.
             * @property dewpoStringC The dew point in Celsius.
             * @property dewpoStringF The dew point in Fahrenheit.
             * @property feelslikeC The feels-like temperature in Celsius.
             * @property feelslikeF The feels-like temperature in Fahrenheit.
             * @property gustKph The wind gust speed in kilometers per hour.
             * @property gustMph The wind gust speed in miles per hour.
             * @property heatindexC The heat index in Celsius.
             * @property heatindexF The heat index in Fahrenheit.
             * @property humidity The humidity percentage.
             * @property isDay Indicator if it is day time.
             * @property precipIn The precipitation in inches.
             * @property precipMm The precipitation in millimeters.
             * @property pressureIn The pressure in inches.
             * @property pressureMb The pressure in millibars.
             * @property snowCm The snowfall in centimeters.
             * @property tempC The temperature in Celsius.
             * @property tempF The temperature in Fahrenheit.
             * @property time The time of the forecast.
             * @property timeEpoch The time of the forecast in epoch format.
             * @property uv The UV index.
             * @property visKm The visibility in kilometers.
             * @property visMiles The visibility in miles.
             * @property willItRain Indicator if it will rain.
             * @property willItSnow Indicator if it will snow.
             * @property windDegree The wind direction in degrees.
             * @property windDir The wind direction.
             * @property windKph The wind speed in kilometers per hour.
             * @property windMph The wind speed in miles per hour.
             * @property windchillC The wind chill in Celsius.
             * @property windchillF The wind chill in Fahrenheit.
             */
            data class Hour(
                @SerializedName("chance_of_rain")
                val chanceOfRain: String?,
                @SerializedName("chance_of_snow")
                val chanceOfSnow: String?,
                @SerializedName("cloud")
                val cloud: String?,
                @SerializedName("condition")
                val condition: Condition?,
                @SerializedName("dewpoString_c")
                val dewpoStringC: String?,
                @SerializedName("dewpoString_f")
                val dewpoStringF: String?,
                @SerializedName("feelslike_c")
                val feelslikeC: String?,
                @SerializedName("feelslike_f")
                val feelslikeF: String?,
                @SerializedName("gust_kph")
                val gustKph: String?,
                @SerializedName("gust_mph")
                val gustMph: String?,
                @SerializedName("heatindex_c")
                val heatindexC: String?,
                @SerializedName("heatindex_f")
                val heatindexF: String?,
                @SerializedName("humidity")
                val humidity: String?,
                @SerializedName("is_day")
                val isDay: String?,
                @SerializedName("precip_in")
                val precipIn: String?,
                @SerializedName("precip_mm")
                val precipMm: String?,
                @SerializedName("pressure_in")
                val pressureIn: String?,
                @SerializedName("pressure_mb")
                val pressureMb: String?,
                @SerializedName("snow_cm")
                val snowCm: String?,
                @SerializedName("temp_c")
                val tempC: String?,
                @SerializedName("temp_f")
                val tempF: String?,
                @SerializedName("time")
                val time: String?,
                @SerializedName("time_epoch")
                val timeEpoch: String?,
                @SerializedName("uv")
                val uv: String?,
                @SerializedName("vis_km")
                val visKm: String?,
                @SerializedName("vis_miles")
                val visMiles: String?,
                @SerializedName("will_it_rain")
                val willItRain: String?,
                @SerializedName("will_it_snow")
                val willItSnow: String?,
                @SerializedName("wind_degree")
                val windDegree: String?,
                @SerializedName("wind_dir")
                val windDir: String?,
                @SerializedName("wind_kph")
                val windKph: String?,
                @SerializedName("wind_mph")
                val windMph: String?,
                @SerializedName("windchill_c")
                val windchillC: String?,
                @SerializedName("windchill_f")
                val windchillF: String?
            ) {

                /**
                 * Data class representing the weather condition.
                 *
                 * @property code The condition code.
                 * @property icon The condition icon URL.
                 * @property text The condition description.
                 */
                data class Condition(
                    @SerializedName("code")
                    val code: String?,
                    @SerializedName("icon")
                    val icon: String?,
                    @SerializedName("text")
                    val text: String?
                )
            }
        }
    }

    /**
     * Data class representing the location information.
     *
     * @property country The country name.
     * @property lat The latitude.
     * @property localtime The local time.
     * @property localtimeEpoch The local time in epoch format.
     * @property lon The longitude.
     * @property name The city name.
     * @property region The region name.
     * @property tzId The time zone ID.
     */
    data class Location(
        @SerializedName("country")
        val country: String,
        @SerializedName("lat")
        val lat: String?,
        @SerializedName("localtime")
        val localtime: String,
        @SerializedName("localtime_epoch")
        val localtimeEpoch: String?,
        @SerializedName("lon")
        val lon: String?,
        @SerializedName("name")
        val name: String,
        @SerializedName("region")
        val region: String?,
        @SerializedName("tz_id")
        val tzId: String?
    )
}