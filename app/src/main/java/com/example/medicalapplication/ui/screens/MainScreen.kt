package com.example.medicalapplication.ui.screens

// 绘制主页除顶部bar以外的
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.medicalapplication.ui.components.BottomNavItem
import com.example.medicalapplication.ui.components.BottomNavigationBar
import com.example.medicalapplication.ui.components.MedicalTopBar
import com.example.medicalapplication.ui.content.Home.HomeContent
import com.example.medicalapplication.ui.content.Message.MessageContent
import com.example.medicalapplication.ui.content.Profile.ProfileContent

// 主页屏幕，
@Composable
fun MainScreen() {
    // 使用 rememberSaveable 在配置更改（如旋转屏幕）后保持选中状态
    // 使用=，没有用by，所以后续都要.value取值
    val selectedItem = rememberSaveable(stateSaver = BottomNavItem.saver) {
        mutableStateOf(BottomNavItem.Home)
    }
    // rememberSaveable 默认只能保存可以放入 Android Bundle 的类型（如基本类型、String、Parcelable 等）。而 BottomNavItem 是一个自定义的密封类，默认情况下无法被序列化到 Bundle 中。
    // var selectedItem by rememberSaveable { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }
    // var selectedItem by remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }，此代码可行，但是配置改变如开启暗色模式会回到默认首页丢失状态

    Scaffold(
        topBar = {
            when (selectedItem.value) {
                BottomNavItem.Home -> MedicalTopBar(title = { Text("北方协和医院") })
                BottomNavItem.Message -> MedicalTopBar(title = { Text("消息") })
                BottomNavItem.Profile -> MedicalTopBar(title = { Text("个人中心") })
            }
        },
        bottomBar = {
            // 自定义的底部导航栏组件，内部使用了NavigationBar组件
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem.Home,
                    BottomNavItem.Message,
                    BottomNavItem.Profile
                ),
                selectedItem = selectedItem.value,
                onItemSelected = { selectedItem.value = it }
            )
        }
    ) { innerPadding ->
        // 根据选中的项显示对应的页面
        Box(modifier = Modifier.padding(innerPadding)){
            when (selectedItem.value) {
                BottomNavItem.Home -> HomeContent()
                BottomNavItem.Message -> MessageContent()
                BottomNavItem.Profile -> ProfileContent()
            }
        }
        // 注意：这里如果需要调整页面内容的内边距，可以结合 innerPadding
        // 但我们的页面已经 fillMaxSize，所以直接显示即可
    }
}


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