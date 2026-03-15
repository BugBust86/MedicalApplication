package com.example.medicalapplication.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
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

// token管理者，单例模式，避免内存泄露
class TokenManager private constructor(private val context: Context) {

    companion object {      // 类级别的成员，类似java的static静态成员
        // 定义一个全局共享的类级别的常量，即不论创建多少个TokenManager对象，TOKEN_KEY只有一个，且不用创建实例就能访问，类似于java的static final
        val TOKEN_KEY = stringPreferencesKey("auth_token")
        val PHONE_KEY = stringPreferencesKey("user_phone")
        val TOKEN_EXPIRY_KEY = longPreferencesKey("token_expiry")  // token 过期时间（时间戳）

        @Volatile
        private var instance: TokenManager? = null

        fun getInstance(context: Context): TokenManager {
            return instance ?: synchronized(this) {
                instance ?: TokenManager(context.applicationContext).also { instance = it }
            }
        }
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

    // 读取手机号
    val phoneFlow: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[PHONE_KEY]
    }

    // 保存手机号
    suspend fun savePhone(phone: String) {
        context.dataStore.edit { preferences ->
            preferences[PHONE_KEY] = phone
        }
    }

    // 获取手机号
    suspend fun getPhone(): String? {
        return context.dataStore.data.map { preferences ->
            preferences[PHONE_KEY]
        }.firstOrNull()
    }

    // 清除手机号
    suspend fun clearPhone() {
        context.dataStore.edit { preferences ->
            preferences.remove(PHONE_KEY)
        }
    }

    // 保存 token 过期时间（当前时间 + 有效期，单位毫秒）
    suspend fun saveTokenExpiry(expiryTime: Long) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_EXPIRY_KEY] = expiryTime
        }
    }

    // 获取 token 过期时间
    suspend fun getTokenExpiry(): Long? {
        return context.dataStore.data.map { preferences ->
            preferences[TOKEN_EXPIRY_KEY]
        }.firstOrNull()
    }

    // 判断 token 是否有效（未过期）
    suspend fun isTokenValid(): Boolean {
        val token = getToken()
        val expiry = getTokenExpiry()
        return token != null && token.isNotEmpty() && expiry != null && System.currentTimeMillis() < expiry
    }

    // 清除所有认证信息
    suspend fun clearAll() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
            preferences.remove(PHONE_KEY)
            preferences.remove(TOKEN_EXPIRY_KEY)
        }
    }

    // 用于通知 token 失效的 EventBus
    private val _tokenExpiredEvent = kotlinx.coroutines.flow.MutableSharedFlow<Unit>(replay = 0)
    val tokenExpiredEvent: kotlinx.coroutines.flow.SharedFlow<Unit> = _tokenExpiredEvent

    // 触发 token 失效事件
    suspend fun notifyTokenExpired() {
        clearAll()
        _tokenExpiredEvent.emit(Unit)
    }
}
