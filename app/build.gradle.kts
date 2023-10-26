import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "kangparks.android.vostom"
    compileSdk = 33

    defaultConfig {
        applicationId = "kangparks.android.vostom"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "kakao_api_key", getApiKey("kakao_api_key"))

        manifestPlaceholders["NATIVE_APP_KEY"] = getApiKey("NATIVE_APP_KEY") as String
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "kakao_api_key", getApiKey("kakao_api_key"))
        }
        getByName("debug"){
            buildConfigField("String", "kakao_api_key", getApiKey("kakao_api_key"))
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
        viewBinding = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

fun getApiKey(propertyKey :String):String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // Jetpack Compose dependencies
    val compose_ui_version = "1.7.2"
    implementation("androidx.activity:activity-compose:$compose_ui_version")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3-window-size-class")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.2")

    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // accompanist - status bar
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")

    // android Navigation dependencies
    val nav_version = "2.5.3"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version") // Kotlin
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version") // Kotlin
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version") // Feature module Support
    implementation("androidx.navigation:navigation-compose:$nav_version") // Jetpack Compose Integration

    // accompanist - navigation
    implementation("com.google.accompanist:accompanist-navigation-material:0.30.1")

    // Kakao Login
    implementation("com.kakao.sdk:v2-user:2.4.0")

    // Splash Screen
    implementation("androidx.core:core-splashscreen:1.0.1")

    // exo player
    val exoPlayerVersion = "2.19.1"
    implementation("com.google.android.exoplayer:exoplayer:$exoPlayerVersion")
    implementation("com.google.android.exoplayer:exoplayer-ui:$exoPlayerVersion")
    implementation("com.google.android.exoplayer:exoplayer-dash:$exoPlayerVersion")
    implementation("com.google.android.exoplayer:exoplayer-ui:$exoPlayerVersion")

    // lottie
    val lottieVersion = "6.1.0"
    implementation("com.airbnb.android:lottie:$lottieVersion")
    implementation("com.airbnb.android:lottie-compose:$lottieVersion")

    // coil
    implementation("io.coil-kt:coil-compose:2.4.0")

    // Jsoup
    implementation("org.jsoup:jsoup:1.13.1")

    // Youtube Player
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.0")

    // Youtube Extractor
    implementation("com.github.HaarigerHarald:android-youtubeExtractor:v1.7.0")
//    implementation("com.github.HaarigerHarald:android-youtubeExtractor:master-SNAPSHOT")

    //json parser
    implementation ("org.json:json:20210307")

    // testing dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}