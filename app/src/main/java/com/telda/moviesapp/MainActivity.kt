package com.telda.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.telda.moviesapp.screens.MoviesAppContent
import com.telda.moviesapp.ui.theme.MoviesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAppTheme { MoviesAppContent() }
        }
    }
}


