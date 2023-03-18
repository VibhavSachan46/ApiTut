package com.example.apitut

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("products") //endpoint of url
    fun getProductData(): Call<MyData>
}