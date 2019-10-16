package com.ginzo.spacex_info_data

import com.ginzo.spacex_info_data.api.SpaceXInfoRestApi
import com.ginzo.spacex_info_data.repositories.SpaceXInfoDataRepository
import com.ginzo.spacex_info_domain.repository.SpaceXInfoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
abstract class DataModule {

  @Module
  companion object {
    @Provides
    @Singleton
    @JvmStatic
    internal fun providesSpaceXInfoRestApi(retrofit: Retrofit): SpaceXInfoRestApi {
      return retrofit.create(SpaceXInfoRestApi::class.java)
    }
  }

  @Binds
  abstract fun providesSpaceXInfoDataRepository(repository: SpaceXInfoDataRepository): SpaceXInfoRepository
}