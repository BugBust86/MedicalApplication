package com.example.medicalapplication.ui.content.Home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.medicalapplication.ui.content.Home.section.ServiceGridItem
import com.example.medicalapplication.ui.content.Home.section.ServiceSection
import com.example.medicalapplication.ui.content.Home.section.outpatientItems

@Composable
fun HomeContent() {
    // 内容区用可滚动的 Column 布局，为防止冲突已经去除
     LazyColumn(
        modifier = Modifier.fillMaxSize()      // 此处的 Modifier 是 HomeContent组件的 Modifier，最大化是在父容器的空间下的
    ){
         // 门诊预约服务区块
        item{
            ServiceSection(title = "门诊预约", items = outpatientItems)
        }
         grid(
             columns = GridCells.Fixed(4),
             horizontalArrangement = Arrangement.spacedBy(8.dp),
             verticalArrangement = Arrangement.spacedBy(16.dp),
             contentPadding = PaddingValues(16.dp)
         ) {
             items(outpatientItems) { item ->
                 ServiceGridItem(item = item)
             }
         }




//        item{
//            ServiceSection(title = "检查化验", items = outpatientItems)
//        }
//        item{
//            ServiceSection(title = "便民服务", items = outpatientItems)
//        }

    }
}