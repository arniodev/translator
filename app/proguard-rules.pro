# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#-keep class com.arniodev.translator.**{*;}
#-keep class okhttp3.**{*;}
#-keep class retrofit2.**{*;}
#-keep class okio.**{*;}
##-keep class com.arniodev.translator.service.ArTranslatorService
##-keep class com.arniodev.translator.service.DeepLTranslateService
##-keep class com.arniodev.translator.service.GoogleTranslateService
##-keep interface com.arniodev.translator.interface.ArTranslatorInterface
##-keep interface com.arniodev.translator.interface.DeepLTranslateInterface
##-keep interface com.arniodev.translator.interface.GoogleTranslateInterface
#-keep class org.jetbrains.kotlin.** { *; }
#-keep class kotlin.** { *; }
#-keep class META-INF.services.**{ *; }
-keep class kotlinx.serialization.** { *; }
-keep class kotlinx.serialization.json.** { *; }
-keep class com.arniodev.translator.** { *; }
-keep class org.jetbrains.kotlin.compiler.plugin.** { *; }
-keep class org.jetbrains.kotlin.diagnostics.rendering.** { *; }