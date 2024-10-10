package com.telda.domain.usecase

import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 09-Oct-24
 * @Project : com.telda.domain.usecase
 */
class GetYearFromReleaseDateUseCase @Inject constructor() {
    operator fun invoke(releaseDate: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return try {
            val date = dateFormat.parse(releaseDate)
            SimpleDateFormat("yyyy", Locale.getDefault()).format(date!!)
        } catch (e: Exception) {
            "Unknown Year" // Handle parsing errors
        }
    }
}
