package com.android.example.private_network_class

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
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

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)

        viewModel.details.observe(viewLifecycleOwner, { result ->
            setDetails(result)
        })

        viewModel.retrieveDetails()

        return binding.root
    }

    private fun setDetails(result: Data) {
        binding.textView2.text =  getString(R.string.names, result.first)
        binding.textView3.text =  getString(R.string.names, result.second)
    }

}