package com.example.footballleague.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.footballleague.model.League
import com.example.footballleague.repository.LeagueRepository
import com.example.footballleague.utils.Result
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

class LeagueViewModel (private val leagueRepository: LeagueRepository) : ViewModel(), CoroutineScope{

    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val league = MutableLiveData<League>()

    fun loadLeagueDetail(idLeague: String){
        loading.value = true
        launch {
            val result = withContext(Dispatchers.IO) { leagueRepository.getDetailLeague(idLeague) }
            loading.value = false
            when (result) {
                is Result.Success -> league.value = result.data?.list?.get(0)
                is Result.Error -> error.value = result.exception.message
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}