package com.example.skycast.network

/**
 * A sealed class representing the possible outcomes of an API call.
 *
 * @param T The type of data returned in the success case.
 */
sealed class ApiResponse<out T> {

    /**
     * Represents a successful API response.
     *
     * @param T The type of data returned.
     * @property data The data returned from the API.
     */
    data class Success<out T>(val data: T) : ApiResponse<T>()

    /**
     * Represents an error response from the API.
     *
     * @property message The error message describing what went wrong.
     */
    data class Error(val message: String) : ApiResponse<Nothing>()

    /**
     * Represents a loading state while waiting for the API response.
     */
    data object Loading : ApiResponse<Nothing>()
}