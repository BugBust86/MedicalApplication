@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.medicalapplication.ui.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

// 顶部bar组件
@Composable
fun MedicalTopBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = { Text("北方协和医院") }   // 默认为智慧医疗文本，还可以传搜索框
){
    CenterAlignedTopAppBar(
        // 此处期待有@Composable注解、输入为空返回为空的函数，使用必须lambda表达式包裹，直接Text(“”)返回Unit
        title = title,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ),
        modifier = modifier
    )
}