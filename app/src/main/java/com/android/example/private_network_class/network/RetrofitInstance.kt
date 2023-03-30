package com.android.example.private_network_class.network

import com.android.example.private_network_class.AuthorizationInterceptor
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val retrofit = Retrofit.Builder()
        .client(
            OkHttpClient.Builder()
                .addInterceptor(AuthorizationInterceptor())
                .addInterceptor(loggingInterceptor)
                .dispatcher(Dispatcher().apply { maxRequests = 1 })
                .build()
        )
        .baseUrl("https://pokemon-go1.p.rapidapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

 val apiService = retrofit.create(PokemonApiService::class.java)

}