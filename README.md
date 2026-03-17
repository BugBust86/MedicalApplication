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

# 运行单个测试类
./gradlew test --tests "com.example.medicalapplication.ExampleUnitTest"

# 清理构建
./gradlew clean

# 检查依赖更新
./gradlew dependencyUpdates
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
    │   ├── MainScreen.kt         # 主页面（含底部导航 + NavHost）
    │   └── LoginRegisterScreen.kt # 登录注册页面
    ├── content/                 # 页面内容区域
    │   ├── Home/                # 首页
    │   │   ├── HomeContent.kt
    │   │   └── section/
    │   │       ├── ServiceItem.kt
    │   │       └── ServiceSection.kt
    │   ├── Message/              # 消息页
    │   │   ├── MessageContent.kt
    │   │   ├── section/
    │   │   │   └── MessageMenuItem.kt
    │   │   └── page/              # 消息子页面
    │   │       ├── HospitalDynamicScreen.kt
    │   │       ├── MyMessageScreen.kt
    │   │       ├── ConsultMessageScreen.kt
    │   │       └── DiseaseScienceScreen.kt
    │   └── Profile/              # 个人中心页
    │       ├── ProfileContent.kt
    │       └── UserInfoSection.kt
    ├── components/              # 可复用组件
    │   ├── BottomNavigationBar.kt
    │   ├── BottomNavItem.kt      # 底部导航项密封类
    │   ├── MedicalTopBar.kt      # 顶部导航栏（支持返回按钮）
    │   └── SearchTextField.kt
    └── theme/                   # Compose 主题配置
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

应用使用 NavHost + 底部导航栏，包含三个 Tab：
- Home (首页) - 显示"北方协和医院"
- Message (消息)
- Profile (个人中心)

消息页面有子页面导航（点击列表项进入）：
- 医院动态 (`hospital_dynamic`)
- 我的消息 (`my_message`)
- 问诊消息 (`consult_message`)
- 专病科普 (`disease_science`)

路由定义在 `MainScreen.kt` 的 `MessageRoutes` 对象中。

当前登录状态逻辑在 `SmartHealthApp.kt` 中（暂时硬编码直接显示 MainScreen）。
