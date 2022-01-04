package com.example.mescours1.api

import com.example.mescours1.models.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("api/login")
    suspend fun login(@Body() body: User): Response<UserResponse>

    @DELETE("api/sceance/delete/{code}")
    suspend fun deleteSeance(@Path("code") code: Int)

    @GET("api/cours/professeurCours/{code}")
    suspend fun professeurCours(@Path("code") code: Int):  Response<Cours>

    @GET("api/cours/professeurNombreCours/{code}")
    suspend fun professeurNombreCours(@Path("code") code: Int): Response<Professeur>

    @GET("api/cours/professeurNombreHeureCours/{code}")
    suspend fun professeurNombreHeureCours(@Path("code") code: Int):  Response<Professeur>

    @GET("api/seance/professeurNombreSeance/{code}")
    suspend fun professeurNombreSeance(@Path("code") code: Int): Response<Seance>

    @POST("api/sceance/create")
    suspend fun seanceCreate(@Body() body: Seance): Response<SeanceResponse>

    @GET("api/seance/professeurSeance/{code}/{code1}")
    suspend fun professeurSeance(@Path("code") code: Int,@Path("code1") y: Int):  Response<ListeSeance>
}