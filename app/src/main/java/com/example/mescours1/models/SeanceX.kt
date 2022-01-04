package com.example.mescours1.models

import java.text.DateFormat
import java.util.*

data class SeanceX(
    val id: Int,
    val created_at: String,
    val description: String,
    val duree: String,
    val nature: String,
    val remarque: String,
    val titre: String
)