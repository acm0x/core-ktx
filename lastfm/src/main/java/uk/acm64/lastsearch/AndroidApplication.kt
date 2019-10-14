package uk.acm64.lastsearch

import android.app.Application
import uk.acm64.lastsearch.contract.di.ApplicationComponent
import uk.acm64.lastsearch.contract.di.DaggerApplicationComponent

class AndroidApplication : Application() {
    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
                .builder()
                .applicationContext(applicationContext)
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        injectMembers()
    }

    private fun injectMembers() = appComponent.inject(this)

}