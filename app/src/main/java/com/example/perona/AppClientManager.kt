package com.example.perona

import android.graphics.Bitmap
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface CloudAPI2 {
    @GET("v1/rest/datastore/F-C0032-001")
    fun data(
        @Query("Authorization") authorization: String,
        @Query("format") format: String,
        @Query("locationName") locationName: List<String>
    ): Call<ApiResponse>
}

class AppClientManager private constructor() {
    private val retrofit: Retrofit
    private val okHttpClient = OkHttpClient()
    var url = "https://opendata.cwb.gov.tw/api/"
    init {
        retrofit = Retrofit.Builder()
            .baseUrl(url)
//            .addConverterFactory(WrapperConverterFactory(GsonConverterFactory.create()))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    companion object {
        private val manager = AppClientManager()
        val client: Retrofit
            get() = manager.retrofit
    }
}