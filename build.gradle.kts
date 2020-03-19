buildscript {

    repositories {
        google()
        jcenter()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath (Deps.Plugins.AndroidBuildTools)
        classpath(Deps.Plugins.Kotlin)
        classpath(Deps.Plugins.JacocoAndroid)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
