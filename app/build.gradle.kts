plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-android")
    id ("kotlin-kapt")
}

android {
    namespace = Versions.namespace
    compileSdk = Versions.complerSdk

    defaultConfig {
        applicationId = Versions.applicationId
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = Versions.testInstrumentationRunner
    }

    buildTypes {
        getByName("release"){
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        viewBinding = true
    }
}

dependencies {

    // Kotlin
    implementation(KotlinLibraries.coroutineCore)
    implementation(KotlinLibraries.coroutineAndroid)

    // Android
    implementation(AndroidLibraries.core)
    implementation(AndroidLibraries.appCompat)
    implementation(AndroidLibraries.material)
    implementation(AndroidLibraries.constrainLayout)
    implementation(AndroidLibraries.recyclerView)
    implementation(AndroidLibraries.lifecycleLiveData)
    implementation(AndroidLibraries.lifecycleViewModel)
    implementation(AndroidLibraries.lifecycleRuneTime)
    implementation(AndroidLibraries.navigationFragment)
    implementation(AndroidLibraries.navigationUi)
    implementation(AndroidLibraries.splash)
    implementation(AndroidLibraries.activity)
    implementation(AndroidLibraries.fragment)
    implementation(AndroidLibraries.dataStore)
    implementation(AndroidLibraries.roomRuntime)
    implementation(AndroidLibraries.roomKtx)
    implementation(AndroidLibraries.navFragment)
    implementation(AndroidLibraries.navUi)

    // Libraries
    implementation(Libraries.koinAndroid)
    implementation(Libraries.lottie)
    implementation(Libraries.dotViewPager)
    implementation(Libraries.glide)
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitGson)
    implementation(Libraries.gson)
    implementation(Libraries.logging)
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Kapt
    kapt(Kapt.roomCompiler)
    kapt(Kapt.glideCompiler)
    kapt(Kapt.lifecycleCompiler)

    // AnnotationProcessor
    annotationProcessor(AnnotationProcessor.roomCompiler)

    // TestLibraries
    testImplementation(TestLibraries.junit)
    androidTestImplementation(TestLibraries.extJunit)
    androidTestImplementation(TestLibraries.espresso)
}