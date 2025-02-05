plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.bionickhand.vknewsclient"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bionickhand.vknewsclient"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
//        addManifestPlaceholders(
//            mapOf(
//                "VKIDClientID" to "52053645", // ID вашего приложения (app_id).
//                "VKIDClientSecret" to "ux9XqnuolLs0IS324WDA", // Ваш защищенный ключ (client_secret).
//                "VKIDRedirectHost" to "vk.com", // Обычно используется vk.com.
//                "VKIDRedirectScheme" to "vk52053645", // Обычно используется vk{ID приложения}.
//            )
//        )
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.compose.livedata)
    implementation(libs.compose.navigation)
    implementation(libs.compose.viewModel)
    implementation(libs.gson)
    implementation(libs.coil)
    implementation(libs.vk.core)
    implementation(libs.vk.api)
//    implementation(libs.vk.id)
    implementation(libs.retrofit2)
    implementation(libs.okhttp3)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}