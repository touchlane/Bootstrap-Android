plugins {
    kotlin("jvm")
}

kotlin {

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