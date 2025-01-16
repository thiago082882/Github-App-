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
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.0.0")
    testImplementation("org.mockito:mockito-inline:5.0.0")
    testImplementation("com.google.truth:truth:1.1.5")
    testImplementation("androidx.room:room-testing:2.5.2")
    testImplementation("com.squareup.retrofit2:retrofit-mock:2.9.0")


    // Instrumentation Testing
    androidTestImplementation("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.8")
    androidTestImplementation("com.google.truth:truth:1.1.5")


    // Debug
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.8")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.8")
}

