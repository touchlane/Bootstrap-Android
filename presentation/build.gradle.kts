plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
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
            configFrom(file("$homeDir/.signing/bootstrap-release-signing.properties"))
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
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Deps.Libs.KotlinStdLib)
    implementation(Deps.Libs.AppCompat)
    implementation(Deps.Libs.ConstraintLayout)
    implementation(Deps.Libs.CoreKtx)
    implementation(Deps.Libs.RecyclerView)
    implementation(project(":domain"))
    implementation(project(":data"))
    testImplementation(Deps.Libs.Test.JUnit)
    androidTestImplementation(Deps.Libs.AndroidTest.JUnitExt)
    androidTestImplementation(Deps.Libs.AndroidTest.EspressoCore)
}
