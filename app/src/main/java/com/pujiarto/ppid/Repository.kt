package com.pujiarto.ppid

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url


interface Repository {
    @GET("api/Data/")
    fun getDinas(): Call<MutableList<Dinas>>

    @GET
    @Streaming
    fun donwloadFile(@Url url: String?): Call<ResponseBody>
}