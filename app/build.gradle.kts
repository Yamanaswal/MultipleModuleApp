plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    kotlin("kapt")
}

configure<ExtraPropertiesExtension> {
    set("useCore", true)
    set("useKtxCore", true)
    set("useTesting", true)
    set("useSupportLibrary", false)
    set("useRetrofit", true)
    set("useScalar", false)
    set("useGson", false)
    set("useGlide", false)
    set("useCoil", false)
    set("useRxJava", false)
    set("useTimber", false)
    set("useKtxUi", false)
    set("useKtxNavigation", false)
    set("useKtxWorkManager", false)
    set("useKtxRoom", false)
    set("useHilt", true)
    set("useCoroutines", false)
    set("usePlayCore", false)
}

apply(from = "../common-dependencies.gradle")

android {
    namespace = "com.yaman.multiplemoduleapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.yaman.multiplemoduleapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures{
        dataBinding = true
    }
}

dependencies {
    implementation(project(mapOf("path" to ":pdf_viewer")))
    implementation(project(mapOf("path" to ":runtime_permissions")))
    implementation(project(mapOf("path" to ":recycler_view")))
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}