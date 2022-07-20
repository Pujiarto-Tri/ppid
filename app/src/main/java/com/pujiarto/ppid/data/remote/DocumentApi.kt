package com.pujiarto.ppid.data.remote

import com.pujiarto.ppid.domain.model.DocumentListing
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DocumentApi {

    @GET("/api/Data/?format=api")
    suspend fun getListings(
//        @Query("apikey") apiKey: String = API_KEY
    ): Response<DocumentListing>


    companion object {
//        const val API_KEY = "/api/Data/?format=api"
        const val BASE_URL = "https://ppidlobarnew.pythonanywhere.com"
    }
}