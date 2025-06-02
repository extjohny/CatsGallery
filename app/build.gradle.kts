plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.catsgallery"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.catsgallery"
        minSdk = 24
        targetSdk = 35
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
}

dependencies {

    // RxJava
    implementation(libs.rxandroid)
    implementation(libs.rxjava)

    // Glide
    implementation(libs.glide)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.adapter.rxjava3)

    // Gson
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}