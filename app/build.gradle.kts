plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
//    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    namespace = "com.app.dummyapi"

    defaultConfig {
        applicationId = "com.app.dummyapi"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        // We use a bundled debug keystore, to allow debug builds from CI to be upgradable
//        named("debug") {
//            storeFile = rootProject.file("debug.keystore")
//            storePassword = "android"
//            keyAlias = "androiddebugkey"
//            keyPassword = "android"
//        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        getByName("release") {
            isMinifyEnabled = true
//            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = false
        aidl = false
        renderScript = false
        resValues = false
        shaders = false
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    packaging {
        resources {
            // The Rome library JARs embed some internal utils libraries in nested JARs.
            // We don't need them so we exclude them in the final package.
            excludes += "/*.jar"

            // Multiple dependency bring these files in. Exclude them to enable
            // our test APK to build (has no effect on our AARs)
            excludes += "/META-INF/AL2.0"
            excludes += "/META-INF/LGPL2.1"
            excludes += "/META-INF/gradle/incremental.annotation.processors"
            excludes += "/META-INF/licenses/**"
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    compilerOptions {
        freeCompilerArgs.add("-Xjvm-default=all")
    }
}

dependencies {

    testImplementation("junit:junit:4.12")
    // Compose
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // kotlin
    implementation(libs.kotlin.stdlib)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.kotlinx.coroutines.test)

    // Androidx
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.window)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.palette)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.lifecycle.runtime)

    // Compose Ui
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.materialWindow)
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)

    // Compose ViewModel
    implementation(libs.androidx.lifecycle.viewModelCompose)

    // Compose Navigation
    implementation(libs.androidx.navigation.compose)

    // Compose Foundation
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)

    // Compose Animation
    implementation(libs.androidx.compose.animation)

    // Accompanist
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)
    implementation(libs.accompanist.flowlayout)
    implementation(libs.accompanist.swiperefresh)
    implementation(libs.accompanist.permissions)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.adaptive)

    // Compose Runtime
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.compose.runtime.livedata)

    // DataStore Preferences
    implementation(libs.androidx.datastore.preferences)

    // Retrofit
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.retrofit.moshi)
    implementation(libs.squareup.retrofit.gson)

    // Gson
    implementation(libs.gson)

    // Moshi
    implementation(libs.squareup.moshi)
    implementation(libs.squareup.moshi.kotlin)

    // Okhttp
    implementation(libs.okhttp3)
    implementation(libs.okhttp.logging)

//    implementation(libs.rometools.rome)
//    implementation(libs.rometools.modules)
//
//    implementation(libs.androidx.room.runtime)
//    implementation(libs.androidx.room.ktx)

    implementation(libs.willowtreeapps.assertk)
    implementation(libs.test.mockk)

//    ksp(libs.androidx.room.compiler)
    coreLibraryDesugaring(libs.core.jdk.desugaring)
}