package com.example.footballleague.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.footballleague.model.Team
import com.example.footballleague.model.Teams
import com.example.footballleague.repository.TeamRepository
import com.example.footballleague.utils.Result
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TeamViewModel (private val teamRepository: TeamRepository)
    : ViewModel(), CoroutineScope{

    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val teams = MutableLiveData<List<Team>>()
    val teamsSearch : MutableLiveData<List<Team>>?  = MutableLiveData<List<Team>>()
    val teamDetail = MutableLiveData<List<Team>>()
    val teamsFav : MutableLiveData<List<Team>>?  = MutableLiveData<List<Team>>()
    val idSave = MutableLiveData<Long>()
    val idDelete = MutableLiveData<Int>()

    fun loadTeams(idleague : String){
        loading.value = true
        launch {
            val result = withContext(Dispatchers.IO) { teamRepository.getTeams(idleague) }
            loading.value = false
            when (result) {
                is Result.Success -> teams.value = result.data?.teams
                is Result.Error -> error.value = result.exception.message
            }
        }
    }

    fun loadTeamsSearch(query : String){
        loading.value = true
        launch {
            val result = withContext(Dispatchers.IO) { teamRepository.getTeamsSearch(query) }
            loading.value = false
            when (result) {
                is Result.Success -> teamsSearch?.value = result.data?.teams
                is Result.Error -> error.value = result.exception.message
            }
        }
    }

    fun loadTeamsDetail(idleague: String){
        loading.value = true
        launch {
            val result = withContext(Dispatchers.IO) { teamRepository.getDetailTeam(idleague) }
            loading.value = false
            when (result) {
                is Result.Success -> teamDetail.value = result.data?.teams
                is Result.Error -> error.value = result.exception.message
            }
        }
    }

    fun saveFavTeam(team: Team) {
        loading.value = true
        val result = teamRepository.saveFavoriteTeam(team)
        loading.value = false
        when (result) {
            is Result.Success -> idSave.value = result.data
            is Result.Error -> error.value = result.exception.message
        }
    }

    fun deleteFavTeam(idTeam: String) {
        loading.value = true
        val result = teamRepository.deleteFavoriteTeam(idTeam)
        loading.value = false
        when (result) {
            is Result.Success -> idDelete.value = result.data
            is Result.Error -> error.value = result.exception.message
        }
    }

    fun loadFavTeams() {
        loading.value = true
        launch {
            val result = withContext(Dispatchers.IO) { teamRepository.getFavoriteTeams() }
            loading.value = false
            when (result) {
                is Result.Success -> teamsFav?.value = result.data
                is Result.Error -> error.value = result.exception.message
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}