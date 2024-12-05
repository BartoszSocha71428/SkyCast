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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
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

    Box(
        Modifier.fillMaxSize()
            .paint(
                painterResource(id = R.drawable.cloudyskybackground),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Column (
            Modifier.fillMaxWidth().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
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
                    label = { Text("Search for location") },
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
                        contentDescription = "Search for location"
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
                contentDescription = "Location icon",
                Modifier.size(40.dp)
            )
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
            contentDescription = "Weather condition icon",
        )

        Text(
            data.current.condition?.text ?: "",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(15.dp))
        // TODO: Improve string formatting (e.g., text alignment, additional information)
        Card {
            Column (Modifier.fillMaxWidth()) {
                Row(
                    Modifier.fillMaxWidth(),
                    Arrangement.SpaceAround
                ) {
                    WeatherDetails("Humidity", "${data.current.humidity}%")
                    WeatherDetails("Wind", "${data.current.windKph} km/h")
                }
                Row(
                    Modifier.fillMaxWidth(),
                    Arrangement.SpaceAround
                ) {
                    WeatherDetails("Pressure", "${data.current.pressureMb} mb")
                    WeatherDetails("Cloud", "${data.current.cloud}%")
                }
                Row(
                    Modifier.fillMaxWidth(),
                    Arrangement.SpaceAround
                ) {
                    WeatherDetails("Local Time", data.location.localtime.split(" ")[1])
                    WeatherDetails("Local Date", data.location.localtime.split(" ")[0])
                }
            }

        }
    }
}

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