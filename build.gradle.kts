// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
//    项目构建脚本，要引入这些插件，apply false表示不自动应用
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}