package uk.acm64.proto.contract.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import uk.acm64.proto.AndroidApplication
import uk.acm64.proto.ProtoActivity
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
    fun inject(protoActivity: ProtoActivity)

}
