plugins {
    kotlin("jvm")
    jacoco
    id("org.jlleitschuh.gradle.ktlint") version Versions.Plugins.Ktlint
}

jacoco {
    toolVersion = Versions.Libs.Test.JacocoTools
}

ktlint {
    disabledRules.set(Ktlint.disabledRules)
}

dependencies {
    implementation(Deps.Libs.KotlinStdLib)
    implementation(Deps.Libs.RxJava)
    implementation(Deps.Libs.Retrofit)
    implementation(Deps.Libs.RetrofitGsonConverter)
    implementation(Deps.Libs.RetrofitRxJavaAdapter)
    testImplementation(Deps.Libs.Test.JUnit)
    testImplementation(Deps.Libs.Test.MockitoKotlin)
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
