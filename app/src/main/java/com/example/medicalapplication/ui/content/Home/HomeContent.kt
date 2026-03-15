package com.example.medicalapplication.ui.content.Home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.medicalapplication.ui.content.Home.section.ServiceSection
import com.example.medicalapplication.ui.content.Home.section.checkoutItems
import com.example.medicalapplication.ui.content.Home.section.convenientItems
import com.example.medicalapplication.ui.content.Home.section.outpatientItems

@Composable
fun HomeContent() {
    // 使用 LazyColumn 作为外层滚动容器
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        // 门诊预约服务区块
        item{
            ServiceSection(title = "门诊预约", items = outpatientItems)
        }
        item{
            ServiceSection(title = "检查化验", items = checkoutItems)
        }
        item{
            ServiceSection(title = "便民服务", items = convenientItems)
        }

    }
}