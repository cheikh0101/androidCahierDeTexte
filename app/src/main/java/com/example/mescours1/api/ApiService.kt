package com.example.mescours1.api

import com.example.mescours1.models.User
import com.example.mescours1.models.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/login")
    suspend fun login(@Body() body: User): Response<UserResponse>
}