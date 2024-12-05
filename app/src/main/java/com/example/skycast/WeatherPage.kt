package com.example.skycast


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.skycast.network.ApiResponse
import com.example.skycast.network.WeatherResponse


@Composable
fun WeatherPage(viewModel: WeatherViewModel) {
    // TODO: background image/color
    // TODO: Dark mode
    var city by remember {
        mutableStateOf("")
    }

    val weatherResult = viewModel.weatherResult.observeAsState()

    val keyboardController = LocalSoftwareKeyboardController.current

    Column (
        Modifier.fillMaxWidth().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        Row (
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            Arrangement.SpaceEvenly,
            Alignment.CenterVertically
        ) {
            // TODO: add functionality so that when user click enter on keypad it start search
            // TODO: statically sized textfield
            // TODO: add hint text maybe search history
            // TODO: Option for geo location (necessary permissions added to Androidmanifest.xml)
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = city,
                onValueChange = {
                    city = it
                },
                label = {
                    Text("Search for location")
                }
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

        when (val result = weatherResult.value) {
            is ApiResponse.Loading -> CircularProgressIndicator()
            is ApiResponse.Success -> WeatherDetails(result.data)
            is ApiResponse.Error -> Text(result.message)
            null -> { }
        }
    }
}

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
        // TODO: better string format (e.g text aliment maybe different information
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
                    WeatherDetails("Local Time", data.location.localtime?.split(" ")?.get(1))
                    WeatherDetails("Local Date", data.location.localtime?.split(" ")?.get(0))
                }
            }

        }
    }
}

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