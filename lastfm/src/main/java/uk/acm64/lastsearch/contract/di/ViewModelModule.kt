package uk.acm64.lastsearch.contract.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import uk.acm64.core.di.viewmodel.ViewModelFactory
import uk.acm64.core.di.viewmodel.ViewModelKey
import uk.acm64.lastsearch.feature.artist.presentation.artistinfo.ArtistInfoViewModel
import uk.acm64.lastsearch.feature.artist.presentation.artists.ArtistsViewModel

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ArtistsViewModel::class)
    abstract fun bindsArtistsViewModel(artistsViewModel: ArtistsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArtistInfoViewModel::class)
    abstract fun bindsArtistInfoViewModel(artistInfoViewModel: ArtistInfoViewModel): ViewModel


}
