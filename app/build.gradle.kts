import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.daggerHiltAndroid)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.realm)
    id("kotlin-kapt")
}

fun File.loadGradleProperties(fileName: String): Properties {
    val properties = Properties()
    val signingProperties = File(this, fileName)

    if (signingProperties.isFile) {
        properties.load(signingProperties.inputStream())
    }
    return properties
}

val keystoreProperties = rootDir.loadGradleProperties("signing.properties")
val appKeyProperties = rootDir.loadGradleProperties("appkey.properties")

android {
    namespace = "vn.hoanguyen.weatherforecast"
    compileSdk = 34

    defaultConfig {
        applicationId = "vn.hoanguyen.weatherforecast"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            // Remember to edit signing.properties to have the correct info for release build.
            storeFile = file("../config/release.keystore")
            storePassword = keystoreProperties.getProperty("KEYSTORE_PASSWORD") as String
            keyPassword = keystoreProperties.getProperty("KEY_PASSWORD") as String
            keyAlias = keystoreProperties.getProperty("KEY_ALIAS") as String
        }

        getByName("debug") {
            storeFile = file("../config/debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            buildConfigField(
                "String",
                "BASE_API_URL",
                "\"https://api.openweathermap.org/data/2.5/\""
            )
            // Use the API key from the properties file
            buildConfigField("String", "WEATHER_API_KEY", "\"${appKeyProperties["WEATHER_API_KEY"]}\"")
        }

        getByName("debug") {
            applicationIdSuffix = ".debug"
            isDebuggable = true
            buildConfigField(
                "String",
                "BASE_API_URL",
                "\"https://api.openweathermap.org/data/2.5/\""
            )
            // Use the API key from the properties file
            buildConfigField("String", "WEATHER_API_KEY", "\"${appKeyProperties["WEATHER_API_KEY"]}\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        allWarningsAsErrors = false
        freeCompilerArgs += listOf(
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
        )
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    flavorDimensions += "version"
    productFlavors {
        create("demo") {
            dimension = "version"
            applicationIdSuffix = ".demo"
            versionNameSuffix = "-demo"
        }
        create("prod") {
            dimension = "version"
            applicationIdSuffix = ".prod"
            versionNameSuffix = "-prod"
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
    implementation(libs.androidx.lifecycle.runtime.compose.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //UI
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.compose.material)

    // Hilt
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.android.compiler)

    //DB
    implementation(libs.realm.base)

    //Navigation
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.hilt.navigation.compose)

    //Network
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    debugImplementation(libs.chuckerDebug)
    releaseImplementation(libs.chuckerRelease)

    //Utils
    implementation(libs.timber)

}