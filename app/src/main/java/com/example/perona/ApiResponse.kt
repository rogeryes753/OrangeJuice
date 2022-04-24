package com.example.perona

import com.google.gson.annotations.SerializedName
import retrofit2.http.Url
import java.net.URL


data class ApiResponse(
    @SerializedName("protocol")
    val protocol : String ,
    @SerializedName("code")
    val code : Int = 0,
    @SerializedName("message")
    val message : String = "",
    @SerializedName("url")
    val url : String
)
