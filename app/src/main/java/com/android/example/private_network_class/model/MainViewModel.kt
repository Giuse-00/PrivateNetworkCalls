package com.android.example.private_network_class.model

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.private_network_class.Prefs
import com.android.example.private_network_class.network.RetrofitInstance
import com.android.example.private_network_class.network.dto.Data
import com.android.example.private_network_class.prefs
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel : ViewModel() {

    private val _details = MutableLiveData<Data>()
    val details: LiveData<Data> = _details

    fun retrieveDetails(){
        Log.d("First Fragment", "Details called")
        viewModelScope.launch {
            try {
                val result = RetrofitInstance().apiService.getDetails()
                _details.value = result
            } catch (e:Exception){
                Log.d("ViewModel", "ERROR ${e.message}")
            }
        }
    }
}