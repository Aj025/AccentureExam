object Apps {
    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.3"
    const val applicationId = "simple.program.accentureexam"
    const val minSdkVersion = 21
    const val targetSdkVersion = 30
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
    const val kotlin = "1.5.10"
    const val androidx_core_version = "1.6.0"
    const val appcompat_version = "1.3.0"

    const val constraint_layout_version = "2.0.4"
    const val material_version = "1.4.0"

    const val junit_version = "4.13"
    const val androidx_test_version = "1.1.3"
    const val espresso_core_version = "3.4.0"
    const val arch_testing_version = "2.1.0"
    const val fragment_testing_version = "1.2.5"
    const val coroutine_test_version = "1.2.1"
    const val google_truth_version = "1.0.1"

    const val fragmentKtx_version = "1.2.5"
    const val viewModelKtx_version = "2.3.0-alpha07"

    const val coroutines_version = "1.3.7"

    const val hilt_version = "2.35"

    const val glide_version = "4.11.0"

    const val nav_version = "2.3.4"


    const val okhttp_version = "4.9.0"
    const val moshi_version = "1.10.0"
    const val retrofit_version = "2.9.0"
    const val okhttp_logging_iterceptor_version = "3.12.1"
    const val retrofit_moshi_converter_version = "2.9.0"

    const val room_version = "2.3.0"

    const val timber_version = "4.7.1"
    const val threetenabp_version = "1.2.4"
}

object Dependencies {

    const val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val androidxCore = "androidx.core:core-ktx:${Versions.androidx_core_version}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat_version}"
    const val androidMaterial = "com.google.android.material:material:${Versions.material_version}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout_version}"

    // Navigation components
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"

    // Glide for image caching
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide_version}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide_version}"

    // Logging
    const val timber = "com.jakewharton.timber:timber:${Versions.timber_version}"

    // View model
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx_version}"
    const val viewmodelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelKtx_version}"
    const val liveDataKtx =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.viewModelKtx_version}"

    // Coroutines
    const val coroutineCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"

    // Room
    const val room_compiler = "androidx.room:room-compiler:${Versions.room_version}"
    const val room_runtime = "androidx.room:room-runtime:${Versions.room_version}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room_version}"

    // Hilt
    const val dagger_hilt = "com.google.dagger:hilt-android:${Versions.hilt_version}"
    const val dagger_hilt_compiler = "com.google.dagger:hilt-compiler:${Versions.hilt_version}"

    // Testing Library
    const val junit = "junit:junit:${Versions.junit_version}"
    const val junitAndroid = "androidx.test.ext:junit:${Versions.androidx_test_version}"
    const val espressoCore =
        "androidx.test.espresso:espresso-core:${Versions.espresso_core_version}"
    const val coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutine_test_version}"
    const val google_truth = "com.google.truth:truth:${Versions.google_truth_version}"

    // Retrofit
    const val okhttp = "com.squareup.okhttp3:okhttp"
    const val okhttpBom = "com.squareup.okhttp3:okhttp-bom:${Versions.okhttp_version}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"
}

