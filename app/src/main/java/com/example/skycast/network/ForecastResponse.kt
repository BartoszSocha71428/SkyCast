package com.example.skycast.network


import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("current")
    val current: Current?,
    @SerializedName("forecast")
    val forecast: Forecast?,
    @SerializedName("location")
    val location: Location?
) {
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
        data class Condition(
            @SerializedName("code")
            val code: String?,
            @SerializedName("icon")
            val icon: String?,
            @SerializedName("text")
            val text: String?
        )
    }

    data class Forecast(
        @SerializedName("forecastday")
        val forecastday: List<Forecastday?>?
    ) {
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
                data class Condition(
                    @SerializedName("code")
                    val code: String?,
                    @SerializedName("icon")
                    val icon: String?,
                    @SerializedName("text")
                    val text: String?
                )
            }

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

    data class Location(
        @SerializedName("country")
        val country: String?,
        @SerializedName("lat")
        val lat: String?,
        @SerializedName("localtime")
        val localtime: String?,
        @SerializedName("localtime_epoch")
        val localtimeEpoch: String?,
        @SerializedName("lon")
        val lon: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("region")
        val region: String?,
        @SerializedName("tz_id")
        val tzId: String?
    )
}