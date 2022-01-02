package com.example.mescours1.models

data class Seance(
    val nature: String,
    val titre: String,
    val description: String,
    val remarque: String,
    val duree: String,
    val users_id: Int,
    val cours_id: Int
)