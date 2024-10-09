package com.telda.data.resource.error

import com.telda.domain.result.Result
import com.telda.domain.result.error.DataError
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 09-Oct-24
 * @Project : com.telda.data.resource.error
 */
suspend fun <DATA> responseHandler(apiCall: suspend () -> Response<DATA>): Result<DATA, DataError.NetworkError> {
    return try {
        val response = apiCall()
        if (response.isSuccessful && response.body() != null)
            Result.Success(response.body()!!)
        else {
            when (response.code()) {
                401 -> Result.Error(DataError.NetworkError.INVALID_API_TOKEN)
                else -> Result.Error(DataError.NetworkError.GENERAL_ERROR)
            }
        }
    } catch (e: HttpException) {
        when (e.code()) {
            408 -> Result.Error(DataError.NetworkError.REQUEST_TIMEOUT)
            else -> Result.Error(DataError.NetworkError.GENERAL_ERROR)
        }
    } catch (e: UnknownHostException) {
        Result.Error(DataError.NetworkError.NO_INTERNET)
    }
}
