object Deps {

    object Plugins {

        const val Kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin}"
        const val AndroidBuildTools =
            "com.android.tools.build:gradle:${Versions.Plugins.AndroidBuildTools}"
        const val JacocoAndroid = "com.hiya:jacoco-android:${Versions.Plugins.JacocoAndroid}"
    }

    object Libs {

        const val KotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Kotlin}"
        const val AppCompat = "androidx.appcompat:appcompat:${Versions.Libs.AppCompat}"
        const val CoreKtx = "androidx.core:core-ktx:${Versions.Libs.CoreKtx}"
        const val ConstraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.Libs.ConstraintLayout}"
        const val RecyclerView = "androidx.recyclerview:recyclerview:${Versions.Libs.RecyclerView}"
        const val RxJava = "io.reactivex.rxjava3:rxjava:${Versions.Libs.RxJava}"
        const val RxAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.Libs.RxAndroid}"
        const val Retrofit = "com.squareup.retrofit2:retrofit:${Versions.Libs.Retrofit}"
        const val RetrofitGsonConverter =
            "com.squareup.retrofit2:converter-gson:${Versions.Libs.Retrofit}"
        const val RetrofitRxJavaAdapter =
            "com.github.akarnokd:rxjava3-retrofit-adapter:${Versions.Libs.RetrofitRxJavaAdapter}"
        const val Dagger = "com.google.dagger:dagger:${Versions.Libs.Dagger}"
        const val DaggerCompiler =
            "com.google.dagger:dagger-compiler:${Versions.Libs.Dagger}"
        const val Moxy = "com.github.moxy-community:moxy:${Versions.Libs.Moxy}"
        const val MoxyAndroidX = "com.github.moxy-community:moxy-androidx:${Versions.Libs.Moxy}"
        const val MoxyKtx = "com.github.moxy-community:moxy-ktx:${Versions.Libs.Moxy}"
        const val MoxyCompiler = "com.github.moxy-community:moxy-compiler:${Versions.Libs.Moxy}"
        const val LeakCanary =
            "com.squareup.leakcanary:leakcanary-android:${Versions.Libs.LeakCanary}"

        object Test {
            const val JUnit = "junit:junit:${Versions.Libs.Test.JUnit}"
            const val MockitoKotlin =
                "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.Libs.Test.MockitoKotlin}"
            const val MockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.Libs.Test.MockWebServer}"
        }

        object AndroidTest {
            const val JUnitExt = "androidx.test.ext:junit:${Versions.Libs.AndroidTest.JUnitExt}"
            const val EspressoCore =
                "androidx.test.espresso:espresso-core:${Versions.Libs.AndroidTest.Espresso}"
            const val TestCore = "androidx.test:core:${Versions.Libs.AndroidTest.AndroidxTest}"
            const val TestRunner = "androidx.test:runner:${Versions.Libs.AndroidTest.AndroidxTest}"
            const val TestRules = "androidx.test:rules:${Versions.Libs.AndroidTest.AndroidxTest}"
            const val MockitoCore = "org.mockito:mockito-core:${Versions.Libs.AndroidTest.Mockito}"
            const val MockitoAndroid =
                "org.mockito:mockito-android:${Versions.Libs.AndroidTest.Mockito}"
            const val EspressoIntents =
                "androidx.test.espresso:espresso-intents:${Versions.Libs.AndroidTest.Espresso}"
            const val Orchestrator =
                "androidx.test:orchestrator:${Versions.Libs.AndroidTest.AndroidxTest}"
        }
    }
}