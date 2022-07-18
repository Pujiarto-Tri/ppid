package com.pujiarto.ppid.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface DocumentApi {

    @GET("/api/Data/")
    suspend fun getListings(
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody

    companion object {
        const val API_KEY = "/api/Data/"
        const val BASE_URL = "https://ppidlobarnew.pythonanywhere.com"
    }
}