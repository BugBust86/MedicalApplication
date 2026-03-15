package com.example.medicalapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalapplication.data.local.TokenManager
import com.example.medicalapplication.data.network.RetrofitInstance
import com.example.medicalapplication.data.network.model.LoginRequest
import com.example.medicalapplication.data.network.model.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    application: Application    // 构造函数的参数
): AndroidViewModel(application) {
    // 使用 StateFlow 替代 mutableStateOf（ViewModel 中不能用 Compose 的 State）
    private val _isLoginMode = MutableStateFlow(true)
    val isLoginMode: StateFlow<Boolean> = _isLoginMode.asStateFlow()

    private val _account = MutableStateFlow("")
    val account: StateFlow<String> = _account.asStateFlow()

    // 调用MutableStateFlow(T)函数返回MutableStateFlow类型的状态数据流，该数据流可读可写，暂设初始值为“”
    private val _password = MutableStateFlow("")
    // asStateFlow，接口MutableStateFlow接口的扩展函数
    // 调用它返回只读的状态数据流：StateFlow
    val password: StateFlow<String> = _password.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword.asStateFlow()

    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName.asStateFlow()

    // 添加加载状态和错误信息
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null) // 可为空的String类型，初始值为null
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess: StateFlow<Boolean> = _loginSuccess.asStateFlow()

    // 自动登录状态（检查 token 是否有效）
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    // Token 失效状态（用于通知 UI 跳转到登录页）
    private val _tokenExpired = MutableStateFlow(false)
    val tokenExpired: StateFlow<Boolean> = _tokenExpired.asStateFlow()

    // TokenManager（单例模式）
    private val tokenManager = TokenManager.getInstance(application)

    init {
        // 初始化时检查 token 是否有效
        viewModelScope.launch {
            val isValid = tokenManager.isTokenValid()
            _isLoggedIn.value = isValid
            if (isValid) {
                _loginSuccess.value = true
            }

            // 监听 token 失效事件
            tokenManager.tokenExpiredEvent.collect {
                _tokenExpired.value = true
                _isLoggedIn.value = false
                _loginSuccess.value = false
            }
        }
    }

    // 更新方法
    fun updateAccount(value: String) {
        _account.value = value
        _errorMessage.value = null
    }

    fun updatePassword(value: String) {
        _password.value = value
        _errorMessage.value = null
    }

    fun updateConfirmPassword(value: String) {
        _confirmPassword.value = value
        _errorMessage.value = null
    }

    fun updateUserName(value: String){
        _userName.value = value
        _errorMessage.value = null  // 每次输入新内容时把错误消息清空
    }

    fun toggleLoginMode() {
        _isLoginMode.value = !_isLoginMode.value
        // 切换模式时清空输入
        _account.value = ""
        _password.value = ""
        _confirmPassword.value = ""
        _errorMessage.value = null
    }

    // 登录方法
    fun login() {
        // 验证输入
        if (!validateInput()) return

        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                // 定义一个请求体对象
                val request = LoginRequest(
                    phone = _account.value,
                    password = _password.value
                )
                val response = RetrofitInstance.api.login(request)
                if (response.isSuccessful && response.body() != null) {
                    val loginResponse = response.body()!!
                    if (loginResponse.code == 0) {
                        // 登录成功，保存 token、手机号和过期时间
                        tokenManager.saveToken(loginResponse.token)
                        tokenManager.savePhone(_account.value)
                        // token 有效期 10 小时
                        val expiryTime = System.currentTimeMillis() + (10 * 60 * 60 * 1000L)
                        tokenManager.saveTokenExpiry(expiryTime)
                        _loginSuccess.value = true
                        println("登录成功，token: ${loginResponse.token}")
                    } else {
                        // 登录失败
                        _errorMessage.value = loginResponse.message
                        println("登录失败：${loginResponse.message}")
                    }
                } else {
                    // 请求失败
                    _errorMessage.value = "登录失败：${response.message()}"
                    println("登录失败：${response.message()}")
                }
            } catch (e: Exception) {
                _errorMessage.value = "网络错误：${e.message}"
                println("登录异常：${e.message}")
            } finally {
                // 登录请求发送完后，将正在加载的状态设为false，解除按钮、输入框的锁定效果
                _isLoading.value = false
            }
        }
    }

    // 注册方法
    fun register() {
        if (!validateInput()) return
        viewModelScope.launch {
            _isLoading.value = true     //正在加载，输入框、按钮等进入锁定状态，避免重复发送请求
            _errorMessage.value = null
            try {
                val request = RegisterRequest(
                    phone = _account.value,
                    password = _password.value,
                    userName = _userName.value,
                )
                val response = RetrofitInstance.api.register(request)
                if(response.isSuccessful && response.body() != null){
                    response.body()?.let { registerResponse ->
                        if (registerResponse.code ==0){ // 注册成功，跳回登录模块，同时把账号、密码等信息的值清空
                            _isLoginMode.value = true
                            _account.value = ""
                            _password.value = ""
                            _confirmPassword.value = ""
                            println("后端返回的消息：${registerResponse.message}")
                        } else {    // 业务失败手机端显示失败的消息，Logcat打印code和message
                            _errorMessage.value = registerResponse.message
                            println("后端返回的code：${registerResponse.code}//")
                            println("后端返回的data：${registerResponse.data}//")
                            println("后端返回的message：${registerResponse.message}//")
                        }
                    }
                }else{  // 网络请求失败
                    _errorMessage.value = "注册失败：${response.message()}"
                    println("Http状态消息：${response.message()}, Http状态码：${response.code()}")
                }
            } catch (e: Exception) {
                _errorMessage.value = "网络错误：${e.message}"
                println("注册异常：${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    // 验证输入
    private fun validateInput(): Boolean {
        // 验证手机号（11 位数字）
        if (!_account.value.matches("^\\d{11}$".toRegex())) {
            _errorMessage.value = "请输入正确的手机号"
            return false
        }
        // 验证注册模式下用户名是否为空
        if (!_isLoginMode.value && _userName.value.isBlank()){
            _errorMessage.value = "请输入用户名"
            return false
        }
        // 验证密码（5-16 位非空字符）
        if (_password.value.length !in 5..16 || _password.value.isBlank()) {
            _errorMessage.value = "密码长度为 5-16 位"
            return false
        }
        // 注册模式下验证确认密码
        if (!_isLoginMode.value && _password.value != _confirmPassword.value) {
            _errorMessage.value = "两次输入的密码不一致"
            return false
        }
        return true
    }
    // 清除错误信息
    fun clearError() {
        _errorMessage.value = null
    }

    // 退出登录
    fun logout() {
        viewModelScope.launch {
            tokenManager.clearAll()
            _loginSuccess.value = false
            _isLoggedIn.value = false
        }
    }
}