package com.example.medicalapplication.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

// 给Context类动态增加一个属性dataStore，
// by preferencesDataStore委托创建DataStore实例，把preferencesDataStore函数返回的键值对赋给dataStore对象
// preferencesDataStore函数的name参数：指定存储文件名auth_preferences，
// 实际效果会在应用目录下创建文件：  /data/data/com.example.medicalapplication/datastore/auth_preferences.preferences_pb
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_preferences")

// token管理者，自身的构造函数是传递Context，用于操作数据库等系统资源，所以必须传Context，也拥有传递进来的Context变量
class TokenManager(private val context: Context) {

    companion object {      // 类级别的成员，类似java的static静态成员
        // 定义一个全局共享的类级别的常量，即不论创建多少个TokenManager对象，TOKEN_KEY只有一个，且不用创建实例就能访问，类似于java的static final
        val TOKEN_KEY = stringPreferencesKey("auth_token")
    }

    // 读取 token，需要在请求头携带token的时候用
    val tokenFlow: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[TOKEN_KEY]
    }

    // 保存 token
    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    // 删除 token
    suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }

    // 同步获取 token（用于非协程环境）
    suspend fun getToken(): String? {
        return context.dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }.firstOrNull()
    }
}
