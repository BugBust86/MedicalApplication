package com.example.medicalapplication.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
//  搜索框组件
fun SearchTextField(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = { demo ->println("搜索内容：$demo") }, // 当用户提交搜索时的回调
) {
    var text by remember { mutableStateOf("") }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,  // Material Icons中的搜索图标
                    contentDescription = "搜索图标"
                )
            },
            trailingIcon = {
                // 当有文本时显示清空按钮
                if (text.isNotEmpty()) {
                    IconButton(onClick = { text = "" }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "清空"
                        )
                    }
                }
            },
            placeholder = { Text("搜索医生") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),//键盘选项
            keyboardActions = KeyboardActions(
                onSearch = {
                    // 点击键盘搜索按钮时触发，将输入的文本传入onSearch回调函数
                    onSearch(text)
                }
            ),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                cursorColor = MaterialTheme.colorScheme.onPrimary
            ),
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = androidx.compose.ui.unit.TextUnit.Unspecified,
                color = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = modifier
                .fillMaxWidth(0.92f)
                .height(56.dp)
        )
    }
}