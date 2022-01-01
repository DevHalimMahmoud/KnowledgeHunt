package com.example.knowledgehunt

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.knowledgehunt.ui.screens.AppMainScreen
import com.example.knowledgehunt.ui.theme.KnowledgeHuntTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        setContent {
            KnowledgeHuntTheme {
                AppMainScreen()
            }
        }
    }

}

