package com.example.footballleague.repository

import com.example.footballleague.api.ApiInterface
import com.example.footballleague.model.Leagues
import com.example.footballleague.utils.Result

interface LeagueRepository {
    suspend fun getDetailLeague(idLeague: String): Result<Leagues>
}

class LeagueRepositoryImpl(private val apiInterface: ApiInterface) : LeagueRepository {

    override suspend fun getDetailLeague(idLeague: String): Result<Leagues> {
        return try {
            val result = apiInterface.getDetailLeague(idLeague).await()
            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}