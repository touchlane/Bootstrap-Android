plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
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
        applicationId = "com.touchlane.android.bootstrap"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    signingConfigs {
        val homeDir = System.getProperty("user.home")
        register("release") {
            configFrom(file("$homeDir/.signing/release-signing.properties"))
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Deps.Libs.KotlinStdLib)
    implementation(Deps.Libs.AppCompat)
    implementation(Deps.Libs.ConstraintLayout)
    implementation(Deps.Libs.CoreKtx)
    implementation(Deps.Libs.RecyclerView)
    implementation(Deps.Libs.RxJava)
    implementation(Deps.Libs.RxAndroid)
    implementation(Deps.Libs.Retrofit)
    implementation(Deps.Libs.RetrofitRxJavaAdapter)
    implementation(Deps.Libs.RetrofitGsonConverter)
    implementation(Deps.Libs.Moxy)
    implementation(Deps.Libs.MoxyAndroidX)
    implementation(Deps.Libs.MoxyKtx)
    kapt(Deps.Libs.MoxyCompiler)
    implementation(Deps.Libs.Dagger)
    kapt(Deps.Libs.DaggerCompiler)
    implementation(project(":domain"))
    implementation(project(":data"))
    debugImplementation(Deps.Libs.LeakCanary)
    testImplementation(Deps.Libs.Test.JUnit)
    androidTestImplementation(Deps.Libs.AndroidTest.JUnitExt)
    androidTestImplementation(Deps.Libs.AndroidTest.EspressoCore)
}
