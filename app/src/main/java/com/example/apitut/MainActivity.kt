package com.example.apitut

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java )// gson is used here


        val retrofitData = retrofitBuilder.getProductData()

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                //if api call is success then use the data of api and show in your app
                val responseBody = response.body()
                val productList = responseBody?.products!!

                val collectDataStringBuilder = StringBuilder()

                for(myData in productList){
                    collectDataStringBuilder.append(myData.title + "\n ")
                }

                val tv = findViewById<TextView>(R.id.text)
                tv.text = collectDataStringBuilder
            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                // if api fails
                Log.d("Main Activity", "On fialure" + t.message)
            }
        }) //enque method ends
    }
}