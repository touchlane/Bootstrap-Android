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

tasks.create("ktlintCheckAll") {
    group = "verification"
    dependsOn(
        ":domain:ktlintCheck",
        ":data:ktlintCheck",
        ":presentation:ktlintCheck"
    )
}

tasks.create("jacocoTestReports") {
    group = "reporting"
    dependsOn(
        ":domain:jacocoTestReport",
        ":data:jacocoTestDebugUnitTestReport",
        ":presentation:jacocoTestDebugUnitTestReport"
    )
}