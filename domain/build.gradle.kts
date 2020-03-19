plugins {
    kotlin("jvm")
    id("org.jlleitschuh.gradle.ktlint") version Versions.Plugins.Ktlint
}

ktlint {
    disabledRules.set(Ktlint.disabledRules)
}

dependencies {
    implementation(Deps.Libs.KotlinStdLib)
    testImplementation(Deps.Libs.Test.JUnit)
}

repositories {
    google()
    jcenter()
    mavenCentral()
}
