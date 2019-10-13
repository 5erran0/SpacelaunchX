package com.ginzo.spacelaunchx

object Libs {
  object kotlin {
    val kotlinVersion = "1.3.50"

    val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"

    object gradle {
      val core = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
      val allopen = "org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion"
    }
  }

  val coreKtx = "androidx.core:core-ktx:1.0.2"

  val constrainLayout = "androidx.constraintlayout:constraintlayout:1.1.3"

  object androidx {
    val appCompat = "androidx.appcompat:appcompat:1.0.2"
    val lifecycle = "androidx.lifecycle:lifecycle-common-java8:2.1.0"
    val recyclerview = "androidx.recyclerview:recyclerview:1.0.0"

    object test {
      private const val version = "1.2.0"

      const val core = "androidx.test:core:$version"
      const val rules = "androidx.test:rules:$version"
      const val runner = "androidx.test:runner:$version"

      object espresso {
        private const val version = "3.2.0"

        const val core = "androidx.test.espresso:espresso-core:$version"
        const val contrib = "androidx.test.espresso:espresso-contrib:$version"
        const val intents = "androidx.test.espresso:espresso-intents:$version"
      }
    }
  }

  object google {
    val material = "com.google.android.material:material:1.0.0"
  }

  object dagger {
    private const val version = "2.24"

    val core = "com.google.dagger:dagger:$version"
    val compiler = "com.google.dagger:dagger-compiler:$version"
  }

  object retrofit {
    private const val retrofitVersion = "2.6.1"

    val core = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    val rxAdapter = "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"
    val moshiConverter = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
  }

  object moshi {
    private const val version = "1.8.0"

    const val core = "com.squareup.moshi:moshi:$version"
    const val codegen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
  }

  object rxjava2 {
    const val core = "io.reactivex.rxjava2:rxjava:2.2.12"
    const val android = "io.reactivex.rxjava2:rxandroid:2.0.2"
    const val relay = "com.jakewharton.rxrelay2:rxrelay:2.1.1"

    object bindings {
      private const val version = "3.0.0"

      const val core = "com.jakewharton.rxbinding3:rxbinding-core:$version"
      const val recyclerview = "com.jakewharton.rxbinding3:rxbinding-recyclerview:$version"
    }
  }

  object arrow {
    const val core = "io.arrow-kt:arrow-core:0.10.0"
  }

  const val threeten = "org.threeten:threetenbp:1.4.0"

  const val hamcrest = "org.hamcrest:hamcrest-library:1.3"

  const val junit = "junit:junit:4.12"

  object glide {
    private const val version = "4.10.0"

    val core = "com.github.bumptech.glide:glide:$version"
    val compiler = "com.github.bumptech.glide:compiler:$version"
  }

  object mockito {

    const val version = "2.23.0"

    const val core = "org.mockito:mockito-core:$version"
    const val kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0"
    const val android = "org.mockito:mockito-android:$version"
  }
}

object Conf {
  const val compileSdkVersion = 28
  const val targetSdkVersion = 28
  const val minSdkVersion = 21
}