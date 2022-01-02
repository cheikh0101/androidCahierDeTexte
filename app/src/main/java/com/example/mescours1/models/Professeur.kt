package com.example.mescours1.models

data class Professeur(
    val email: String,
    val id: Int,
    val matricule: Int,
    val nom: String,
    val telephone: String,
    val prenom: String,
    val heureCours: Int,
    val nombreCours: Int
)
