package com.pujiarto.ppid

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    const val BASE_URL = "https://ppidlobarnew.pythonanywhere.com"
    val retrofit: Retrofit? = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
}