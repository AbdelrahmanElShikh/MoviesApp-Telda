package com.telda.moviesapp.utils

import java.text.NumberFormat
import java.util.Locale

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 10-Oct-24
 * @Project : com.telda.moviesapp.utils
 */
fun Long.formatMoney(): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale.US)
    return formatter.format(this)
}
