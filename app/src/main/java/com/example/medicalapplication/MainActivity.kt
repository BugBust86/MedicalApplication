package com.example.medicalapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.medicalapplication.ui.theme.MedicalApplicationTheme
import com.example.medicalapplication.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    // 使用 viewModels() 委托创建 ViewModel，它会自动提供 Application
    private val authViewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MedicalApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background    // theme包里面没设置则为默认的白色
                ) {
                    SmartHealthApp(authViewModel)
                }
            }
        }
    }
}

