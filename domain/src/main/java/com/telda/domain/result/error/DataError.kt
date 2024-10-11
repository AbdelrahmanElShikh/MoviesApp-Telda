package com.telda.domain.result.error

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 09-Oct-24
 * @Project : com.telda.domain.resource.error
 */
sealed interface DataError : Error {
    enum class NetworkError : DataError{
        REQUEST_TIMEOUT,
        PARSING_ERROR,
        INVALID_API_TOKEN,
        NO_INTERNET,
        GENERAL_ERROR
    }

    enum class LocalDbError : DataError{
        DATABASE_ERROR
    }
}
