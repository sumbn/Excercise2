package com.example.roomex2.network

import com.example.roomex2.model.Contact
import retrofit2.Call
import retrofit2.http.GET


interface ServiceContact {
    @GET("users")
    fun listReponse(): Call<List<Contact?>?>?
}