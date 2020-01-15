package com.example.footballleague.repository

import android.content.Context
import com.example.footballleague.api.ApiInterface
import com.example.footballleague.model.Teams
import com.example.footballleague.utils.Result

interface TeamRepository {
    suspend fun getTeams(idLeague : String) : Result<Teams>

    suspend fun getTeamsSearch(query : String) : Result<Teams>

    suspend fun getDetailTeam(idTeam: String) : Result<Teams>
}

class TeamRepositoryImpl(private val apiInterface: ApiInterface, val context: Context)
    : TeamRepository{
    override suspend fun getTeams(idLeague: String): Result<Teams> {
        return try {
            val result = apiInterface.getListTeam(idLeague).await()
            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getTeamsSearch(query: String): Result<Teams> {
        return try {
            val result = apiInterface.getListTeamSearch(query).await()
            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getDetailTeam(idTeam: String): Result<Teams> {
        return try {
            val result = apiInterface.getDetailTeam(idTeam).await()
            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}