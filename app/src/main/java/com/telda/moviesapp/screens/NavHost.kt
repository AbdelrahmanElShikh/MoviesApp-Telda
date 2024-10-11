package com.telda.moviesapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.telda.moviesapp.screens.movieDetails.MovieDetailsScreen
import com.telda.moviesapp.screens.movieList.MovieListsScreen

/**
 * @Author : Abdel-Rahman El-Shikh
 * @Date : 10-Oct-24
 * @Project : com.telda.moviesapp.screens
 */

@Composable
fun MoviesAppContent() {
    val navController = rememberNavController()
    Scaffold { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(
                navController = navController,
                startDestination = ScreenMovieSearch
            ) {
                composable<ScreenMovieSearch> {
                    MovieListsScreen(navController = navController)
                }

                composable<ScreenMovieDetails> {
                    val args = it.toRoute<ScreenMovieDetails>()
                    MovieDetailsScreen(navController = navController, movieName = args.movieName,)
                }
            }
        }
    }
}
