package com.example.medicalapplication.ui.content.Profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.medicalapplication.R

@Composable
fun UserInfoSection(){
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp), // 圆角（和截图一致）
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp), // 阴影（层级感）
        colors = CardDefaults.cardColors(containerColor = Color.White/* 背景色，按区块设置 */)
    ) {
        // 区块内部布局（Row/Column/网格等）
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // 区块内边距
            horizontalArrangement = Arrangement.SpaceBetween, // 左右分散对齐
            verticalAlignment = Alignment.CenterVertically // 垂直居中
        ) {
            // 头像（Image + CircleShape）
            Image(
                painter = painterResource(R.drawable.touxiang),
                contentDescription = "头像",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            // 手机号（占满剩余空间）
            Text(
                // 将登录时用的手机号填入
                text = "1933****8075",
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp),
                style = MaterialTheme.typography.titleSmall
            )
            // 设置图标（IconButton）
            IconButton(onClick = { /* 打开设置 */ }) {
                Icon(Icons.Outlined.Settings, contentDescription = "设置")
            }
        }
    }
}
