package com.example.skycast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.skycast.network.ApiResponse
import com.example.skycast.network.WeatherResponse
import com.example.skycast.ui.theme.BlueDark
import com.example.skycast.ui.theme.BlueLight
import java.text.SimpleDateFormat
import java.util.*

/**
 * Composable function to display the weather page.
 *
 * @param viewModel The WeatherViewModel instance to fetch weather data.
 */

@Composable
fun WeatherPage(viewModel: WeatherViewModel) {
    // TODO: Implement dark mode support
    var city by remember {
        mutableStateOf("")
    }
    val weatherResult = viewModel.weatherResult.observeAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberScrollState()

    Box(
        Modifier.fillMaxSize()
            .paint(
                painterResource(id = R.drawable.cloudyskybackground),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Column (
            Modifier.fillMaxWidth().padding(8.dp).verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Spacer(modifier = Modifier.height(100.dp))

            Row (
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                Arrangement.SpaceEvenly,
                Alignment.CenterVertically
            ) {
                // TODO: Add hint text and maybe search history
                // TODO: Add option for geolocation (necessary permissions added to Androidmanifest.xml)
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = city,
                    onValueChange = { city = it },
                    label = { Text(stringResource(R.string.search)) },
                    shape = RoundedCornerShape(20.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        unfocusedIndicatorColor = BlueLight,
                        focusedIndicatorColor = BlueLight,
                        focusedLabelColor = BlueDark,
                        unfocusedLabelColor = BlueLight,
                    ),
                    keyboardOptions = KeyboardOptions( imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            viewModel.getData(city)
                            keyboardController?.hide()
                        }
                    )
                )
                IconButton(onClick = {
                    viewModel.getData(city)
                    keyboardController?.hide()
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search)
                    )
                }
            }
            menageRespons(weatherResult)
        }
    }
}


/**
 * Composable function to display weather details.
 *
 * @param data The WeatherResponse data to display.
 */
@Composable
fun WeatherDetails(data: WeatherResponse) {
    Column (
        Modifier.fillMaxWidth().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            Modifier.fillMaxWidth(),
            Arrangement.Start,
            Alignment.Bottom
        ) {
            Icon(
                Icons.Default.LocationOn,
                contentDescription = stringResource(R.string.locationIcon),
                Modifier.size(40.dp)
            )
            // TODO: need to change logic because of API limitations to translate country name
            Text(data.location.name, fontSize = 30.sp)
            Spacer(modifier = Modifier.width(10.dp))
            Text(data.location.country, fontSize = 20.sp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            "${data.current.tempC}°C",
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        AsyncImage(
            modifier = Modifier.size(160.dp),
            model = "https:${data.current.condition?.icon}".replace("64x64", "128x128"),
            contentDescription = stringResource(R.string.conditionIcon),
        )

        Text(
            data.current.condition?.text ?: "",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(15.dp))

        // TODO: Improve string formatting (e.g., text alignment, additional information)
        val weatherData: List<Pair<String, String>> = listOf(
            stringResource(R.string.humidity) to "${data.current.humidity}%",
            stringResource(R.string.wind) to "${data.current.windKph} km/h",
            stringResource(R.string.pressure) to "${data.current.pressureMb} mb",
            stringResource(R.string.cloud) to "${data.current.cloud}%",
            stringResource(R.string.localTime) to data.location.localtime.split(" ")[1],
            stringResource(R.string.localDate) to data.location.localtime.split(" ")[0]
        )

        Card (
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black,
                disabledContainerColor = Color.White,
                )
        ) {
            Column (Modifier.fillMaxWidth().padding(8.dp)) {
                WeatherDetails(weatherData)
            }
        }
    }
    WeatherForecast(data)
}

@Composable
fun WeatherDetails(data: List<Pair<String, String>>) {
    for (i in data.indices step 2) {
        Row(
            Modifier.fillMaxWidth(),
            Arrangement.SpaceAround
        ) {
            WeatherDetails(data[i].first, data[i].second)
            if (i + 1 < data.size) {
                WeatherDetails(data[i + 1].first, data[i + 1].second)
            }
        }
    }
}
// TODO: Update string dictionaries (Polish and English) with strings used below
/**
 * Composable function to display a key-value pair of weather details.
 *
 * @param key The key or label of the weather detail.
 * @param value The value of the weather detail.
 */
@Composable
fun WeatherDetails(key: String?, value: String?) {
    Column(
        Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(value ?:"N/A", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(key ?: "N/A", fontWeight = FontWeight.SemiBold, color = Color.Gray)
    }
}

@Composable
fun WeatherForecast(data: WeatherResponse) {
    Spacer(modifier = Modifier.height(20.dp))
    Card (
        colors = CardDefaults.cardColors(
        containerColor = Color.Transparent,
        contentColor = Color.Black,
        disabledContainerColor = Color.White
        )
    ) {
        Column (Modifier.fillMaxWidth()) {
            Text("Forecast", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            data.forecast?.forecastday?.forEach {
                Row (
                    Modifier.fillMaxWidth().padding(4.dp),
                    Arrangement.Start,
                ) {
                    WeatherForecast(it)
                }
            }
        }
    }
}

@Composable
fun WeatherForecast(forecast: WeatherResponse.Forecast.Forecastday?) {
    forecast?.let {
        val dayOfTheWeek = it.date?.let  { x -> getDayOfWeekLegacy(x) }
        Text(dayOfTheWeek ?: "N/A", fontWeight = FontWeight.Bold)
        AsyncImage(
            model = "https:${it.day?.condition?.icon}",
            contentDescription = "Weather condition icon",
        )
        Text("${it.day?.maxtempC}°C ${it.day?.mintempC}°C", fontWeight = FontWeight.Bold)

    }
}

/**
 * Composable function to manage the API response and display the appropriate UI.
 *
 * @param weatherResult The state of the API response.
 */
@Composable
private fun menageRespons(weatherResult: State<ApiResponse<WeatherResponse>?>) {
    when (val result = weatherResult.value) {
        is ApiResponse.Loading -> CircularProgressIndicator()
        is ApiResponse.Success -> WeatherDetails(result.data)
        is ApiResponse.Error -> Text(result.message)
        null -> {}
    }
}

@Composable
fun getDayOfWeekLegacy(dateString: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = dateFormat.parse(dateString)
    val calendar = Calendar.getInstance()
    calendar.time = date

    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) // Returns 1 (Sunday) to 7 (Saturday)

    return when (dayOfWeek) {
        Calendar.SUNDAY -> "SUNDAY"
        Calendar.MONDAY -> "MONDAY"
        Calendar.TUESDAY -> "TUESDAY"
        Calendar.WEDNESDAY -> "WEDNESDAY"
        Calendar.THURSDAY -> "THURSDAY"
        Calendar.FRIDAY -> "FRIDAY"
        Calendar.SATURDAY -> "SATURDAY"
        else -> "UNKNOWN"
    }
}