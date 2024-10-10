package com.telda.moviesapp.combosables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 09-Oct-24
 * @Project : com.telda.moviesapp.combosables
 */
@Composable
fun LoadingWithCircularProgressBarCentered(
    disableBackground: Boolean = true
) {
    val modifier: Modifier = if (disableBackground) Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.surface.copy(alpha =  0.3f))
        .pointerInput(Unit) {} else Modifier.fillMaxSize()

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center) {
        AnimatedVisibility(visible = true) {
            CircularProgressIndicator()
        }
    }
}
