import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.axat.newzo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.axat.newzo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }


        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())
        buildConfigField("String", "API_KEY", properties.getProperty("API_KEY"))

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {


    // Androidx
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.lifecycleRuntimeKtx)

    // Compose
    implementation(Dependencies.activityCompose)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeBom)
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.navigationCompose)


    // Navigation
    implementation(Dependencies.hiltNavigation)
    implementation(Dependencies.composeNavigation)

    // Network
    implementation(Dependencies.retrofit)
    implementation(Dependencies.converterMoshi)
    implementation(Dependencies.loggingInterceptor)
    implementation(Dependencies.moshi)
    ksp(Dependencies.moshiKotlinCodegen)
    implementation(Dependencies.coil) {
        because("An image loading library for Android backed by Kotlin Coroutines")
    }
    implementation(Dependencies.flower) {
        because("Flower simplifies networking and database caching on Android/Multiplatform")
    }



    // Dependency Injection
    implementation(Dependencies.hiltAndroid)
    ksp(Dependencies.hiltCompiler)
    ksp(Dependencies.hiltAndroidCompiler)


    // Testing
    implementation(Dependencies.junitExt)
    implementation(Dependencies.espressoCore)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeUiTestJunit4)
    implementation(Dependencies.composeUiTooling)
    implementation(Dependencies.composeUiTestManifest)

}