package com.telda.moviesapp.screens.movieDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 10-Oct-24
 * @Project : com.telda.moviesapp.screens.movieDetails
 */
@Composable
fun MovieDetailsScreen(
    navController: NavController,
    movieId: Int,
    movieName: String,
) {
    Scaffold(topBar = {
        MovieDetailsTopBar(navController = navController, title = movieName)
    }) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = movieId.toString())
            Text(text = movieName)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsTopBar(navController: NavController, title: String) {
    TopAppBar(title = {
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize
        )
    },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }

        })
}
