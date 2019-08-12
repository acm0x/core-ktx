package uk.acm64.proto.contract.di

import dagger.Module

@Module
object ApplicationModule {
//    @Provides
//    @Reusable
//    fun provideApplicationContext(): Context = application

//    @Provides @Singleton fun provideRetrofit(): Retrofit {
//        return Retrofit.Builder()
//                .baseUrl("https://raw.githubusercontent.com/android10/Sample-Data/master/Android-CleanArchitecture-Kotlin/")
//                .client(createClient())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//    }
//
//    private fun createClient(): OkHttpClient {
//        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
//        if (BuildConfig.DEBUG) {
//            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
//            okHttpClientBuilder.addInterceptor(loggingInterceptor)
//        }
//        return okHttpClientBuilder.build()
//    }
//
//    @Provides
//    @Reusable
//    @JvmStatic
//    fun provideAppDatabase(context: Context): AppDatabase = AppDatabase.create(context)
//
//    @Provides
//    @Reusable
//    @JvmStatic
//    fun provideMortgageRepository(appDatabase: AppDatabase): MortgageRepository = DbMortgageRepository(appDatabase)
}
