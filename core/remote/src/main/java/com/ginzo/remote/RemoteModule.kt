package com.ginzo.remote

import com.ginzo.remote.moshi.adapters.LocalDateTimeAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
abstract class RemoteModule {
  @Module
  companion object {
    @Provides
    @JvmStatic
    @Singleton
    fun okHttpClientProvider(): OkHttpClient {
      return OkHttpClient().newBuilder().build()
    }

    @Provides
    @JvmStatic
    @Singleton
    fun moshiProvider(): Moshi {
      return Moshi.Builder()
        .add(LocalDateTimeAdapter())
        .build()
    }

    @Provides
    @JvmStatic
    @Singleton
    fun retrofitProvider(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
      return Retrofit.Builder()
        .baseUrl("https://api.spacexdata.com/v3/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .client(okHttpClient)
        .build()
    }
  }

}