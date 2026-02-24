package com.example.medicalapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.medicalapplication.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MedicalApplicationTheme {
                LoginScreen()
            }
        }
    }
}

@Composable
fun LoginScreen(){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // 顶部导航栏
        Text(
            text="便捷医疗",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
            textAlign = TextAlign.Center
        )
        // Logo区域
        Box(
            modifier = Modifier
                .size(100.dp)
                .padding(bottom = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            // 创建DNA图标（使用圆形和线条模拟）
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.White, shape = RoundedCornerShape(50))
                    .border(2.dp, Pink80, shape = RoundedCornerShape(50))
            ) {
                // DNA双螺旋效果
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(12.dp, 24.dp)
                            .background(Pink80)
                    )
                    Box(
                        modifier = Modifier
                            .size(12.dp, 24.dp)
                            .background(Purple40)
                    )
                    Box(
                        modifier = Modifier
                            .size(12.dp, 24.dp)
                            .background(Pink80)
                    )
                }
            }

            // 右侧文字
            Text(
                text = "便捷医疗",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Pink80,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }


        // 登录按钮
        Button(
            onClick = { /* 登录逻辑 */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
        ) {
            Text(
                text = "登录",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        // 注册按钮
        Button(
            onClick = { /* 注册逻辑 */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
        ) {
            Text(
                text = "注册",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Composable
fun TestTextField() {
    // by是kotlin的一个关键字，表示委托一个对象，此处remember方法返回的是一个对象
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("用户名") },
        modifier = Modifier.fillMaxWidth()
    )
}
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MedicalApplicationTheme {
//        TestTextField()
//    }
//}