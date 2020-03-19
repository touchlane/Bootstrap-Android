object Deps {

    object Plugins {

        const val Kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin}"
        const val AndroidBuildTools = "com.android.tools.build:gradle:${Versions.Plugins.AndroidBuildTools}"
        const val JacocoAndroid = "com.hiya:jacoco-android:${Versions.Plugins.JacocoAndroid}"
    }

    object Libs {

        const val KotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Kotlin}"
        const val AppCompat = "androidx.appcompat:appcompat:${Versions.Libs.AppCompat}"
        const val CoreKtx = "androidx.core:core-ktx:${Versions.Libs.CoreKtx}"
        const val ConstraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.Libs.ConstraintLayout}"
        const val RecyclerView = "androidx.recyclerview:recyclerview:${Versions.Libs.RecyclerView}"

        object Test {
            const val JUnit = "junit:junit:${Versions.Libs.Test.JUnit}"
            const val MockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.Libs.Test.MockitoKotlin}"
        }

        object AndroidTest {
            const val JUnitExt = "androidx.test.ext:junit:${Versions.Libs.AndroidTest.JUnitExt}"
            const val EspressoCore = "androidx.test.espresso:espresso-core:${Versions.Libs.AndroidTest.EspressoCore}"
        }
    }
}