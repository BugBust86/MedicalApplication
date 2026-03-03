package com.example.medicalapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.medicalapplication.ui.components.MedicalTopBar
import com.example.medicalapplication.ui.screens.LoginRegisterScreen
import com.example.medicalapplication.ui.theme.MedicalApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MedicalApplicationTheme {
                Scaffold(
                    topBar = { MedicalTopBar(  )
                    }
                ) { // innerPadding的实际值是Scaffold函数根据topBar、bottomBar等测出来的，防止内容区域与上下bar重合，
                    // 变量名叫什么都可以，一般为了可读性，叫innerPadding
                    innerPadding->
                    // 有.padding(innerPadding)的内边距，不会重合
                    LoginRegisterScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

