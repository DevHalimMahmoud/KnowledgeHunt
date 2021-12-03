package com.example.knowledgehunt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.knowledgehunt.ui.AppMainScreen
import com.example.knowledgehunt.ui.theme.KnowledgeHuntTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KnowledgeHuntTheme {
                AppMainScreen()
            }
        }
    }
}

