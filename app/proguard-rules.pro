# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\AndoridSDK/tools/proguard/proguard-android.txt
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

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

##########################lecheng add###############################
#指定压缩级别
-optimizationpasses 5

#不跳过非公共的库的类成员
-dontskipnonpubliclibraryclassmembers

#混淆时采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#把混淆类中的方法名也混淆了
-useuniqueclassmembernames

#优化时允许访问并修改有修饰符的类和类的成员
-allowaccessmodification

#将文件来源重命名为“SourceFile”字符串
-renamesourcefileattribute SourceFile
#保留行号
-keepattributes SourceFile,LineNumberTable
#保持泛型
-keepattributes Signature

#保持所有实现 Serializable 接口的类成员
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#Fragment不需要在AndroidManifest.xml中注册，需要额外保护下
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Fragment

# 保持测试相关的代码
-dontnote junit.framework.**
-dontnote junit.runner.**
-dontwarn android.test.**
-dontwarn android.support.test.**
-dontwarn org.junit.**

###########################################################################
#如果有引用v4包可以添加下面这行
-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment


#如果引用了v4或者v7包，可以忽略警告，因为用不到android.support
-dontwarn android.support.**
#保持自定义组件不被混淆
-keep public class * extends android.view.View {
&nbsp; &nbsp; public &lt;init&gt;(android.content.Context);
&nbsp; &nbsp; public &lt;init&gt;(android.content.Context, android.util.AttributeSet);
&nbsp; &nbsp; public &lt;init&gt;(android.content.Context, android.util.AttributeSet, int);
&nbsp; &nbsp; public void set*(...);
}

#保持 Serializable 不被混淆
-keepnames class * implements java.io.Serializable
#保持 Serializable 不被混淆并且enum 类也不被混淆
-keepclassmembers class * implements java.io.Serializable {
&nbsp; &nbsp; static final long serialVersionUID;
&nbsp; &nbsp; private static final java.io.ObjectStreamField[] serialPersistentFields;
&nbsp; &nbsp; private void writeObject(java.io.ObjectOutputStream);
&nbsp; &nbsp; private void readObject(java.io.ObjectInputStream);
&nbsp; &nbsp; java.lang.Object writeReplace();
&nbsp; &nbsp; java.lang.Object readResolve();
}
#保持枚举 enum 类不被混淆 如果混淆报错，建议直接使用上面的 -keepclassmembers class * implements java.io.Serializable即可
-keepclassmembers enum * {
&nbsp; public static **[] values();
&nbsp;public static ** valueOf(java.lang.String);
}

-keepclassmembers class * {
&nbsp; &nbsp; public void *ButtonClicked(android.view.View);
}
#不混淆资源类
#-keepclassmembers class **.R$* {
# &nbsp; &nbsp;public static &lt;fields&gt;;
#}


#xUtils(保持注解，及使用注解的Activity不被混淆，不然会影响Activity中你使用注解相关的代码无法使用)&nbsp;
-keep class * extends java.lang.annotation.Annotation {*;}
-keep class com.otb.designerassist.activity.** {*;}


#自己项目特殊处理代码（这些地方我使用了Gson类库和注解，所以不希望被混淆，以免影响程序）
-keep class com.otb.designerassist.entity.** {*;}
-keep class com.otb.designerassist.http.rspdata.** {*;}
-keep class com.otb.designerassist.service.** {*;}


##混淆保护自己项目的部分代码以及引用的第三方jar包library（想混淆去掉"#"）
#-libraryjars libs/umeng-analytics-v5.2.4.jar
#-libraryjars libs/alipaysecsdk.jar
#-libraryjars libs/alipayutdid.jar
#-libraryjars libs/weibosdkcore.jar&nbsp;


# 以libaray的形式引用的图片加载框架,不想混淆（注意，此处不是jar包形式，想混淆去掉"#"）
#-keep class com.nostra13.universalimageloader.** { *; }


###--------&nbsp;Gson 相关的混淆配置--------
-keepattributes Signature
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }




###--------&nbsp;pulltorefresh 相关的混淆配置---------
-dontwarn com.handmark.pulltorefresh.library.**
-keep class com.handmark.pulltorefresh.library.** { *;}
-dontwarn com.handmark.pulltorefresh.library.extras.**
-keep class com.handmark.pulltorefresh.library.extras.** { *;}
-dontwarn com.handmark.pulltorefresh.library.internal.**
-keep class com.handmark.pulltorefresh.library.internal.** { *;}


###--------- &nbsp;reservoir 相关的混淆配置-------
-keep class com.anupcowkur.reservoir.** { *;}


###--------&nbsp;ShareSDK 相关的混淆配置---------
-keep class cn.sharesdk.** { *; }
-keep class com.sina.sso.** { *; }


###--------------umeng 相关的混淆配置-----------
-keep class com.umeng.** { *; }
-keep class com.umeng.analytics.** { *; }
-keep class com.umeng.common.** { *; }
-keep class com.umeng.newxp.** { *; }


###-----------MPAndroidChart图库相关的混淆配置------------
-keep class com.github.mikephil.charting.** { *; }

