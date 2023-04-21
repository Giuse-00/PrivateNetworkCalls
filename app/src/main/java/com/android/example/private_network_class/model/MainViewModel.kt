package com.android.example.private_network_class.model

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.private_network_class.SharedPreferences.MyApplication
import com.android.example.private_network_class.network.RetrofitInstance
import com.android.example.private_network_class.network.dto.Data
import com.android.example.private_network_class.repository.entity.RepoEntity
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MainViewModel(application: MyApplication) : AndroidViewModel(application) {

    private val repoDao = (application as MyApplication).database.repoDao()

    val details = MutableSharedFlow<Data>()

    fun getAllName(): List<RepoEntity>{
        return repoDao.getAll()
    }

    suspend fun insertName(name: RepoEntity){
        repoDao.insert(name)
    }

    suspend fun updateName(name: RepoEntity){
        repoDao.update(name)
    }

    suspend fun deleteName(name: RepoEntity){
        repoDao.delete(name)
    }

    fun retrieveDetails(){
        Log.d("First Fragment", "Details called")
        viewModelScope.launch {
            try {
                val result = RetrofitInstance().apiService.getDetails()
                details.emit(result)
            } catch (e:Exception){
                Log.d("ViewModel", "ERROR ${e.message}")
            }
        }
    }
}