package com.example.footballleague.repository

import android.content.Context
import com.example.footballleague.api.ApiInterface
import com.example.footballleague.utils.database
import com.example.footballleague.model.Team
import com.example.footballleague.model.Teams
import com.example.footballleague.utils.Const
import com.example.footballleague.utils.Result
import org.jetbrains.anko.db.*

interface TeamRepository {
    suspend fun getTeams(idLeague : String) : Result<Teams>

    suspend fun getTeamsSearch(query : String) : Result<Teams>

    suspend fun getDetailTeam(idTeam: String) : Result<Teams>

    fun saveFavoriteTeam(team: Team): Result<Long>

    fun deleteFavoriteTeam(idTeam: String): Result<Int>

    suspend fun getFavoriteTeams(): Result<List<Team>>
}

class TeamRepositoryImpl(private val apiInterface: ApiInterface, val context: Context)
    : TeamRepository{
    override suspend fun getFavoriteTeams(): Result<List<Team>> {
        return try {
            context.database.use {
                select(Const.TB_FAVORITE_TEAMS)
                    .exec {
                        Result.Success(this.parseList(classParser<Team>()))
                    }
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override fun saveFavoriteTeam(team: Team): Result<Long> {
        return try {
            var result: Long = 0
            context.database.use {
                result =
                    insert(
                        Const.TB_FAVORITE_TEAMS,
                        Const.COL_ID_TEAM to team.idTeam,
                        Const.COL_NAME_TEAM to team.nameTeam,
                        Const.COL_ID_LEAGUE to team.idLeague,
                        Const.COL_NAME_LEAGUE to team.nameLeague,
                        Const.COL_WEBSITE_URL to team.website,
                        Const.COL_YOUTUBE_URL to team.youtube,
                        Const.COL_COUNTRY to team.country,
                        Const.COL_TEAM_BADGE to team.teamBadge,
                        Const.COL_DESCRIPTION to team.description
                    )
            }

            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override fun deleteFavoriteTeam(idTeam: String): Result<Int> {
        return try {
            val result = context.database.use {
                delete(
                    Const.TB_FAVORITE_TEAMS, "( " + Const.COL_ID_TEAM + " = {idTeam})",
                    "idTeam" to idTeam
                )
            }
            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

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