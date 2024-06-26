plugins {
  alias(libs.plugins.androidApplication)
  alias(libs.plugins.jetbrainsKotlinAndroid)
  alias(libs.plugins.jetbrainsKotlinKapt)
  alias(libs.plugins.daggerHiltAndroid)
}

android {
  namespace = "com.sba.rickandmortytutorial"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.sba.rickandmortytutorial"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = true
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
    dataBinding = true
    buildConfig = true
  }
}

dependencies {
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.androidx.activity)
  implementation(libs.androidx.constraintlayout)

  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)

  //my additions
  implementation(libs.kotlin.reflect)
  implementation(libs.lifecycle.extensions)
  implementation(libs.lifecycle.viewmodel.ktx)
  implementation(libs.lottie)
  implementation(libs.hilt.android)
  implementation(libs.glide)
  implementation(libs.bundles.retrofit.netowrk)

  kapt(libs.hilt.compiler)
  kaptAndroidTest(libs.hilt.compiler)
  androidTestImplementation(libs.hilt.testing)
}