package com.android.example.private_network_class.network

import com.android.example.private_network_class.network.dto.Data
import retrofit2.http.GET

interface PokemonApiService {

    @GET("/pokemon_names.json")

    suspend fun getDetails() : Data
}