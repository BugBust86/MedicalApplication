package com.example.medicalapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalapplication.data.local.TokenManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    application: Application
): AndroidViewModel(application) {

    private val tokenManager = TokenManager.getInstance(application)

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber: StateFlow<String> = _phoneNumber.asStateFlow()

    init {
        viewModelScope.launch {
            tokenManager.phoneFlow.collect { phone ->
                _phoneNumber.value = phone ?: ""
            }
        }
    }

    // 隐藏手机号中间4位，如 193****8075
    fun getHiddenPhoneNumber(): String {
        val phone = _phoneNumber.value
        return if (phone.length == 11) {
            "${phone.substring(0, 3)}****${phone.substring(7)}"
        } else {
            phone
        }
    }
}
