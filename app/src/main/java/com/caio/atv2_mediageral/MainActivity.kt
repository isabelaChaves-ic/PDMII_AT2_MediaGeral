package com.caio.atv2_mediageral

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.caio.atv2_mediageral.ui.screens.MediaScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val corBordo = Color(0xFF800000)

            val meuEsquemaDeCores = lightColorScheme(
                primary = corBordo
            )

            MaterialTheme (
                colorScheme = meuEsquemaDeCores
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MediaScreen()
                }
            }
        }
    }
}