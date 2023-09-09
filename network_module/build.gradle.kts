plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

configure<ExtraPropertiesExtension> {
    set("useCore", true)
    set("useKtxCore", true)
    set("useTesting", true)
    set("useSupportLibrary", false)
    set("useRetrofit", true)
    set("useScalar", true)
    set("useGson", true)
    set("useGlide", false)
    set("useCoil", false)
    set("useRxJava", false)
    set("useTimber", false)
    set("useKtxUi", false)
    set("useKtxNavigation", false)
    set("useKtxWorkManager", false)
    set("useKtxRoom", false)
    set("useHilt", false)
    set("useCoroutines", false)
}

apply(from = "../common-dependencies.gradle")


android {
    namespace = "com.yaman.network_module"
    compileSdk = 33

    defaultConfig {
        minSdk = 23

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.Yamanaswal"
            artifactId = "network-module"
            version = "1.0.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}