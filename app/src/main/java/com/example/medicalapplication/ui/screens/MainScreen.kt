package com.example.medicalapplication.ui.screens

// 绘制主页除顶部bar以外的



//import android.net.http.SslCertificate.restoreState
//import android.net.http.SslCertificate.saveState
//import androidx.compose.material3.Button
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.navigation.NavController
//import androidx.navigation.NavGraph.Companion.findStartDestination
//import androidx.navigation.compose.currentBackStackEntryAsState
//import androidx.navigation.compose.rememberNavController
//@Composable
//fun MainScreen(onLogout: () -> Unit) {
//    val mainNavController = rememberNavController()
//    Scaffold(
//        bottomBar = { BottomNavigationBar(mainNavController) }
//    ) { innerPadding ->
//        NavHost(
//            navController = mainNavController,
//            startDestination = "home",
//            modifier = Modifier.padding(innerPadding)
//        ) {
//            composable("home") { HomeTab() }
//            composable("message") { MessageTab() }
//            composable("profile") { ProfileTab(onLogout = onLogout) }
//        }
//    }
//}
//
//@Composable
//fun BottomNavigationBar(navController: NavController) {
//    val items = listOf(
//        BottomNavItem("home", "首页"),
//        BottomNavItem("message", "消息"),
//        BottomNavItem("profile", "我的")
//    )
//    BottomNavigation {
//        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
//        items.forEach { item ->
//            BottomNavigationItem(
//                icon = { /* 图标 */ },
//                label = { Text(item.label) },
//                selected = currentRoute == item.route,
//                onClick = {
//                    navController.navigate(item.route) {
//                        popUpTo(navController.graph.findStartDestination().id) {
//                            saveState = true
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
//                }
//            )
//        }
//    }
//}
//
//data class BottomNavItem(val route: String, val label: String)
//
//@Composable
//fun ProfileTab(onLogout: () -> Unit) {
//    Button(onClick = onLogout) {
//        Text("登出")
//    }
//}