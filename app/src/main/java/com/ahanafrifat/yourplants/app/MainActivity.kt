package com.ahanafrifat.yourplants.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.ahanafrifat.yourplants.app.navigation.NavigationRoot
import com.ahanafrifat.yourplants.core.presentation.designsystem.theme.YourPlantsTheme
import com.ahanafrifat.yourplants.enhos.data.recording.AndroidVoiceRecorder
import com.ahanafrifat.yourplants.enhos.presentation.echos.EchosRoot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val recorder = AndroidVoiceRecorder(
            context = applicationContext,
            applicationScope = (application as YourPlantsApp).applicationScope
        )
//        ActivityCompat.requestPermissions(
//            this,
//            arrayOf(Manifest.permission.RECORD_AUDIO),
//            0
//        )
        setContent {
            YourPlantsTheme {
                NavigationRoot(
                    navHostController = rememberNavController()
                )
                // A surface container using the 'background' color from the theme

//                EchosRoot()
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

