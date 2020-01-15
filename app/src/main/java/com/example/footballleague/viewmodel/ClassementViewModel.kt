package com.example.footballleague.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.footballleague.model.Classement
import com.example.footballleague.repository.ClassementRepository
import com.example.footballleague.utils.Result
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ClassementViewModel(private val classementRepository: ClassementRepository) : ViewModel(),
    CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val classement = MutableLiveData<List<Classement>>()

    fun loadClassement(idleague: String) {
        loading.value = true
        launch {
            val result =
                withContext(Dispatchers.IO) { classementRepository.getTableClassement(idleague) }
            loading.value = false
            when (result) {
                is Result.Success -> classement.value = result.data?.classements
                is Result.Error -> error.value = result.exception.message
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}