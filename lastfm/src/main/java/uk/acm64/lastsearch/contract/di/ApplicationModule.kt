package uk.acm64.lastsearch.contract.di

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import uk.acm64.lastsearch.BuildConfig
import uk.acm64.lastsearch.feature.artist.domain.repository.LastFmRepository
import uk.acm64.lastsearch.feature.artist.data.repository.LastFmRepositoryImpl
import uk.acm64.lastsearch.feature.artist.data.LastFmRetrofitService
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApplicationModule() {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.LASTFM_BASE_URL)
            .client(createClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesLastFmRetrofitService(retrofit: Retrofit): LastFmRetrofitService =
        retrofit.create(LastFmRetrofitService::class.java)

    @Provides
    @Singleton
    fun providesLastFmRepository(lastFmRetrofitService: LastFmRetrofitService): LastFmRepository {
        return LastFmRepositoryImpl(
            lastFmRetrofitService
        )
    }

    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url()
            .newBuilder()
            .addEncodedQueryParameter("api_key", BuildConfig.LASTFM_API_KEY)
            .addEncodedQueryParameter("format", "json")
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()
        chain.proceed(newRequest)
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun createClient(): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(authInterceptor)
        retryOnConnectionFailure(true)
        connectTimeout(15, TimeUnit.SECONDS)
        readTimeout(15, TimeUnit.SECONDS)
        writeTimeout(15, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            addInterceptor(loggingInterceptor)
        }
    }.build()


}
