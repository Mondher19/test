package com.example.myapplication.Model

data class transport(
    val _id: String,
    val type: String,
    val name: String,
    val price: String,
    val destination: String,
    val shedule: String,
    val stations: List<String>
)
