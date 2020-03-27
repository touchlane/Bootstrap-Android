plugins {
    id("com.android.application")
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
        applicationId = "com.touchlane.android.bootstrap"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments = mapOf("clearPackageData" to "true")
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
    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Deps.Libs.KotlinStdLib)
    implementation(Deps.Libs.AppCompat)
    implementation(Deps.Libs.ConstraintLayout)
    implementation(Deps.Libs.CoreKtx)
    implementation(Deps.Libs.RecyclerView)
    implementation(Deps.Libs.ViewModelKtx)
    implementation(Deps.Libs.CoroutinesAndroid)
    implementation(Deps.Libs.KtorClient)
    implementation(Deps.Libs.KtorGson)
    implementation(Deps.Libs.KtorClientJson)
    implementation(Deps.Libs.Koin)
    implementation(Deps.Libs.KoinViewModel)
    implementation(Deps.Libs.LiveEvent)
    implementation(project(":domain"))
    implementation(project(":data"))
    debugImplementation(Deps.Libs.LeakCanary)
    testImplementation(Deps.Libs.Test.JUnit)
    androidTestImplementation(Deps.Libs.AndroidTest.JUnitExt)
    androidTestImplementation(Deps.Libs.AndroidTest.EspressoCore)
    androidTestImplementation(Deps.Libs.AndroidTest.TestCore)
    androidTestImplementation(Deps.Libs.AndroidTest.TestRunner)
    androidTestImplementation(Deps.Libs.AndroidTest.TestRules)
    androidTestImplementation(Deps.Libs.AndroidTest.MockitoCore)
    androidTestImplementation(Deps.Libs.AndroidTest.MockitoAndroid)
    androidTestImplementation(Deps.Libs.AndroidTest.EspressoIntents)
    androidTestImplementation(Deps.Libs.Test.KtorMock)
    androidTestUtil(Deps.Libs.AndroidTest.Orchestrator)
}
