package com.android.example.private_network_class

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainViewModel : ViewModel() {

    private val _details = MutableLiveData<Data>()
    val details: LiveData<Data> = _details

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val apiService = Retrofit.Builder()
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
        .create(PokemonApiService::class.java)

    fun retrieveDetails(){
        Log.d("First Fragment", "Details called")
        viewModelScope.launch {
            try {
                val result = apiService.getDetails()
                _details.value = result
            } catch (e:Exception){
                Log.d("ViewModel", "ERROR ${e.message}")
            }
        }
    }
}