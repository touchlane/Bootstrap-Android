plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    id("com.hiya.jacoco-android")
    id("org.jlleitschuh.gradle.ktlint") version Versions.Plugins.Ktlint
}

jacoco {
    toolVersion = Versions.Libs.Test.JacocoTools
}

ktlint {
    android.set(true)
    disabledRules.set(Ktlint.disabledRules)
}

android {
    compileSdkVersion(29)
    buildToolsVersion = "29.0.3"
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Deps.Libs.KotlinStdLib)
    implementation(Deps.Libs.RxJava)
    implementation(Deps.Libs.Retrofit)
    implementation(Deps.Libs.RetrofitGsonConverter)
    implementation(Deps.Libs.RetrofitRxJavaAdapter)
    implementation(project(":domain"))
    testImplementation(Deps.Libs.Test.JUnit)
    testImplementation(Deps.Libs.Test.MockitoKotlin)
    testImplementation(Deps.Libs.Test.MockWebServer)
}
