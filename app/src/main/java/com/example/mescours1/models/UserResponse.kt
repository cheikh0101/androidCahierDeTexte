package com.example.mescours1.models

data class UserResponse(
    val access_token: AccessToken,
    val code: Int,
    val expires_in: Int,
    val message: String,
    val status: Int,
    val token_type: String,
    val user_id : Int,
    val email : String,
    val matricule: Int,
    val nom: String,
    val prenom: String,
    val telephone: String,
)
