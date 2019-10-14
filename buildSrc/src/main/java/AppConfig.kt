object AppConfig {
    const val id = "uk.acm64"
    const val versionName = "0.1"
    const val versionCode = 1

    // Last Fm specific properties
    const val lasfmBaseUrl = "https://ws.audioscrobbler.com/2.0/"
    const val lastfmApiKey = "f67f7b58fc991333e760f51a648f7508"
}

object AppPublish {
    const val group = "uk.acm64.kotlin"
    const val name = "core"
    const val version = "0.2"
}

object StoreDebug {
    const val storePath = "../signing/debug.keystore"
    const val storePassword = "android"
    const val keyAlias = "androiddebugkey"
    const val keyPassword = "android"
}