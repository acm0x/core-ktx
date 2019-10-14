package uk.acm64.lastsearch.contract.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import uk.acm64.lastsearch.AndroidApplication
import uk.acm64.lastsearch.feature.artist.presentation.artistinfo.ArtistInfoFragment
import uk.acm64.lastsearch.feature.artist.presentation.artists.ArtistsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): ApplicationComponent
    }

    fun inject(androidApplication: AndroidApplication)
    fun inject(artistsFragment: ArtistsFragment)
    fun inject(artistInfoFragment: ArtistInfoFragment)

}
