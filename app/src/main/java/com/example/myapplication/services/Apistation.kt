package com.example.myapplication.services

import com.example.myapplication.Model.Reclamation
import com.example.myapplication.Model.User
import com.example.myapplication.Model.station
import com.example.myapplication.Model.transport
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Apistation {

    @GET("/user/getAllUsers")
    fun getusers(): Call<List<User>>

    @GET("/reclamation/reclamations")
    fun getreclamtions(): Call<List<Reclamation>>


    @GET("/transport/transports")
    fun gettransportss(): Call<List<transport>>

    @GET("/station/getStationsByType/{type}")
    fun getStations(@Path("type") type: String): Call<List<station>>


    @GET("/transport/stations/{id}/transports")
    fun gettransports(@Path("id") type: String): Call<List<transport>>

    @GET("/transport/transports/{id}")
    fun gettransportbyid(@Path("id") type: String): Call<List<transport>>

}