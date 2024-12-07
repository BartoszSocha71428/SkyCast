package com.example.skycast

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.example.skycast.ui.theme.SkyCastTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateOf

class MainActivity : ComponentActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var latilonState = mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Creates service to localization on activity.
         */
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        /**
         * Checks permission and ask for it if not granted.
         */
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            return
        }

        /**
         * Checking if location is null. If it's not parse Double to string
         */
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                latilonState.value = "${location.latitude},${location.longitude}"
            } else {
                // TODO: change logic if is null
                Log.d("Location","Nie można uzyskać lokalizacji")
            }
        }

        val weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        setContent {
            SkyCastTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeatherPage(weatherViewModel, latilonState.value)
                }
            }
        }
    }

    /**
     * This value represents if permission for localization was granted. Sometimes localization
     * can be null. To avoid this application simply restarts.
     */
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Log.d("Location","Access Granted")
                recreate()
            } else {
                Log.d("Location", "Access denied")
            }
        }
}

