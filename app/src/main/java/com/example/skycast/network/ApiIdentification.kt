package com.example.skycast.network

import com.example.skycast.BuildConfig

/**
 * Object to hold API identification constants.
 */
object ApiIdentification {
    // Api key is read from the build configuration (stored in local.properties).
    const val API_KEY: String = BuildConfig.API_KEY
}