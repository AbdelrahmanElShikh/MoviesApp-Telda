package com.telda.moviesapp.screens.movieDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.telda.data.BuildConfig
import com.telda.domain.model.Contributor
import com.telda.moviesapp.R

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 10-Oct-24
 * @Project : com.telda.moviesapp.screens.movieDetails
 */

@Composable
fun ContributorItem(contributor: Contributor?) {

    contributor?.let {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = "${BuildConfig.IMAGE_BASE_URL}${contributor.profilePath}",
                contentDescription = "profile Path",
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.ic_placeholder)

            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = contributor.name,
                style = TextStyle(fontSize = MaterialTheme.typography.bodyMedium.fontSize, fontWeight = FontWeight.SemiBold)
            )

            Spacer(modifier = Modifier.height(8.dp))

        }
    }
}
