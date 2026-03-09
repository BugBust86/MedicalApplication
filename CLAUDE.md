# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

这是一个 Android 医疗健康应用，采用 Kotlin + Jetpack Compose 开发，使用 MVVM 架构模式。

## Build Commands

```bash
# 构建 Debug APK
./gradlew assembleDebug

# 构建 Release APK
./gradlew assembleRelease

# 运行测试
./gradlew test

# 清理构建
./gradlew clean
```

## Architecture

```
app/src/main/java/com/example/medicalapplication/
├── MainActivity.kt              # 应用入口，Activity
├── SmartHealthApp.kt            # 根 Composable，根据登录状态切换页面
├── viewmodel/
│   └── AuthViewModel.kt         # 认证 ViewModel，处理登录/注册逻辑
├── data/
│   ├── local/
│   │   └── TokenManager.kt      # Token 本地存储（DataStore）
│   └── network/
│       ├── RetrofitInstance.kt  # Retrofit 单例，网络请求初始化
│       ├── api/AuthApi.kt       # 认证 API 接口定义
│       └── model/               # 请求/响应数据模型
└── ui/
    ├── screens/
    │   ├── MainScreen.kt         # 主页面（含底部导航）
    │   └── LoginRegisterScreen.kt # 登录注册页面
    ├── content/                 # 页面内容区域
    │   ├── Home/                # 首页
    │   ├── Message/            # 消息页
    │   └── Profile/            # 个人中心页
    ├── components/             # 可复用组件
    │   ├── BottomNavigationBar.kt
    │   ├── MedicalTopBar.kt
    │   └── SearchTextField.kt
    └── theme/                  # Compose 主题配置
```

## Key Dependencies

- Jetpack Compose (BOM)
- Navigation Compose
- Retrofit + Gson
- DataStore (本地存储)
- Lifecycle ViewModel Compose

## API Configuration

- Base URL: `http://192.168.148.24:8080` (在 `RetrofitInstance.kt` 中配置)

## Navigation

应用使用底部导航栏，包含三个 Tab：
- Home (首页) - 显示"北方协和医院"
- Message (消息)
- Profile (个人中心)

当前登录状态逻辑在 `SmartHealthApp.kt` 中（暂时硬编码直接显示 MainScreen）。
