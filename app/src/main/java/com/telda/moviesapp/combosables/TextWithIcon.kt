package com.telda.moviesapp.combosables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 11-Oct-24
 * @Project : com.telda.moviesapp.combosables
 */
@Composable
fun TextWithIcon(text: String, icon: ImageVector, tint: Color = LocalContentColor.current) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = text,
            fontSize = MaterialTheme.typography.bodySmall.fontSize
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            imageVector = icon,
            tint = tint,
            contentDescription = "watchlist icon"
        )
    }
}
