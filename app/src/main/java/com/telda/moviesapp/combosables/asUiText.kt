package com.telda.moviesapp.combosables

import com.telda.domain.result.Result
import com.telda.domain.result.error.DataError
import com.telda.moviesapp.R

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 09-Oct-24
 * @Project : com.telda.moviesapp
 */
fun DataError.asUiText(): UiText {
    return when (this) {
        DataError.NetworkError.REQUEST_TIMEOUT -> UiText.StringResource(
            R.string.error_request_time_out_msg
        )

        DataError.NetworkError.PARSING_ERROR -> UiText.StringResource(
            R.string.error_parsing_msg
        )


        DataError.NetworkError.INVALID_API_TOKEN -> UiText.StringResource(
            R.string.error_unauthorized_msg
        )

        DataError.NetworkError.NO_INTERNET -> UiText.StringResource(
            R.string.error_no_internet_msg
        )

        DataError.NetworkError.GENERAL_ERROR -> UiText.StringResource(
            R.string.error_general_error_msh
        )

        DataError.LocalDbError.DATABASE_ERROR -> UiText.StringResource(
            R.string.error_database_error
        )
    }
}

fun Result.Error<*, DataError>.asErrorUiText(): UiText {
    return error.asUiText()
}
