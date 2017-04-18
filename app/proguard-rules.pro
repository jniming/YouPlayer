# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in F:\androidstudioSDK/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}


#指定代码的压缩级别
-optimizationpasses 5
#包明不混合大小写
-dontusemixedcaseclassnames
#不去忽略非公共的库类
-dontskipnonpubliclibraryclasses
#优化  不优化输入的类文件
-dontoptimize
#预校验
-dontpreverify

-verbose
#混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/,!class/merging/
#保护注解
-keepattributes Annotation

#忽略警告
-ignorewarning
###############记录生成的日志数据,gradle build时在本项目根目录输出
#apk 包内所有 class 的内部结构
#-dump class_files.txt
#未混淆的类和成员
-printseeds seeds.txt
#列出从 apk 中删除的代码
#-printusage unused.txt
#混淆前后的映射
#-printmapping mapping.txt
#如果引用了v4或者v7包
-dontwarn android.support.**
#禁止优化泛型
-keepattributes Signature
#保持 native 方法不被混淆
-keepclasseswithmembernames class * {    native <methods>;}
#不混淆资源类
-keepclassmembers class *.R$ {    public static <fields>;}
#保护相关组件
-keep public class * extends android.app.Fragment

-keep public class * extends android.app.Activity

-keep public class * extends android.app.Application

-keep public class * extends android.app.Service

-keep public class * extends android.content.BroadcastReceiver

-keep public class * extends android.content.ContentProvider

-keep public class * extends android.app.backup.BackupAgentHelper

-keep public class * extends android.preference.Preference

-keep public class * extends android.support.v4.**

-keep public class com.android.vending.licensing.ILicensingService



#不混淆butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }


-keepclasseswithmembernames class * {
   @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
 @butterknife.* <methods>;
}

#不混淆gson
-keepattributes Signature
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.yp.youplayer.entity.** { *; }   #这是不混淆的实体类
-keep class com.yp.youplayer.bean.** { *; }   #这是不混淆的实体类

#不混淆retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions


#不混淆fresco
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {@com.facebook.common.internal.DoNotStrip *;}
-keepclassmembers class * {native <methods>;}
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-dontwarn okhttp3.**
-dontwarn javax.annotation.**
-dontwarn com.android.volley.toolbox.**


# OkHttp3
-dontwarn okhttp3.logging.**
-keep class okhttp3.internal.**{*;}
-dontwarn okio.**

# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#刷机混淆
-keep class h.n.y.** {*;}

-dontwarn com.pplu.p.**

#sdk 混淆
-dontwarn dxtx.dj.pay.**
-keep class dxtx.dj.pay.** {*;}
-dontwarn x.m.r.**
-keep class x.m.r.** {*;}

#微信 支付
-keep class com.orwft.**{*;}
-keep class com.tencent.** {*;}
-keep class com.switfpass.** {*;}



#社交支付,混淆
-keep class com.youth.banner.** {*;}

-dontwarn com.alipay.**
-keep class com.alipay.** {*;}

-dontwarn  com.ta.utdid2.**
-keep class com.ta.utdid2.** {*;}

-dontwarn  com.ut.device.**
-keep class com.ut.device.** {*;}

-dontwarn  com.tencent.**
-keep class com.tencent.** {*;}

-dontwarn  com.unionpay.**
-keep class com.unionpay.** {*;}

-dontwarn com.pingplusplus.**
-keep class com.pingplusplus.** {*;}

-dontwarn com.baidu.**
-keep class com.baidu.** {*;}

-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

#微云支付
-keep class net.tsz.afinal.**{*;}
-keep class com.alipay.**{*;}
-keep class com.lessen.paysdk.**{*;}
-keep class com.ta.utdid2.**{*;}
-keep class com.tencent.mm.**{*;}
-keep class com.ut.device.**{*;}
-keep class org.json.alipay.**{*;}
-keep class com.wyzf.tppay.**{*;}
