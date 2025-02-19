plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.navigationSafeargs)
    alias(libs.plugins.ksp)
    id("kotlin-parcelize")
}

android {
    namespace = "br.thiago.githubapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "br.thiago.githubapp"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {

        viewBinding = true
    }

}
dependencies {
    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // UI
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Splashscreen
    implementation(libs.androidx.core.splashscreen)

    // Lifecycle
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Pagination
    implementation(libs.androidx.paging.runtime.ktx)

    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Local repository
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Remote repository
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.gson)

    // Dependency Injection
    implementation(libs.koin.android)

    // Image Loading
    implementation(libs.coil)

    // Unit Testing
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.truth)
    testImplementation(libs.androidx.room.testing)
    testImplementation(libs.retrofit.mock)


    // Instrumentation Testing
    androidTestImplementation(libs.androidx.core.testing)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit.v115)
    androidTestImplementation(libs.androidx.espresso.core.v351)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.truth)


    // Debug
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

