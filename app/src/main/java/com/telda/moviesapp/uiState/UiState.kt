package com.telda.moviesapp.uiState

import com.telda.moviesapp.combosables.UiText

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 09-Oct-24
 * @Project : com.telda.moviesapp.uiState
 */
data class UiState<T>(
    val uiStatus: Status<T> = Status.Loading()
)

sealed class Status<T>(
    val data: T? = null
) {
    class Success<T>(data: T?) : Status<T>(data = data)
    data class Error<T>(val error: UiText) : Status<T>()
    class Loading<T> : Status<T>()
}
