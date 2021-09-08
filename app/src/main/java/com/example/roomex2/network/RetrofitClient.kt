package com.example.roomex2.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private object Retro{
        val retrofitbuilder : Retrofit.Builder = Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
        val retrofit:Retrofit = retrofitbuilder.build()
        val contactApi = retrofit.create(ServiceContact::class.java)
    }
    companion object{
        fun getContactApi(): ServiceContact {
            return Retro.contactApi
        }
    }

}