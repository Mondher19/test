package com.example.myapplication.Model


data class User(
    val _id: String,
    val firstName: String,
    val email: String,
    val password: String,
    val mobile: String,
    val Adresse: String,
    val verified: Boolean,
    val activationCode: String,
    val date: String,
    val __v: Int
)