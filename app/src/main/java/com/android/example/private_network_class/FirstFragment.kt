package com.android.example.private_network_class

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.android.example.private_network_class.databinding.FragmentFirstBinding
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val API_AUTHORIZATION_HEADER = "X-RapidAPI-Key"

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newRequest = request.newBuilder().addHeader(
            API_AUTHORIZATION_HEADER, "5981294f6emsh9b3f692332d7652p1ed052jsn6f444b449ccd"
        ).build()

        return chain.proceed(newRequest)
    }
}

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    val logging = HttpLoggingInterceptor()
    val authorizationInterceptor = AuthorizationInterceptor()
    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(authorizationInterceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://pokemon-go1.p.rapidapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(PokemonApiService::class.java)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)

        logging.level = HttpLoggingInterceptor.Level.BODY
        retrieveDetails()

        return binding.root
    }

    private fun setDetails(result: Data) {
        binding.textView2.text =  getString(R.string.names, result.first)
        binding.textView3.text =  getString(R.string.names, result.second)
    }

    private fun retrieveDetails() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val details = apiService.getDetails()
                setDetails(details)
            } catch (e: Exception) {
                Log.v("First Fragment", "ERROR ${e.message}")
            }
        }
    }
}