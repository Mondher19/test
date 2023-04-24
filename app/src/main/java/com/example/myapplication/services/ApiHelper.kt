package com.example.myapplication.services

import com.example.myapplication.Model.Reclamation
import com.example.myapplication.Model.User
import com.example.myapplication.Model.station
import com.example.myapplication.Model.transport
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiHelper() {

    private val BASE_URL = "http://10.0.2.2:9090"

    private val apiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Apistation::class.java)

    val calltransportbyid = apiService.gettransportbyid("643c1c1023cd8c16dc460c16")



    fun fetchDataFromApi(stationtype: String,callback: (List<station>) -> Unit) {
        val callstations = apiService.getStations(stationtype)
        callstations.enqueue(object : Callback<List<station>> {
            override fun onResponse(call: Call<List<station>>, response: Response<List<station>>) {
                if (response.isSuccessful) {
                    callback(response.body()!!)

                }
            }

            override fun onFailure(call: Call<List<station>>, t: Throwable) {
                // handle API call failure here
            }
        })
    }


    fun fetchTransportsFromApi(stationId: String, callback: (List<transport>) -> Unit) {
        val calltransports = apiService.gettransports(stationId)
        calltransports.enqueue(object : Callback<List<transport>> {
            override fun onResponse(call: Call<List<transport>>, response: Response<List<transport>>) {
                if (response.isSuccessful) {
                    callback(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<transport>>, t: Throwable) {
                // handle API call failure here
            }
        })
    }

    fun fetchReclamation( callback: (List<Reclamation>) -> Unit) {
        val callUsers = apiService.getreclamtions()
        callUsers.enqueue(object : Callback<List<Reclamation>> {
            override fun onResponse(call: Call<List<Reclamation>>, response: Response<List<Reclamation>>) {
                if (response.isSuccessful) {
                    callback(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Reclamation>>, t: Throwable) {
                // handle API call failure here
            }
        })
    }

    fun fetchUsers( callback: (List<User>) -> Unit) {
        val callUsers = apiService.getusers()
        callUsers.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    callback(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                // handle API call failure here
            }
        })
    }


    fun fetchtransports( callback: (List<transport>) -> Unit) {
        val calltransportss = apiService.gettransportss()
        calltransportss.enqueue(object : Callback<List<transport>> {
            override fun onResponse(call: Call<List<transport>>, response: Response<List<transport>>) {
                if (response.isSuccessful) {
                    callback(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<transport>>, t: Throwable) {
                // handle API call failure here
            }
        })
    }

    fun fetchTransportbyidFromApi(callback: (List<transport>) -> Unit) {
        calltransportbyid.enqueue(object : Callback<List<transport>> {
            override fun onResponse(call: Call<List<transport>>, response: Response<List<transport>>) {
                if (response.isSuccessful) {
                    callback(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<transport>>, t: Throwable) {
                // handle API call failure here
            }
        })
    }
}
