package com.example.footballleague.repository

import android.content.Context
import com.example.footballleague.api.ApiInterface
import com.example.footballleague.model.TableClassement
import com.example.footballleague.utils.Result

interface ClassementRepository {
    suspend fun getTableClassement(idLeague: String): Result<TableClassement>
}

class ClassementRepositoryImpl(private val apiInterface: ApiInterface, val context: Context) :
    ClassementRepository {
    override suspend fun getTableClassement(idLeague: String): Result<TableClassement> {
        return try {
            val result = apiInterface.getListClassement(idLeague).await()
            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}