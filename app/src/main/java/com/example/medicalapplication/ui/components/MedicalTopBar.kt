@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.medicalapplication.ui.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.medicalapplication.R

// 顶部bar组件
@Composable
fun MedicalTopBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = { Text("北方协和医院") },
    showBackButton: Boolean = false,
    onBackClick: () -> Unit = {}
){
    CenterAlignedTopAppBar(
        title = title,
        navigationIcon = {
            if (showBackButton) {   // 默认不展示这个图标，因为 showBackButton 默认值是 false
                IconButton(onClick = onBackClick) {
                    Icon(
                        painter = painterResource(id = R.drawable._return),
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ),
        modifier = modifier
    )
}
