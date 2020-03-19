plugins {
    kotlin("jvm")
    jacoco
}

jacoco {
    toolVersion = Versions.Libs.Test.JacocoTools
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

tasks.jacocoTestReport {

    dependsOn(tasks["test"])

    reports {
        xml.isEnabled = false
        csv.isEnabled = false
        html.isEnabled = true
    }
}