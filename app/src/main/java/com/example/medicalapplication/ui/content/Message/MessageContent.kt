package com.example.medicalapplication.ui.content.Message

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.medicalapplication.R
import com.example.medicalapplication.ui.content.Message.section.MessageMenuItem
import com.example.medicalapplication.ui.content.Message.section.messageMenuItems

@Composable
fun MessageContent(
    // 导航到页面
    onNavigateToPage: (String) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),   // 水平方向内边距，左右两侧边距大小
//        verticalArrangement = Arrangement.spacedBy(0.dp)    //垂直间距为 0
    ) {
        items(messageMenuItems) { item ->
            MessageListItem(
                item = item.copy(onClick = { onNavigateToPage(item.route) })
            )
            if (item != messageMenuItems.last()) {
                HorizontalDivider(
                    modifier = Modifier.padding(start = 72.dp),
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            }
        }
    }
}

@Composable
fun MessageListItem(item: MessageMenuItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { item.onClick() }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 左侧图标
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(item.backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.foundation.Image(
                painter = painterResource(id = item.drawableRes),
                contentDescription = item.label,
                modifier = Modifier.size(28.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // 右侧标题和描述
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = item.label,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = getMessageDescription(item.route),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // 右侧箭头
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(RoundedCornerShape(4.dp)),
//                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable._enter),
                contentDescription = "箭头",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

private fun getMessageDescription(route: String): String {
    return when (route) {
        "hospital_dynamic" -> "查看医院最新动态资讯"
        "my_message" -> "个人就诊记录和通知"
        "consult_message" -> "医生问诊消息提醒"
        "disease_science" -> "专病知识健康科普"
        else -> "点击查看详情"
    }
}
