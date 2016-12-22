# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\workProgram\android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-keepattributes EnclosingMetho
#优化  不优化输入的类文件
-dontoptimize
#保护注解
#-keepattributes *Annotation*
#如果引用了v4或者v7包
-dontwarn android.support.**
#保持 native 方法不被混淆
-keepclasseswithmembernames class * {
   native <methods>;
}
##XBanner 图片轮播混淆配置
-keep class com.stx.xhb.xbanner.**{*;}