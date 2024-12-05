package com.example.skycast.network


import com.google.gson.annotations.SerializedName

/**
 * Data class representing the weather response from the API.
 *
 * @property current The current weather conditions.
 * @property location The location information.
 */
data class WeatherResponse(
    @SerializedName("current")
    val current: Current,
    @SerializedName("location")
    val location: Location
) {

    /**
     * Data class representing the current weather conditions.
     *
     * @property cloud The cloud coverage.
     * @property condition The weather condition.
     * @property dewpointC The dew point in Celsius.
     * @property dewpointF The dew point in Fahrenheit.
     * @property feelslikeC The feels-like temperature in Celsius.
     * @property feelslikeF The feels-like temperature in Fahrenheit.
     * @property gustKph The wind gust speed in kilometers per hour.
     * @property gustMph The wind gust speed in miles per hour.
     * @property heatindexC The heat index in Celsius.
     * @property heatindexF The heat index in Fahrenheit.
     * @property humidity The humidity percentage.
     * @property isDay Indicator if it is day (1) or night (0).
     * @property lastUpdated The last updated time.
     * @property lastUpdatedEpoch The last updated time in epoch.
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
     * @property windDir The wind direction as a string.
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
        @SerializedName("dewpoint_c")
        val dewpointC: String?,
        @SerializedName("dewpoint_f")
        val dewpointF: String?,
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
        val tempC: String,
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
         * @property icon The URL of the condition icon.
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
     * Data class representing the location information.
     *
     * @property country The country name.
     * @property lat The latitude.
     * @property localtime The local time.
     * @property localtimeEpoch The local time in epoch.
     * @property lon The longitude.
     * @property name The location name. t
     * @property region The region name.
     * @property tzId The time zone ID.
     */
    data class Location(
        @SerializedName("country")
        val country: String,
        @SerializedName("lat")
        val lat: String?,
        @SerializedName("localtime")
        val localtime: String?,
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