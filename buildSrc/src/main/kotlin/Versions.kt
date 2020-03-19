object Versions {

    const val Kotlin = "1.3.70"

    object Plugins {
        const val AndroidBuildTools = "3.5.3"
        const val JacocoAndroid = "0.2"
        const val Ktlint = "9.2.1"
    }

    object Libs {
        const val AppCompat = "1.1.0"
        const val CoreKtx = "1.2.0"
        const val ConstraintLayout = "1.1.3"
        const val ViewModelKtx = "2.2.0"
        const val RecyclerView = "1.1.0"

        object Test {
            const val JUnit = "4.13"
            const val MockitoKotlin = "2.2.0"
            const val JacocoTools = "0.8.5"
        }

        object AndroidTest {
            const val JUnitExt = "1.1.0"
            const val EspressoCore = "3.2.0"
        }
    }
}