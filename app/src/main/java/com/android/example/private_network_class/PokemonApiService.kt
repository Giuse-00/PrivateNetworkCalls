package com.android.example.private_network_class

import okhttp3.Call
import retrofit2.http.GET

interface PokemonApiService {

    @GET("/pokemon_names.json")

    suspend fun getDetails() : Data
}