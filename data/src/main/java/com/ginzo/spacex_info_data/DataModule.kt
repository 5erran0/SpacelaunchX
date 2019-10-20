package com.ginzo.spacex_info_data

import com.ginzo.spacex_info_data.company.api.CompanyRestApi
import com.ginzo.spacex_info_data.company.repositories.CompanyDataRepository
import com.ginzo.spacex_info_data.launches.api.LaunchesRestApi
import com.ginzo.spacex_info_data.launches.repositories.LaunchesDataRepository
import com.ginzo.spacex_info_domain.repository.CompanyRepository
import com.ginzo.spacex_info_domain.repository.LaunchesRepository
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
    internal fun providesCompanyRestApi(retrofit: Retrofit): CompanyRestApi {
      return retrofit.create(CompanyRestApi::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
    internal fun providesLaunchesRestApi(retrofit: Retrofit): LaunchesRestApi {
      return retrofit.create(LaunchesRestApi::class.java)
    }
  }

  @Binds
  abstract fun providesCompanyDataRepository(repository: CompanyDataRepository): CompanyRepository

  @Binds
  abstract fun providesLaunchesDataRepository(repository: LaunchesDataRepository): LaunchesRepository
}