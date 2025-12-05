package com.ahanafrifat.yourplants

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ahanafrifat.yourplants.core.presentation.designsystem.theme.YourPlantsTheme
import com.ahanafrifat.yourplants.core.presentation.designsystem.theme.bgGradient
import com.ahanafrifat.yourplants.enhos.presentation.echos.EchosRoot
import com.ahanafrifat.yourplants.enhos.presentation.echos.EchosScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YourPlantsTheme {
                // A surface container using the 'background' color from the theme
                EchosRoot()
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background,
//                ) {
//                    BakingScreen()
//                }
            }
        }
    }
}