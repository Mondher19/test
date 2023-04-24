package com.example.myapplication.Model


data class station(
    val _id: String,
    val type: String,
    val name: String,
    val location: Location,
    val transport: List<String>
)

data class Location(
    val type: String,
    val coordinates: List<Double>
)