plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    compileSdk = 34
    namespace = "com.handy.module.network"

    defaultConfig {
        minSdk = 21

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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    implementation(project(":module-core"))
    implementation(project(":module-utils"))
    /*
    implementation "com.squareup.okhttp3:okhttp:${versions.okhttp3_version}"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:${versions.kotlinx_coroutines_version}"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:${versions.kotlinx_coroutines_version}"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:${versions.kotlinx_coroutines_version}"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:${versions.lifecycle_version}"

    implementation "androidx.fragment:fragment-ktx:1.4.1"

    implementation rootProject.ext.dependencies["gson"]
     */

}