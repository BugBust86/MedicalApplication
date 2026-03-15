package com.example.medicalapplication.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.medicalapplication.ui.components.BottomNavItem
import com.example.medicalapplication.ui.components.BottomNavigationBar
import com.example.medicalapplication.ui.components.MedicalTopBar
import com.example.medicalapplication.ui.content.Home.HomeContent
import com.example.medicalapplication.ui.content.Message.MessageContent
import com.example.medicalapplication.ui.content.Message.page.ConsultMessageScreen
import com.example.medicalapplication.ui.content.Message.page.DiseaseScienceScreen
import com.example.medicalapplication.ui.content.Message.page.HospitalDynamicScreen
import com.example.medicalapplication.ui.content.Message.page.MyMessageScreen
import com.example.medicalapplication.ui.content.Profile.ProfileContent
import com.example.medicalapplication.ui.content.Profile.page.ConsultationRecordScreen
import com.example.medicalapplication.ui.content.Profile.page.InsuranceCardScreen
import com.example.medicalapplication.ui.content.Profile.page.MedicalCardScreen
import com.example.medicalapplication.ui.content.Profile.page.PersonalInfoScreen

// 消息页面子路由
object MessageRoutes {
    const val HOSPITAL_DYNAMIC = "hospital_dynamic"
    const val MY_MESSAGE = "my_message"
    const val CONSULT_MESSAGE = "consult_message"
    const val DISEASE_SCIENCE = "disease_science"
}

// 个人中心页面子路由
object ProfileRoutes {
    const val PERSONAL_INFO = "personal_info"
    const val MEDICAL_CARD = "medical_card"
    const val INSURANCE_CARD = "insurance_card"
    const val CONSULTATION_RECORD = "consultation_record"
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // 根据当前路由获取对应的标题
    val currentTitle = when (currentDestination?.route) {
        BottomNavItem.Home.route -> "北方协和医院"
        BottomNavItem.Message.route -> "消息"
        BottomNavItem.Profile.route -> "个人中心"
        MessageRoutes.HOSPITAL_DYNAMIC -> "医院动态"
        MessageRoutes.MY_MESSAGE -> "我的消息"
        MessageRoutes.CONSULT_MESSAGE -> "问诊消息"
        MessageRoutes.DISEASE_SCIENCE -> "专病科普"
        ProfileRoutes.PERSONAL_INFO -> "个人信息"
        ProfileRoutes.MEDICAL_CARD -> "就诊卡管理"
        ProfileRoutes.INSURANCE_CARD -> "医保卡管理"
        ProfileRoutes.CONSULTATION_RECORD -> "问诊记录"
        else -> "北方协和医院"
    }

    // 判断是否显示底部导航栏（消息和个人中心子页面不显示）
    val showBottomBar = currentDestination?.route in listOf(
        BottomNavItem.Home.route,
        BottomNavItem.Message.route,
        BottomNavItem.Profile.route
    )

    // 判断是否显示返回按钮（消息子页面显示）
    val showBackButton = !showBottomBar

    // 根据当前路由获取选中的导航项
    val items = listOf(BottomNavItem.Home, BottomNavItem.Message, BottomNavItem.Profile)
    val selectedItem = items.firstOrNull { item ->
        currentDestination?.hierarchy?.any { it.route == item.route } == true
    } ?: BottomNavItem.Home

    Scaffold(
        topBar = {
            MedicalTopBar(
                title = { Text(currentTitle) },
                showBackButton = showBackButton,
                onBackClick = { navController.popBackStack() }
            )
        },
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(
                    items = listOf(
                        BottomNavItem.Home,
                        BottomNavItem.Message,
                        BottomNavItem.Profile
                    ),
                    selectedItem = selectedItem,
                    onItemSelected = { item ->
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            // 底部导航页面
            composable(BottomNavItem.Home.route) {
                HomeContent()
            }
            composable(BottomNavItem.Message.route) {
                MessageContent(
                    onNavigateToPage = { route ->
                        navController.navigate(route)
                    }
                )
            }
            composable(BottomNavItem.Profile.route) {
                ProfileContent(
                    onNavigateToPage = { route ->
                        navController.navigate(route)
                    }
                )
            }

            // 消息子页面
            composable(MessageRoutes.HOSPITAL_DYNAMIC) {
                HospitalDynamicScreen()
            }
            composable(MessageRoutes.MY_MESSAGE) {
                MyMessageScreen()
            }
            composable(MessageRoutes.CONSULT_MESSAGE) {
                ConsultMessageScreen()
            }
            composable(MessageRoutes.DISEASE_SCIENCE) {
                DiseaseScienceScreen()
            }

            // 个人中心子页面
            composable(ProfileRoutes.PERSONAL_INFO) {
                PersonalInfoScreen()
            }
            composable(ProfileRoutes.MEDICAL_CARD) {
                MedicalCardScreen()
            }
            composable(ProfileRoutes.INSURANCE_CARD) {
                InsuranceCardScreen()
            }
            composable(ProfileRoutes.CONSULTATION_RECORD) {
                ConsultationRecordScreen()
            }
        }
    }
}
