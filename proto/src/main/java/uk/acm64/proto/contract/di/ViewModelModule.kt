package uk.acm64.proto.contract.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import uk.acm64.core.di.viewmodel.ViewModelFactory
import uk.acm64.core.di.viewmodel.ViewModelKey
import uk.acm64.proto.ProtoViewModel

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ProtoViewModel::class)
    abstract fun bindsProtoViewModel(mortgageViewModel: ProtoViewModel): ViewModel


}
