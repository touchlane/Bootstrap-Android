plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    google()
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin") }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.70")
    implementation("com.android.tools.build:gradle:3.5.3")
}