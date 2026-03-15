package com.example.medicalapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.medicalapplication.data.network.RetrofitInstance
import com.example.medicalapplication.ui.theme.MedicalApplicationTheme
import com.example.medicalapplication.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    // 使用 viewModels() 委托创建 ViewModel，它会自动提供 Application
    private val authViewModel: AuthViewModel by viewModels()
    private var lastBackPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化 RetrofitInstance 的 context
        RetrofitInstance.init(this)
        enableEdgeToEdge()
        setContent {
            MedicalApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background   // theme包里面没设置则为默认的白色
                ) {
                    SmartHealthApp(authViewModel)
                }
            }
        }

        setupBackPressHandler()
    }

    private fun setupBackPressHandler() {
        // activity 提供的返回按钮调度器注册了一个回调监听器，传入自身(activity)和匿名对象 object
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            // 这个匿名类继承了抽象类OnBackPressedCallback，该抽象类的构造方法是OnBackPressedCallback(enabled: Boolean)
            // 匿名对象的类体, 重写了其中的handleOnBackPressed()
            override fun handleOnBackPressed() {
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastBackPressedTime < 2000) {
                    finish()
                } else {
                    lastBackPressedTime = currentTime
                    Toast.makeText(this@MainActivity, "再按一次退出应用", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}

