package com.example.perona

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


interface CloudAPI {
    @GET("v1/rest/datastore/F-C0032-001")
    fun data(
        @Query("Authorization") authorization: String,
        @Query("format") format: String,
        @Query("locationName") locationName: List<String>
    ): Call<CloudResponse>
}


object APIClient {

    private var retrofit: Retrofit? = null
    var url = "https://opendata.cwb.gov.tw/api/"
    val client: Retrofit
        get() {
//            val interceptor = HttpLoggingInterceptor()
//            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .retryOnConnectionFailure(true)
                .build()
            if (retrofit == null) {
                val gson = GsonBuilder()
                    .setLenient()
                    .create()

                retrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build()


            }
            return this.retrofit!!

        }

}
