package com.telda.domain.result

import com.telda.domain.result.error.Error

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 09-Oct-24
 * @Project : com.telda.domain.resource
 */

typealias RootError = Error

sealed interface Result<out DATA, out ERROR : RootError> {
    data class Success<out DATA, out ERROR : RootError>(val data: DATA) : Result<DATA, ERROR>
    data class Error<out DATA, out ERROR : RootError>(val error: ERROR) : Result<DATA, ERROR>
}
