
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.chaquo.python")
}

android {
    namespace = "com.vidora.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.vidora.app"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        ndk {
            abiFilters += listOf("arm64-v8a", "x86_64")
        }

        python {
            version = "3.11"
        }
    }
}

chaquopy {
    defaultConfig {
        pip {
            install("yt-dlp")
        }
    }
}
